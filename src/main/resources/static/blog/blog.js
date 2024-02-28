var register = {
    "registerNameType": "phone",
    "return": {
        "ACCOUNT_EXIST": {
            "code": "1030",
            "message": "Register failed, the account is existed",
        },
        "NAME_EXIST": {
            "code": "1031",
            "message": "The account name is existed",
        },
        "EMAIL_EXIST": {
            "code": "1033",
            "message": "The email is existed",
        },
        "CAN_REGISTER": {
            "code": "1034",
            "message": "Can register",
        },
        "REGISTER_SUCCESS": {
            "code": "1035",
            "message": "Register success",
        },
        "REGISTER_FAIL": {
            "code": "1036",
            "message": "Register failed",
        },
    },
    "rsa": {
        "modulus": "",
        "exponent": "",
    },

    //url
    "requestUrl": {
        //register
        "registerSubmitUrl": "/user/register",
    },

    //ajax
    "request": {
        //submit
        "registerSubmit": function () {

            var sendData = {"name": "", "email": "", "password": ""};
            sendData.name = $("#register-name-input").val();
            sendData.password = $("#register-password-input").val();
            sendData.email = $("#register-email-input").val();

            var jsonData = JSON.stringify(sendData);
            console.log(jsonData);
            $.ajax({
                type: "post",
                url: register.requestUrl.registerSubmitUrl,
                contentType: "application/json;charset=utf-8",
                dataType: "json",
                //数据格式是json串,传进一个person
                data: jsonData,

                success: function (data, status) {
                    console.log(data.message);
                    if (data.code == register.return.REGISTER_SUCCESS.code) {
                        $("#register-table").hide();
                        $("#register-success-disp").show();
                    } else if ((data.code == register.return.ACCOUNT_EXIST.code)
                        || (data.code == register.return.EMAIL_EXIST.code)
                        || (data.code == register.return.REGISTER_FAIL.code)
                    ) {
                        $("#register-warn").show();
                        $("#register-warn").text(data.message);
                    }
                }
            })
        },
    },
    /**
     *check two times input password success
     * return: true:
     false:
     */
    "checkPasswordEquals": function () {
        var password = $("#register-password-input").val();
        var passwordAgain = $("#register-password-input-again").val();
        console.log("password.length = " + password.length);
        console.log("passpasswordAgainword.length = " + passwordAgain.length);
        if ((password.length == 0)
            || (passwordAgain.length == 0)) {
            $("#password-input-warn").show();
            $("#password-input-warn-disp").text("Please input password！");
            return false;
        }
        if (password != passwordAgain) {
            $("#password-input-warn").show();
            $("#password-input-warn-disp").text("The password is not same！");
            return false;
        } else {
            $("#password-input-warn").hide();
            console.log("Input password currently")
            return true;
        }

    },

    /**
     * check all input
     */
    "checkAllInput": function () {
        //检测密码是否一致
        if (register.checkPasswordEquals() == false) {
            return false;
        }
        console.log("+++all input success++++")
        return true;
    },


};

$(function () {

    /**
     * submit
     */
    $("#register-submit-btn").click(function () {
        //if(register.checkAllInput() == true)
        {
            register.request.registerSubmit();
        }

    });


    /**
     * 密码输入框字符改变时检测密码强度
     */
    $("#register-password-input").bind("input propertychang", function (event) {
        var password = $("#register-password-input").val();

        var strength = regex.checkPasswordStrength(password);
        console.log("当前密码 =  " + $("#password-strength").text("简单") + password + " strength = " + strength);

        if (strength == 0) {
            $("#password-strength").text("简单");
        } else if (strength == 1) {
            $("#password-strength").text("中等");
        } else if (strength == 2) {
            $("#password-strength").text("复杂");
        } else {
            $("#password-strength").text("");
        }


    });


});
