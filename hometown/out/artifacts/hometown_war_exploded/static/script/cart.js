function editCart(cartItemId, buyCount) {
    window.location.href = 'cart.do?operate=editCart&cartItemId=' + cartItemId + "&buyCount=" + buyCount;
}
function deleteCart(cartItemId) {
    window.location.href = 'cart.do?operate=deleteCart&cartItemId=' + cartItemId;
}
window.onload = function () {
    console.log("123");
    var vue = new Vue({
        el: "#cart_div",
        data: {
            cart:{}
        },
        methods: {
            getCart: function (page) {
                axios({
                    method: "post",
                    url: "cart.do",
                    params: {
                        operate: 'cartInfo'
                    }
                }).then(function (value) {
                    var cart=value.data;
                    vue.cart=cart;
                }).catch(function (resaon) {
                });
            }
        },
        mounted: function () {
            this.getCart();
        }

    });

}