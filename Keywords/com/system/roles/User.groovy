package com.system.roles

import com.pages.ItemAbstract
import com.pages.LogInPage
import com.system.enums.EnumLanguage
import com.system.enums.EnumRole

public abstract class User extends ItemAbstract {

	static LogInPage loginPage
	static EnumRole role
	protected User(EnumRole role, EnumLanguage lang){
		super(lang)
		this.role=role
		this.lang=lang
	}

	protected static LogInPage getLoginPage() {
		return loginPage=new LogInPage(role, lang);
	}
}
