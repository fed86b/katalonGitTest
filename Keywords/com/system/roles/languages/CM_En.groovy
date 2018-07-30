package com.system.roles.languages

import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

import com.kms.katalon.core.model.FailureHandling
import com.system.LanguageHelper
import com.system.WebHelper
import com.system.enums.EnumLanguage
import com.system.roles.CM
public class CM_En extends CM  {


	protected CM_En(){
		super(EnumLanguage.English)
	}

	static protected  Login_En(){
		WebHelper.Run_Test(findTestCase('LogIn_Tests/Languages/En/Login_Content_Manager_En_Test'))
	}

	static protected  Create_General_Online_Test_En(){
		WebHelper.Run_Test(findTestCase('Kms_Tests/Languages/En/Item_Creation/Delete_all_Created_Items_Test_En'))
	}
}
