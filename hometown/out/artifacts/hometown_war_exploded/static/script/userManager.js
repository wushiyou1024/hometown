window.onload = function () {


    var vue = new Vue({
        el: "#goods",

        data: {
            goods: {},
            keyword: "",
            id: "",
            uname: "",
            pwd: "",
            email: "",
            phone: "",
            wechat: "",
            realName: ""
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
                        realName: vue.realName
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
                        operate: 'getAllUser',
                        pageNo: pageNo
                    }
                }).then(function (value) {
                    var lost = value.data;
                    vue.goods = lost;
                }).catch(function (resaon) {
                });
            },
            getAllUser: function () {
                axios({
                    method: "post",
                    url: "user.do",
                    params: {
                        operate: 'getAllUser'
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
                        operate: 'updateUserById',
                        id: id
                    }
                }).then(function (value) {
                    vue.id = value.data.id,
                        vue.uname = value.data.uname,
                        vue.pwd = value.data.pwd,
                        vue.email = value.data.email,
                        vue.phone = value.data.phone,
                        vue.wechat = value.data.wechat,
                        vue.realName = value.data.realName;
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
                            operate: 'deleteUserById',
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
            this.getAllUser();
        }

    });

}