window.onload = function () {


    var vue = new Vue({
        el: "#goods",

        data: {
            goods: {},
            keyword: "",
        },
        currentId:"1",//获取被选中的value值， 默认选中的是1
        methods: {

            change: function (event) {
                var _this = this
                this.currentId = event.target.value; //获取option对应的value值
                axios({
                    method: "post",
                    url: "goods.do",
                    params: {
                        operate: 'getGoodsById',
                        keyword: vue.currentId,
                        oper:'search'
                    }
                }).then(function (value) {
                    var lost = value.data;
                    vue.goods = lost;
                }).catch(function (resaon) {
                });
            },
            page: function (pageNo) {
                axios({
                    method: "post",
                    url: "goods.do",
                    params: {
                        operate: 'getGoodsById',
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
                        operate: 'getGoodsById'
                    }
                }).then(function (value) {
                    var lost = value.data;
                    vue.goods = lost;
                }).catch(function (resaon) {
                });
            },
            updateById: function (id) {

                axios({
                    method: "post",
                    url: "goods.do",
                    params: {
                        operate: 'updateGoodsById',
                        id: id
                    }
                }).then(function (value) {
                    if (value.data == '1')
                        window.location.href = "page.do?operate=page&page=mechant/updateGoods.html";
                }).catch(function (resaon) {
                });
            },
            deleteById: function (id) {
                if (confirm("是否删除")) {
                    axios({
                        method: "post",
                        url: "goods.do",
                        params: {
                            operate: 'deleteById',
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