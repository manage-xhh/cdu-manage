/**
  * 哈喽师傅WEB官网->司机加盟
  * author:张渊
  * modifyTime:2016-8-10
  *
  */
require.config(config.require);
require(config.classList.driver, function(ReSlider){
 	var Complain = function(){
 		var public = Complain.prototype;
 		var element = {
 			$cityGroup : $("#cityGroup"),
 			$item : $("#cityGroup .select-item")
 		};
 		var checkSource = function(text){
 			var b = /^[0-9a-zA-Z_-]*$/g;
 			return b.test(text);
 		}
 		var checkForm = function(){
 			var PHONE_REG = /^1[3|4|5|7|8]\d{9}$/;
 			var $loading = $("#pageLoading");
 			var placeholder = {
 				username : "请输入真实姓名",
 				phone : "请输入有效的电话号码",
 				selectCity : "选择城市"
 			};
 			var username = $("input[name='username']").val();
 			var phone = $("input[name='phone']").val();
 			var selectCity = $("input[name='selectCity']").val();
 			if(username == "" || username == placeholder.username){
 				alert("请输入真实姓名");
 				return false;
 			}
 			if(phone == "" || phone == placeholder.phone){
 				alert("请输入您的电话号码");
 				return false;
 			}
 			if(!PHONE_REG.test(phone)){
 				alert("请输入正确的电话号码");
 				return false;
 			}
 			if(selectCity == "" || selectCity == placeholder.selectCity){
 				alert("请选择加盟城市");
 				return false;
 			}
 			ViewUtil.mask(true);
 			$loading.show();
            

 			var source = $.getUrlParam('source');
 			if(!checkSource(source)){
 				alert('非法来源');
 				return false;
 			}
 			source = !source?"PC":source;
 			
 			var requestUrl = baseUrl + "/join/create.html";
 			var requestData = {
 				uname: username,
 				phone: phone,
 				city: selectCity,
 				source : source
 			};
			
			RequestUtil.sendRequest({
				requestUrl : requestUrl,
				type : "POST",
				requestData: requestData,
				succeedCallback: function(res){
					alert("提交成功");
					ViewUtil.mask(false);
 					$loading.hide();
 					// 重置表单
 					$(".join-us-item input").val("").blur();
 					$("#selectCityInput").val("选择城市");
				}
			});
 		};
 		var submitForm = function(){
 			$("#submitBtn").on("click", function() {
				return checkForm();
			});
 		};

 		var onSelectCityFocus = function() {
 			$("#selectCityWrap").on("click", function() {
 				$("#cityWrapperCon").show();
 			});

 			$("#selectCityInput").on("blur", function() {
 				setTimeout(function() {
 					$("#cityWrapperCon").hide();
 				}, 150);
 			})
 		};

 		var onCityItemClick = function() {
 			$("#scroller a").click(function(e) {
 				e.preventDefault();
 				e.stopPropagation();
 				$("#selectCityInput").val($(this).text());
 				setTimeout(function() {
 					$("#cityWrapperCon").hide();
 				})
 			});
 		};

 		var cityListScrollView = function() {
 			$("#cityWrapperCon").show();
 			try {
				var myScroll = new IScroll('#cityWrapper', {
					scrollbars: true,
					mouseWheel: true,
					interactiveScrollbars: true,
					shrinkScrollbars: 'scale',
					fadeScrollbars: false
				});
 			} catch(e) {
 				$("#cityWrapper").css({
 					"overflow-y": "scroll"
 				});
 			}
 			$("#cityWrapperCon").hide();
 		};

 		var placeholder = function(id, text) {
 			var input = $(id);
 			input.attr("data-placeholder", text);
 			input.on("focus", function() {
 				var $this = $(this);
 				if ($this.val() === $this.attr("data-placeholder")) {
 					$this.val("");
 				}
 			}).on("blur", function() {
 				var $this = $(this);
 				if ($.trim($this.val()) === "") {
 					$this.val($this.attr("data-placeholder"));
 				}
 			});
 		};

 		var initCarSlider = function() {
 			ReSlider.slide({
				container: document.getElementById("carSlider"),
				debug: true,
				pauseOnHover: true,
				// autoPlay: false
				duration: 2000
			});
 		};

 		var initMasterSlider = function() {
 			ReSlider.slide({
				container: document.getElementById("masters"),
				debug: true,
				pauseOnHover: true,
				autoPlay: true,
				duration: 5000
			});
 		};

 		var initSlideDown = function() {
 			$("#masters .col").hover(function() {
 				var $tip = $(this).find(".tip");
 				if ($tip.css("display") !== "block") {
 					$tip.slideDown(200);
 				}
 			}, function() {
 				$(this).find(".tip").slideUp(200);
 			}).on("click", function() {
 				var url = $(this).find(".name a").attr("href");
 				if (url) {
 					window.location.href = url;
 				}
 			});
 		};

 		var initAcitivityScroll = function() {
 			try {
	 			var iscoll = new IScroll("#jumaActivity", {
	 				scrollbars: true,
					mouseWheel: true,
					interactiveScrollbars: true,
					fadeScrollbars: false,
					snap: true
	 			});
 			} catch(e) {
 				$("#jumaActivity").css("overflow-y", "scroll");
 			}
 		};

		public.init = function(){
			// setSelectState();
			// selectCity();
			initSlideDown();
			initCarSlider();
			initMasterSlider();
			initAcitivityScroll();
			placeholder("#inputUserName", "请输入真实姓名");
			placeholder("#inputPhone", "请输入有效的电话号码");
			onSelectCityFocus();
			onCityItemClick();
			cityListScrollView();
			submitForm();
		};
	};
	PublicUtil.pageLoad(null, function(){
		$.getUrlParam = function (name) {
		    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
		    var r = window.location.search.substr(1).match(reg);
		    if (r != null) return unescape(r[2]); return null;
		}
		var complain = new Complain();
		complain.init();
	});
});
