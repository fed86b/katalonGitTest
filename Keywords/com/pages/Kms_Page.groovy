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
		this._nav =nav
		this._web =AppManager.getInstance().getWebHelper()
	}


	public Kms_Page init_Kms_Page(Enum_Role role, Enum_Language lang){
		//WebUI.verifyAllLinksOnCurrentPageAccessible(true, [],FailureHandling.CONTINUE_ON_FAILURE)
		WebUI.waitForPageLoad(GlobalVariable.G_Wait)
		this.lang=lang
		this.role=role
		return this
	}
	public LogIn_Page logout(){
		_web.verify_text_MouseOver(findTestObject('Kms_Page_OR/Shared/span__profile-avatar'))
		switch(lang){
			case Enum_Language.RUSSIAN.toString():
				_web.verify_text_click(findTestObject('Kms_Page_OR/Languages/Ru/a_Logout_Ru'),'Выход')
				break
			case Enum_Language.ENGLISH.toString():
				_web.verify_text_click(findTestObject('Kms_Page_OR/Languages/En/a_Logout_En'),'Logout')


				break
			case Enum_Language.ARABIC.toString():assert true==false
				break
			case Enum_Language.BOLGARSKY.toString():assert true==false
				break
			case Enum_Language.CHINESE.toString():assert true==false
				break
			case Enum_Language.DANSK.toString().toString():assert true==false
				break
			case Enum_Language.HEBREW.toString():assert true==false
				break
			case Enum_Language.ITALIANO.toString():assert true==false
				break
			case Enum_Language.ROMANIA.toString():assert true==false
				break
		}

		return _nav.getLogin()
	}



	private Check_Administrative_Tabs() {
		if(!WebUI.verifyElementVisible
		(findTestObject('Kms_Page_OR/Languages/En/Roles/Administrator/tabs/span_glyphicon glyphicon-optio'))){

			WebUI.verifyElementVisible
			(findTestObject('Kms_Page_OR/Languages/En/Roles/Administrator/tabs/a_Authorizations_tab'))

			WebUI.verifyElementVisible
			(findTestObject('LoginPage_English_OR/Roles/adminisrtator_tabs/a_Code_Tables'))

			WebUI.verifyElementVisible
			(findTestObject('Kms_Page_OR/Languages/En/Roles/Administrator/tabs/a_Feedback Assignment'))

			WebUI.verifyElementVisible
			(findTestObject('Kms_Page_OR/Languages/En/Roles/Administrator/tabs/a_Forum'))

			WebUI.verifyElementVisible
			(findTestObject('Kms_Page_OR/Languages/En/Roles/Administrator/tabs/a_Manage Homepages'))

			WebUI.verifyElementVisible
			(findTestObject('Kms_Page_OR/Languages/En/Roles/Administrator/tabs/a_Utilities'))

			WebUI.verifyElementVisible
			(findTestObject('Kms_Page_OR/Languages/En/Roles/Administrator/tabs/a_Wizard'))

			WebUI.verifyElementVisible
			(findTestObject('Kms_Page_OR/Languages/En/Roles/Administrator/tabs/a_Workflow'))
		}
	}
}
