function success(text) {
    var textarea = document.getElementById('response-text');

    var data = JSON.parse(text);
    textarea.innerHTML = data.msg;
    if (data.success) {
        setTimeout("javascript:location.href='index.html'", 1000)
    }
}

function fail(code) {
    var textarea = document.getElementById('response-text');
    textarea.innerHTML = 'Error code: ' + code;
}

var request = new XMLHttpRequest(); // 新建XMLHttpRequest对象

request.onreadystatechange = function () { // 状态发生变化时，函数被回调
    if (request.readyState === 4) { // 成功完成
        // 判断响应结果:
        if (request.status === 200) {
            // 成功，通过responseText拿到响应的文本:
            return success(request.responseText);
        } else {
            // 失败，根据响应码判断失败原因:
            return fail(request.status);
        }
    } else {
        // HTTP请求还在继续...
    }
}

function register() {
    let username = document.getElementsByName("username")[0].value
    let password = document.getElementsByName("password")[0].value
    let confirm = document.getElementsByName("confirm")[0].value

    let textarea = document.getElementById('response-text');
    if (password === confirm) {
        let data = {
            username: username,
            password: password
        }
        
        let datastr = JSON.stringify(data)
        request.open("POST", "api/user/regist", true)
        request.setRequestHeader("Content-Type", "application/json");
        request.send(datastr)
    } else {
        textarea.innerHTML = "两次密码输入不一致"
    }
}