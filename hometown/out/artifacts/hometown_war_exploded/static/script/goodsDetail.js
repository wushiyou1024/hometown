var vue = new Vue({
    el: "#content_tile",
    data: {

        goodsTitle: "",
        goodsContent: "",
        goodsprice: 0.0,
    },
    methods: {

        purchaseDetails: function (id) {
            axios({
                method: "post",
                url: "goods.do",
                params: {
                    operate: "getGoodsByid",
                    id: id
                },


            }).then(function (value) {
                window.location.href = "page.do?operate=page&page=user/detail.html"
            }).catch(function (resaon) {
            });

        }
    }
})
