package mycode.speeks;


import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import mycode.models.Message;
import mycode.models.User;


public class MessagesService {

	protected MessagesService(){
		
	}
	
	//------get messages betwen users----------
	
	protected  String getMessages(String from, String to ) { 
		
	     EntityManagerFactory emfactory = Persistence. 	createEntityManagerFactory( "speeks" );			//create factory
				EntityManager entitymanager = emfactory.createEntityManager();							//create manager
					
				//{ date: '12/1/2011', reading: 3, id: 20055 },
											
				GetUserID id= new GetUserID();  
				Integer idFrom = id.getID(from);  //get id of first user
				
				GetUserID id2= new GetUserID();
				Integer idTo = id2.getID(to);   //get id of second user
				
				//create objects 
				User usrFrom = new User();	
				Message msgID = new Message();
				
				String str = "";
				try{
					// sql query 	
					String sql ="SELECT  concat(m.msg_from ,',', m.msgID,',', m.msg_to ) "  
							+ "FROM   users_msg m "                                                                     
							+ "where (msg_from Like \'"+idFrom+"\' and msg_to =\'"+idTo+"\' )"
									+ "or (msg_from =\'"+idTo+"\' and msg_to =\'"+idFrom+"\' )";
		
					Query query = entitymanager.createNativeQuery(sql);
					@SuppressWarnings("unchecked")List<String> msgList  = query.getResultList();
					String row[]= new String[3];
					
					StringBuilder sb = new StringBuilder();
					sb.append("[");
					for(int i=0; i < msgList.size(); i++) {
						
						String messages=   (String) msgList.get(i);
						row=messages.split(",");// return array of messages
						
								for(int k = 0; k<2; k++) {
														
					//create return string like -> {"name":"john","msg":"hello ","time":"2018-04-24 17:25:11"},
										if(k==0) {
											usrFrom = entitymanager.find(User.class, Integer.parseInt(row[k]));													
										}
										if(k==1) {	
											msgID =entitymanager.find(Message.class, Integer.parseInt(row[k]));																
										}
													
								}
								 sb.append("{\"name\":"+"\"" +usrFrom.getFirstName().toString()+"\", ");
								 sb.append("\"msg\":\""+ msgID.getUserMsg().toString()+"\", ");
								 sb.append("\"time\":\""+ msgID.getUmsgTime().toString()+"\", ");
								 sb.append("\"readMsg\":\""+ msgID.getReadMsg().toString()+"\", ");
								 sb.append("\"id\":\""+ msgID.getIdMsg()+"\"}");
								if(i+1 < msgList.size()) {
								
									 sb.append(", ");
								}
								
					}  			
				sb.append("]");
				str =sb.toString();
						return str;
				}catch(Exception e){
					return "Еrror:" + e;
				}
				finally{
					entitymanager.close();
					emfactory.close();
				}
   }
}
