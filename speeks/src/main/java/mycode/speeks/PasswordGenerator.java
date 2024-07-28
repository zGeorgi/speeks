package mycode.speeks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class PasswordGenerator {
		
	PasswordGenerator(){
		
	}
	private static final String LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DIGITS = "0123456789";
    private static final String PUNCTUATION = "!@#$%&*()_+-=[]|,./?><";
    
    List<String> arrlist = new ArrayList<String>();
    List<String> position = new ArrayList<String>();
    List<String> list = new ArrayList<String>();
    
     public String generate() {
    	 // any type less one time
    	 arrlist.add("LOWER");
	      arrlist.add("UPPER");
	      arrlist.add("DIGITS");  
	      arrlist.add("PUNCTUATION");
	     // System.out.println(arrlist);
	      // random times for next positions
	      Random rand = new Random(System.nanoTime());
	      list = arrlist; 
	      //System.out.println(list);
	      for (int i = 0; i <6; i++) {
	    	  list.add((String) arrlist.get(rand.nextInt(4))) ;  //add ore six random 
	    	 
				}// System.out.println(list);
	   //swap digits   
	      Collections.shuffle(list);   // mix the charachters 
	      position=list; 
	      //System.out.println(position);
	      Random random = new Random(System.nanoTime());
	      String pass ="" ;
	      
	      for (int i = 0; i < 10; i++) {
		    	//System.out.println(position.get(i));
		    	if(position.get(i).equals("LOWER")) {		    		
		    		int posit =random.nextInt(LOWER.length());
		    		pass=pass+LOWER.charAt(posit);
		    	}
		    	
		    	if(position.get(i).equals("UPPER")) {		    	
		    		int posit =random.nextInt(UPPER.length());			    	
			    	pass=pass+UPPER.charAt(posit);
		    	}
		    	
		    	if(position.get(i).equals("DIGITS")) {	    	
		    		int posit =random.nextInt(DIGITS.length());			   
			    	pass=pass+DIGITS.charAt(posit);
		    	}
		    	
		    	if(position.get(i).equals("PUNCTUATION")) {		    	
		    		int posit =random.nextInt(PUNCTUATION.length());			    	
			    	pass=pass+PUNCTUATION.charAt(posit);
		    	}
			}
    	 return pass;
     }
	
}
