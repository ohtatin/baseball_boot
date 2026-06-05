document.addEventListener("DOMContentLoaded", () => {

    const loginForm = document.getElementById("loginForm");
    const registerForm = document.getElementById("registerForm");

    if (loginForm) {
        loginForm.addEventListener("submit", login);
    }

    if (registerForm) {
        registerForm.addEventListener("submit", register);
    }

});

// 登入
function login(event) {
    event.preventDefault();

    const form = event.target;
    const team = form.team.value;
    const password = form.password.value;

    fetch("/api/auth/login", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ team, password })
    })
    .then(res => res.text())
    .then(data => {
		data = data.trim();
		console.log("登入回傳內容：", data);
		if(data.startsWith("eyJ")){
		    localStorage.setItem("token", data);	
		    window.location.href = "/html/stats.html";	
		} else {
			alert("登入失敗");
		}
	})	
    .catch(err => { console.error(err); alert("登入失敗"); });
}

// 註冊
function register(event) {
    event.preventDefault();

    const form = event.target;
    const team = form.team.value;
    const password = form.password.value;

    fetch("/club/register", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ team, password })
    })
    .then(res => res.text())
    .then(data => alert("註冊結果: " + data))
    .catch(err => { console.error(err); alert("註冊失敗"); });
}