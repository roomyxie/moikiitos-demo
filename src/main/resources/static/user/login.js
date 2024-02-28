var login = {
    //登录名称类型 phone / email
    "requestUrl": {
        "loginSubmitUrl": "/user/login",
        //获取 rsa modulus  exponent
        "requestModulusAndExponentUrl": "/user/key",
    },
    "rsa": {
        "modulus": "",
        "exponent": "",
    },
    "return": {
        "LOGIN_SUCCESS": {
            "code": "1000",
            "message": "login success",
        },
    },
    "request": {
        //login commit
        "loginSubmit": function () {

            var sendData = {"name": "", "password": ""};
            sendData.name = $("#login-name-input").val();
            sendData.password = $("#login-password-input").val();

            var jsonData = JSON.stringify(sendData);
            console.log(jsonData);
            $.ajax({
                type: "post",
                url: login.requestUrl.loginSubmitUrl,
                contentType: "application/json;charset=utf-8",
                //person data is a json, please input a person
                data: jsonData,
                dataType: "json",
                success: function (data, status) {
                    console.log(data.message);
                    if (data.code === login.return.LOGIN_SUCCESS.code) {
                        $("#login-table").hide();
                        $("#login-success-disp").show();
                        $("#login-warn").hide();
                        window.location.href = "../blog/blog.html";
                    } else {
                        $("#login-table").show();
                        $("#login-success-disp").hide();
                        $("#login-warn").show();
                        $("#login-warn").text(data.message);
                        window.location.href = "../blog/blog.html";
                    }
                }


            });


        },
    },
    /**
     *检测密码输入
     * return: true: 正常
     false: 错误
     */
    "checkPassword": function () {
        var password = $("#login-password-input").val();

        console.log("password.length = " + password.length);

        if (password.length == 0) {
            $("#login-password-input-warn").show();
            $("#login-password-input-warn-disp").text("please input the right password！");
            return false;
        }
        $("#login-password-input-warn").hide();
        return true;


    },

    /**
     * check all input
     */
    "checkAllInput": function () {
        //check password right
        if (login.checkPassword() == false) {
            return false;
        }
        console.log("+++all input right++++")
        return true;
    },
};

$(function () {
    /**
     * 登录请求提交
     */
    $("#login-submit-btn").click(function () {
        console.log("login-submit-btn click")
        if (login.checkAllInput() == true) {
            login.request.loginSubmit();
        }
    });
});