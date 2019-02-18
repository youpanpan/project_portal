$(document).ready(function() {
    layui.use(['form', 'layer'], function() {

        var $ = layui.jquery,
            form = layui.form,
            layer = layui.layer;

        // ---------------------------表单核心-----------------------

        form.on('submit(update-user-filter)', function(data){

            if (data.field.confirmPassword != data.field.newPassword) {
                layer.msg("确认密码和新密码不一样", {icon: 5});
                return false;
            }

            if (data.field.newPassword.length < 6 || data.field.newPassword.length > 18 ) {
                layer.msg("密码长度必须在6到18位之间", {icon: 5});
                return false;
            }

            var encrypt=new JSEncrypt();
            encrypt.setPublicKey(PUBLIC_KEY);
            var oldPassword = encrypt.encrypt(data.field.oldPassword);
            var newPassword = encrypt.encrypt(data.field.newPassword);

            $.ajax({
                url: '/user/manager/do-update-password',
                type: 'PUT',
                data: {oldPassword: oldPassword, newPassword: newPassword},
                dataType: 'json',
                success: function(data) {
                    if (data.success) {
                        layer.msg("修改成功", {icon: 6});
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
