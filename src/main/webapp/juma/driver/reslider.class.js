(function(factory) {
	if (typeof define === 'function' && define.amd) {
		define(factory);
	} else if (typeof module === 'object' && module.exports) {
		module.exports = factory();
	} else {
		try { window.ReSlider = factory(); } catch(e) {}
	}
})(function() {

	var CLASS_ITEMS = "items";        // 轮播元素容器 className
	var CLASS_SUBS  = "subs";         // 下标元素容器 className
	var CLASS_PREV  = "prev";         // 向前导航元素 className
	var CLASS_NEXT  = "next";         // 向后导航元素 className
	var CLASS_CURRENT  = "current";   // 当前选中的元素、下标 className

	// 绑定 this
	// @param {Function} func
	// @param {Object} context
	// @return {Function}
	var bind = function(func, context) {
		try {
			return func.bind(context);
		} catch(e) {
			return function() {
				return func.apply(context, arguments);
			};
		}
	};

	// 迭代函数 如果 callback 返回 false 则退出循环
	// @param {ArrayLike} items
	// @param {Function(item, index, items)} callback
	// @param {Object|Undefined|Null} thisArg
	var loop = function(items, callback, thisArg) {
		for (var i = 0, l = items.length; i < l; i += 1) {
			if (callback.call(thisArg, items[i], i, items) === false) {
				break;
			}
		}
	};

	// 创建匹配 className 的正则表达式
	// @param {String} className
	// @return {RegExp}
	var buildClassRegExp = function(className) {
		return new RegExp("(?:^|\\s+)" + className + "(?:\\s+|$)", "g");
	};

	// 检查元素是否包含 className（可用空格指定多个）
	// 当且仅当每个 className 都有时才返回 true
	// @param {Element} element
	// @param {String} className
	// @return {Boolean}
	var hasClass = function(element, className) {
		var classNames = element.className, has = true;
		loop(className.split(/\s+/), function(item) {
			if (item !== "") {
				has = buildClassRegExp(item).test(classNames);
				// 如果 has === false 的话，则跳出循环
				return has;
			}
		});
		return has;
	};

	// 向元素添加 className（可用空格指定多个）
	// @param {Element} element
	// @param {String} className
	var addClass = function(element, className) {
		if (!hasClass(element, className)) {
			element.className = element.className + " " + className;
		}
	};

	// 移除指定的 className（可用空格指定多个）
	// @param {Element} element
	// @param {String} className
	var removeClass = function(element, className) {
		var classNames = element.className;
		loop(className.split(/\s+/), function(item) {
			if (item !== "") {
				classNames = classNames.replace(buildClassRegExp(item), " ");
			}
		});
		element.className = classNames;
	};

	// 跨浏览器绑定事件
	// @param {Element} element
	// @param {String} type
	// @param {Function} listener
	var bindEvent = function(element, type, listener) {
		if (element.addEventListener) {
			bindEvent = function(element, type, listener) {
				element.addEventListener(type, listener, false);
			};
		} else if (element.attachEvent) {
			bindEvent = function(element, type, listener) {
				element.attachEvent("on" + type, listener);
			};
		} else {
			bindEvent = function() {
				element["on" + type] = listener;
			};
		}
		return bindEvent(element, type, listener);
	};

	// 打印日志
	var log = function() {
		try {
			if (arguments[arguments.length-1] === true) {
				console.log.apply(null, [].slice.call(arguments, 0, -1));
			}
		} catch(e) {}
	};

	// 打印警告
	var warn = function() {
		try {
			if (arguments[arguments.length-1] === true) {
				console.warn.apply(null, [].slice.call(arguments, 0, -1));
			}
		} catch(e) {}
	};

	// 打印错误
	var error = function() {
		try {
			if (arguments[arguments.length-1] === true) {
				console.error.apply(null, [].slice.call(arguments, 0, -1));
			}
		} catch(e) {}
	};

	// 覆盖默认参数
	// @param {Object} defaults
	// @param {Object} config
	// @return {Object}
	var overwriteDefaults = function(defaults, config) {
		var name, hasOwn = {}.hasOwnProperty;
		for (name in config) {
			if (hasOwn.call(config, name)) {
				defaults[name] = config[name];
			}
		}
		return defaults;
	};

	var stopPropagation = function(event) {
			try {
				event.stopPropagation();
			} catch(e) {
				event.cancelBubble = true;
			}
	};

	// 为 slider 绑定导航按钮
	// @param {ReSlider} slider
	// @param {Element|Null} btn
	// @param {"prev"|"next"} type
	var bindNav = function(slider, btn, type) {
		bindEvent(btn, "mouseover", bind(slider.pause, slider));
		bindEvent(btn, "mouseout", bind(slider.resume, slider));
		bindEvent(btn, "click", function(event) {
			// 阻止事件冒泡
			stopPropagation(event || window.event);
			slider[type]();
		});
	};

	var animateDefault = function(event, waitForNext) {
		waitForNext();
	};
	var animateFade = function(event, waitForNext) {
		waitForNext();
	};
	var animateSlide = function(event, waitForNext) {
		waitForNext();
	};

	// 私有方法

	// 初始化
	// @param {ReSlider} that
	var init = function(that) {
		var debug = that.defaults.debug;
		var container = that.defaults.container;
		// 调试模式提示
		warn("调试模式已开启，请在生产环境中关闭调试模式！", debug);
		// 检查容器参数
		if (container && container.nodeType === 1) {
			that.items = [];
			that.subs  = [];
			that.prev  = null;
			that.next  = null;
			that.subContainer = null;
			// 获取 items, subs, prev, next, subContainer
			loop(container.childNodes, function(item) {
				if (item.nodeType === 1) {
					if (hasClass(item, CLASS_ITEMS)) {
						loop(item.childNodes, function(item) {
							if (item.nodeType === 1) { that.items.push(item); }
						});
					} else if (hasClass(item, CLASS_SUBS)) {
						that.subContainer = item;
					} else if (hasClass(item, CLASS_PREV)) {
						that.prev = item;
					} else if (hasClass(item, CLASS_NEXT)) {
						that.next = item;
					}
				}
			});
			// 检查动画参数
			initAnimate(that);
			// 鼠标悬停暂停切换初始化
			initPauseOnHover(that);
			// 初始化导航
			initNav(that);
			// 初始化下标
			initSubs(that);
		} // end if container && container.nodeType === 1
	};

	// 检查动画参数(animate)
	// @param {ReSlider} that
	var initAnimate = function(that) {
		var animate, option = that.defaults.animate;
		if (typeof option !== "function") {
			switch(option) {
				case "fade":
					animate = animateFade;
					break;
				case "slide":
					animate = animateSlide;
					break;
				default:
					error("未知动画类型(animate = " + option + ")，默认不使用动画。");
					animate = animateDefault;
			}
			that.defaults.animate = animate;
		}
	};

	// 检查是否在鼠标悬停时暂停自动切换
	// @param {ReSlider} that
	var initPauseOnHover = function(that) {
		if (that.defaults.pauseOnHover && that.items.length > 1) {
			bindEvent(that.defaults.container, "mouseover", bind(that.pause, that));
			bindEvent(that.defaults.container, "mouseout", bind(that.resume, that));
		}
	};

	// 初始化导航
	// @param {ReSlider} that
	var initNav = function(that) {
		var prev = that.prev, next = that.next;
		if (that.items.length > 1) {
			if (prev && prev.nodeType === 1) { bindNav(that, prev, "prev"); };
			if (next && next.nodeType === 1) { bindNav(that, next, "next"); };
		} else {
			if (prev && prev.nodeType === 1) { prev.style.display = "none" };
			if (next && next.nodeType === 1) { next.style.display = "none" };
		}
		// 解除对 DOM 节点的引用
		delete that.prev;
		delete that.next;
	};

	// 初始化下标
	// @param {ReSlider} that
	var initSubs = function(that) {
		var defaults = that.defaults;
		if (defaults.useSubs === false || that.items.length <= 1) {
			// 隐藏小标容器
			if (that.subContainer) {
				that.subContainer.style.display = "none";
				that.subContainer.style.visibility = "visible";
			}
			defaults.useSubs = false; // in case that.items.length <= 1
		} else if (!(defaults.useSubs === null && that.subContainer === null)) {
			// 处理 useSubs 参数
			if (defaults.useSubs === true || defaults.useSubs === null) {
				defaults.useSubs = "hover";
			} else if (defaults.useSubs !== "click" && defaults.useSubs !== "hover") {
				error(
					"参数错误 useSubs=>" + defaults.useSubs + ", 使用默认值 hover!",
					defaults.debug
				); // error
				defaults.useSubs = "hover";
			} // defaults.useSubs === null && ...
			// 初始化下标元素
			initSubItems(that);
			initSubEvents(that);
			that.subContainer.style.visibility = "visible";
		}
		delete that.subContainer;
	};

	// 初始化下标元素
	// @param {ReSlider} that
	var initSubItems = function(that) {
		var debug = that.defaults.debug;
		var subContainer = that.subContainer;
		var subs = that.subs, items = that.items, subCopy;
		// 如果没有 subContainer 则创建一个
		if (subContainer === null) {
			subContainer = document.createElement("ul");
			addClass(subContainer, CLASS_ITEMS);
			that.defaults.container.appendChild(subContainer);
		} // subContainer === null
		// 查找直接子元素
		loop(subContainer.childNodes, function(sub) {
			if (sub.nodeType === 1) { subs.push(sub); }
		});
		// 检查下标数量
		if (subs.length < items.length) {
			if (subs.length === 0) {
				subCopy = document.createElement("li");
				addClass(subCopy, CLASS_CURRENT);
				subContainer.appendChild(subCopy);
				subs.push(subCopy);
				warn("未自定义下标，使用默认下标!", debug);
			} else {
				subCopy = subs[subs.length - 1];
				error("下标数量不足，自动复制最后一个补充，请检查！", debug);
			}
			subCopy = subCopy.cloneNode(true);
			removeClass(subCopy, CLASS_CURRENT);
			while (subs.length < items.length) {
				subContainer.appendChild(subCopy);
				subs.push(subCopy);
				subCopy = subCopy.cloneNode(true);
			}
		} else if (subs.length > items.length) {
			// 从 DOM 中移除多余的下标
			loop(subs.slice(items.length), function(item) {
				subContainer.removeChild(item);
			});
			// 从 subs 中移除多余的下标
			subs.length = items.length;
			warn("下标数量过多，已删除多余部分！", debug);
		}
	};

	// 初始化下标事件
	// @param {ReSlider} that
	var initSubEvents = function(that) {
		loop(that.subs, function(item, index) {
			if (that.defaults.useSubs === "click") {
				// 点击暂停切换
				bindEvent(item, "click", (function(that, item, index) {
					return function(event) {
						stopPropagation(event || window.event);
						that.pause();
						if (that.currentIndex !== index) {
							that.play(index, "sub");
						}
					};
				})(that, item, index));
				// 添加 hover className
				bindEvent(item, "mouseover", (function(item) {
					return function() {
						addClass(item, "hover");
					};
				})(item));
			} else {
				// 阻止点击冒泡
				bindEvent(item, "click", function(event) {
					stopPropagation(event || window.event);
				});
				// 鼠标 hover 暂停
				bindEvent(item, "mouseover", (function(that, item, index) {
					return function() {
						addClass(item, "hover");
						that.pause();
						if (that.currentIndex !== index) {
							that.play(index, "sub");
						}
					};
				})(that, item, index));
			} // that.defaults.useSubs
			// 鼠标移出继续切换
			bindEvent(item, "mouseout", (function(that, item) {
				return function() {
					removeClass(item, "hover");
					that.resume();
				};
			})(that, item));
		});
	};

	// ReSlider 类
	var ReSlider = function(config) {
		this.defaults = {
			animate: "fade",
			autoPlay: true,
			container: null,
			debug: false,
			duration: 3000,
			pauseOnHover: true,
			useSubs: null
		};
		this.defaults     = overwriteDefaults(this.defaults, config);
		this.timer        = null;
		this.paused       = false;
		this.zIndex       = 1;
		this.currentIndex = 0;
		init(this);
		log(this, this.defaults.debug);
	};

	// 公有方法
	ReSlider.prototype = {
		constructor: ReSlider,
		// 设置自动播放
		autoPlay: function() {
			if (!this.defaults.autoPlay) {
				this.defaults.autoPlay = true;
				this.waitFor(this.currentIndex + 1);
			}
		},
		// 开始播放
		start: function() {
			this.waitFor(1);
			return this;
		},
		// 等待 index 指定的帧
		// @param {Number} index 指定帧对应的下标
		waitFor: function(index) {
			var length = this.items.length;
			if (length < 2) {
				error(this.items.length + "个元素，无轮播！", this.defaults.debug);
				return;
			}
			// 检查下标
			index = typeof index === "number" ? index : (this.currentIndex + 1);
			// 循环下标
			if (index >= length) {
				index = 0;
			} else if (index < 0) {
				index += length;
			}
			// 取消当前定时器
			if (this.timer !== null) {
				clearTimeout(this.timer);
			}
			// 设定定时器
			if (!this.paused && this.defaults.autoPlay) {
				this.timer = setTimeout(bind(function() {
					this.play(index);
				}, this), this.defaults.duration);
			}
		},
		// 等待下一帧
		waitForNext: function() {
			var lastIndex, lastItem;
			// 移除上一帧的 selected css类
			lastIndex = this.currentIndex - 1;
			if (lastIndex < 0) {
				lastIndex += this.items.length;
			}
			lastItem = this.items[lastIndex];
			removeClass(lastItem, CLASS_CURRENT);
			// 等待下一帧
			this.waitFor(this.currentIndex + 1);
		},
		// 播放指定帧
		// @param {Number} index 对应的下标
		// @param {String} eventType 事件类型
		play: function(index, eventType) {
			// 更新当前下标
			var items       = this.items;
			var subs        = this.subs;
			var lastIndex   = this.currentIndex;
			var lastSub     = subs[lastIndex];
			var currentSub  = subs[index];
			var currentItem = items[index];
			var event       = {};
			// 保存状态
			this.eventType = eventType;
			this.currentIndex = index;
			this.zIndex += 1;
			currentItem.style.zIndex = this.zIndex;
			// 为当前元素设置选中状态
			addClass(currentItem, CLASS_CURRENT);
			// 更新下标选中状态
			if (this.defaults.useSubs) {
				removeClass(lastSub, CLASS_CURRENT);
				addClass(currentSub, CLASS_CURRENT);
			}
			// 封装 event
			event.instance      = this;
			event.type          = eventType || "normal";
			event.subs          = subs;
			event.items         = items;
			event.lastIndex     = lastIndex;
			event.currentIndex  = index;
			event.currentItem   = currentItem;
			event.lastItem      = items[lastIndex];
			event.nextItem      = items[(index + 1) % items.length];
			event.CLASS_CURRENT = CLASS_CURRENT;
			// 执行动画效果，并在动画完成后等待下一帧
			this.defaults.animate(event, bind(this.waitForNext, this));
		},
		// 暂停播放
		pause: function() {
			this.paused = true;
			if (this.timer !== null) {
				clearTimeout(this.timer);
				this.timer = null;
			}
			log("暂停", this.currentIndex, this.defaults.debug);
		},
		// 继续播放
		resume: function() {
			if (this.paused) {
				this.paused = false;
				this.waitFor(this.currentIndex + 1);
				log("继续播放", this.currentIndex, this.defaults.debug);
			}
		},
		// 播放上一帧
		prev: function() {
			var prevIndex = this.currentIndex - 1;
			if (prevIndex < 0) {
				prevIndex += this.items.length;
			}
			this.play(prevIndex, "prev");
		},
		// 播放下一帧
		next: function() {
			var nextIndex = this.currentIndex + 1;
			if (nextIndex >= this.items.length) {
				nextIndex = 0;
			}
			this.play(nextIndex, "next");
		}
	};

	// 快捷方式
	ReSlider.slide = function(config) { return (new ReSlider(config)).start(); };
	// 公开 className 操作函数
	ReSlider.hasClass = hasClass;
	ReSlider.addClass = addClass;
	ReSlider.removeClass = removeClass;

	return ReSlider;
});
