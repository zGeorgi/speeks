package mycode.speeks;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import mycode.models.User;


public class ChangePassword {
	ChangePassword(){
		
	}
	
	protected String Change(String newPassword, String userName) {
		
		EntityManagerFactory emfactory = Persistence. 	createEntityManagerFactory( "speeks" );			
		EntityManager entitymanager = emfactory.createEntityManager();		
		
		GetUserID id= new GetUserID();		
		Integer usrID = id.getID(userName); 
			User usr = new User();			
   
		try {
				entitymanager.getTransaction().begin();
							usr = entitymanager.find(User.class, usrID);
							usr.setPass(newPassword);
				entitymanager.getTransaction().commit();
			return "Done" ;
		}catch(Exception ex) {
			return "error "+ ex;
		}finally {
			entitymanager.close();
			emfactory.close();
		}
		
	}
	
}
