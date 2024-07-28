package mycode.speeks;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import mycode.models.Message;

// set message with status read
public class ReadMessages {
	ReadMessages(){
		
	}
		
	protected  void setMsgStatus(Integer id){
		
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("speeks" );			
		EntityManager entitymanager = emfactory.createEntityManager();							
		
		Message msg = new Message();	
		
		try {
			
			entitymanager.getTransaction().begin();
			msg = entitymanager.find(Message.class, id);
			msg.setReadMsg("Yes");
			entitymanager.persist(msg);
			
			entitymanager.getTransaction().commit();
		
	}catch(Exception ex) {
		System.out.println(ex);
	}finally {
		entitymanager.close();
		emfactory.close();
		}
	}
}
