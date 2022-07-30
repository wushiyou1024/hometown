
function getOrder(orderId) {
    window.location.href = 'order.do?operate=getOrderDetail&orderId=' + orderId;
}

function updateOrderStatus(id,status)
{
    window.location.href = 'order.do?operate=updateOrderStatus&id=' + id+"&status="+status;
}
//6.26
function pay(id)
{

    window.location.href='order.do?operate=pay&id='+id;
}


window.onload=function () {

    var vue = new Vue({
        el: "#contentc",
        data: {
            content:"",
        },
        methods: {

            submit:function (){
                axios({
                    method: "post",
                    url: "order.do",
                    params: {
                        operate:'commentByGid',
                        content:vue.content
                    }
                }).then(function (value) {
                    alert("评论成功!")
                    window.location.href="order.do?operate=getOrderList";
                }).catch(function (resaon) {
                });
            },
        },

    });

}