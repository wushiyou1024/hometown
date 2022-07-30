window.onload = function () {


    var vue = new Vue({
        el: "#commented",

        data: {
            goods: {},

        },

        methods: {


            page: function (pageNo) {
                axios({
                    method: "post",
                    url: "merchant.do",
                    params: {
                        operate: 'getCommentedById',
                        pageNo: pageNo
                    }
                }).then(function (value) {
                    var lost = value.data;
                    vue.goods = lost;
                }).catch(function (resaon) {
                });
            },
            getCommentedById: function () {
                axios({
                    method: "post",
                    url: "merchant.do",
                    params: {
                        operate: 'getCommentedById'
                    }
                }).then(function (value) {
                    var lost = value.data;
                    vue.goods = lost;
                }).catch(function (resaon) {
                });
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
            this.getCommentedById();
        }

    });

}