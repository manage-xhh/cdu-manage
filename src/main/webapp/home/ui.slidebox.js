/**
  * JS幻灯片
  * author:张渊
  * modifyTime:2016-8-9
  *
  */
(function(){
	var SlideBox = window.SlideBox = function(config){
		var $doc = $(document);
		var public = SlideBox.prototype;
		var slideConfig = config;
		var defaults = {
			items : "#slideItem",
			title : null,
			nav : "#slideNav",
			prevBtn : null,                  
			nextBtn : null,                  
			navSelect : "active",
			slideEnabled : true,   
            slideInterval : 5000,   
            slideDelay : 300,
            slideSpeed : 500,    
			slideType : 0,        
            onShow : function(index){ 
				return true;
			}    
		}; 
		var index = 0;
		//创建一个幻灯片
		var createSlide = function(){
			var intervalId = null,
				 delayId = null;
			var start = function(){
				intervalId = setInterval(function(){
					next();
				}, defaults.slideInterval);
			};
			var clear = function(){
				clearInterval(intervalId);
				clearTimeout(delayId);
			};
			var delay = function(){
				if(defaults.slideEnabled){
					delayId = setTimeout(start, defaults.slideDelay);
				}
			};
		    if(defaults.slideEnabled){
				start();
			}
			//绑定事件
			$doc.on("mouseover", defaults.nav, function() {
				var $this = $(this);
				index = $this.index();
				play(index);
				clear();
			});
			$doc.on("mouseout", defaults.nav, delay);
			if(defaults.prevBtn != null){
				$doc.on("click", defaults.prevBtn, prev);
				$doc.on("mouseover", defaults.prevBtn, clear);
				$doc.on("mouseout", defaults.prevBtn, delay);
			}
			if(defaults.nextBtn != null){
				$doc.on("click", defaults.nextBtn, next);
				$doc.on("mouseover", defaults.nextBtn, clear);
				$doc.on("mouseout", defaults.nextBtn, delay);
			}
		};
		//向上切换
		var prev = function(){
			var itemLen = getItemLen();
			index--;
			if(index < 0){
				index = itemLen-1;
			}
			play(index);
		};
		//向下切换
		var next = function(){
			var itemLen = getItemLen();
			index++;
			if(index > itemLen-1){
				index = 0;
			}
			play(index);
		};
		var getItemLen = function(){
			return $(defaults.items).length;
		};
		/**
		 * 切换到指定帧
		 * @param integer index 当前索引
		 */
		var play = function(index){
			var $items = $(defaults.items);
			var $nav = $(defaults.nav);
			var $title = $(defaults.title);
			if(defaults.slideType){
				$items.not($items.eq(index)).fadeOut(defaults.slideSpeed);
				$items.eq(index).fadeIn(defaults.slideSpeed);
			}
			else{
				$items.hide();
				$items.eq(index).show();
			}
			$nav.removeClass(defaults.navSelect);
			$nav.eq(index).addClass(defaults.navSelect);
			if(defaults.title != null){
				$title.hide();
				$title.eq(index).show();
			}
			defaults.onShow(index);
		};
		//初始化
		public.init = function(){
			$.extend(true, defaults, slideConfig);
			createSlide();
		};
	};
})();