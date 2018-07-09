package com.server.roles.languages

import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.server.enums.Enum_Language
import com.server.roles.Admin

import internal.GlobalVariable

public  class Admin_En extends Admin {

	protected Admin_En(){
		super(Enum_Language.ENGLISH)
	}

	static protected  Login_En(){
		Run_Test(findTestCase('Test Cases/LogIn_Tests/Languages/En/Login_Administrator_En_Test'))
	}
}
