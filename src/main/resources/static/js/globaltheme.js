const chk = document.getElementById('switch');

chk.addEventListener('click', () => {
  chk.checked?document.body.classList.add("dark"):document.body.classList.remove("dark");
  localStorage.setItem('darkModeStatus', chk.checked);
});

window.addEventListener('load', (event) => {
  if(localStorage.getItem('darkModeStatus')=="true"){
    document.body.classList.add("dark"); 
    document.getElementById('switch').checked = true;
  }
});