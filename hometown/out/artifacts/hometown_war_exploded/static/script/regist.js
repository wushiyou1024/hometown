function $(id) {
    //BOM方式
    // document.forms[0].uname
    //DOM方式
    return document.getElementById(id);

}

function preRegist() {
    //用户名不能为空 而且是6-16位数字和字母组成
    var unameReg = /[0-9a-zA-z]{6,16}/;
    var unameTxt = $("unameTxt");
    var uname = unameTxt.value;
    var unameSpan = $("unameSpan");
    if (!unameReg.test(uname)) {
        unameSpan.style.visibility = "visible";
        return false;
    } else {
        unameSpan.style.visibility = "hidden";

    }


    //密码的长度至少为8位
    var pwdTxt = $("pwdTxt");
    var pwd = pwdTxt.value;
    var pwdReg = /[\w]{8,}/;
    var pwdSpan = $("pwdSpan");
    if (!pwdReg.test(pwd)) {
        pwdSpan.style.visibility = "visible";
        return false;
    } else {
        pwdSpan.style.visibility = "hidden";
    }
    //两次密码不一致的情况
    if (!($("pwdTxt2").value == $("pwdTxt").value)) {
        $("pwdSpan2").style.visibility = "visible";
        return false;
    } else {
        $("pwdSpan2").style.visibility = "hidden";

    }

    //请输入正确的邮箱格式
    var emailTxt = $("emailTxt").value;
    var emailSpan = $("emailSpan");
    var emailReg = /^[a-zA-Z0-9_\.-]+@([a-zA-Z0-9-]+[\.]{1})+[a-zA-Z]+$/;
    if (!emailReg.test(emailTxt)) {
        emailSpan.style.visibility = "visible";
        return false;
    } else {
        emailSpan.style.visibility = "hidden";

    }
    return true;
}

//如果想要发布异步请求 就是ajax 需要一个关键的对象
var xmlHttpRequest;

function createXMLHttpRequest() {
    if (window.XMLHttpRequest) {
        //符合dom2标准的浏览器xmlHttpRequest创建方式
        xmlHttpRequest = new XMLHttpRequest();
    } else if (window.ActiveXObject)//ie浏览器
    {
        try {
            xmlHttpRequest = new ActiveXObject("Microsoft.XMLHTTP");
        } catch (e) {
            xmlHttpRequest = new ActiveXObject("Msxml2.XMLHTTP");
        }

    }
}

function ckUname(uname) {
    createXMLHttpRequest();
    xmlHttpRequest.open("GET", "user.do?operate=ckUname&uname=" + uname, true);
    //设置回调参数
    xmlHttpRequest.onreadystatechange = ckUnameCB;
    //发送请求
    xmlHttpRequest.send();
}

function ckUnameCB() {
    if (xmlHttpRequest.readyState == 4 && xmlHttpRequest.status == 200) {
        //*xmlHttpRequest.responseText表示服务器端响应给我的内容
        var responseText = xmlHttpRequest.responseText;
     if (responseText=="{'uname':'1'}")
     {
         alert("用户名已经被注册了");
     }
    }
}