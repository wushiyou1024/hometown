function getAddress(id)
{
    window.location.href ='address.do?operate=getAddress&addressId=' + id;
}

function changeDefaultAddress(new_id)
{
    window.location.href ='address.do?operate=setAddressDefault&addressId=' + new_id
}

function deleteAddress(id)
{
window.location.href='address.do?operate=deleteAddress&id='+id;
}
function addAddress()
{
    var vue = new Vue({
        el: "#goods",
        data: {
            id:"",
            address:"",
            uname:"",
            telephone:""
        },
        methods: {
            clearSort: function () {
                document.getElementById('light').style.display = 'none';
                document.getElementById('fade').style.display = 'none'
            },
            submit: function () {
                axios({
                    method: "post",
                    url: "address.do",
                    params: {
                        operate: 'addAddress',
                        id: vue.id,
                        address:vue.address,
                        uname:vue.uname,
                        telephone:vue.telephone
                    }
                }).then(function (value) {
                    document.getElementById('light').style.display = 'none';
                    document.getElementById('fade').style.display = 'none';
                    vue.clearSort();
                    window.location.href="address.do?operate=getAddressList";
                }).catch(function (resaon) {
                });
            },
            addAddress: function () {
                    document.getElementById('light').style.display = 'block';
                    document.getElementById('fade').style.display = 'block';

            },
        },
        mounted: function () {
            this.addAddress();
        }
    });
}
function updateById(id)
{
    var vue = new Vue({
        el: "#goods",
        data: {
          id:"",
            address:"",
            uname:"",
            telephone:""
        },
        methods: {
            clearSort: function () {
                document.getElementById('light').style.display = 'none';
                document.getElementById('fade').style.display = 'none'
            },
            submit: function () {
                axios({
                    method: "post",
                    url: "address.do",
                    params: {
                        operate: 'updateAddress',
                        id: vue.id,
                        address:vue.address,
                        uname:vue.uname,
                        telephone:vue.telephone
                    }
                }).then(function (value) {
                    document.getElementById('light').style.display = 'none';
                    document.getElementById('fade').style.display = 'none';
                    vue.clearSort();
                    window.location.href="address.do?operate=getAddressList";
                }).catch(function (resaon) {
                });
            },
            updateById: function () {

                axios({
                    method: "post",
                    url: "address.do",
                    params: {
                        operate: 'savaAddress',
                        id: id
                    }
                }).then(function (value) {
                    vue.id = value.data.id,
                        vue. address=value.data.address,
                        vue.uname=value.data.uname,
                        vue.telephone=value.data.telephone
                        document.getElementById('light').style.display = 'block';
                    document.getElementById('fade').style.display = 'block';
                }).catch(function (resaon) {
                });
            },

        },
        mounted: function () {
            this.updateById();
        }
    });

}
