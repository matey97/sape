package es.uji.ei1027.sape.model;

public class UserDetails {
	
	public static final int STUDENT = 1, BTC = 2, DCC = 3;
	
	String username;
	String password;
	int type;
	
	public String getUsername(){
		return username;
	}
	
	public void setUsername(String username){
		this.username = username;
	}
	
	public String getPassword(){
		return password;
	}
	
	public void setPassword(String password){
		this.password = password;
	}
	
	public int getType(){
		return this.type;
	}
	
	public void setType(int type){
		this.type = type;
	}
}
