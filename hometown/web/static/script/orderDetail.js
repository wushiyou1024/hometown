// 6.27
function commentGoodsById(gid) {
    window.location.href = 'order.do?operate=commentGoodsById&gid=' + gid;
}


function updateOrderStatus(id,status)
{
    window.location.href = 'order.do?operate=updateOrderStatus&id=' + id+"&status="+status;
}
function checkCommentById(gid) {
    var vue = new Vue({
        el: "#detail",
        data: {
            content: "",
            reply: "没有主人回复"
            , cdate: ""
            ,
            rdate: ""
        },

        methods: {
            clearSort: function () {
                document.getElementById('light').style.display = 'none';
                document.getElementById('fade').style.display = 'none'

            },
            submit: function () {
                axios({
                    method: "post",
                    url: "order.do",
                    params: {
                        operate: 'checkCommentById',
                        id: gid,
                    }
                }).then(function (value) {
                    vue.content = value.data.content,
                        vue.reply = value.data.reply,
                        vue.cdate = value.data.cdate,
                        vue.rdate = value.data.rdate,
                        document.getElementById('light').style.display = 'block';
                    document.getElementById('fade').style.display = 'block';

                }).catch(function () {
                });
            },

        },
        mounted: function () {
            this.submit();
        }

    });

}
