function SSE(token){
	var sse;
	var event;

		if (window.XMLHttpRequest) { 						
			sse = new XMLHttpRequest();
		} 
		else {						
			sse = new ActiveXObject("Microsoft.XMLHTTP");
		}
		//BROADCAST
			sse.open('get', 'http://localhost:8080/speeks/webapi/broadcast', true);
			sse.setRequestHeader ("Authorization", "Basic "+ token);
			sse.send();
		 
		var pol;
		sse.onreadystatechange = function(){
			if(sse.status === 200 && sse.readyState === 3){ 
					pol = sse.responseText;
					console.log(pol);
							var ev = pol.lastIndexOf("event: ");				
							var dat = pol.lastIndexOf("data: ");							
							var ev2 = pol.slice((ev+7),dat);
				
			//--------------recive user status ------------
					var status=ev2.trim().localeCompare("GetStatus");
											if(status==0){
											loadFrends(token);
										}
			//---------------recive messages------------
					var updateMsg=ev2.trim().localeCompare("UpdateMessage");
					
											if(updateMsg==0){
												var index = pol.lastIndexOf(" ");
												var nov = pol.slice(index);
												var name = 	nov.split(">");	   
											 		 			    
											  var user= name[0];//to user
											  var fr= name[1]; // from user
											  
											 var u= user.trim(); 
											 var f= fr.trim();					 
											
											 var toUser=u.localeCompare(userName.trim());
											 
											 if(toUser==0){											 
												 alert("Message from "+f);	
												 opens(f);	
												 getMessages(token);
													 
											 }
											 											 									
										}
						
		   		}				
	}
}		

//------------- function GET MESSAGES betwen users----
function getMessages(token){
	var  request;
	if (window.XMLHttpRequest) { 
			request = new XMLHttpRequest();
	} else {
	request = new ActiveXObject("Microsoft.XMLHTTP");
	}

	request.open('get', 'http://localhost:8080/speeks/webapi/secured/messages', true);
	request.setRequestHeader ("Authorization", "Basic " + token);
	request.send();
	 	
	request.onreadystatechange = function () {
	    if (request.readyState === 4) {                
	      if (request.status == 200 && request.status <300){
	    	  var msg=request.responseText;	    	    	 
	    	 var arr=[];   	
	    	 
	    			    		
	    	var listObj = { };
	     listObj= eval(msg);	
	    	console.log(listObj);
	        	for(i = 0; i < listObj.length; i++)
	        {
	    		var who = listObj[i].name.trim().localeCompare(frend.trim());
	    		
	    		
	    		if(who==0){
	    			var readStatus=listObj[i].readMsg.trim().localeCompare("NO");
	    			var Fn;
	    			
	    			if(readStatus==0){
	 	    				Fn = "onmouseover=readFn(this," +listObj[i].id +","+listObj[i].readMsg+")";
	    			
	    			}
	    			else{
	    				Fn="id=\"Yes\"";
	    				}
	    			
	    			arr+=("<div "+Fn+" id=\""+listObj[i].readMsg +"\" class=\"frend\"> " +
	    					"<p id=\"msgFrend\">"+listObj[i].name+"</p>"
	    					+"<p id= \"fMsg\">"+listObj[i].msg+"</p>"+
	    					"<p id= \"msgDate\">"+listObj[i].time+"</p>" +
	    					"<p id=\""+listObj[i].id +"\" ></p></div>");
	    		
	    		}else{
	    			
	    			arr+=("<div class=\"I\"> <p id=\"msgMy\">"+listObj[i].name+"</p>"
	    					+"<p id= \"myMsg\">"+listObj[i].msg+"</p>"+
	    					"<p id= \"msgDate\">"+listObj[i].time+"</p>" +
	    					"<p id=\""+listObj[i].id +"\" ></p></div>");
	    	    		}
	    		
	 	     }
	    	 document.getElementById('msgDB').innerHTML =arr;   
	    	  document.getElementById('frend_name').innerHTML = $.cookie("frend");
	    	
	    	//----------scroll to msg---------
	    		 $('html, body').animate({
	    			    scrollTop: $("#textarea").offset().top
	    			}, 1000);
	    		 	    		 
	      }  
	     
	    }
	}
}



//--------------set up Message Flag-------
function readFn(x, id, msgStatus){
	x.style.backgroundColor = "#6e6e6e";
	//console.log("id"+ id);
	
	var  read;	
	if (window.XMLHttpRequest) { 
		read = new XMLHttpRequest();
		} else {
			read = new ActiveXObject("Microsoft.XMLHTTP");
		}

	read.open('post', 'http://localhost:8080/speeks/webapi/secured/sawMessages', true);
	read.setRequestHeader ("Authorization", "Basic " + token);
	read.send(id);
}


function showMap(Lat, Lon){
	 var latlon = new google.maps.LatLng(Lat, Lon)
	    var mapholder = document.getElementById('map')
	    mapholder.style.height = '50%';
	    mapholder.style.width = '100%';

					    var myOptions = {
					    center:latlon,zoom:14,
					    mapTypeId:google.maps.MapTypeId.ROADMAP,
					    mapTypeControl:false,
					    navigationControlOptions:{style:google.maps.NavigationControlStyle.SMALL}
					    }

	    var map = new google.maps.Map(document.getElementById('map'), myOptions);
	    var marker = new google.maps.Marker({position:latlon,map:map,title:"My location!"});
}
 