/**
 * 哈喽师傅WEB官网->首页
 * author:张渊
 * modifyTime:2016-8-9
 *
 */
require.config(config.require);
require(config.classList.index, function () {
    var Truck = function () {
        var public = Truck.prototype;
        var fxSpeed = Tween.Cubic.easeInOut(100, 300, 200, 120);
        var layerTimer = null;

        /*banner动效*/
        var bannerAnimation = function () {
            var $background = $(".truck-banner-back"),
                $yellowBackLine = $(".truck-banner-back-2"),
                $redBackLine = $(".truck-banner-back-3"),
                $man = $(".truck-banner-man"),
                $text1 = $(".truck-banner-text-1"),
                $text2 = $(".truck-banner-text-2"),
                $button = $(".truck-banner-button");

            $background.show().transition({
                left: '0'
            }, 300, 'linear');
            $man.transition({
                left: '128px'
            }, 300, 'linear');

            $yellowBackLine.show().transition({
                right: '79px',
                bottom: 0
            }, 500, 'linear');

            $redBackLine.transition({
                delay: 300
            }).transition({
                display: 'block'
            }, 0).transition({
                right: '41px',
                bottom: 0
            }, 300, 'linear');

            $text1.transition({
                delay: 200
            }).transition({
                display: 'block'
            }, 0).transition({
                right: '112px',
                bottom: 0
            }, 300, 'linear');

            $text2.transition({
                delay: 400
            }).transition({
                display: 'block'
            }, 0).transition({
                right: '158px',
                bottom: 0,
            }, 300, 'linear');

            $button.transition({
                delay: 600
            }).transition({
                display: 'block'
            }, 0).transition({
                right: '158px',
                bottom: 0,
            }, 300, 'linear');



        };

        /*获取元素坐标*/
        var getRectLeft = function (element) {
            var rect = element.getBoundingClientRect();
            var left = document.documentElement.clientLeft;
            return rect.left - left;
        };


        /*显示客户信息*/
        var showCustomerInfo = function ($dom) {
            layerTimer && clearTimeout(layerTimer);
            layerTimer = setTimeout(function () {
                var _iLeft = getRectLeft($dom[0]);
                var _fLeft = getRectLeft($(".section-wrap")[0]);
                var $info = $("#truckCustomerTipLayer");
                var bot = 100;
                var left = _iLeft - _fLeft - 68.5;
                $info.css({
                    bottom: bot,
                    left: left
                });
                $info.children("p").text($dom.children("p").text());
                $info.show();
            }, 500);

        };
        var hideCustomerInfo = function () {
            var $info = $("#truckCustomerTipLayer");
            layerTimer && clearTimeout(layerTimer);
            $info.hide();
        };

        //服务客户
        var touchScrollFunc = function () {
            if ($("#imgAreaScroll>a").length <= 3) {
                $(".main .cooperation .img-mask").fadeOut();
            }
            var rollLayer = new RollLayer({
                scrollLayer: ".section-wrap .scroll-layer",
                listLayer: ".section-wrap .scroll-layer li",
                everyScreenLength: 4,
                speed: fxSpeed,
                prevBtn: ".arrow-prev",
                nextBtn: ".arrow-next"
            });
            rollLayer.init();

            window.rollLayer = rollLayer;

            $(".customer-block .item").on("mouseover", function () {
                showCustomerInfo($(this));
            }).on("mouseout", function () {
                hideCustomerInfo();
            });
            /*屏蔽rolllayer click委托事件*/
            $(document).off("click", ".section-wrap .scroll-layer li");
        };

        /*常见问题*/
        var problemsFunc = function () {
            $(".problem-block li").on("mousedown", function () {
                var $this = $(this);
                if (!$this.hasClass("current")) {
                    $this.addClass("current");
                    $this.siblings("li").removeClass("current");
                    var index = $this.index();
                    $answer = $(".problem-block .answer").children(".answer_" + index);
                    $answer.show().siblings().hide();
                }
            });
        };

        public.init = function () {
            bannerAnimation();
            touchScrollFunc();
            problemsFunc();
        };
    };
    PublicUtil.pageLoad(null, function () {
        var truck = new Truck();
        truck.init();
    });
});
