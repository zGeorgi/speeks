package mycode.speeks;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class EmailCheck {
	EmailCheck(){
		
	}
	//return firstName from user with this email
public String check(String mail) {
	
	EntityManagerFactory emfactory = Persistence. 	createEntityManagerFactory( "speeks" );			
	EntityManager entitymanager = emfactory.createEntityManager();	
	
	String sql = "SELECT users.firstName FROM speekdb.users where BINARY users.еmail like \'"+ mail+"\'";			
	
	String result = "";
	
				try {
					Query query = entitymanager.createNativeQuery(sql);
					
					 result=String.valueOf(query.getSingleResult()); 
					 
					 return result;					 
				}catch(Exception ex){
					return "" ;
				}finally{
					entitymanager.close();
					emfactory.close();
				}
	}
	
}
