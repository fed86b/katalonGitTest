package com.server.roles

import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import com.kms.katalon.core.context.TestCaseContext
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.pages.Kms_Page
import com.server.enums.Enum_Language
import com.server.enums.Enum_Role

import internal.GlobalVariable

public abstract  class User extends Kms_Page {

	protected static   Enum_Language language
	protected static  Enum_Role role
	protected static int interations=0
	protected User ( Enum_Role role,Enum_Language language){
		super(language)
		this.role=role
		this.language=language
	}

	static protected  Run_Test(TestCase test){
		boolean fail=false
		try {
			WebUI.callTestCase(test, [:], FailureHandling.STOP_ON_FAILURE)
			WebUI.waitForPageLoad(GlobalVariable.G_Wait)
			interations=0
		} catch (Exception e) {
			fail=true;
		}
		if(fail){
			for ( ;interations< GlobalVariable.G_Run_On_Fail; ) {
				interations+=1
				Login(test)
			}
		}
	}
}
