// --------------LOAD FREND AVATAR---------
function loadAvatar(token, user, Idelement){
	var  avatarFrend;
	if (window.XMLHttpRequest) { 
		avatarFrend = new XMLHttpRequest();
	} else {
	
		avatarFrend = new ActiveXObject("Microsoft.XMLHTTP");
	}	
	
	avatarFrend.open('POST', 'http://localhost:8080/speeks/webapi/secured/rtrAvatar', true);
	avatarFrend.setRequestHeader ("Authorization", "Basic " + token);
	avatarFrend.send(user);
	
	avatarFrend.onreadystatechange = function () {
	    if (avatarFrend.readyState === 4) {            
	      if (avatarFrend.status == 200 && avatarFrend.status <500){
	    	  var pic=avatarFrend.responseText;
	    	  
	    	  var compare = pic.localeCompare("NoContent");
	    	  if(compare == 0){
	    		  document.getElementById(Idelement).innerHTML=
			    		'<img src="avatar/logo.jpg" >';
	    	  }else{
	    		  document.getElementById(Idelement).innerHTML=
		    		'<img src="avatar/'+pic+'" alt="Profile pictures" >';
	    	  }
	    	  
		     }
	    }
	  }
}

//
//-------------SET USERS STATUS ONLINE/OFLINE--------
function status(val, token){
	var  stat;
	if (window.XMLHttpRequest) { 
		stat = new XMLHttpRequest();
	} else {
		stat = new ActiveXObject("Microsoft.XMLHTTP");
	}
	//BROADCAST
	try{
		stat.open('post', 'http://localhost:8080/speeks/webapi/broadcast', true);
		stat.setRequestHeader ("Authorization", "Basic " + token);
		stat.setRequestHeader ("EventType", "GetStatus");
		stat.send(val);

		return "Complete";
		
	}catch (ex) {
		alert('Error', ex.message);
	}
	
	
}

//------------LOAD USERS ONLINE------------
function loadFrends(token){
	var  fr;
	if (window.XMLHttpRequest) { 
			fr = new XMLHttpRequest();
	} else {
	
	fr = new ActiveXObject("Microsoft.XMLHTTP");
	}	
	fr.open('GET', 'http://localhost:8080/speeks/webapi/secured/frends', true);
	fr.setRequestHeader ("Authorization", "Basic " + token);
	fr.send();
	
	fr.onreadystatechange = function () {
	                        
	   if (fr.readyState == 4) {      
		   var response= fr.responseText;	
		   var spl= response.split(' ');		   
		   var frends = [];
		   for(i = 0; i < spl.length; i++)
	        {
			   if((spl[i]==userName)||(spl[i]==""))
			   		{continue}
			   else{
				   frends+=("<p  onclick=opens(\'"+spl[i]+"\') >"+spl[i]+"</p>") ;
				  
			   }
	        }
		   
		   document.getElementById("users").innerHTML= frends;
		
	    }
	}

}

//-----------------UPLOAD PROFILE PIC--------
function uploadAvatar(token ) {
	var fileVal=document.getElementById("file");
			var  avat;
			if (window.XMLHttpRequest) { 						
					avat = new XMLHttpRequest();
			} else {						
			avat = new ActiveXObject("Microsoft.XMLHTTP");
			}
	
	var formData= new FormData();  
	formData.append('file', fileVal.files[0]);
		
		
	avat.open('post', 'http://localhost:8080/speeks/webapi/secured/avatar', true);  
	avat.setRequestHeader ("Authorization", "Basic " + token);
	avat.send(formData); 
	
	avat.onreadystatechange = function(){
		if(avat.status === 200 && avat.readyState === 4){ 
 			alert("successfull");
			setTimeout(function () { location.reload(1); }, 5000);
		
				}
	}
}