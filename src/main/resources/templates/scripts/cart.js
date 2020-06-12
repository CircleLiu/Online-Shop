function calcTotal() {
    let realtotal = document.getElementsByClassName("realtotal")[0]
    let subs = document.getElementsByClassName("pricesubtotal")
    let total = 0
    for (let i = 0; i < subs.length; ++i) {
        total += Number(subs[i].innerHTML)
    }
    realtotal.innerHTML = total.toFixed(2)
}

function recalc() {
    let row = this.parentNode.parentNode
    let id = row.getAttribute("item-id")
    
    let price = Number(row.getElementsByClassName("price")[0].innerHTML)
    let amount = Number(row.getElementsByTagName("input")[0].value)
    let pricesubtotal = row.getElementsByClassName("pricesubtotal")[0]
    pricesubtotal.innerHTML = (price * amount).toFixed(2)
    localStorage.setItem(id, amount)
    
    calcTotal()
}

function removeItem() {
    let item = this.parentNode
    let id = item.getAttribute("item-id")
    item.remove()
    localStorage.removeItem(id)

    calcTotal()
}

function appendTable(item, count) {
    let table = document.getElementById("cart")
    let row = document.createElement("tr")
    row.setAttribute("class", "p")
    row.setAttribute("item-id", item.id)
    let image = document.createElement("td")
    image.setAttribute("class", "image")
    let img = document.createAttribute("img")
    img.src = item.img
    image.innerHTML = "<img src='" + item.img + "' />"
    let name = document.createElement("td")
    name.setAttribute("class","name")
    name.innerHTML = item.name
    let price = document.createElement("td")
    price.setAttribute("class", "price")
    price.innerHTML = item.price
    let amount = document.createElement("td")
    amount.setAttribute("class", "amount")
    let amountInput = document.createElement("input")
    amountInput.setAttribute("type", "number")
    amountInput.setAttribute("value", count)
    amountInput.setAttribute("min", "1")
    amountInput.oninput = recalc
    amount.appendChild(amountInput)
    let pricesubtotal = document.createElement("td")
    pricesubtotal.setAttribute("class", "pricesubtotal")
    pricesubtotal.innerHTML = (Number(item.price) * Number(count)).toFixed(2)
    let remove = document.createElement("td")
    remove.setAttribute("class", "remove")
    remove.innerHTML = "<div>&times</div>"
    remove.onclick = removeItem
    row.appendChild(image)
    row.appendChild(name)
    row.appendChild(price)
    row.appendChild(amount)
    row.appendChild(pricesubtotal)
    row.appendChild(remove)
    table.appendChild(row)
}

function success(text) {
    var textarea = document.getElementById('show-username');

    var data = JSON.parse(text);
    if (data.success) {
        var item = data.detail
        var count = localStorage.getItem(item.id)
        appendTable(item, count)
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

function getItemInfo(id) {
    let url = "/api/item/id?id=" + id
    request.open("GET", url, false)
    request.setRequestHeader("token", token);
    request.send()
}

window.onload = function() {
    for (let i = 1; i <= 8; ++i) {
        if (localStorage.getItem(i)) {
            this.getItemInfo(i)
        }
    }

    calcTotal()
}

function checkoutSuccess(text) {
    var textarea = document.getElementById('msg');

    var data = JSON.parse(text);
    textarea.innerHTML = data.msg;
    if (data.success) {
        for (let i = 1; i <= 8; ++i) {
            localStorage.removeItem(i)
        }
        setTimeout("javascript:location.href='index.html'", 1000)
    }
}

function checkout() {
    let total = document.getElementById("realtotal")
    let data = {
        total: total.innerHTML
    }
    let datastr = JSON.stringify(data)

    var request = new XMLHttpRequest();

    request.onreadystatechange = function () { 
        if (request.readyState === 4) { 
            if (request.status === 200) {
                return checkoutSuccess(request.responseText);
            } else {
                return fail(request.status);
            }
        } else {
        }
    }

    request.open("POST", "/api/order/create", false)
    request.setRequestHeader("token", token);
    request.send(datastr)
}