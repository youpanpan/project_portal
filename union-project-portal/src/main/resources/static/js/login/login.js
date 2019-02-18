$(document).ready(function() {
    layui.use(['layer'], function() {
		var layer = layui.layer;
		
		//----------------------表单验证 begin-----------------
		/**
		 * 添加表单验证
		 * */
		function formValidator() {
			//初始化表单验证器
			$('#loginForm').bootstrapValidator({
		        message: 'This value is not valid',
		        feedbackIcons: {
		            valid: 'glyphicon glyphicon-ok',
		            invalid: 'glyphicon glyphicon-remove',
		            validating: 'glyphicon glyphicon-refresh'
		        },
		        fields: {
		        	email: {
		                message: '邮箱不合法',
		                validators: {
		                    notEmpty: {
		                        message: '邮箱不能为空'
		                    },
		                    stringLength: {
		                        min: 1,
		                        max: 100,
		                        message: '邮箱最多为100个字符'
		                    },
		                    emailAddress: {
		                        message: '邮箱地址格式有误'
		                    }
		                }
		            },
		            password: {
		                message: '密码不合法',
		                validators: {
		                	notEmpty: {
		                        message: '密码不能为空'
		                    },
		                    stringLength: {
		                        min: 6,
		                        max: 18,
		                        message: '密码长度必须在6到18位之间'
		                    }
		                }
		            }
		        }
		    }).on('success.form.bv', function(e) {
		        // 阻止默认事件提交
		        e.preventDefault();
		    });
		}
		
		/**
		 * 重置表单验证
		 * */
		function resetFormValidator() {
			$("#loginForm").data('bootstrapValidator').destroy();
			$('#loginForm').data('bootstrapValidator',null);
			formValidator();
		}
		//----------------------表单验证 end-----------------
		
		/**
		 * 获取表单填写的数据
		 * @return	表单数据
		 * */
		function getFormData() {
			return $("#loginForm").serializeJson();
		}
		
		//----------------------绑定操作按钮事件 begin---------------
		
		//保存
		$("#loginBtn").click(function(){
			
			//验证表单是否填写正确
			$("#loginForm").bootstrapValidator('validate');
	        if ($("#loginForm").data('bootstrapValidator').isValid()) {
	        	if (!isVerify) {
	        		resetFormValidator();
	        		layer.msg("请向右滑动完成验证", {icon: 7, time: 1000});
	        		return;
	        	}
	        	var formData = getFormData();
	        	var passwordLen = formData.password.length;
	        	
	        	//如果oldpassword跟存储的password一样时，说明没有更改就直接登录了
	        	var oldPassword = $("#oldpassword").val();
	        	if (oldPassword == LocalDataUtil.getItem("password")) {
	        		formData.password = LocalDataUtil.getItem("password");
	        	} else {
	        		var encrypt=new JSEncrypt();
	            	encrypt.setPublicKey(PUBLIC_KEY);
	            	formData.password = encrypt.encrypt(formData.password);
	        	}
	        	
	    		$.ajax({
	    			url: "/login/do-login",
	    			type: 'post',
	    			data: formData,
	    			dataType: 'json',
	    			success: function(data) {
	    				if (data.success) {
	    					
	    					//记住密码
	    					if ($("#rember-password").prop("checked")) {
	    						//往本地存入邮箱，密码、密码长度
	    						LocalDataUtil.setItem("email", formData.email);
	    						LocalDataUtil.setItem("password", formData.password);
	    						LocalDataUtil.setItem("passwordLen", passwordLen);
	    					} else {
	    						//移除本地数据
	    						LocalDataUtil.deleteItem("email");
	    						LocalDataUtil.deleteItem("password");
	    						LocalDataUtil.deleteItem("passwordLen");
	    					}
	    					window.location.href = "/";
	    				} else {
	    					layer.alert(data.message, {icon: 7});
	    					resetFormValidator();
	    				}
	    			},
	    			error: function() {
	    				resetFormValidator();
	    			}
	    		});
	        }
		});
		
		
		
		
		//----------------------绑定操作按钮事件 end---------------
		
		
		//----------------------初始化 begin------------------
		
		//验证码是否验证成功
		var isVerify = false;
		
		/**
		 * 回写本地的数据到页面上
		 * */
		function rewrite() {
	
			//回写email
			var email = LocalDataUtil.getItem("email");
			$("#email").val(email);
			
			//回写password、oldpassword
			var password = LocalDataUtil.getItem("password");
			if (password != null) {
				var len = parseInt(LocalDataUtil.getItem("passwordLen"));
				console.log(len);
				var placholder = "";
				for (var i = 0; i < len; i++) {
					placholder += "*";
				}
				$("#password").val(placholder);
				
				//记住密码
				$("#rember-password").prop("checked", true);
			}
			$("#oldpassword").val(password);
		}
		
		/**
		 * 初始化
		 * */
		function init() {
			//验证
			formValidator();
			
			//回写
			rewrite();
			
			//滑动验证码
			$("#verify-code").slideVerify({
				type: 1,
				vOffset: 5,
				barSize: {
					width: '100%',
					height: '38px'
				},
				success: function() {
					isVerify = true;
				},
				error: function() {
					isVerify = false;
				}
			});
			
			//监听password更改，则修改oldpassword=password
			$("#password").change(function(){
				$("#oldpassword").val($("#password").val());
			});
		}
		init();
		
		//----------------------初始化 end------------------
	
	
    });
});