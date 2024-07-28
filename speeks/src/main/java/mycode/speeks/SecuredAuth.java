package mycode.speeks;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import java.nio.file.Files;
import java.nio.file.Paths;


import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;

//import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;


//services 
@Path("secured")
public class SecuredAuth {
	

//for authentication-------------------
		@GET
		@Path("home")
		@Produces(MediaType.APPLICATION_JSON)	
		 public String resp() {
			return "Welcome";
			    }
		
		//get messages between users
		@GET
		@Path("messages")
		@Produces(MediaType.APPLICATION_JSON)	
		 public String authSecure( @CookieParam("frend") String to, @CookieParam("username") String from) {
			MessagesService all = new MessagesService();
			String msg= all.getMessages(from, to);
		       System.out.println(msg);
			        return msg.toString();
			    }
	
	
	//get online users --------
		@GET
		@Path("frends")
		@Produces(MediaType.APPLICATION_JSON)
		public String retrive() {
			LoadContacts load = new LoadContacts();
			 String frends= load.LoadConnt();
			return frends ;
			    }
		

				
	//retrive avatar pic name--------------
				@POST
				@Path("rtrAvatar")
				@Produces(MediaType.APPLICATION_JSON)
				public String retriveAV( String userName) {
					RtrAvatar avat = new RtrAvatar();
					String answ = avat.getAvatar(userName);
					return answ;
					    }
				 
				@POST
				@Path("changePassword")
				@Produces(MediaType.TEXT_PLAIN)
				public String change(@HeaderParam("currentPass") String currentPass,
						@CookieParam("username") String userName,
						String newPass) {
					
					SearchDB name= new SearchDB();
					String pass= name.search(userName);
				
						if(currentPass.equals(pass)) {
							ChangePassword password= new ChangePassword();
						return	 password.Change(newPass, userName);
						}
					
						return "Error";
					
				}
				
		// download file
				@POST
			    @Path("/down")
				@Produces(MediaType.APPLICATION_OCTET_STREAM)
			    public Response downloadFile(String Name){
					
			        StreamingOutput fileStream =  new StreamingOutput()
			        {
			            @Override
			            public void write(OutputStream output) throws IOException, WebApplicationException
			            {//java.io.OutputStream
			                try
			                {
			                	
			                    java.nio.file.Path path = Paths.get("C://Users//User//eclipse-workspace//speeks/"
			                    		+ "/src//main//webapp//files//"+Name); 
			                    byte[] data = Files.readAllBytes(path);
			                    output.write(data); 
			                    output.flush();
			                }
			                catch (Exception e)
			                {
			                    throw new WebApplicationException("File Not Found !!");
			                }
			            }
			        };
			        return Response
			                .ok(fileStream, MediaType.APPLICATION_OCTET_STREAM)//response 200 , file like response and Content-Type of response
			                .header("Content-Disposition","attachment; filename = "+Name)	//show pop up in browser with name
			                //attachment show that file should be downloaded
			                //.header("fileName", Name) // NOT IN USE 
			                .build();
			    }
				
				//---upload avatar pic- on Server---------- 
				@POST
				@Path("avatar")
				@Consumes(MediaType.MULTIPART_FORM_DATA)
				public String uploadFile(@CookieParam("username") String userName,@HeaderParam("FileName") String Filename,
					@FormDataParam("file") InputStream uploadedInputStream
					/*@FormDataParam("file") FormDataContentDisposition fileDetai*/) {
					
					try {
						String name= URLDecoder.decode(Filename, "UTF-8");
						Avatar ava = new Avatar();
						ava.writeToFile(uploadedInputStream, name, userName);
						return "Uploaded File is "+ name;
						
					} catch (UnsupportedEncodingException e) {
					
						e.printStackTrace();
						return "Error "+e;
					}
					
					
				}
				
		//upload file on server---------
				@POST
				@Path("upFile")
				@Consumes(MediaType.MULTIPART_FORM_DATA)
				public String fileUpload(@HeaderParam("Location") String location,@HeaderParam("FileName") String Filename,
						@FormDataParam("file") InputStream uploadedInputStream ){
					
					String folder= "";
					try {
						if(location.equals("share")) {
							folder= "files";
							String name=URLDecoder.decode(Filename, "UTF-8");
							
							
							//System.out.println(" отговор "+urln);
							Avatar upload = new Avatar();
							upload.writeTo(uploadedInputStream, name, folder);
						}
						return "Upload is Done!";
					}catch(Exception e) {
						return "Error: "+e;
					}
				
			 }
		//set message status like READ
				@POST
				@Path("sawMessages")
				@Produces(MediaType.APPLICATION_JSON)
				public String readMessages(Integer id) {
					
					try {
						ReadMessages msg = new ReadMessages();
						msg.setMsgStatus(id);
						return "SawMessage Done!";
					}catch(Exception e) {
						return "Error- "+e;
					}
					
						
					
				}
	}
