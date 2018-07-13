package com.system.roles

import com.pages.LogIn_Page
import com.pages.My_Item
import com.system.enums.Enum_Language
import com.system.enums.Enum_Role

public class User extends My_Item {
	
	
	

	static LogIn_Page loginPage
	static Enum_Role role
	static Enum_Language lang
	protected User(Enum_Role role, Enum_Language lang){
		super(lang)
		this.role=role
		this.lang=lang
	}

	protected static LogIn_Page getLoginPage() {
		return loginPage=new LogIn_Page(role, lang);
	}


	


	
}
