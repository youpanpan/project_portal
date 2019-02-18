/**
 * jquery插件（一些小功能）
 * @author	kutome
 * @date	2018-08-14
 * */


(function(window, $) {
	
	/**
	 * 获取表单填写的json对象
	 * @return	表单数据对应的json对象
	 * */
    $.fn.serializeJson = function() {
        var serializeObj = {};
        var array = this.serializeArray();
        var str = this.serialize();
        $(array).each(
            function() {
                if (serializeObj[this.name]) {
                    if ($.isArray(serializeObj[this.name])) {
                        serializeObj[this.name].push(this.value);
                    } else {
                        serializeObj[this.name] = [
                                serializeObj[this.name], this.value ];
                    }
                } else {
                    serializeObj[this.name] = this.value;
                }
            }
        );
        return serializeObj;
    };
})(window, jQuery);