window.onload = function () {


    var vue = new Vue({
        el: "#commented",

        data: {
            goods: {},
            id:{},
            reply:"请输入单号"
        },

        methods: {
            clearSort:function (){
                vue.id='',
                    vue.reply='请输入回复内容';
                document.getElementById('light').style.display='none';document.getElementById('fade').style.display='none'
            },
            submit: function () {
                axios({
                    method: "post",
                    url: "merchant.do",
                    params: {
                        operate: 'updateOrderStatus',
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
            popReply: function (id) {
                vue.id=id;
                document.getElementById('light').style.display = 'block';
                document.getElementById('fade').style.display = 'block';
            },

            page: function (pageNo) {
                axios({
                    method: "post",
                    url: "merchant.do",
                    params: {
                        operate: 'UngetOrderedById',
                        pageNo: pageNo
                    }
                }).then(function (value) {
                    var lost = value.data;
                    vue.goods = lost;
                }).catch(function (resaon) {
                });
            },
            getOrderedById: function () {
                axios({
                    method: "post",
                    url: "merchant.do",
                    params: {
                        operate: 'UngetOrderedById'
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
            this.getOrderedById();
        }

    });

}