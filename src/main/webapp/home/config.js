/*
 * 配置文件
 * author:张渊
 * modifyTime:2016-7-22
 */
var BASE_URL = "/forward/";
//var BASE_PATH = "/template/";
var BASE_PATH = baseUrl+"/";
var JS_PATH = BASE_PATH+"js/";
var LIB = JS_PATH+"lib/";
var UNTIL_PATH = JS_PATH+"until/";
var PLUGIN_PATH = JS_PATH+"plugin/";
var UI_PATH = JS_PATH+"ui/";
var config = {
	//模块
	require : {
		//添加版本号
		urlArgs: "v=" + new Date().getTime(),
		baseUrl: BASE_PATH,
		paths: {
			Jquery: LIB+"jquery/jquery.min",
			Router: LIB+"director/director.min",
			Md5: LIB+'md5/md5.min',
			Swipe: LIB+'swipe/swipe.min',
			css3Transit: LIB+'css3Transit/transit.min',
			Zfx: LIB+'zepto/zepto.zfx.min',
			ZfxMethod: LIB+'zepto/zepto.zfxmethods.min',
			Iscroll: LIB+'iscroll/iscroll.min',
			IScroll: LIB+'iscrollv5/iscroll.min',
			Plupload: LIB+'plupload/plupload.min',
			Toucher: LIB+'toucher/toucher.min',
			Json: LIB+'json/json2.min',
			Juicer: LIB+'template/juicer.min',
			DownCount: LIB+'downCount/jquery.downcount.min',
			CoordTransform: LIB+'coordTransform/coord.transform.min',
			BgPosAnimate: PLUGIN_PATH+'plugin.animate.background',
			PublicUtil: UNTIL_PATH+'public.until',
			ViewUtil: UNTIL_PATH+'view.until',
			RequestUtil: UNTIL_PATH+'request.until',
			Tween: UI_PATH+'ui.animate.tween',
			SlideBox: UI_PATH+'ui.slidebox',
			RollLayer: UI_PATH+'ui.rolllayer',
			FxNum: UI_PATH+'ui.animate.number'
		},
		shim: {
			'PublicUtil': {
				deps: [
					'Jquery'
				],
				exports: 'PublicUtil'
			},
			'ViewUtil': {
				deps: [
					'Jquery'
				],
				exports: 'ViewUtil'
			},
			'RequestUtil': {
				deps: [
					'Jquery',
					'Json',
					'Juicer'
				],
				exports: 'RequestUtil'
			},
			'BgPosAnimate': {
				deps: [
					'Jquery'
				],
				exports: 'BgPosAnimate'
			}
		}
	},
	//视图
	view : {
		maskEl : ".page-mask",
		loadingEl : ".page-loading",
		listLoadEl : ".load-more",
		poptips : {
			el : ".app-poptips",
			pos : "center",
			showTime : 1
		},
		loadMoreBtn : ".app-more-btn",
		animate : {
			speed : 300,
			translate : "ease-in-out"
		},
		dilog : {
			el : "#dilog",
			cancelBtn : ".cancel",
			confirmBtn : ".confirm"
		},
		pageSize : 10,
		loginUrl : BASE_URL+"user/login.html"
	},
	//模版配置
	tpl : {
		temp : {
			'tag::operationOpen': '(@',
			'tag::operationClose': ')',
			'tag::interpolateOpen': '$(',
			'tag::interpolateClose': ')',
			'tag::noneencodeOpen': '$$(',
			'tag::noneencodeClose': ')',
			'tag::commentOpen': '(#',
			'tag::commentClose': ')'
		}
	},
	//控制器
	classList : {
		index : [
			'Jquery',
			'PublicUtil',
			'RequestUtil',
			'Tween',
			'SlideBox',
			'RollLayer',
			'BgPosAnimate',
			'FxNum'
		],
		list : [
			'Jquery',
			'PublicUtil',
			'RequestUtil'
		],
		complain : [
			'js/class/reslider.class.js',
			'Jquery',
			'PublicUtil',
			'ViewUtil',
			'RequestUtil'
		],
		driver : [
			'js/class/reslider.class.js',
			'Jquery',
			'PublicUtil',
			'ViewUtil',
			'RequestUtil',
			'IScroll'
		]
	},
	// 物流配送页面 分公司联系点 配置信息
	contactPoints: [
		{
			top: 433,
			left: 608,
			company: "成都分公司",
			contact: "028-68536826",
			email: "chengdu@jumapeisong.com",
			address:"成都市双流县西航港物流大道567号中国物资储运办公楼210室"
		},
		{
			top: 378,
			left: 666,
			company: "西安分公司",
			contact: "029-89309833",
			email: "xian@jumapeisong.com",
			address:"西安市沣东新城三桥后卫寨启航时代广场B座2001"
		},
		{
			top: 445,
			left: 759,
			company: "武汉分公司",
			contact: "027-83066156",
			email: "wuhan@jumapeisong.com",
			address:"武汉市东西湖区吴家山七雄路海峡创业城二期2栋12层"
		},
		{
			top: 471,
			left: 735,
			company: "长沙分公司",
			contact: "0731-81824962",
			email: "shangsha@jumapeisong.com",
			address:"湖南省长沙县经济技术开发区螺丝塘路68号星沙国际企业中心10栋503室"
		},
		{
			top: 565,
			left: 766,
			company: "深圳分公司",
			contact: "0755-84573265",
			email: "shenzhen@jumapeisong.com",
			address:"深圳市龙岗区南湾街道上李朗平吉大道78号友信食品城综合楼B507"
		},
		{
			top: 557,
			left: 744,
			company: "广州分公司",
			contact: "020-31477425",
			email: "guangzhou@jumapeisong.com",
			address:"广州市白云区白云大道北628号211跨境电子产业园712室"
		},
		{
			top: 456,
			left: 640,
			company: "重庆分公司",
			contact: "023-67079612",
			email: "chongqing@jumapeisong.com",
			address:"重庆市江北区海尔路825号重庆市中钢现代物流楼宇产业园1-9-21"
		},
		{
			top: 415,
			left: 853,
			company: "上海分公司",
			contact: "021-60460009",
			email: "shanghai@jumapeisong.com",
			address:"上海市青浦区e通世界商务园南区B座905室"
		},
		{
			top: 368,
			left: 740,
			company: "郑州分公司",
			contact: "0371-86162137",
			email: "zhengzhou@jumapeisong.com",
			address:" 河南省郑州市新郑市龙湖镇世贸汽车城A区18号"
		},
		{
			top: 576,
			left: 684,
			company: "南宁分公司",
			contact: "0771-3376077",
			email: "nanning@jumapeisong.com",
			address:"南宁市西乡塘区科园西十路11号国电智能大厦1609号"
		},
		{
			top: 406,
			left: 795,
			company: "合肥分公司",
			contact: "0551-62769886",
			email: "hefei@jumapeisong.com",
			address:"合肥市包河区兰州路88号青网大厦902"
		},
		{
			top: 279,
			left: 777,
			company: "天津分公司",
			contact: "022-26913282",
			email: "tianjin@jumapeisong.com",
			address:"天津市北辰区京津公路与北辰道交叉路口金玺大厦2号楼601室（北辰区政府旁）"
		},
		{
			top: 444,
			left: 846,
			company: "杭州分公司",
			contact: "0571-87383559",
			email: "hangzhou@jumapeisong.com",
			address:"杭州市下城区石祥路59号34幢216室"
		},
		{
			top: 418,
			left: 818,
			company: "无锡分公司",
			contact: "0510-85882830",
			email: "wuxigongsi@jumapeisong.com",
			address:"无锡惠山区探索路6号宇野大厦 1701"
		},
		{
			top: 325,
			left: 788,
			company: "济南分公司",
			contact: "0531-55661605",
			email: "jinan@jumapeisong.com",
			address:"济南市天桥区济泺路71号齐鲁云商2309"
		}
	]
};
//移动端PC端首页跳转
(function() {
	// 判断当前路径是否需要跳转
	var isJumpurl = function(jumpurls, pathname, prefix) {
		var i = 0, l = jumpurls.length;
		for (; i<l; i+=1) {
			if ((prefix + jumpurls[i]) === pathname) {
				return true;
			}
			println((prefix + jumpurls[i]) + " != " + pathname);
		}
		return false;
	};
	var println = function(msg) {
		try {
			console.log(msg);
		} catch(e) {}
	}
	// 跳转
	var setup = function() {
		var prefix = "";
		var isMobileSite = window.location.host === "m.jumapeisong.com";
		var isMobile = /(Android|iPhone|iPad)/i.test(navigator.userAgent);
		var pathname = window.location.pathname;
		var pcUrl = "//www.jumapeisong.com" + pathname;
		var mobileUrl = "//m.jumapeisong.com" + pathname;
		var jumpurls = [
			"",
			"/",
			"/index.html",
			"/delivery.html",
			"/driver-recruit.html",
			"/project.html",
			"s/juma-truck.html",
			"/contact.html",
			"/about.html"
		];
		// 判断当前路径是否需要跳转
		if (isJumpurl(jumpurls, pathname, prefix)) {
			println("跳转 " + pathname);
			// 跳转至移动端
			if (isMobile) {
				println("移动端");
				// 如果访问的不是移动端则跳转至移动端
				if (!isMobileSite) {
					println("跳转至" + mobileUrl);
					window.location.href = mobileUrl;
				} else {
					println("不跳转");
				}
			}
			// 跳转至PC端
			else {
				println("PC端");
				if (isMobileSite) {
					println("跳转至" + pcUrl);
					window.location.href = pcUrl;
				} else {
					println("不跳转");
				}
			}
		} else {
			println("不跳转 " + pathname);
		}
	};

	setup();
})();