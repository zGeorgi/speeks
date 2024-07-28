package mycode.speeks;


import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import mycode.models.Message;
import mycode.models.User;
import mycode.models.UsersMsg;

public class UpdateMessage {
	
	UpdateMessage(){
		
	}
	// update message in DB from user to user	
	protected  void update(String from, String to, String text) {
		
		GetUserID id= new GetUserID();
		Integer idFrom = id.getID(from);
		
		GetUserID id2= new GetUserID();
		Integer idTo = id2.getID(to);
						
		Message msg = new Message();
		msg.setUserMsg(text);
		msg.setReadMsg("NO");
		Date date = new Date();
		msg.setUmsgTime(date);
		
		UsersMsg um = new UsersMsg();
		User usr = new User();
		User usr2 = new User();
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("speeks");
		EntityManager em = emf.createEntityManager();
		try
		{
			
			em.getTransaction().begin();
			usr = em.find(User.class, idFrom);
			usr2 = em.find(User.class, idTo);
		
				em.persist(msg);	
				//set join table
				um.setMessage(msg);
				um.setUser1(usr);
				um.setUser2(usr2);
				
				em.persist(um);
						
			em.getTransaction().commit();			
		}
		catch(Exception ex){
			System.out.println(ex);
		}finally{
		em.close();
		emf.close();
		}
	}
}

