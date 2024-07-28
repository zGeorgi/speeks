package mycode.speeks;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import mycode.models.User;

public class RtrAvatar {
	RtrAvatar(){
		
	}
	// Retrieve profile pic name from DB
	protected String getAvatar(String username){
		EntityManagerFactory emfactory = Persistence. 	createEntityManagerFactory( "speeks" );			
		EntityManager entitymanager = emfactory.createEntityManager();	
		
		GetUserID id= new GetUserID();
		Integer usrID = id.getID(username);
		User usr = new User();	
	
		try{
				entitymanager.getTransaction().begin();
				usr = entitymanager.find(User.class, usrID);
				entitymanager.getTransaction().commit();
		
				return usr.getAvatar();
			}catch(Exception e){
				return "Error:"+e;
			}
			finally{
				entitymanager.close();
				emfactory.close();
			}
	}
	
}
