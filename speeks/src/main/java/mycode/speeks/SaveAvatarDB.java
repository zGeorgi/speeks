package mycode.speeks;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import mycode.models.User;

public class SaveAvatarDB {
	SaveAvatarDB(){
		
	}
	// update profile pictures name in DB
	protected void save(String userName, String img){
		EntityManagerFactory emfactory = Persistence. 	createEntityManagerFactory( "speeks" );			
		EntityManager entitymanager = emfactory.createEntityManager();							
							
		GetUserID id= new GetUserID();
		Integer usrID = id.getID(userName);	
		User usr = new User();	
		try{
			
			entitymanager.getTransaction().begin();
			usr = entitymanager.find(User.class, usrID);
			usr.setAvatar(img);
			entitymanager.persist(usr);		
			entitymanager.getTransaction().commit();
					 
				}catch(Exception e){
					System.out.println(e);
				}
		finally{
					entitymanager.close();
					emfactory.close();
			}
	}
}