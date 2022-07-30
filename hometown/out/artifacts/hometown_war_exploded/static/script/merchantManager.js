window.onload = function () {


    var vue = new Vue({
        el: "#goods",

        data: {
            goods: {},
            keyword: "",
            id: "",
            mig: "",
            mname: "",
            muname: "",
            pwd: "",
            mintro: "",
            email: "",
            status: ""
        },

        methods: {
            clearSort: function () {
                document.getElementById('light').style.display = 'none';
                document.getElementById('fade').style.display = 'none'
            },
            submit: function () {
                axios({
                    method: "post",
                    url: "user.do",
                    params: {
                        operate: 'updateUser',
                        id: vue.id,
                        uname: vue.uname,
                        pwd: vue.pwd,
                        email: vue.email,
                        phone: vue.phone,
                        wechat: vue.wechat,
                        realName: vue.realName,
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
                    url: "user.do",
                    params: {
                        operate: 'getAllMerchant',
                        pageNo: pageNo
                    }
                }).then(function (value) {
                    var lost = value.data;
                    vue.goods = lost;
                }).catch(function (resaon) {
                });
            },
            getAllMerchant: function () {
                axios({
                    method: "post",
                    url: "user.do",
                    params: {
                        operate: 'getAllMerchant'
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
                    url: "user.do",
                    params: {
                        operate: 'updateMerchantById',
                        id: id
                    }
                }).then(function (value) {
                    vue.id = value.data.id,
                        vue.mname = value.data.mname,
                        vue.muname = value.data.muname,
                        vue.pwd = value.data.mpwd,
                        vue.mintro = value.data.mintro,
                        vue.email = value.data.email,
                        vue.status = value.data.status,
                        document.getElementById('light').style.display = 'block';
                    document.getElementById('fade').style.display = 'block';
                }).catch(function (resaon) {
                });
            },
            deleteById: function (id) {
                if (confirm("是否删除")) {
                    axios({
                        method: "post",
                        url: "user.do",
                        params: {
                            operate: 'deleteMerchantById',
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
            this.getAllMerchant();
        }

    });

}