//-----------------------LOGOUT---------------------
function logout(){
	var ready = status("offline", token);
	var resp=ready.trim().localeCompare("Complete")
	if(resp == 0){
		alert("Goodbye");
	$.removeCookie("token");
	$.removeCookie("username");
	$.removeCookie("frend");
	localStorage.clear();
	// empty browser cache?
	 $.ajax({
	        url: "",
	        context: document.body,
	        success: function(s,x){

	            $('html[manifest=saveappoffline.appcache]').attr('content', '');
	                $(this).html(s);
	                
	        }
	    }); 
		 location.href  = 'http://localhost:8080/speeks/'; 
	}
}



//------------------UPDATE MESSAGE-----------
function updateMsg(){
    	var text=document.getElementById("textarea").value;
    	    	
    	if(text.trim()!=""){
    		var msg=text; 
    		// document.getElementById("textarea").value ="";
    		if(frend!=null){
    			var  up_msg;
        		if (window.XMLHttpRequest) { 
        			up_msg = new XMLHttpRequest();
        		} else {
        			up_msg = new ActiveXObject("Microsoft.XMLHTTP");
        		}
        	//BROADCAST
        		up_msg.open('post', 'http://localhost:8080/speeks/webapi/broadcast', true);
        		up_msg.setRequestHeader ("Authorization", "Basic " + token);
        		up_msg.setRequestHeader ("EventType", "UpdateMessage");
        		up_msg.send(msg.trim());
        		
        		
        		up_msg.onreadystatechange = function () {
     			   if (up_msg.readyState == 4) { 	
     				   if (up_msg.status == 200 && up_msg.status <300){
     					  document.getElementById("textarea").value ="";		  
     					   getMessages(token);
     					  	
     					     }    				    				   
     			   }
     		}
    	}
    }
}
