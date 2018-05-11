package es.uji.ei1027.sape.dao;

import java.util.HashMap;
import java.util.Map;

import org.jasypt.util.password.BasicPasswordEncryptor;
import org.springframework.stereotype.Repository;

import es.uji.ei1027.sape.model.UserDetails;

@Repository
public class UserDAO {
	
	final Map<String, UserDetails> knownUsers = new HashMap<String, UserDetails>();
	
	public UserDAO(){
		BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
		
	    UserDetails user = new UserDetails(); 
	    user.setUsername("student"); 
	    user.setPassword(passwordEncryptor.encryptPassword("student")); 
	    user.setType(UserDetails.STUDENT);
	    knownUsers.put("student", user);
	      
	    user = new UserDetails(); 
	    user.setUsername("btc"); 
	    user.setPassword(passwordEncryptor.encryptPassword("btc")); 
	    user.setType(UserDetails.BTC);
	    knownUsers.put("btc", user);
	    
	    user = new UserDetails(); 
	    user.setUsername("dcc"); 
	    user.setPassword(passwordEncryptor.encryptPassword("dcc")); 
	    user.setType(UserDetails.DCC);
	    knownUsers.put("dcc", user);
	}
	
	public UserDetails loadUserByUsername(String username, String password){
		UserDetails user = knownUsers.get(username.trim());
		if (user == null)
			return null;
		BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor(); 
		if (passwordEncryptor.checkPassword(password, user.getPassword())) {
			return user; 
        } 
		else {
			return null;
		}
	}
	
}
