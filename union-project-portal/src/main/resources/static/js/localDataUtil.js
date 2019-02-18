/**
 * 客户端数据操作操作文件
 * @author	kutome
 * @date	2018-08-30
 */
var LocalDataUtil = {
		
	//是否支持localStorage
	isLocalStorage:window.localStorage ? true : false,
	
	//过期天数
	expireDay: 30,
	
	/**
	 * 存储数据项
	 * 
	 * @param	key	键
	 * @param	value	值
	 * */
	setItem: function(key, value, exdays) {
		if (this.isLocalStorage) {
			window.localStorage.setItem(key, value);
		} else {
			//使用cookie
			setCookie(key, value, this.expireDay);
		}
	},
	
	/**
	 * 获取存储的指定键的值
	 * 
	 * @param	key	键
	 * @return	值
	 * */
	getItem: function(key) {
		if (this.isLocalStorage) {
			return window.localStorage.getItem(key);
		} else {
			//使用cookie
			var strCookie=document.cookie;
		    var arrCookie=strCookie.split(";");
		    for (var i = 0; i < arrCookie.length; i++) {
		        var c = arrCookie[0].split("=");
		        if(c[0] == key) {
		            return c[1];
		        }
		    }
		}
	},
	
	
	/**
	 * 删除指定键的数据项
	 * 
	 * @param	key	键
	 * */
	deleteItem: function(key) {
		if (this.isLocalStorage) {
			return window.localStorage.removeItem(key);
		} else {
			//使用cookie
			setCookie(key, "", -1);
		}
		
	},
	
	/**
	 * 设置cookie
	 * 
	 * @param	key	键
	 * @param	value	值
	 * @param	exdays	过期天数
	 * */
	setCookie: function(key, value, exdays) {
	    var d = new Date();
	    d.setTime(d.getTime() + (exdays*24*60*60*1000));
	    var expires = "expires="+d.toUTCString();
	    document.cookie = key + "=" + value + "; " + expires;
	}
		
}
