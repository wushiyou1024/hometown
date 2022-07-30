window.onload=function () {

    var vue = new Vue({
        el: "#userTable",
        data: {
            user:{},
        },
        methods: {
            getUser: function () {
                axios({
                    method: "post",
                    url: "user.do",
                    params: {
                        operate: 'getUser'
                    }
                }).then(function (value) {
                    var data=value.data;
                    vue.user=data;
                }).catch(function (resaon) {
                });
            },
            submit: function (user) {
                axios({
                    method: "post",
                    url: "user.do",
                    params: {
                        operate: 'update',
                    },
                    data: {
                        user:user,
                    }
                }).then(function (value) {
                    alert("修改成功！");
                    var user=value.data;
                    vue.user=user;
                }).catch(function (resaon) {
                });
            },
        },
        mounted: function () {
            this.getUser();
        }

    });

}