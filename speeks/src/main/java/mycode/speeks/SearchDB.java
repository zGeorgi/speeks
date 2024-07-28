package mycode.speeks;

// for login system 

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import mycode.models.User;

public class SearchDB {

	SearchDB(){
		
	}
	//searching password for certain username
	 protected  String search(String user_name ) { 		
	     EntityManagerFactory emfactory = Persistence. 	createEntityManagerFactory( "speeks" );			//create factory
		EntityManager entitymanager = emfactory.createEntityManager();			//create manager
											
				GetUserID id= new GetUserID();		
				Integer usrID = id.getID(user_name); 	
				User usr = new User();		
				try{
			
					entitymanager.getTransaction().begin();
								usr = entitymanager.find(User.class, usrID);
								entitymanager.getTransaction().commit();
					return usr.getPass(); 
				}catch(Exception e){
					return "User doesn't exist";
				}
				finally{
					entitymanager.close();
					emfactory.close();
				}
   }
}