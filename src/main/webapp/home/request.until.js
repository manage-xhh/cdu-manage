/*
 * 数据请求工具类
 * author:张渊
 * modifyTime:2016-7-25
 */
(function(){	
 		var viewConfig = config.view;
 		//获取列表参数
 		var getListParam = function(res, paramStr){
 			var result = res;
 			if(paramStr != null){
	 			var param = paramStr.split(".");
	 			var paramLen = param.length;
	 			for(var i = 0; i < paramLen; i++){
	 				result = result[param[i]];
	 			}
 			}
 			return result;
 		};
 		//设置模版边界符
 		var setTempTag = function(){
		    juicer.set(config.tpl.temp);
		};
 		var RequestUtil = window.RequestUtil = {
 			//给请求设置时间戳，防止缓存
			timeStamp : function(requestUrl){
				var date = new Date();
				var connect = "?";
				if(requestUrl.indexOf("?") != -1){
					connect = "&";
				}
				return requestUrl + connect + "timeStamp="+date.getTime();
			},
			//发送请求
			sendRequest : function(config){
				var defaults = {
					requestUrl : "",
					type : "GET",
					requestData: "",
					succeedCallback: function(res){
						return true;
					},
					failureCallback : function(res){
						return true;
					}
				};
				PublicUtil.setConfig(defaults, config);
				var requestUrl = RequestUtil.timeStamp(defaults.requestUrl);
				var type = defaults.type;
				var requestData = defaults.requestData;
				var succeedCallback = defaults.succeedCallback;
				var failureCallback = defaults.failureCallback;
				if(requestData == ""){
					requestData = null;
				}
				else{
					requestData = JSON.stringify(requestData);
				}
				$.ajax({
					type: type,
					url: requestUrl,
					data: requestData,
					dataType: "json",
					headers: {
						"Content-Type": "application/json"
					},
					success: function(res){
						var code = parseInt(res.code);
						if(!code){
							setTempTag();
							succeedCallback(res);
						}
						else if(code == 1){
							var message  = res.message;
							ViewUtil.mask(true);
							ViewUtil.tip({
								content : message, 
								callback : function(){
									ViewUtil.mask(false);
									//location.href = viewConfig.loginUrl;
								}
							});
						}
						else{
							failureCallback(res);
						}
					}
				});
			},
		    /**
		     * 点击按钮异步加载数据
		     * @config Object $btnObj 按钮
		     * @config String ajaxType 请求方式，默认为POST
		     * @config String requestUrl 请求地址
		     * @config Object requestData 请求数据
		     * @config String listParamName 返回的列表参数名称
		     * @config Function succeed 请求成功后执行的回调函数
		     */
		    clickBtnLoad : function(config){
			   	var loadingTxt = "正在加载数据,请稍后...";
			   	var noDataText = "没有更多数据了";
			   	var btnClick = false;
			   	var noData = false;
			   	var defaults = {
			   		$btnObj : $("#btn"),
			   		ajaxType : "POST",
			   		requestUrl : "load.php",
			   		requestData : null,
			   		pageParamName : "page",
			   		listParamName : "list",
			   		succeed : function(res){
			   			return true;
			   		}
			   	};
			   	PublicUtil.setConfig(defaults, config);
			   	if(btnClick){
			   		return false;
			   	}
			   	//点击按钮执行的回调函数
			   	var btnFunc = function($this){
			   		if(noData){
			   			return false;
			   		}
			   		btnClick = true;
			   		var $span = $this.children("span");
			   		var page = parseInt($this.data("page"));
			   		var btnTxt = $span.text();
			   		var requestData = defaults.requestData;
			   		var pageParamName = defaults.pageParamName;
			   		var listParamName = defaults.listParamName;
			   		page++;
			   		if(requestData == null){
			   			requestData = {};
			   		}
			   		requestData[pageParamName] = page;
			   		$span.text(loadingTxt);
			   		RequestUtil.sendRequest({
			   			requestUrl : defaults.requestUrl,
			   			type : defaults.ajaxType,
			   			requestData: requestData,
			   			succeedCallback: function(res){
			   				btnClick = false;
			   				$span.text(btnTxt);
			   				if(!parseInt(res.code)){
			   					var list = getListParam(res, listParamName);
			   					if(list.length){
			   						$this.data("page", page);
			   						defaults.succeed(res);
			   					}
			   					else{
			   						noData = true;
			   						$span.text(noDataText);
			   					}	
			   				}
			   			}
			   		});
 				};
			   	defaults.$btnObj.on("mousedown", function(){
			   		btnFunc($(this));
			   	});
		    },
		    /**
		     * 上拉自动加载
		     * @config Object $scrollObj 包含滚动条的容器
		     * @config Boolean isWindow 滚动的对象是否为窗口对象
		     * @config String ajaxType 请求方式，默认为POST
		     * @config String requestUrl 请求地址
		     * @config Object requestData 请求数据
		     * @config String pageParamName 页码参数名称
		     * @config String listParamName 返回的列表参数名称
		     * @config Function succeed 请求成功后执行的回调函数
		     */
		    pullDownLoad : function(config){
			   	var $document = $(document);
			   	var $window = $(window);
			   	var defaults = {
			   		$scrollObj : $window,
			   		isWindow : true,
			   		ajaxType : "POST",
			   		requestUrl : "load.php",
			   		requestData : null,
			   		pageParamName : "page",
			   		listParamName : "list",
			   		succeed : function(res){
			   			return true;
			   		}
			   	};
			   	PublicUtil.setConfig(defaults, config);
			   	var $scrollObj = defaults.$scrollObj;
			   	var $loadingObj = $(viewConfig.listLoadEl);
			   	var pageParamName = defaults.pageParamName;
			   	var listParamName = defaults.listParamName;
			   	var headers = defaults.headers;
			   	var loading = false;
			   	var noData = false;
			   	if(loading){
			   		return false;
			   	}
			   	//上拉自动加载的回调函数
			   	var scrollFunc = function(){
			   		var isBottom = defaults.isWindow ? $window.scrollTop() >= $document.height()-$window.height() 
			   		: $scrollObj.scrollTop() >= $scrollObj.get(0).scrollHeight-$scrollObj.height();
					  //此时滚动条已经到达页面底部
					  if(isBottom){
					  	if(noData){
					  		return false;
					  	}
					  	loading = true;
					  	var page = parseInt($loadingObj.data("page"));
					  	var requestData = defaults.requestData;
					  	$loadingObj.css({
					  		height : "40px",
					  		overflow : "visible"
					  	});
					  	page++;
					  	if(requestData == null){
					  		requestData = {};
					  	}
					  	requestData[pageParamName] = page;
					  	RequestUtil.sendRequest({
					  		requestUrl : defaults.requestUrl,
					  		type : defaults.ajaxType,
					  		requestData: requestData,
					  		succeedCallback: function(res){
					  			loading = false;
					  			$loadingObj.css({
							  		height : 0,
							  		overflow : "hidden"
							  	});
					  			if(!parseInt(res.code)){
					  				var list = getListParam(res, listParamName);
					  				if(list.length){
					  					$loadingObj.data("page", page);
					  					defaults.succeed(res);
					  				}
					  				else{
					  					noData = true;
					  				}
					  			}
					  		}
					  	});
					  }
			   	};
			   	$scrollObj.off().on("scroll", scrollFunc);
		    },
		    /**
		     * 加载数据
		     * @config Object $temp 模版
		     * @config Object $list 列表容器
		     * @config String requestUrl 请求地址
		     * @config String||Object requestData 请求参数
		     * @config Object registerFuc 为模版注册自定义函数
		     * @config function loadCallback 数据正在加载时的回调函数
		     * @config function succeedCallback 数据加载成功时的回调函数
		     */
		    loadData : function(config){
			  var defaults = {
				  $temp : $("#temp"),
				  $list : $("#list"),
				  requestUrl : "demo.php",
				  requestType : "POST",
				  requestData : "",
				  listParamName : "list",
				  registerFuc : [],
				  loadCallback : function(res){
					  return true;
				  },
				  succeedCallback : function(res){
					  return true;
				  }
			  };
			  PublicUtil.setConfig(defaults, config);
			  var $temp = defaults.$temp;
			  var $list = defaults.$list;
			  var requestUrl = defaults.requestUrl;
			  var requestType = defaults.requestType;
			  var requestData = defaults.requestData;
			  var listParamName = defaults.listParamName;
			  var registerFuc = defaults.registerFuc;
			  var loadCallback = defaults.loadCallback;
			  var succeedCallback = defaults.succeedCallback;
			  if(requestData == ""){
				  requestData = null;
			  }
			  if(loadCallback){
				  loadCallback();
			  }
			  RequestUtil.sendRequest({
				  	requestUrl : requestUrl,
				  	type : requestType,
				  	requestData: requestData,
				  	succeedCallback: function(res){
				  		var list = getListParam(res, listParamName);
				  		var len = registerFuc.length;
				  		if(len){
				  			for(var i = 0; i < len; i++){
				  				juicer.register(registerFuc[i].name, registerFuc[i].func);
				  			}
				  		}				  		
				  		PublicUtil.view($temp, $list, list, "write");
				  		if(succeedCallback){
				  			succeedCallback(res);
				  		}
				  	}
			  });
		  }
    };
})();
