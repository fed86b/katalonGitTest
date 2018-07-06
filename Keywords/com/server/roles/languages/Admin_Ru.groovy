package com.server.roles.languages
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.server.enums.Enum_Language
import com.server.roles.Admin

import internal.GlobalVariable
public class Admin_Ru extends Admin{

	protected Admin_Ru(){
		super(Enum_Language.RUSSIAN)

	}

	static protected  Login_Ru(){
		Login(findTestCase('Test Cases/LogIn_Tests/Languages/Ru/LogIn_Administrator_Ru_Test'))
	}
}
