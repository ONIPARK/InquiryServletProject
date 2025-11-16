let isChecked = false;

document.getElementById("username").addEventListener("input", function() {
	isChecked = false;
	document.getElementById("checkResult").innerText = "";
});

function checkDuplicate() {
	const userName = document.getElementById("username").value;
	console.log("重複チェックするusername: " + userName);
	
	fetch(contextPath + "/checkId?username=" + encodeURIComponent(userName))
		.then(res => res.text())
		.then(result => {
			console.log("サーバーでもらった結果:", result);
			document.getElementById("checkResult").innerText = result;
			
			if (result === "OK") {
				isChecked = true;
				document.getElementById("checkResult").innerText = "使用可能なIDです。";
			} else {
				isChecked = false;
				document.getElementById("checkResult").innerText = "既に使用されているIDです。";
			}
		});
}

// フォームを渡す前検査
function validateForm() {
	if (!isChecked) {
		alert("IDの重複確認をお願いします。")
		return false;
	}
	return true;
}
