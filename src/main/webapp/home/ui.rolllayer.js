/**
  * JS层滚动
  * author:张渊
  * modifyTime:2015-11-13
  *
  * scrollLayer : "#scroll_layer"                                  //滚动层
  * listLayer : "#img_layer"                                       //列表对象
  * everyScreenLength : 4                                          //每一屏显示多少个列表
  * direction : 1                                                  //方向  1：水平；2：垂直；
  * speed : 900                                                    //动画速度
  * prevBtn : "#prev_btn"                                          //上翻按钮
  * nextBtn : "#next_btn"                                          //下翻按钮
  * itemsSelectStyle : "current"                                   //列表选中样式
  * btnDisableStyle : "disable"                                    //按钮禁用样式
  * itemsClickCallback : function(){                               //给选中的items附加一个回调函数
		return false;
	}
  *
  * author:张渊
  * modifyTime:2016-8-9
  *
  */
(function(){
	var RollLayer = window.RollLayer = function(config){
		var $doc = $(document);
		var that = this;
		var public = RollLayer.prototype;
		var rollLayerConfig = config;
		var isAnimate = false;
		var scrollLeft = 0, scrollTop = 0;
		var maskObjWidth = 0, maskObjHeight = 0, scrollLayerWidth = 0, scrollLayerHeight = 0;
		var scrollStep = 0;
		var listLayerLength;
		var defaults = {
			scrollLayer : "#scrollLayer",
			listLayer : "#imgLayer",
			everyScreenLength : 4,
			direction : 1,
			speed : 900,
			prevBtn : "#prevBtn",                                       
			nextBtn : "#nextBtn",
			itemsSelectStyle : "current",
			btnDisableStyle : "disable",
			itemsClickCallback : function(itemsIndex){
				return false;
			}
		}; 
		var horScroll = function(){
			maskObjWidth = getMaskObjWidth();
			scrollLayerWidth = getScrollLayerWidth();
		};
		var verScroll = function(){
			maskObjHeight = getMaskObjHeight();
			scrollLayerHeight = getScrollLayerHeight();
		};
		//获取遮罩层宽度
		var getMaskObjWidth = function(){
			return $(defaults.listLayer).outerWidth(true) * defaults.everyScreenLength;
		};
		//获取遮罩层高度
		var getMaskObjHeight = function(){
			return $(defaults.listLayer).outerHeight(true) * defaults.everyScreenLength;
		};
		//获取滚动层宽度
		var getScrollLayerWidth = function(){
			return $(defaults.listLayer).outerWidth(true) * listLayerLength;
		};
		//获取滚动层高度
		var getScrollLayerHeight = function(){
			return $(defaults.listLayer).outerHeight(true) * listLayerLength;
		};
		/**
		 * 动态获取水平滚动步长
		 * @param string orientation 水平滚动方向
		 */
		var getHorScrollStep = function(orientation){
			var left = 0 - parseInt($(defaults.scrollLayer).css("margin-left"));
			scrollStep = maskObjWidth;
			if(orientation == "prev"){
				if(left % maskObjWidth != 0 && Math.floor(left / maskObjWidth) <= 1){
					scrollStep = left;
				}
			}
			else{
				if((scrollLayerWidth - left) % maskObjWidth != 0 && Math.floor((scrollLayerWidth - left) / maskObjWidth) <= 1){
					scrollStep = scrollLayerWidth - left - scrollStep;
				}
			}
		};
		/**
		 * 动态获取垂直滚动步长
		 * @param string orientation 垂直滚动方向
		 */
		var getVerScrollStep = function(defaults){
			var top = 0 - parseInt($(_options.scrollLayer).css("margin-top"));
			scrollStep = maskObjHeight;
			if(orientation == "prev"){
				if(top % maskObjHeight != 0 && Math.floor(top / maskObjHeight) <= 1){
					scrollStep = top;
				}
			}
			else{
				if((scrollLayerHeight - top) % maskObjHeight != 0 && Math.floor((scrollLayerHeight - top) / maskObjHeight) <= 1){
					scrollStep = scrollLayerHeight - top - scrollStep;
				}
			}
		};
		//检测水平向上滚动
		var checkHorPrevScroll = function(){
			if(!scrollLeft){
				return false;
			}
			return true;
		};
		//检测水平向下滚动
		var checkHorNextScroll = function(){
			if(scrollLeft <= 0 - (scrollLayerWidth - maskObjWidth)){
				return false;
			}
			return true;
		};
		//检测垂直向上滚动
		var checkVerPrevScroll = function(){
			if(!scrollTop){
				return false;
			}
			return true;
		};
		//检测垂直向下滚动
		var checkVerNextScroll = function(){
			if(scrollTop <= 0 - (scrollLayerHeight - maskObjHeight)){
				return false;
			}
			return true;
		};
		//上翻、下翻按钮样式
		var btnStyle = function(){
			var $prevBtn = $(defaults.prevBtn);
			var $nextBtn = $(defaults.nextBtn);
			if(checkHorPrevScroll() || checkVerPrevScroll()){
				$prevBtn.removeClass(defaults.btnDisableStyle);
			}
			else{
				$prevBtn.addClass(defaults.btnDisableStyle);
			}
			if(checkHorNextScroll() || checkVerNextScroll()){
				$nextBtn.removeClass(defaults.btnDisableStyle);
			}
			else{
				$nextBtn.addClass(defaults.btnDisableStyle);
			}
		};
		/**
		 * 水平滚动
		 * @param string orientation 水平滚动方向
		 */
		var horScrollPlay = function(orientation){
			if(isAnimate){
				return;
			}
			getHorScrollStep(orientation);
			if(orientation == "prev"){
				scrollLeft += scrollStep;
			}
			else{
				scrollLeft -= scrollStep;
			}
			isAnimate = true;
			$(defaults.scrollLayer).animate({"margin-left" : scrollLeft}, defaults.speed, function(){
				isAnimate = false;
				btnStyle();
			});
		};
		/**
		 * 垂直滚动
		 * @param string orientation 垂直滚动方向
		 */
		var verScrollPlay = function(orientation){
			if(isAnimate){
				return;
			}
			getVerScrollStep(orientation);
			if(orientation == "prev"){
				scrollTop += scrollStep;
			}
			else{
				scrollTop -= scrollStep;
			}
			isAnimate = true;
			$(defaults.scrollLayer).animate({"margin-top" : scrollTop}, defaults.speed, function(){
				isAnimate = false;
				btnStyle();
			});
		};
		var bindEvent = function(){
			$doc.off("click", defaults.prevBtn).on("click", defaults.prevBtn, function() {
				if(defaults.direction === 1){
					if(!checkHorPrevScroll()){
						return false;
					}
					horScrollPlay("prev");
				}
				else{
					if(!checkVerPrevScroll()){
						return false;
					}
					verScrollPlay("prev");
				}
			});
			$doc.off("click", defaults.nextBtn).on("click", defaults.nextBtn, function() {
				if(defaults.direction === 1){
					if(!checkHorNextScroll()){
						return false;
					}
					horScrollPlay("next");
				}
				else{
					if(!checkVerNextScroll()){
						return false;
					}
					verScrollPlay("next");
				}
			});
			$doc.off("click", defaults.listLayer).on("click", defaults.listLayer, function() {
				var itemsIndex = $(this).index();
				if(defaults.direction === 1){
					that.setHorSelectItems(itemsIndex);
				}
				else{
					that.setVerSelectItems(itemsIndex);
				}
				defaults.itemsClickCallback(itemsIndex);
			});
		};
		/**
		 * 设置水平滚动选中的items
		 * @param integer currentIndex 选中items的索引
		 */
		public.setHorSelectItems = function(currentIndex){
			var $listLayer = $(defaults.listLayer);
			$listLayer.eq(currentIndex).addClass(defaults.itemsSelectStyle);
			$listLayer.not($listLayer.eq(currentIndex)).removeClass(defaults.itemsSelectStyle);
			if(listLayerLength <= defaults.everyScreenLength){
				return false;
			}
			if(currentIndex <= Math.floor(defaults.everyScreenLength / 2)){
				scrollLeft = 0;
			}
			else if(currentIndex >= listLayerLength - defaults.everyScreenLength + Math.floor(defaults.everyScreenLength / 2)){
				scrollLeft = 0 - (listLayerLength - defaults.everyScreenLength) * $listLayer.outerWidth(true);
			}
			else{
				scrollLeft =  0 - (currentIndex - Math.floor(defaults.everyScreenLength / 2)) * $listLayer.outerWidth(true);
			}
			$(defaults.scrollLayer).stop().animate({"margin-left" : scrollLeft}, defaults.speed, function(){
				btnStyle();
			});
		};
		/**
		 * 设置垂直滚动选中的items
		 * @param integer currentIndex 选中items的索引
		 */
		public.setVerSelectItems = function(currentIndex){
			var $listLayer = $(defaults.listLayer);
			$listLayer.eq(currentIndex).addClass(defaults.itemsSelectStyle);
			$$listLayer.not($(defaults.listLayer).eq(currentIndex)).removeClass(defaults.itemsSelectStyle);
			if(listLayerLength <= defaults.everyScreenLength){
				return false;
			}
			if(currentIndex <= Math.floor(defaults.everyScreenLength / 2)){
				scrollTop = 0;
			}
			else if(currentIndex >= listLayerLength - defaults.everyScreenLength + Math.floor(defaults.everyScreenLength / 2) && currentIndex >= defaults.everyScreenLength){
				scrollTop = 0 - (listLayerLength - defaults.everyScreenLength) * $listLayer.outerHeight(true);
			}
			else{
				scrollTop =  0 - (currentIndex - Math.floor(defaults.everyScreenLength / 2)) * $listLayer.outerHeight(true);
			}
			$(defaults.scrollLayer).stop().animate({"margin-top" : scrollTop}, defaults.speed, function(){
				btnStyle();
			});
		};
		//初始化
		public.init = function(){
			$.extend(true, defaults, rollLayerConfig);
			listLayerLength = $(defaults.listLayer).length;
			if(defaults.direction === 1){
				horScroll();	
				this.setHorSelectItems(0);
			}
			else{
				verScroll();
				this.setVerSelectItems(0);
			}
			$(defaults.prevBtn).addClass(defaults.btnDisableStyle);
			bindEvent();
		};
	};
})();