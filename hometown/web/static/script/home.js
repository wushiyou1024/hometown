window.onload=function () {

    var vue = new Vue({
        el: "#app",
        data: {
            goods:{},
            keyword: "",
            first: "",
            ending: "",
            number:"",
            pjson:""
        },
        methods: {

            purchaseDetails:function (id){
                axios({
                    method: "post",
                    url: "goods.do",
                    params: {
                        operate: 'userGetGoodsById',
                        id:id
                    }
                }).then(function (value) {
                    window.location.href = "page.do?operate=page&page=user/detail.html"
                }).catch(function (resaon) {
                });
            },

            getSort:function (id){
                axios({
                    method: "post",
                    url: "goods.do",
                    params: {
                        operate: 'index',
                          sid:id,
                        oper:'search'
                    }
                }).then(function (value) {
                    var lost=value.data;
                    vue.goods=lost;
                }).catch(function (resaon) {
                });
            },
            page:function (pageNo){
                axios({
                    method: "post",
                    url: "goods.do",
                    params: {
                        operate: 'index',
                        keyword: vue.keyword,
                        pageNo:pageNo
                    }
                }).then(function (value) {
                    var lost=value.data;
                    vue.goods=lost;
                }).catch(function (resaon) {
                });
            },
            submit: function () {
                axios({
                    method: "post",
                    url: "goods.do",
                    params: {
                        operate: 'index',
                        keyword: vue.keyword,
                        oper:'search'
                    }
                }).then(function (value) {
                    var list=value.data;
                    vue.goods=list;
                }).catch(function (resaon) {
                });
            },
            getGoods: function () {
                axios({
                    method: "post",
                    url: "goods.do",
                    params: {
                        operate: 'index'
                    }
                }).then(function (value) {
                    var lost=value.data;
                    vue.goods=lost;
                    var swiper = new Swiper('.swiper-container', {
                        autoplay: true,
                        pagination: {
                            el: '.swiper-pagination',
                            dynamicBullets: true
                        },
                        navigation: {
                            nextEl: '.swiper-button-next',
                            prevEl: '.swiper-button-prev'
                        }
                    })
                }).catch(function (resaon) {
                });
            },
            dateFormat:function(time) {
                var  year=time.date.year;
                var month=time.date.month;
                var day=time.date.day;
                var  hours=time.time.hour;
                var minutes=time.time.minute;
                var seconds=time.time.second;
                return year+"-"+month+"-"+day+" "+hours+":"+minutes+":"+seconds;
            },


            sprice: function () {
                axios({
                    method: "post",
                    url: "goods.do",
                    params: {
                        operate: 'priceSearch',
                        first: vue.first,
                        ending: vue.ending,

                        oper:'search'
                    }
                }).then(function (value) {

                    var list=value.data;

                    if ((parseInt(list.ending)!=0 || list.ending!="")&&list.first!=null){
                        if (parseInt(list.first)>parseInt(list.ending)){
                            alert("输入有误！")
                        }else {
                            vue.goods=list;
                        }
                    }

                }).catch(function (resaon) {
                });
            },

        },
        mounted: function () {
            this.getGoods();
        },



    });

}