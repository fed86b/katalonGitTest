package com.server.roles.languages

import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.pages.cm.languages.Briefing_En
import com.server.TemplateHelper
import com.server.enums.Enum_Language
import com.server.roles.CM

import internal.GlobalVariable
public class CM_En extends CM  {

	static final Enum_Language lang=Enum_Language.ENGLISH
	static Briefing_En briefing


	protected CM_En(){
		super(lang)

		briefing= TemplateHelper.getBriefing_En()
	}

	static protected  Login_En(){
		Run_Test(findTestCase('LogIn_Tests/Languages/En/Login_Content_Manager_En_Test'))
	}

	static protected  Create_Briefing_Online_Test_En(){
		Run_Test(findTestCase('Test Cases/Kms_Tests/Languages/En/Item_Creation/Create_Briefing_Online_Test_En'))
	}

	protected static delete_all_created_items_en(){
		delete_all_created_items("Yes")
	}
}
