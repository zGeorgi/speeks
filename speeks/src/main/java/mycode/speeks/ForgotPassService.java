package mycode.speeks;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/ForgotPassword")
public class ForgotPassService {
	
	@POST	
	@Produces(MediaType.APPLICATION_JSON)	
	public String ForgotPass(String mail) {
		PasswordGenerator pass = new PasswordGenerator();
		
		EmailCheck checkEmail = new EmailCheck();  // check if email is in DB
		ForgotPass send = new ForgotPass(); // send new password class
	
		
		String res;
		res= checkEmail.check(mail);
			System.out.println("email " + res);
		if(res!="") {
			
			try {
				String password = pass.generate(); //generate password 
				ChangePassword setPass = new ChangePassword(); 
				setPass.Change(password, res);  //write new password id DB
				send.generateAndSendEmail(password, mail);   // send password  
				
			return "New password is send on your email!";
			} catch (AddressException e) {
								
				e.printStackTrace();
			} catch (MessagingException e) {
				
				e.printStackTrace();
			}	
		}
		
		return "No such user!";
		
	}
}
