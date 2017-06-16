/**
  * 哈喽师傅WEB官网->首页
  * author:张渊
  * modifyTime:2016-8-9
  *
  */
require.config(config.require);
require(config.classList.index, function(){
 	var Index = function(){
 		var public = Index.prototype;
		var fxSpeed = Tween.Cubic.easeInOut(100, 300, 200, 120);
		//banner
		var slideBox = function(){
			var slide = new SlideBox({
				items : "#banner .image-item",
				nav : "#bannerNav i",
				navSelect : "active",   
				slideType : 1,       
				slideSpeed : fxSpeed,
				slideInterval: 2500
			});
			slide.init();
		};
		//数字动画
		var fxNum = function($wrap, number){
			var fx = new FxNum({
				$wrapper : $wrap,
				temp : {
					num : '<i class="num"></i>',
					sepa : '<i class="sepa"></i>',
					dot: '<i class="dot"></i>'
				},
				speed : fxSpeed,
				frameHeight : 37
			});
			fx.init(number);
		};
		//获取货车数量
		var getcarNum = function(){
			// var requestUrl = "request.php";
			// var requestData = {
			// 	func : "getNumber1",
			// 	num : 7896903489
			// };
			// RequestUtil.sendRequest({
			// 	requestUrl : requestUrl,
			// 	type : "POST",
			// 	requestData: requestData,
			// 	succeedCallback: function(res){
			// 		if(!res.data.update){
			// 			return false;
			// 		}
			// 		fxNum($("#numWrap1"), res.data.num);
			// 	}
			// });
			fxNum($("#numWrap1"), 3000);
		};
		//获取司机数量
		var getDriverNum = function(){
			// var requestUrl = "request.php";
			// var requestData = {
			// 	func : "getNumber2",
			// 	num : 6908904567
			// };
			// RequestUtil.sendRequest({
			// 	requestUrl : requestUrl,
			// 	type : "POST",
			// 	requestData: requestData,
			// 	succeedCallback: function(res){
			// 		if(!res.data.update){
			// 			return false;
			// 		}
			// 		fxNum($("#numWrap2"), res.data.num);
			// 	}
			// });
			fxNum($("#numWrap2"), 3129);
		};
		//获取总里程数
		var getMileNum = function(){
			// var requestUrl = "request.php";
			// var requestData = {
			// 	func : "getNumber3",
			// 	num : 90
			// };
			// RequestUtil.sendRequest({
			// 	requestUrl : requestUrl,
			// 	type : "POST",
			// 	requestData: requestData,
			// 	succeedCallback: function(res){
			// 		if(!res.data.update){
			// 			return false;
			// 		}
			// 		fxNum($("#numWrap3"), res.data.num);
			// 	}
			// });
			fxNum($("#numWrap3"), 2.5);
		};
		//城配专车车辆、加盟司机人数、物流配送里程
		var showNumber = function(){
			getcarNum();
			getDriverNum();
			getMileNum();
			//var timer = setInterval(function(){
				getcarNum();
				getDriverNum();
				getMileNum();
			//}, 1000);
		};
		//我们的合作
		var cooperation = function(){
			if ($("#imgAreaScroll>a").length <= 3) {
				$(".main .cooperation .img-mask").fadeOut();
			}
			var rollLayer = new RollLayer({
				scrollLayer : "#imgAreaScroll",
				listLayer : "#imgAreaScroll .img",
				everyScreenLength : 3,
				speed : fxSpeed,
				prevBtn : "#prevScroll",
				nextBtn : "#nextScroll"
			});
			rollLayer.init();
		};
		public.init = function(){
			slideBox();
			showNumber();
			cooperation();
		};
	};
	PublicUtil.pageLoad(null, function(){
		var index = new Index();
		index.init();
	});	
});