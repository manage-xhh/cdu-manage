/*
 * 视图工具类
 * author:张渊
 * modifyTime:2016-7-22
 */
(function(){
 	var viewConfig = config.view;
 	//获取窗口尺寸
 	var getWinSize = function(){
 		var $win = $(window);
 		var $doc = $(document);
 		var winW = $win.width();
 		var winH = $win.height();
 		var docW = $doc.width();
 		var docH = $doc.height();
 		return {
 			winW : winW,
 			winH : winH,
 			docW : docW,
 			docH : docH
 		};		
 	};
 	var ViewUtil = window.ViewUtil = {		
		//遮罩层
		mask : function(show){	
			var $el = $(viewConfig.maskEl);	
			var winSize = getWinSize();
			var init = function(){				
				if(show){
					setHeight();
					$el.show();
				}
				else{
					$el.hide();
				}
			};
			var setHeight = function(){
				$el.css({
					width : winSize.docW,
					height : winSize.winH < winSize.docH ? winSize.docH : winSize.winH
				});
			};
			init();
		},
		//进度提示
		loading : function(config){
			var $el =$(viewConfig.loadingEl);
			var defaults = {
				loadText : "正在加载...",
				show : true
			};
			PublicUtil.setConfig(defaults, config);
			var loadText = defaults.loadText;
			var show = defaults.show;
			var init = function(){				
				if(show){
					setText(loadText);
					$el.show();
				}
				else{
					$el.hide();
				}
			};
			var setText =  function(text){
				$el.children("span").html(text);
			};
			init();
		},
		//提示信息
		tip : function(config){
			var $el =$(viewConfig.poptips.el);
			var winSize = getWinSize();
			var defaults = {
				content : "弹出的小气泡", 
				callback : function(){
					return true;
				}
			};
			PublicUtil.setConfig(defaults, config);
			var pos = viewConfig.poptips.pos;
			var showTime = viewConfig.poptips.showTime;
			var content = defaults.content;
			var callback = defaults.callback;
			$el.children("p").text(content);
			var left = winSize.docW/2 - $el.width()/2;
			var top = 10;
			if(pos == "center"){
				top = winSize.winH/2 - $el.height()/2;
			}
			else if(pos == "bottom"){
				top = winSize.winH - $el.height() - 10;
			}
			$el.css({
				left : left,
				top : top,
				visibility : "visible"
			});
			PublicUtil.timerFunc(showTime, null, function(){
				$el.css("visibility", "hidden");
				if(callback){
					callback();
				}
			});
		},
		//定义元素动画
		animate : function(config){
			var defaults = {			
				$showEl : $("#next"),
				speed : viewConfig.animate.speed, 
				translate : viewConfig.animate.translate,
				type : "slide-in-right",
				animateConfig : null, 
				callback : function(){
					return true;
				}
			};
			PublicUtil.setConfig(defaults, config);
			var $showEl = defaults.$showEl;
			var speed = defaults.speed;
			var translate = defaults.translate;
			var type = defaults.type;
			var callback = defaults.callback;
			var animateConfig = null;
			switch(type){
				case "slide-in-top" : 
				case "slide-in-right" : 
				case "slide-in-bottom" :
				case "slide-in-left" : animateConfig = {
					opacity: 1,
					translate3d:"0,0,0"
				};
				break;
				case "slide-out-top" : animateConfig = {
					opacity: 1,
					translate3d:"0,-100%,0"
				};
				break;
				case "slide-out-right" : animateConfig = {
					opacity: 0,
					translate3d:"100%,0,0"
				};
				break;		
				case "slide-out-bottom" : animateConfig = {
					opacity: 0,
					translate3d:"0,100%,0"
				};
				break;	
				case "slide-out-left" : animateConfig = {
					opacity: 0,
					translate3d:"-100%,0,0"
				};
				break;
				case "zoom-in" : animateConfig = {
					scale:"1,1"
				};
				break;
				case "zoom-out" : animateConfig = {
					scale:"0,0"
				};
				break; 
				case "user-define" : animateConfig = defaults.animateConfig;
				break;		
				default : break;			
			}
			$showEl.animate(animateConfig, speed, translate, function(){
				if(callback){
					callback();
				}
			});
		},
		//对话框
		dilog : function(config){
			var defaults = {			
				$el : $(viewConfig.dilog.el),
				speed : viewConfig.animate.speed, 
				translate : viewConfig.animate.translate,
				type : "slide-in-bottom",
				cancelCallback : function(){
					return true;
				},
				confirmCallback : function(){
					return true;
				}
			};
			PublicUtil.setConfig(defaults, config);
			var $el = defaults.$el;
			var cancelCallback = defaults.cancelCallback;
			var confirmCallback = defaults.confirmCallback;
			ViewUtil.mask(true);
			$el.show();
			//取消回调
			var cancelFunc = function(e){
				ViewUtil.mask(false);
				$el.hide();
				if(cancelCallback){
					cancelCallback();
				}
				e.stopPropagation();
			};
			//确定回调
			var confirmFunc = function(e){
				ViewUtil.mask(false);
				$el.hide();
				if(confirmCallback){
					confirmCallback();
				}
				e.stopPropagation();
			};
			//点击对话框取消按钮
			$el.children().find(viewConfig.dilog.cancelBtn).off().on("mousedown", function(e){
				cancelFunc(e);
			});
			//点击对话框确定按钮
			$el.children().find(viewConfig.dilog.confirmBtn).off().on("mousedown", function(e){
				confirmFunc(e);
			});
		}
	};
})();
