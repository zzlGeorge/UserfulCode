// 订单管理状态切换
$('.status-l>li').on('click', function () {
   $(this).addClass('current').siblings().removeClass('current');
});