/**
  * JS数字翻转动画(需要引入plugin.animate.background.js重写animate函数)
  * author:张渊
  * modifyTime:2016-8-9
  *
  */
(function(){
	var FxNum = window.FxNum = function(config){
		var $doc = $(document);
		var public = FxNum.prototype;
		var fxNumConfig = config;
		var defaultTemp = {
			num : '<li class="num"></li>',
			sepa : '<li class="sepa"></li>',
			dot: '<li class="dot">.</li>'
		};
		var defaults = {
			$wrapper : $("#numWrap"),
			numItems : ".num",
			sepaItems : ".sepa",
			temp : defaultTemp,
			frameHeight : 40,
			speed : 300
		}; 
		//获取数字在图片中的位置
		var getNumPos = function(num){
			var bgY = 0-num*defaults.frameHeight;
			return "0 " + (bgY+"px");
		};
		//将整型转换为带逗号分隔的字符串
		var formatInt = function(number){
		   var num = parseInt(number).toString();
		   var len= num.length;
		   if(len <= 3){
			   return num;
		   }
		   var r = len % 3;
		   return r > 0 ? num.slice(0,r) +"," + num.slice(r,len).match(/\d{3}/g).join(",") : num.slice(r, len).match(/\d{3}/g).join(",");
		}
		// 将数字转换为字符串
		var formatNum = function(number) {
			var formated;
			if (("" + number).indexOf(".") === -1) {
				formated = formatInt(number);
			} else {
				formated = formatInt(number) + "." + ("" + number).split(".")[1];
			}
			return formated;
		};
		//获取数字字符串的长度
		var getNumStrLen = function(number){
			var numStr = formatNum(number);
			return numStr.length;
		};
		//将数字打散转换为数组
		var getNumArr = function(number){
			var numStr = formatNum(number);
			return numStr.split("");
		};
		//创建数字容器
		var createNum = function(number){
			var numArr = getNumArr(number);
			var len = getNumStrLen(number);
			var numHtml = [];
			var numBackPos = getNumPos(0);
			for(var i = 0; i < len; i++){
				if(numArr[i].indexOf(",") != -1){
					numHtml.push(defaults.temp.sepa);
				}
				else if (numArr[i] === ".") {
					numHtml.push(defaults.temp.dot);
				}
				else{
					numHtml.push(defaults.temp.num);
				}
			}
			defaults.$wrapper.html(numHtml.join("\n"));
			defaults.$wrapper.children(defaults.numItems).css("background-position", numBackPos);
			defaults.$wrapper.children(defaults.sepaItems).css("background-position", "left bottom");
		};
		//初始化
		public.init = function(number){
			$.extend(true, defaults, fxNumConfig);
			createNum(number);
			this.update(number);
		};
		//更新数字
		public.update = function(number){
			var $items = defaults.$wrapper.children();
			var len = getNumStrLen(number);
			var numArr = getNumArr(number);
			for(var i = 0; i < len; i++){
				(function(index){
					var num = parseInt(numArr[index], 10);
					if (!isNaN(num)) {
						var backPos = getNumPos(num);
						$items.eq(index).animate({
							"background-position": backPos
						}, defaults.speed);
					}
					// if(numArr[index].indexOf(",") == -1){
					// 	var num = parseInt(numArr[index]);
					// 	var backPos = getNumPos(num);
					// 	$items.eq(index).animate({
					// 		"background-position" : backPos
					// 	}, defaults.speed);
					// }
				})(i);
			}
		};
	};
})();