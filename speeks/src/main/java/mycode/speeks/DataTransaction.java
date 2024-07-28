package mycode.speeks;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import mycode.models.User;
// For Registration
@Path("/registartion")
public class DataTransaction {
	
	@POST
	@Produces({MediaType.TEXT_HTML})
	public  String mysql(@FormParam("username") String firstName,
			@FormParam("email") String email,
			@FormParam("password") String pass){
		
		
		User usr = new User();
		usr.setFirstName(firstName);
		usr.setPass(pass);
		usr.setЕmail(email);
		usr.setState("offline");
		usr.setAvatar("NoContent");
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("speeks");
		EntityManager em = emf.createEntityManager();
		try
		{
			em.getTransaction().begin();
	
			em.persist(usr);
			em.getTransaction().commit();
			//return link to login
		return "<html>"
				+ "<head></head> "
				+ "<body><div id=\"login-form\">"
				+ "<p style=\"text-align: center;\"> Done </p>"
				+ "<p style=\"text-align: center;\"><a  href=\"http://localhost:8080/speeks/\">Login </a></p>"
				+ "</div>"
				+ "</body></html>";
		}
		catch(Exception ex){
			//return link to registration
			return "<html>"
			+"<head></head>"
			+ "<body><div id='login-form'>"
			+ "<p style=\"text-align: center;\"> Email, Password or UserName is busy!</p>"
			+ "<p style=\"text-align: center;\"><a  href=\"http://localhost:8080/speeks/login.html\">Registration </a></p>"
			+ "</div></body></html>";
			
		}finally{
		em.close();
		emf.close();
		}
		
	}
	
}

