window.onload = function () {


    var vue = new Vue({
        el: "#commented",

        data: {
            goods: {},
            id:{},
            reply:"请输入新的单号",

        },

        methods: {
            clearSort:function (){
                vue.id='',
                    vue.reply='请输入回复内容';
                document.getElementById('light').style.display='none';document.getElementById('fade').style.display='none'
            },

            popReply: function (id) {
                vue.id=id;
                document.getElementById('light').style.display = 'block';
                document.getElementById('fade').style.display = 'block';
            },
            submit: function () {
                axios({
                    method: "post",
                    url: "merchant.do",
                    params: {
                        operate: 'updateExpressNoByAdmin',
                        id: vue.id,
                        reply: vue.reply
                    }
                }).then(function (value) {
                    var lost = value.data;
                    vue.goods = lost;

                    document.getElementById('light').style.display = 'none';
                    document.getElementById('fade').style.display = 'none';
                    vue.clearSort();
                }).catch(function (resaon) {
                });
            },
            page: function (pageNo) {
                axios({
                    method: "post",
                    url: "merchant.do",
                    params: {
                        operate: 'getOrderedById',
                        pageNo: pageNo
                    }
                }).then(function (value) {
                    var lost = value.data;
                    vue.goods = lost;
                }).catch(function (resaon) {
                });
            },
            getOrderedAll: function () {
                axios({
                    method: "post",
                    url: "merchant.do",
                    params: {
                        operate: 'getOrderItem'
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
            this.getOrderedAll();
        }

    });

}