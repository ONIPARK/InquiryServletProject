/**
 * 
 */
//console.log("dd");
function subjectCheck(){//件名の妥当性検査をする機能
	let subject = document.getElementById("subject");//<------------
	let subjectError = document.getElementById("subjectError");//　|
	if(subject.value == ""){//件名が空の場合------------------------　　　　　　　　　　
		subject.focus();
		subjectError.textContent="件名を入力してください";	
		subjectError.style.display = "block";//メッセージを表示する
		console.log("内容ない");
		return false;//FORMを送らない
	}else{//件名が空じゃない場合
		subjectError.textContent="";//メッセージを消して
		subjectError.style.display = "none";//見えないようにします。
		return true;
		
	}
}//Function End 他の機能をほぼ同じです。

function contentCheck(){//内容の妥当性検査をする機能
	let content = document.getElementById("content");
	let contentError = document.getElementById("contentError");

	if(content.value == ""){
		content.focus();
		contentError.textContent="内容を入力してください";	
		contentError.style.display = "block";
		console.log("内容ない");
		return false;
	}else{
		contentError.textContent="";
		contentError.style.display = "none";
		return true;
		
	}
}//Function End

function nameCheck(){//名前の妥当性検査をする機能
	let name = document.getElementById("name");
	let nameError = document.getElementById("nameError");

	if(name.value == ""){
		name.focus();
		nameError.textContent="氏名を入力してください";	
		nameError.style.display = "block";
		console.log("内容ない");
		return false;
	}else{
		nameError.textContent="";
		nameError.style.display = "none";
		return true;
		
	}
}//Function End

function FurinameCheck(){//名前（フリガナ）の妥当性検査をする機能
	let nameFurigana = document.getElementById("nameFurigana");
	let furiError = document.getElementById("furiError");
	let exp =/^[ァ-ン]+$/;
	if(nameFurigana.value == ""){
		nameFurigana.focus();
		furiError.textContent="氏名（フリガナ）を入力してください";	
		furiError.style.display = "block";
		console.log("内容ない");
		return false;
	}else if(!exp.test(nameFurigana.value)){//名前（フリガナ）がカタカナで書かれているのかを確認
		nameFurigana.focus();
		furiError.textContent="カタカナを入力してください";	
		furiError.style.display = "block";
		return false;
	}else{
		
		furiError.textContent="";
		furiError.style.display = "none";
		return true;
		
	}
}//Function End
function emailCheck(){//名前（フリガナ）の妥当性検査をする機能
	let email = document.getElementById("email");
	let emailError = document.getElementById("emailError");

	let exp =/^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$/i;//email形式検査
	if(email.value == ""){
		email.focus();
		emailError.textContent="Emailを入力してください";	
		emailError.style.display = "block";
		console.log("内容ない");
		return false;
	}else if(!exp.test(email.value)){//emailが正しい形式なのか検査
		email.focus();
		emailError.textContent="正しいメール形式で入力してください";	
		emailError.style.display = "block";
		return false;
	}else{
		
		emailError.textContent="";
		emailError.style.display = "none";
		return true;
		
	}
	

}//Function End

function confirmEmailCheck(){
	let email = document.getElementById("email");
	let confirmEmail = document.getElementById("confirmEmail");
	let cEE = document.getElementById("confirmEmailError");
	let exp =/^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$/i;
	if(confirmEmail.value == ""){
		confirmEmail.focus();
		cEE.textContent="メールアドレス(確認用)を入力してください。";
		cEE.style.display = "block";
		return false;
	}else if(!exp.test(confirmEmail.value) ){
		confirmEmail.focus();
		cEE.textContent="正しいメール形式で入力してください";
		cEE.style.display = "block";
		return false;
	}else if(email.value!==(confirmEmail.value)){
		confirmEmail.focus();
		cEE.textContent="同いメールアドレスを入力してください。";
		cEE.style.display = "block";		
		return false;
	}else{
		cEE.textContent = "";
		cEE.style.display = "none";
		return true;
	}
}


function submitCheck(event){//formの登録ボタンを押すと起動
	event.preventDefault();//妥当性検査せずに登録されることを止める
	let isSubjectValid = subjectCheck();//各機能trueかfalseを保存
	let isContentValid = contentCheck();
	let isNameValid = nameCheck();
	let isFuriValid = FurinameCheck();
	let isEmailValid = emailCheck();
	let isConfirmValid = confirmEmailCheck();
	
	// 全ての項目が有効の場合だけFORM提出
	if (isSubjectValid && isContentValid && isNameValid && isFuriValid && isEmailValid && isConfirmValid) {
		document.getElementById("inquiryForm").submit(); //submit起動 
	}
}//Function End