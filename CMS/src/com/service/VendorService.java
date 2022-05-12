package com.service;

import com.beans.Login;
import com.db.DB;

public class VendorService {
DB db=new DB(); 
	
	public boolean checkLogin(Login login) {
		 
		return db.checkLogin(login);
	}

	public String fetchNameByUsername(String username) {
		 
		return db.fetchNameByUsername(username);
	}
}
