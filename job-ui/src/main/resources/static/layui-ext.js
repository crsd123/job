/*
 * var date = new Date();
 * var str = date.format('yyyy-MM-dd HH:mm:ss');
 */
Date.prototype.format = function (fmt) {
    var o = {
        "M+": this.getMonth() + 1, //月份
        "d+": this.getDate(), //日
        "H+": this.getHours(), //小时
        "m+": this.getMinutes(), //分
        "s+": this.getSeconds(), //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt))
        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt))
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
};


/*
 * Http 工具类
 */
layui.define('jquery', function (exports) {
    var jquery = layui.jquery;
    var http = {};

    jquery.ajaxSettings.contentType = 'application/json; charset=UTF-8';

    jquery(document).ajaxError(function (event, xhr, options, exc) {
        if (xhr.status == 401 || xhr.status == 405 || xhr.status == 406 || xhr.status == 415 || xhr.status == 500) {
            if (xhr.responseJSON) {
                layer.msg(xhr.responseJSON.message, {time: 1000});
            } else {
                console.log(xhr.responseText);
            }
        }else {
            console.log(xhr.responseText);
        }
    });

    http.post = function (uri, params, callback) {
        var _params = callback ? params : {};
        var _callback = callback ? callback : params;
        jquery.post(uri, JSON.stringify(_params), function (res) {
            if (res.code != 200) {
                layer.msg(res.message, {time: 1000});
            } else {
                _callback.apply(this, [res]);
            }
        }, 'json');
    };

    exports('http', http);
});