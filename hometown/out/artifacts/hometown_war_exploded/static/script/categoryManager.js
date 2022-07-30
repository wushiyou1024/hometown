window.onload = function () {


    var vue = new Vue({
        el: "#sorts",

        data: {
            goods: {},
            keyword: "",
            id: "",
            sort: "请输入类型名"
        },


        methods: {
           clearSort:function (){
               vue.id='',
                   vue.sort='请输入类型名';
               document.getElementById('light').style.display='none';document.getElementById('fade').style.display='none'
           },
            submit: function () {
                axios({
                    method: "post",
                    url: "sort.do",
                    params: {
                        operate: 'updateSort',
                        id: vue.id,
                        sort: vue.sort
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
                    url: "sort.do",
                    params: {
                        operate: 'getSorts',
                        pageNo: pageNo
                    }
                }).then(function (value) {
                    var lost = value.data;
                    vue.goods = lost;
                }).catch(function (resaon) {
                });
            },
            getSorts: function () {
                axios({
                    method: "post",
                    url: "sort.do",
                    params: {
                        operate: 'getSorts'
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
                    url: "sort.do",
                    params: {
                        operate: 'updateSortById',
                        id: id
                    }
                }).then(function (value) {
                    vue.id = id;
                    vue.sort = value.data.sort;
                    document.getElementById('light').style.display = 'block';
                    document.getElementById('fade').style.display = 'block';
                }).catch(function (resaon) {
                });
            },
            deleteById: function (id) {
                if (confirm("是否删除")) {
                    axios({
                        method: "post",
                        url: "sort.do",
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
            this.getSorts();
        }

    });

}