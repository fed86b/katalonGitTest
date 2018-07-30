package com.system.roles.languages
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

import com.system.WebHelper
import com.system.enums.EnumLanguage
import com.system.roles.Admin
public class Admin_Ru extends Admin{

	protected Admin_Ru(){
		super(EnumLanguage.RUSSIAN)
	}

	static protected  Login_Ru(){
		WebHelper.Run_Test(findTestCase('Test Cases/LogIn_Tests/Languages/Ru/LogIn_Administrator_Ru_Test'))
	}
}
