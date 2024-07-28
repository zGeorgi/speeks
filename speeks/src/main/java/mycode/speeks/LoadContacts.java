package mycode.speeks;



import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class LoadContacts {
	
	protected LoadContacts(){
		
	}
//  ------ check user status onine/ofline

protected  String LoadConnt(){
	EntityManagerFactory emfactory = Persistence. 	createEntityManagerFactory( "speeks" );			
	EntityManager entitymanager = emfactory.createEntityManager();							
	
	String sql = "SELECT firstName FROM speekdb.users where state like 'online'";			
		try {
			
			Query query = entitymanager.createNativeQuery(sql); //execute native query to mysql	
			@SuppressWarnings("unchecked") List<String> userList = query.getResultList();
				String str = "";
				for (String x : userList) {
					str+=x+" ";
				}
				
				return str;
		}
		catch(Exception e) {
			return "Еrror: "+e;
		}finally {
			entitymanager.close();
			emfactory.close();
		}
	
	}
}
		