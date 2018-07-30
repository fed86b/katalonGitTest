package com.system.roles.languages

import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

import com.system.WebHelper
import com.system.enums.EnumLanguage
import com.system.roles.Admin

public  class Admin_En extends Admin {

	protected Admin_En(){
		super(EnumLanguage.English)
	}

	static protected  Login_En(){
		WebHelper.Run_Test(findTestCase('Test Cases/LogIn_Tests/Languages/En/Login_Administrator_En_Test'))
	}
}
