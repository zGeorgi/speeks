package mycode.speeks;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import mycode.models.User;

public class UserStatus {
	UserStatus(){
		
	}
	
		// update user status	online /offline
			protected  void setUserStatus(String status,   String user){

			EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("speeks" );			
			EntityManager entitymanager = emfactory.createEntityManager();							
			
			
			GetUserID id= new GetUserID();		
			Integer usrID = id.getID(user); 
			User usr = new User();	
			try{
		
				entitymanager.getTransaction().begin();
							usr = entitymanager.find(User.class, usrID);
							usr.setState(status);
							entitymanager.persist(usr);
				entitymanager.getTransaction().commit();
				
			}catch(Exception e){
				
			}
			finally{
				entitymanager.close();
				emfactory.close();
			}
		}
	}

