!(function() {
    wap2app.appid = '__W2A__101.201.70.76';
    var define = wap2app.define;
    var require = wap2app.require;
    var App = wap2app.App;
    var Page = wap2app.Page;
    var __w2aRoute;
    /************************sitemap begin************************************/
    /*暂时先集成进去兼容旧版本基座*/
    window.__wap2app_sitemap = {"global":{"webviewParameter":{"titleNView":{"autoBackButton":true,"backgroundColor":"#f7f7f7","titleColor":"#000000","titleSize":"17px"},"statusbar":{"style":"dark"},"appendCss":"","appendJs":""},"easyConfig":{}},"pages":[{"webviewId":"__W2A__101.201.70.76","matchUrls":[{"href":["^http\\:\\/\\/101\\.201\\.70\\.76\\:8210$"]},{"href":["^http\\:\\/\\/101\\.201\\.70\\.76\\:8210\\/$"]}],"webviewParameter":{"titleNView":false,"statusbar":{"background":"#f7f7f7"},"appendCss":""}}]};
    /************************sitemap end**************************************/

    /************************util begin***************************************/
    
    /************************util end*****************************************/
    /************************nviews begin*************************************/
    
    /************************nviews end***************************************/
    /************************pages begin**************************************/
    define('app.js', function (require, module) {
    App({
    options: {
        debug: false
    },
    /**
     * 当wap2app初始化完成时，会触发 onLaunch
     */
    onLaunch: function() {
        console.log('launch');
    },
    /**
     * 当wap2app启动，或从后台进入前台显示，会触发 onShow
     */
    onShow: function() {
        console.log('show');
    },
    /**
     * 当wap2app从前台进入后台，会触发 onHide
     */
    onHide: function() {
        console.log('hide');
    }
});
Page('__W2A__101.201.70.76', { //首页扩展配置
    onShow: function() {

    },
    onClose: function() {

    }
});
});
require("app.js");
    /************************pages end****************************************/

    wap2app.initSitemap();
})();