<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Chat</title>
<link rel="stylesheet" type="text/css" href="box.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.0.0-beta1/jquery.js"></script>
</head>

<body>
<script>

var  request;
if (window.XMLHttpRequest) { 
		request = new XMLHttpRequest();
} else {
request = new ActiveXObject("Microsoft.XMLHTTP");
}

$(document).ready(function(){
    $("#login").click(function(){    
    	var user = $("#username").val();
    	var pass = $("#pass").val();
    	
    	document.cookie = "username="+ user; //set cookie 
    	
    	  var patt = new RegExp("[^A-Za-z0-9!@#$%&*()_+-=[]|,./?><]");
          
          if ( patt.test(user)||patt.test(pass) ) {
          	
              alert('The fields should be fieled in only with Latine letters, numbers and special characters!');
          }else{  
        
    	var token= btoa(user/*.trim()*/ + ":" + pass/*.trim()*/); // authetnication token for base64
    	document.cookie= "token="+token;
    	
		    	request.open('get', 'http://localhost:8080/speeks/webapi/secured/home', true);
		    	request.setRequestHeader ("Authorization", "Basic " + token );
		    	request.send();

		    	request.onreadystatechange = function () {
		    	     if (request.readyState === 4) {                
			    	      if (request.status == 200 && request.status <300){// http status between 200 to 299 are all successful
			    	    	 window.location.href  = 'http://localhost:8080/speeks/home.html';
			    	      }  
			    	     else alert("wrong details...");
		    	    }
		    	}
          }
    	
    });
   });

</script>

<div id="login-form">
<h1>Chat Web Application</h1>
<table>
<tr>
<td><input type="text" id="username" placeholder="User name" required /> </td>
</tr>
<tr>
<td><input type="password"  id="pass"  placeholder="Your Password" required/></td>
</tr>
<tr>
<td><button  id="login">LogIn</button></td>
</tr>
<tr>
<td><a href="http://localhost:8080/speeks/login.html"> Sign Me Up</a></td>
</tr>
<tr>
<td><a href="http://localhost:8080/speeks/forgotPassword.html"> Forgot Password</a></td>
</tr>
</table>

</div>
 
   
</body>
</html>