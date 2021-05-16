package com.TeamEclairs.passkeeper;

public class Password {
	
	//private variables
	
	String _account_type;
	String _username;
	String _url;
	String _password;
	String _notes;
	
	// Empty constructor
	public Password(){
		
	}
	// constructor
	public Password(String _account_type, String _username, String _url, String _password, String _notes){
		
		this._account_type = _account_type;
		this._username = _username;
		this._url = _url;
		this._password = _password;
		this._notes = _notes;
		
	}
	
	// constructor
	/*public Contact(String name, String _phone_number){
		this._name = name;
		this._phone_number = _phone_number;
	}*/
	
	// getting account type
	public String getAccountType(){
		return this._account_type;
	}
	
	// setting account type
	public void setAccountType(String _account_type){
		this._account_type = _account_type;
	}
	
	// getting Username
		public String getUsername(){
			return this._username;
		}
		
		// setting Username
		public void setUsername(String _username){
			this._username =_username ;
		}
		
		// getting Url
		public String getUrl(){
			return this._url;
		}
		
		// setting Url
		public void setUrl(String _url){
			this._url =_url ;
		}	
		// getting Password
		public String getPassword(){
			return this._password;
		}
		// setting Password
				public void setPassword(String _password){
					this._password =_password ;
				}	
				
				// getting Notes
				public String getNotes(){
					return this._notes;
				}		
		// setting Notes
		public void setNotes(String _notes){
			this._notes =_notes ;
		}	
		
			
}
