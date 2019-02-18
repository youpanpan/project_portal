$(document).ready(function() {
    layui.use(['form', 'layer', 'carousel'], function() {
    	var carousel = layui.carousel;
    	  //建造实例
		var options = {
            elem: '#project_carousel'
            ,width: '100%' //设置容器宽度
            ,height: '428px'
            ,arrow: 'always' //始终显示箭头
            ,indicator: 'none'
            ,autoplay: false
        };
		var oldWidth = $(window).width();
	  	if (oldWidth < 1000) {
	  		options.height = "642px";
	  	}
        var carouselObj = carousel.render(options);
	  	var islogin = $("#islogin").val();
	  	
	  	
	  	$(window).resize(function() {
	  		var width = $(window).width();
	  		if (oldWidth > 1000 && width < 1000) {
	  			options.height = "642px";
	  			carouselObj.reload(options);
	  			console.log(options);
	  		} else if (oldWidth < 1000 && width > 1000) {
	  			options.height = "428px";
	  			carouselObj.reload(options);
	  			console.log(options);
	  		}
	  		oldWidth = width;
	  	});

	  	/**
         * 激活当前皮肤
         * */
	  	function activeCurrentSkin() {
            var skin = LocalDataUtil.getItem(SKIN);
            if (skin == undefined || skin == null) {
                skin = "default";
                LocalDataUtil.setItem(SKIN, skin);
            }
            $("body").append('<link rel="stylesheet" href="/static/css/skins/'+ skin +'.css" id="skin_link">');

            var currentSkin = LocalDataUtil.getItem(SKIN);
            var inputObj = $("input[name='gd-style'][value='"+ currentSkin +"']");
            if (inputObj.length == 1) {
                $(inputObj).prop("checked", true);
                $(inputObj).parents(".box-content").addClass("active");
            }

        }
        activeCurrentSkin();

	  	var layerIndex = -1;
	  	/**
         * 加载项目
         * */
		function loadProject() {
            layerIndex = layer.load(2);
            $.ajax({
              url: "/project/manager/list/project",
              type: "GET",
              dataType: "json",
              success: function(data) {
                  if (data.success) {
                      var projects = data.data;
                      var html = '';
                      for (var i = 0; i < projects.length; i++) {
                          if (i % 6 == 0) {
                              html += '<div class="layui-row">';
                          }

                          html += '<a class="layui-col-xs6 layui-col-sm6 layui-col-md4 project-item" data-id="'+ projects[i].id +'"' +
                              'style="background-color: '+ projects[i].bgColor
                              +';" href="'+ projects[i].projectUrl +'" target="_blank">';
                          html += '	<img src="/project/manager/download/'+ projects[i].id +'" class="project-image"/>';
                          html += '	<span class="project-name">'+ projects[i].projectName +'</span>';
                          html += '</a>';

                          if (i % 6 == 5) {
                              html += '</div>';
                          }
                      }

                      if (islogin == "true") {
                          if (i % 6 == 0) {
                              html += '<div class="layui-row">';
                          }

                          html += '<a class="layui-col-xs6 layui-col-sm6 layui-col-md4 add-project-item">';
                          html += '	<img class="add-project-icon" src="/static/images/index/add.png"/>';
                          html += '</a>';

                          if (i % 6 == 0) {
                              html += '</div>';
                          }

                          // 初始菜单上下文
                          initMenuContext();
                      }

                      $("#project_list").html(html);
                      carouselObj.reload(options);

                  } else {
                      layer.msg(data.message, {icon: 5});
                  }

                  layer.close(layerIndex);
              },
              error : function() {
                  layer.close(layerIndex);
                  layer.alert("网络超时", {icon: 5});
              }
            });
		}
        loadProject();

        /**
         * 初始化鼠标右键菜单
         * */
        function initMenuContext() {
            var menu=new BootstrapMenu('.project-item',{
                fetchElementData:function($nodeElem){
                    var projectId = $($nodeElem[0]).attr("data-id");
                    return {id: projectId};
                },

                actionsGroups: [  //给右键菜单的选项加一个分组，分割线
                    ['viewUrl'],
                    ['editUrl'],
                    ['deleteUrl']
                ],
                //自定义右键菜单的功能
                actions: {
                    viewUrl: {
                        name: '查看',
                        iconClass: 'fa-eye',
                        onClick: function(data) {   //修改右击事件
                            disableScroll();
                            layer.open({
                                type: 2,
                                title: '查看项目',
                                content: '/project/manager/detail-open/'+ data.id,
                                area: ['700px', '600px'],
                                shadeClose: true,
                                end: function() {
                                    enableScroll();
                                }
                            });
                        },
                        isEnabled: function(data) {
                            return true;
                        }
                    },
                    editUrl: {
                        name: '修改',
                        iconClass: 'fa-edit',
                        onClick: function(data) {   //修改右击事件
                            disableScroll();
                            layer.open({
                                type: 2,
                                title: '修改项目',
                                content: '/project/manager/update-open/'+ data.id,
                                area: ['700px', '640px'],
                                end: function() {
                                    enableScroll();
                                    var data = layerUtil.getData();
                                    if (data) {
                                        loadProject()
                                    }
                                }
                            });
                        },
                        isEnabled: function(data) {
                            return true;
                        }
                    },

                    deleteUrl: {
                        name: '删除',
                        iconClass: 'fa-trash',
                        onClick: function(data) {  //删除右击事件
                            layer.confirm("确定删除？", {icon: 3}, function(index) {
                                $.ajax({
                                    url: "/project/manager/delete/" + data.id,
                                    type: "DELETE",
                                    dataType: "json",
                                    success: function(data) {
                                        if (data.success) {
                                            layer.msg("删除成功", {icon: 6});
                                            loadProject();
                                        } else {
                                            layer.msg(data.message, {icon: 5});
                                        }
                                    },
                                    error: function() {
                                        parent.layer.alert("网络超时", {icon: 5});
                                    }
                                });
                            });
                        },
                        isEnabled: function(data) {
                            return true;
                        }
                    }
                }
            });
        }

        // 新增项目
		$("#project_list").delegate(".add-project-item", "click", function() {
            disableScroll();

            layer.open({
                type: 2,
                title: '新增项目',
                content: '/project/manager/add-open',
                area: ['700px', '600px'],
                shadeClose: true,
                end: function() {
                    enableScroll();
                    var data = layerUtil.getData();
                    if (data != null) {
                        loadProject();
                    }
                }
            });
		});

        // 基本资料
        $(".content-container").delegate(".user-basic-info", "click", function() {
            disableScroll();
            var userId = $("#userId").val();

            layer.open({
                type: 2,
                title: '基本资料',
                content: '/user/manager/update-open/' + userId,
                area: ['600px', '360px'],
                shadeClose: true,
                end: function() {
                    enableScroll();
                    var data = layerUtil.getData();
                    if (data != null) {
                        window.location.reload();
                    }
                }
            });
        });

        // 修改密码
        $(".content-container").delegate(".update-password", "click", function() {
            disableScroll();

            layer.open({
                type: 2,
                title: '修改密码',
                content: '/user/manager/update-password',
                area: ['600px', '320px'],
                shadeClose: true,
                end: function() {
                    enableScroll();
                }
            });
        });

        // 皮肤选择
        $("#container_style").delegate(".box-content img,.box-content p", "click", function() {

            // 选中当前单选框并激活当前皮肤
            var inputObj = $(this).parents(".box-content").find("input[name='gd-style']");
            $(inputObj).prop("checked", true);
            $("#container_style").find(".box-content").each(function() {
                $(this).removeClass("active");
            });
            $(this).parents(".box-content").addClass("active");

            // 加载皮肤
            var skin = $(inputObj).val();
            LocalDataUtil.setItem(SKIN, skin);
            $("#skin_link").remove();
            $("body").append('<link rel="stylesheet" href="/static/css/skins/'+ skin +'.css" id="skin_link">');
        });
    	  
    });
})
