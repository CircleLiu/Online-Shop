function success(text) {
    var textarea = document.getElementById('show-username');

    var data = JSON.parse(text);
    if (data.success) {
        textarea.innerHTML ="用户：" + data.detail.username;
        document.getElementsByClassName("username-container")[0].style.display="block"
        document.getElementsByClassName("account-container")[0].style.display="block"
        document.getElementsByClassName("cart-container")[0].style.display="block"

        document.getElementsByClassName("register-container")[0].style.display="none"
        document.getElementsByClassName("login-container")[0].style.display="none"
    }
}

function fail(code) {
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

function getUserInfo() {
    const token = document.cookie.split(";")[0];
    request.open("GET", "/api/user/info", true)
    request.setRequestHeader("token", token);
    request.send()
}

function removeFromCart(id) {
    localStorage.removeItem(id);
    let button = document.getElementById("button-" + id);
    button.innerHTML = "加入购物车"
    button.onclick = function () {addToCart(id)}
}

function added(id) {
    let button = document.getElementById("button-" + id);
    button.innerHTML = "已加入购物车"
    button.onclick = function () {removeFromCart(id)}
}

window.onload = function() {
    getUserInfo();

    for (let i = 1; i <= 8; ++i) {
        if (localStorage.getItem(i)) {
            this.added(i)
        }
    }
}

function addToCart(id) {
    localStorage.setItem(id, 1);
    added(id)
}