/**
 * 数字工具
 * 
 * @author	kutome
 * @date	2018-09-04
 */

var NumberTool = {
	
	/**
	 * 格式化文件大小
	 * 
	 * @param	fileSize	文件大小，单位：B
	 * @return	返回格式化后的字符串，例4KB
	 * */
	formatFileSize: function(fileSize) {
		return this._recursionFormatFileSize(0, fileSize);
	},
	
	/**
	 * 递归格式化文件大小
	 * 
	 * @param	depth	当前递归深度，用于获取单位
	 * @param	fileSize	文件大小
	 * */
	_recursionFormatFileSize: function(depth, fileSize) {
		var fileSizeMap = {
			"0": "B",
			"1": "KB",
			"2": "MB",
			"3": "G"
		};
		if (parseInt(fileSize / 1024) == 0) {
			if (fileSize > 100) {
				fileSize = parseInt(fileSize);
			}
			return fileSize + fileSizeMap[depth];
		} else {
			return this._recursionFormatFileSize(depth + 1, parseFloat(fileSize / 1024).toFixed(1));
		}
	},
	
	/**
	 * 裁剪字符串
	 * */
	cutStr: function(str, length) {
		if (str == null || str == undefined || str == "") {
			return  str;
		}
		
		if (str.length > length) {
			str = str.substring(0, length) + "...";
		}
		
		return str;
	}
};