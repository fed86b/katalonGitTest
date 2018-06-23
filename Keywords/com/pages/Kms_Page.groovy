package com.pages

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.server.AppManager
import com.server.Enum_Language
import com.server.Enum_Role
import com.server.NavigationHelper
import com.server.WebHelper

import internal.GlobalVariable

public class Kms_Page {

	NavigationHelper _nav
	WebHelper _web
	Enum_Language lang
	Enum_Role role

	public Kms_Page(NavigationHelper nav){
		this._nav =AppManager.getInstance().getNavHelper()
		this._web =AppManager.getInstance().getWebHelper()
		WebUI.verifyAllLinksOnCurrentPageAccessible(true, [],FailureHandling.STOP_ON_FAILURE)
	}

	public LogIn_Page logout(){
		lang=Enum_Language.valueOf(GlobalVariable.G_Language)
		switch(lang){
			case Enum_Language.RUSSIAN:
				assert true==false
				break
			case Enum_Language.ENGLISH:logout_En()
				break
			case Enum_Language.ARABIC:assert true==false
				break
			case Enum_Language.BOLGARSKY:assert true==false
				break
			case Enum_Language.CHINESE:assert true==false
				break
			case Enum_Language.DANSK:assert true==false
				break
			case Enum_Language.HEBREW:assert true==false
				break
			case Enum_Language.ITALIANO:assert true==false
				break
			case Enum_Language.ROMANIA:assert true==false
				break
			default :
				logout_En()
		}

		return _nav.getLogin()
	}

	private logout_En() {

		_web.verify_text_clicability('Kms_Page_OR/En/a_Logout_En','LOGOUT')


		WebUI.click(findTestObject('Kms_Page_OR/En/a_Logout_En'))
	}


	private Check_Administrative_Tabs() {
		if(!WebUI.verifyElementVisible
		(findTestObject('Kms_Page_OR/En/Roles/Administrator/tabs/span_glyphicon glyphicon-optio'))){

			WebUI.verifyElementVisible
			(findTestObject('Kms_Page_OR/En/Roles/Administrator/tabs/a_Authorizations_tab'))

			WebUI.verifyElementVisible
			(findTestObject('LoginPage_English_OR/Roles/adminisrtator_tabs/a_Code_Tables'))

			WebUI.verifyElementVisible
			(findTestObject('Kms_Page_OR/En/Roles/Administrator/tabs/a_Feedback Assignment'))

			WebUI.verifyElementVisible
			(findTestObject('Kms_Page_OR/En/Roles/Administrator/tabs/a_Forum'))

			WebUI.verifyElementVisible
			(findTestObject('Kms_Page_OR/En/Roles/Administrator/tabs/a_Manage Homepages'))

			WebUI.verifyElementVisible
			(findTestObject('Kms_Page_OR/En/Roles/Administrator/tabs/a_Utilities'))

			WebUI.verifyElementVisible
			(findTestObject('Kms_Page_OR/En/Roles/Administrator/tabs/a_Wizard'))

			WebUI.verifyElementVisible
			(findTestObject('Kms_Page_OR/En/Roles/Administrator/tabs/a_Workflow'))
		}
	}
}
