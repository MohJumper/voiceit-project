

function runLoad(isAdmin) { 
console.log('isAdmin: ', isAdmin)
const actionHeader = document.getElementById('action')
	actionHeader.style.display = 'none'
const elements = document.getElementsByClassName("actionButtons");
debugger
for(var i = 0; i < elements.length; i++) {
    elements[i].style.display = 'none'
}


}