package com.service;

import com.beans.Login;
import com.db.DB;

public class CustomerService {
	DB db=new DB(); 
	
	public boolean checkLogin(Login login) {
		 
		return db.checkLogin(login);
	}

}
