const switched = document.getElementById('switch');


switched.addEventListener('click', () => {
  switched.checked ? document.body.classList.add("dark") : document.body.classList.remove("dark");
  localStorage.setItem('darkModeStatus', switched.checked);
});

window.addEventListener('load', (event) => {
  if(localStorage.getItem('darkModeStatus')=="true"){
    document.body.classList.add("dark"); 
    document.getElementById('switch').checked = true;
  }
});