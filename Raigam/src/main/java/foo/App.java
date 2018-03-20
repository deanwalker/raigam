package foo;

import java.util.Random;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception
    {
    	int numberOfRequests = 100;
    	for(int i=0 ; i<numberOfRequests ;i++){
    		   
    	        
    	        RestTemplate template = new RestTemplate();
    	        String url = "http://telees.raigam.lk/index.php/news/saveTextVoteData";    	        
    	        
    	        String email = getRandomGmail();      
    	      
    	        String imei = getRandomImei();
    	        
    	        String data  = MCrypt.bytesToHex(new MCrypt().encrypt(email+ "~" + imei + "~" + "Kalana" + "~" + "Yureni" + "~" + "Koombiyo"));
    		
    			HttpHeaders headers = new HttpHeaders();
    			headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

    			MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
    			map.add("voteData", data);
    			map.add("name", "AndroidApp");
    			map.add("year", "2017");

    			HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

    			ResponseEntity<String> response = template.postForEntity( url, request , String.class );
    	        System.out.println(response.getBody());
    	        Thread.sleep(5000); 
    	      
    	}
    	
    	
    	
    	
       
    }
    
    private static String getRandomImei(){    	
    	StringBuffer tmp = new StringBuffer();    	
    	for(int i=0 ; i<15 ;i++){
    		Random rnd = new Random();
    		int rNumber = rnd.nextInt(10);
    		tmp.append(String.valueOf(rNumber));        	
    	}
    	return tmp.toString();		
	}
    
    private static String getRandomGmail(){
    	String SALTCHARS = "abcdmnpryinhdtdysjk1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 10) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        String email = saltStr +"@gmail.com";
        return email;
    }
}
