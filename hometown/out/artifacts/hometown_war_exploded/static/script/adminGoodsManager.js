window.onload = function () {


    var vue = new Vue({
        el: "#goods",

        data: {
            goods: {},
            keyword: "",
        },

        methods: {

            page: function (pageNo) {
                axios({
                    method: "post",
                    url: "goods.do",
                    params: {
                        operate: 'getAllGoods',
                        pageNo: pageNo
                    }
                }).then(function (value) {
                    var lost = value.data;
                    vue.goods = lost;
                }).catch(function (resaon) {
                });
            },
            getGoodsById: function () {
                axios({
                    method: "post",
                    url: "goods.do",
                    params: {
                        operate: 'getAllGoods'
                    }
                }).then(function (value) {
                    var lost = value.data;
                    vue.goods = lost;
                }).catch(function (resaon) {
                });
            },
            deleteById: function (id) {
                if (confirm("是否删除")) {
                    axios({
                        method: "post",
                        url: "goods.do",
                        params: {
                            operate: 'adminDeleteById',
                            id: id
                        }
                    }).then(function (value) {
                        var lost = value.data;
                        vue.goods = lost;
                    }).catch(function (resaon) {
                    });
                }
            },
            dateFormat: function (time) {
                var year = time.date.year;
                var month = time.date.month;
                var day = time.date.day;
                var hours = time.time.hour;
                var minutes = time.time.minute;
                var seconds = time.time.second;
                return year + "-" + month + "-" + day + " " + hours + ":" + minutes + ":" + seconds;
            }
        },
        mounted: function () {
            this.getGoodsById();
        }

    });

}