/*
 * 常用工具类
 * author:张渊
 * modifyTime:2016-7-22
 */
(function(){
 	var PublicUtil = window.PublicUtil = {
		/**
		 * 设置config
		 * @param Object defaults 默认配置项
		 * @param Object config 用户配置项
		 */
		 setConfig : function(defaults, config){
		 	$.extend(true, defaults, config);
		 },
		/**
		 * 设置路由
		 * @param Object routerConfig 路由配置
		 */
		 setRouter : function(routerConfig){
		 	var router = Router(routerConfig);
		 	router.init();	
		 },
		/** 
		 * 渲染视图
		 * @param Object $tempEl 模版节点
		 * @param Object $viewEl 视图节点
		 * @param Object res 数据源
		 * @param String mode 视图渲染模式
		 */
		 view : function($tempEl, $viewEl, res, mode){
		 	var tpl = $tempEl.html();
		 	var html = juicer(tpl, res);
		 	if(mode == "write"){
		 		$viewEl.html(html);
		 	}
		 	else if(mode == "append"){
		 		$viewEl.append(html);
		 	}
		 },
		/**
		 * 跳转
		 * @param String url 地址
		 * @param Object param 浏览器参数
		 */
		 location : function(url, param){
		 	var paramArr = [];
		 	for(var i in param){
		 		paramArr.push(param[i]);
		 	}
		 	var locUrl = url+"?"+paramArr.join("&");
		 	location.href = locUrl;
		 },
		 /*  
		  * 计时器
		  *  
		  * @param integer timerNmuber 时间
		  * @param function everyCallback 每次调用执行的回调函数
		  * @param function endCallback 计时器销毁时执行的回调函数
		  *   
		  */
		 timerFunc : function(timerNmuber, everyCallback, endCallback){
		 	var timer = null;
		 	var _timerNmuber = timerNmuber;	 	
		 	var play = function(){
		 		if(_timerNmuber <= 0){
		 			clearTimeout(timer);
		 			if(endCallback){
		 				endCallback();
		 			}
		 			return;
		 		}
		 		_timerNmuber--;
		 		if(everyCallback){
		 			everyCallback(_timerNmuber);
		 		}
		 		timer = setTimeout(play, 1000);
		 	};
		 	play();
		},
		//取地址栏参数,如果不存在返回空字符串
		getUrlParam : function(paramName){
			var svalue = window.location.search.match(new RegExp("[\?\&]" + paramName + "=([^\&]*)(\&?)", "i" ));
			return svalue ? svalue[1] : "";
		},
		//刷新当前页面
		refresh : function(){
		    window.location.reload();
		},
		//后退
		goBack : function(){
		    window.history.back();
		},
		/**
		 * 页面加载效果模拟
		 * @param Function loadCallback 页面正在加载时的回调函数
		 * @param Function succeedCallback 页面加载成功时的回调函数
		 */
		pageLoad : function(loadCallback, succeedCallback){
			if(loadCallback){
				loadCallback();
			}
			$(function(){
				if(succeedCallback){
					succeedCallback();
				}
			});
		},
		/**
		 * 使用POSTMESSAGE向目标窗体发送数据
		 * @param Object targetWin 目标窗口
		 * @param Object data 数据对象
		 */
		postMessage : function(targetWin, data){
			targetWin.postMessage(data, "*");
		},
		//得到使用POSTMESSAGE来自父窗口的数据
		getMessage : function(callback){
			window.addEventListener("message", function(e){
				if($.isFunction(callback)){
					callback(e);
				}
			}, false);
		},
		//记录行为
		recordBehavior : function($tag){
			var gpiSign = $tag.attr("data-gpi-sign");
			var gpiName = $tag.text();
			var clientName = "司机端";
			if(CLIENT_NAME == "customer"){
				clientName = "车主端";
			}
			_paq.push(['trackEvent', clientName, gpiName+"点击", gpiSign]);
		}
	};
})();
