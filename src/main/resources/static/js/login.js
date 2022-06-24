var loginBtn = document.querySelector("#login-submit")



loginBtn.addEventListener('click', () => {
	
	
	
	var userName = document.querySelector("#username")
	var passWord = document.querySelector("#password")
	
	if(userName.value == ''){
		alert(`You need to enter a valid username. Input should not be empty`)
	}
	
	if(passWord.value == ''){
		alert(`password should not be empty`)
	}
	
})

