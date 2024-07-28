package mycode.speeks;

import java.util.Properties;
import javax.mail.Transport;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
//SEND email with new password
public class ForgotPass {
	ForgotPass(){
		
	}
	
	static Properties mailServerProperties;
	static Session getMailSession;
	static MimeMessage generateMailMessage;
 
	public  void generateAndSendEmail(String password, String mail) throws AddressException, MessagingException {
 
		// Step1
		System.out.println("\n 1st ===> setup Mail Server Properties.."); 
		mailServerProperties = System.getProperties(); //returns a Properties object. This object contains a complete set of system property definitions.
		mailServerProperties.put("mail.smtp.port", "587"); //Define the SMTP port on which the SMTP server is listening.
		mailServerProperties.put("mail.smtp.auth", "true");//If true, attempt to authenticate the user using the AUTH command
		mailServerProperties.put("mail.smtp.starttls.enable", "true");//Upgrade the regular SMTP connection on the usual port to an encrypted (TLS or SSL) connection.
		mailServerProperties.put("mail.smtp.ssl.trust", "smtp.gmail.com"); // set host smtp.gmail.com like trusted
		System.out.println("Mail Server Properties have been setup successfully..");
 
		// Step2
		System.out.println("\n\n 2nd ===> get Mail Session..");
		getMailSession = Session.getDefaultInstance(mailServerProperties, null);//creates a new session using system properties setup above
		generateMailMessage = new MimeMessage(getMailSession);//Create and return a MimeMessage object.
		generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(mail));//add recipient to
		generateMailMessage.addRecipient(Message.RecipientType.CC, new InternetAddress("georg@gmail.com"));//add recipient from
		generateMailMessage.setSubject("Chat admin..");// from who is
		String emailBody = "From forgotten password. Your password is: "+ password   + "<br><br> Regards, <br>Chat";
		generateMailMessage.setContent(emailBody, "text/html");//set content body and type
		System.out.println("Mail Session has been created successfully..");
 
		// Step3
		System.out.println("\n\n 3rd ===> Get Session and Send mail");
		Transport transport = getMailSession.getTransport("smtp"); //return: a Transport object
 
		// Enter correct gmail UserID and Password
		
		transport.connect("smtp.gmail.com", "georg@gmail.com", "password"); //connect to email
		transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());// Send the Message to the specified list of addresses
		transport.close();//Close this service and terminate its connection
	}
	
	
}
