$(document).ready(function() {
    layui.use(['layer'], function() {
		var layer = layui.layer;
		
		//----------------------表单验证 begin-----------------
		/**
		 * 添加表单验证
		 * */
		function formValidator() {
			//初始化表单验证器
			$('#registerForm').bootstrapValidator({
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
		            userName: {
		                message: '姓名不合法',
		                validators: {
		                    notEmpty: {
		                        message: '姓名不能为空'
		                    },
		                    stringLength: {
		                        min: 1,
		                        max: 25,
		                        message: '姓名最多为25个字符'
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
		            },
		            confirmPassword: {
		                message: '确认密码不合法',
		                validators: {
		                	notEmpty: {
		                        message: '确认密码不能为空'
		                    },
		                    identical: {
		                        field: 'password',
		                        message: '两次密码必须一致'
		                    }
		                }
		            },
		            code: {
		                validators: {
		                	notEmpty: {
		                        message: '邮箱验证码不能为空'
		                    },
		                    stringLength: {
		                        min: 6,
		                        max: 6,
		                        message: '邮箱验证码必须位6位'
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
			$("#registerForm").data('bootstrapValidator').destroy();
			$('#registerForm').data('bootstrapValidator',null);
			formValidator();
		}
		//----------------------表单验证 end-----------------
		
		/**
		 * 获取表单填写的数据
		 * @return	表单数据
		 * */
		function getFormData() {
			return $("#registerForm").serializeJson();
		}
		
		//----------------------绑定操作按钮事件 begin---------------
		//定时器ID
		var timeId = null;
		
		/**
		 * 设置按钮可用剩余时间
		 * */
		function setRemainTime() {
			var second = parseInt($("#getCodeBtn").html().match(/(\S*)秒后发送/)[1]);
			if (second > 1) {
				second -= 1;
				$("#getCodeBtn").html(second + "秒后发送");
			} else {
				clearInterval(timeId);
				$("#getCodeBtn").removeAttr("disabled");
				$("#getCodeBtn").html("获取验证码");
			}
		}
		
		//获取验证码
		$("#getCodeBtn").click(function() {
			
			//验证邮箱
			$("#registerForm").bootstrapValidator();
		    var bv = $("#registerForm").data("bootstrapValidator");
			var isValid = bv.validateField("email");
			
			if ($("#email").siblings("small[data-bv-result='INVALID']").length > 0) {
				layer.msg("邮箱不合法", {icon: 7, time: 1000});
				return;
			}
			
			//将获取验证码按钮设置为不可用
			$("#getCodeBtn").attr("disabled", "disabled");
			$("#getCodeBtn").html("40秒后发送");
			//启动定时（40秒，每秒执行一次）
			timeId = setInterval(setRemainTime,1000);
			
			var formData = getFormData();
			$.ajax({
				url: "/register/sendemail",
				type: 'post',
				data: formData,
				dataType: 'json',
				success: function(data) {
					if (data.success) {
						layer.alert("邮件已发送自：" + formData.email +", 请注意接收!", {icon: 1});
					} else {
						layer.alert("发送邮件失败，请稍后重试！", {icon: 2});
						clearInterval(timeId);
					}
				},
				error: function() {
					layer.alert("网络超时", {icon: 5});
				}
			});
		});
		
		//保存
		$("#registerBtn").click(function(){
			
			//验证表单是否填写正确
			$("#registerForm").bootstrapValidator('validate');
	        if ($("#registerForm").data('bootstrapValidator').isValid()) {
	        	if (!$("#rember-password").prop('checked')) {
	    			layer.msg("请仔细阅读使用条款并同意", {icon: 7, time: 1000});
	    			resetFormValidator();
	    			return;
	    		} 
	        	var formData = getFormData();
	        	formData.confirmPassword = "";
	        	
	        	var encrypt=new JSEncrypt();
	        	encrypt.setPublicKey(PUBLIC_KEY);
	        	formData.password = encrypt.encrypt(formData.password);
	        	
	    		$.ajax({
	    			url: "/register/registeruser",
	    			type: 'post',
	    			data: JSON.stringify(formData),
	    			dataType: 'json',
	    			contentType: 'application/json',
	    			success: function(data) {
	    				if (data.success) {
	    					layer.alert("用户注册成功，请使用邮箱登录系统!", {icon: 1}, function() {
	    						window.location.href = "/";
	    					});
	    					
	    				} else {
	    					layer.alert("用户注册失败 "+ data.message +"!", {icon: 7});
	    					resetFormValidator();
	    				}
	    			},
	    			error: function() {
	    				resetFormValidator();
	    				layer.alert("网络超时", {icon: 5});
	    			}
	    		});
	        }
		});
		
		
		
		
		//----------------------绑定操作按钮事件 end---------------
		
		
		//----------------------初始化 begin------------------
		/**
		 * 初始化
		 * */
		function init() {
			//验证
			formValidator();
		}
		init();
		
		//----------------------初始化 end------------------
    });
});