package mycode.speeks;

import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.internal.util.Base64;

@Provider
public class SecureFilter implements ContainerRequestFilter{

	//lookin for 'Authorization' key word in header
	private static final String AUTHORIZATION_HEADER_KEY = "Authorization";
	private static final String AUTHORIZATION_HEADER_TOKEN = "Basic ";
	private static final String SECURED_URL_PREFIX = "secured";
	private static final String SECURED_URL_Broadcast = "broadcast";
	
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		if(requestContext.getUriInfo().getPath().contains(SECURED_URL_PREFIX)||
				requestContext.getUriInfo().getPath().contains(SECURED_URL_Broadcast)){
			
		List <String> authHeader = requestContext.getHeaders().get(AUTHORIZATION_HEADER_KEY);//catch header context
		
		if(authHeader!= null && authHeader.size() > 0) {					//check if header request isn't empty
			String authToken = authHeader.get(0);							//get first value 'Basic'
		
			authToken = authToken.replaceFirst(AUTHORIZATION_HEADER_TOKEN, "");//replace with empty string
			String decodedString = Base64.decodeAsString(authToken);    		//decode rest of message
		
			StringTokenizer tokenizer = new StringTokenizer(decodedString, ":");	 //slice string by ":" token
			String firstName = tokenizer.nextToken();
			String password = tokenizer.nextToken();
			
			 //retrive password on this username	
			SearchDB name= new SearchDB();
			String pass= name.search(firstName);
			
			if( password.equals(pass)){
				return;
			}
			
		}
		Response unauthorizedStatus = Response
				.status(Response.Status.UNAUTHORIZED)
				.entity("User cannot access the resource")
				.build();
		
		requestContext.abortWith(unauthorizedStatus);
	  }
	}
}