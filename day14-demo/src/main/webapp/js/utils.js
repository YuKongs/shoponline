function handleIsLogin() {
    const isLogin = localStorage.getItem("loginStatus") == '1'
    const hosts = window.location.host
    const isLocal = hosts.indexOf('localhost') > -1
    if (!isLogin) {
        const targetUrl = isLocal ? '/shop/login.html' : hosts + '/shop/login.html'
        window.location.href = targetUrl
    }
}


function handleLogout() {
    window.localStorage.removeItem('loginStatus')
    window.localStorage.removeItem('userInfo')
}