package mycode.speeks;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class GetUserID {
	GetUserID(){
		
	}
	public Integer getID(String firstName) {
		//return user id with .this name
	EntityManagerFactory emfactory = Persistence. 	createEntityManagerFactory( "speeks" );			
	EntityManager entitymanager = emfactory.createEntityManager();	
	String sql = "SELECT id_user FROM speekdb.users where BINARY firstName like \'"+ firstName+"\'";			
	
	String result = "";
	
				try {
					Query query = entitymanager.createNativeQuery(sql);
					
					 result=String.valueOf(query.getSingleResult());
					 
					 return  Integer.parseInt(result);
					 
				}catch(Exception ex){
					return -1;
				}finally{
					entitymanager.close();
					emfactory.close();
				}
				
	}
}
