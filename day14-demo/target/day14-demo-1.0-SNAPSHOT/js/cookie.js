//设置cookie
function setCookie(name,value,expireTime) {
    const exp = new Date();
    exp.setTime(exp.getTime() + expireTime*1000);
    document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString();
}
//读取cookie
function getCookie(name) {
    let arr;
    const reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
    if(arr=document.cookie.match(reg)){
        return unescape(arr[2]);
    }else{
        return null;
    }
}