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

public class LogIn_Page {

	NavigationHelper _nav
	String userName=""
	WebHelper _web
	String password=""
	Enum_Language lang
	Enum_Role role

	public LogIn_Page(NavigationHelper nav){
		_nav =nav
		_web=AppManager.getInstance().getWebHelper()
	}


	private check_Layout_Title() {

		switch(lang){
			case Enum_Language.RUSSIAN:
				_web.verify_text_visibility(findTestObject('Login_Page_OR/Languages/Ru/Title_Choose_Layout_Ru'), 'ВЫБРАТЬ МАКЕТ')
				break

			default :
				_web.verify_text_visibility(findTestObject('Login_Page_OR/Languages/En/Title_Choose_layout_En'), 'CHOOSE A LAYOUT')
		}
	}

	public check_Logos_Title_Links() {
		WebUI.waitForPageLoad(GlobalVariable.G_Wait)
		WebUI.verifyAllLinksOnCurrentPageAccessible(true, [],FailureHandling.CONTINUE_ON_FAILURE)

		WebUI.verifyEqual(WebUI.getWindowTitle(), 'KMS lighthouse', FailureHandling.CONTINUE_ON_FAILURE)

		_web.verify_text_visibility(findTestObject('Login_Page_OR/Shared/Visual/Title_Login'), 'LOGIN')

		WebUI.verifyElementVisible(findTestObject('Login_Page_OR/Shared/Visual/Kms_logo'), FailureHandling.CONTINUE_ON_FAILURE)

		WebUI.verifyElementVisible(findTestObject('Login_Page_OR/Shared/Visual/lang_Logo'), FailureHandling.CONTINUE_ON_FAILURE)

		_web.verify_text_visibility(findTestObject('Login_Page_OR/Shared/Visual/kms_ltd'), '© 2017 KMS lighthouse Ltd')
	}

	public chooseLanguage(Enum_Language language) {

		lang=language
		_web.verify_text_click(findTestObject('Login_Page_OR/Shared/a_Change Language'), 'Change Language')
		_web.verify_text_click(findTestObject('Login_Page_OR/Shared/span_caret_Languages'))
		GlobalVariable.G_Language=lang
		switch(lang){
			case Enum_Language.RUSSIAN:
				_web.verify_text_click(findTestObject('Login_Page_OR/Languages/Ru/span_Russian'), 'Русский')
				break
			case Enum_Language.ENGLISH:
				_web.verify_text_click(findTestObject('Login_Page_OR/Languages/En/span_English'), 'English')
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
				WebUI.click(findTestObject('Login_Page_OR/Languages/En/span_English'))
		}
	}

	public click_LogIn_With_UserName_And_Password() {

		_web.verify_text_click(findTestObject('Login_Page_OR/Shared/button_Login'),'LOGIN')
	}

	private click_Submit_En() {

		_web.verify_text_click(findTestObject('Login_Page_OR/Languages/En/button_EnterKms_Login'),'LOGIN')
	}

	private click_Submit_Ru() {

		_web.verify_text_click(findTestObject('Login_Page_OR/Languages/Ru/button_LogIn_Kms_Ru'),'ВОЙТИ')
	}


	public click_Submit() {

		switch(lang){
			case Enum_Language.RUSSIAN:
				click_Submit_Ru()
				break
			case Enum_Language.ENGLISH:click_Submit_En()
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
				click_Submit_En()
		}
	}



	public select_View(Enum_Role defaultRole) {

		GlobalVariable.G_Cur_User_Role =role=defaultRole
		_web.verify_text_click(findTestObject('Login_Page_OR/Shared/span_caret_View'))

		switch(role){

			case Enum_Role.ADMINISTRATOR:
				_web.verify_text_click(findTestObject('Object Repository/Login_Page_OR/Shared/Roles_DropBox/a_Administrator'),'Administrator')
				break
			case Enum_Role.SCR:assert false==true
				break
			case Enum_Role.MANAGER:assert false==true
				break
			case Enum_Role.HELP_DESK:assert false==true
				break
			case Enum_Role.CONTENT_CONTRIBUTOR:assert false==true
				break
			case Enum_Role.CONTENT_MANAGER: assert false==true
				break
		}
	}


	public fill_UserName_Password(Enum_Role defaultRole) {
		visual_check_login_form()
		GlobalVariable.G_Cur_User_Role=role=defaultRole
		int row=-1
		switch(role){
			case Enum_Role.ADMINISTRATOR :
				row=1
				GlobalVariable.G_Cur_User_ID=1
				break
			case Enum_Role.SCR:
				row=2
				GlobalVariable.G_Cur_User_ID=2
				break
			case Enum_Role.MANAGER:assert false==true
				break
			case Enum_Role.HELP_DESK:assert false==true
				break
			case Enum_Role.CONTENT_CONTRIBUTOR:assert false==true
				break
			case Enum_Role.CONTENT_MANAGER: assert false==true
				break
		}
		userName=_web.get_data(1, row)
		password=_web.get_data(2, row)

		WebUI.setText(findTestObject('Login_Page_OR/Shared/input_username'), userName)

		WebUI.setText(findTestObject('Login_Page_OR/Shared/input_password'), password)
	}

	public visual_check_login_form() {


		_web.verify_text_visibility(findTestObject('Login_Page_OR/Shared/input_username'),"",true)

		_web.verify_text_visibility(findTestObject('Login_Page_OR/Shared/input_password'),"",true)

		_web.verify_text_visibility(findTestObject('Login_Page_OR/Shared/Visual/pass_Logo'))

		_web.verify_text_visibility(findTestObject('Login_Page_OR/Shared/Visual/userName_Logo'))

		_web.verify_text_clicability(findTestObject('Login_Page_OR/Shared/button_Login'),'LOGIN')

		_web.verify_text_visibility(findTestObject('Login_Page_OR/Shared/Visual/span_Username'), 'Username')

		_web.verify_text_visibility(findTestObject('Login_Page_OR/Shared/Visual/span_Password'), 'Password')
	}
}
