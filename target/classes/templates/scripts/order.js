function formatDate(timestamp) {
    var now = new Date(timestamp)
    var year = now.getFullYear();
    var month = now.getMonth() + 1;
    var date = now.getDate();
    var hour = now.getHours();
    var minute = now.getMinutes();
    var second = now.getSeconds();
    return year + "-" + month + "-" + date + " " + hour + ":" + minute + ":" + second;
}

function appendTable(order) {
    let table = document.getElementById("cart")
    let row = document.createElement("tr")
    let orderId = document.createElement("td")
    orderId.innerHTML = order.orderId
    let time = document.createElement("td")
    time.innerHTML = formatDate(order.time)
    let total = document.createElement("td")
    total.innerHTML = "￥" + order.total
    row.appendChild(orderId)
    row.appendChild(time)
    row.appendChild(total)
    table.appendChild(row)
}

function success(text) {
    var textarea = document.getElementById('show-username');

    var data = JSON.parse(text);
    if (data.success) {
        let orders = data.detail
        for (let index = 0; index < orders.length; index++) {
            appendTable(orders[index])
        }
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

const token = document.cookie.split(";")[0];

function getOrders(id) {
    let url = "/api/order/get"
    request.open("GET", url, true)
    request.setRequestHeader("token", token);
    request.send()
}

window.onload = getOrders