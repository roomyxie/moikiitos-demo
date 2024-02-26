var login = {
    //登录名称类型 phone / email
    "loginNameType": "phone",
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

            var sendData = {"loginName": "", "loginNameType": "", "loginPassword": "", "imgVerificationCode": ""};
            sendData.loginName = $("#login-name-input").val();
            sendData.loginNameType = login.loginNameType;
            sendData.verificationCode = $("#login-verification-code-input").val();
            sendData.loginPassword = $("#login-password-input").val();
            sendData.imgVerificationCode = $("#login-img-verification-code-input").val();

            //加密
            if ((login.rsa.exponent == null)
                || (login.rsa.modulus == null)) {
                login.request.requestModulusAndExponent();
                return;
            }
            console.log("encrypt......");
            setMaxDigits(130);
            var publicKey = new RSAKeyPair(login.rsa.exponent, "", login.rsa.modulus);
            var password = encryptedString(publicKey, encodeURIComponent(sendData.loginPassword));
            console.log("encrypt：" + password);
            sendData.loginPassword = password;

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
                    if (data.code == login.return.LOGIN_SUCCESS.code) {
                        $("#login-table").hide();
                        $("#login-success-disp").show();
                        $("#login-warn").hide();
                        var tokenVal = data.data;
                        console.log("token = " + tokenVal);
                        tokenUtil.setToken(tokenVal);

                        console.log("token = " + tokenUtil.getToken());

                    } else {
                        $("#login-table").show();
                        $("#login-success-disp").hide();
                        $("#login-warn").show();
                        $("#login-warn").text(data.message);
                    }
                }


            });


        },
        //获取 rsa modulus  exponent
        "requestModulusAndExponent": function () {
            $.ajax({
                type: "get",
                url: login.requestUrl.requestModulusAndExponentUrl,
                contentType: "application/json;charset=utf-8",
                data: {
                    name: $("#login-name-input").val()
                },
                async: false,
                dataType: "json",
                success: function (data, status) {


                    console.log("data = " + data.message + "  status = " + status);
                    console.log("modulus  = " + data.data.modulus);
                    console.log("exponent  = " + data.data.exponent);

                    login.rsa.modulus = data.data.modulus;
                    login.rsa.exponent = data.data.exponent;

                    login.request.loginSubmit();

                }


            })
        },
    },
    /**
     * check phonenumber or email error
     * return: true:
     false:
     */
    "checkloginName": function () {

        var loginName = $("#login-name-input").val();

        console.log("loginNameType = " + login["loginNameType"]);

        if (login["loginNameType"] == "phone") {
            // not the phonr num
            console.log("isPhone = " + regex.isPhone(loginName));

            if (regex.isPhone(loginName) == false) {
                $("#name-input-warn").show();
                $("#name-input-warn-disp").text("please input the right phone number！");
                console.log("please input the right phone number！！");
                return false;
            } else {
                $("#name-input-warn").hide();
            }
        } else if (login["loginNameType"] == "email") {
            console.log("isEmail = " + regex.isEmail(loginName));
            // not the email
            if (regex.isEmail(loginName) == false) {
                $("#name-input-warn").show();
                $("#name-input-warn-disp").text("please input the right email！");
                console.log("please input the right email！！");
                return false;
            } else {
                $("#name-input-warn").hide();
            }
        }

        return true;
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
        //check phone right
        if (login.checkloginName() == false) {
            return false;
        }
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
     * choice login type
     */
    $("#login-name-select").click(function () {
        console.log("login-name-select  click");
        if ($("#login-name-lable").text() == "phone") {
            $("#login-name-select").text("phone login");
            $("#login-name-lable").text("email");
            login["login-name-type"] = "email";
            $("#login-name-input").attr("placeholder", "please input the email");
        } else if ($("#login-name-lable").text() == "email") {
            $("#login-name-select").text("email login");
            $("#login-name-lable").text("phone");
            login["login-name-type"] = "phone";
            $("#login-name-input").attr("placeholder", "please input phone number");
        }
    });
    /**
     * 登录请求提交
     */
    $("#login-submit-btn").click(function () {
        console.log("login-submit-btn click")
        if (login.checkAllInput() == true) {

            login.request.requestModulusAndExponent();

        }
    });
});