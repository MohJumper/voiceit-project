  $(document).ready(function () {
        $("#locale").change(function () {
            var selectedOption = $('#locale').val();
            if (selectedOption != '') {
                window.location.replace('?lang=' + selectedOption);
            }
        });
    });
    
    function success() {
   	 if(document.getElementById("required").value==="") { 
	console.alter
               document.getElementById('button').disabled = true; 
           } else { 
               document.getElementById('button').disabled = false;
           }
       }
       
 let button = document.getElementById('button')
 
alert(button)