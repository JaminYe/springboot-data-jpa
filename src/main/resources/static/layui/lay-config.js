window.rootPath = (function (src) {
    src = document.scripts[document.scripts.length - 1].src;
    return src.substring(0, src.lastIndexOf("/") + 1);
})();

layui.config({
    base: ctx + "layui-module/",
    version: true
}).extend({
    tableSelect: 'tableSelect/tableSelect', // table选择扩展
});