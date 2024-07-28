package mycode.speeks;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import javax.ws.rs.core.MediaType;


import org.glassfish.jersey.media.sse.EventOutput;
import org.glassfish.jersey.media.sse.OutboundEvent;
import org.glassfish.jersey.media.sse.SseBroadcaster;
import org.glassfish.jersey.media.sse.SseFeature;

@Singleton
@Path("broadcast")
public class Broadcaster {

	
	// BROADCAAST SERVICES
	private SseBroadcaster broadcaster = new SseBroadcaster();
	
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    public String broadcastMessage(String message, @CookieParam("frend") String to, @CookieParam("username") String from,
    		@HeaderParam("EventType") String EventType ) {
    	
		    	 String data = null;   	// update messages in DB
		    	if(EventType.equals("UpdateMessage")) {
		    		UpdateMessage msg= new UpdateMessage();
		        	msg.update(from, to, message);
		    		data = to+">"+from;
		    	}
		    	if(EventType.equals("GetStatus")) { // Set users status
		    		UserStatus status = new UserStatus();
					status.setUserStatus(message, from);	
		    		data = "Reload users \'online\'";
		    	}
		    	
		    	    	   	  	    	
		        OutboundEvent.Builder eventBuilder = new OutboundEvent.Builder();
		        OutboundEvent event = eventBuilder.name(EventType)
		        	
		            .mediaType(MediaType.TEXT_PLAIN_TYPE)
		            .data(String.class, data)
		            .build();
		 
		        broadcaster.broadcast(event);
		    	
		        return "Message  has been broadcast.";
    }
    		
		    @GET
		    @Produces(SseFeature.SERVER_SENT_EVENTS)//broadcast msg
		    public EventOutput listenToBroadcast( ) {
		       
		    	final EventOutput eventOutput = new EventOutput();
		        this.broadcaster.add(eventOutput);
		    
		        	return eventOutput;
			 }
}
