$(document).ready(function() {
    layui.use(['form', 'layer', 'upload'], function() {

        var $ = layui.jquery,
            form = layui.form,
            layer = layui.layer,
            upload = layui.upload;
        
     // ---------------------------表单核心-----------------------
        var uploadSuccess = false;
        var layerIndex = -1;

        //执行实例
        var uploadInst = upload.render({
            elem: '#project_file_btn' //绑定元素
            ,url: '/project/manager/upload' //上传接口
            ,auto: false
            ,bindAction: '#upload_btn'
            ,before: function() {
                layerIndex = layer.load();
            }
            ,done: function(res){
                layer.close(layerIndex);
                if (res.success) {
                    if (res.data.projectImage == undefined || res.data.projectImage == "") {
                        layer.msg("图片上传失败", {icon: 5});
                    } else {
                        $("#projectImage").val(res.data.projectImage);
                        uploadSuccess = true;
                        $("#save_project").click();
                    }

                } else {
                    layer.msg(res.message, {icon: 5});
                }

            }
            ,error: function(){
                //请求异常回调
                layer.close(layerIndex);
                layer.msg("上传项目图片失败", {icon: 5});
            }
        });

        $("#upload_btn").click(function() {
           return false;
        });

        form.on('submit(update-project-filter)', function(data){
        	if (!uploadSuccess) {
                if (data.field.file != undefined && data.field.file != "") {
                    $("#upload_btn").click();
                    return false;
                }
            }

            $.ajax({
               url: '/project/manager/do-update',
               type: 'PUT',
               data: data.field,
               dataType: 'json',
               success: function(data) {
                   if (data.success) {
                       layer.msg("保存成功", {icon: 6});
                       layerUtil.setData(true);
                       layerUtil.closeFrameLayer();
                   } else {
                       layer.msg(data.message, {icon: 5});
                   }
               },
               error: function() {
                   layer.alert("网络超时", {icon: 5});
               }
            });

            return false;
        });

    });
})
