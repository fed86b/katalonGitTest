package com.pages

import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.google.common.base.CaseFormat
import com.google.common.base.CharMatcher.BreakingWhitespace
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.server.AppManager
import com.server.Enum_Language
import com.server.Enum_Role
import com.server.NavigationHelper
import com.server.WebHelper
import com.sun.org.apache.xalan.internal.xsltc.compiler.ForEach
import com.sun.xml.internal.ws.addressing.policy.AddressingPolicyMapConfigurator.AddressingAssertion
import com.sun.xml.internal.ws.policy.spi.AssertionCreationException

import internal.GlobalVariable

public class LogIn_Page {

	NavigationHelper _nav
	WebHelper _web
	public LogIn_Page(){
		_nav =AppManager.getInstance().getNavHelper()
		_web=AppManager.getInstance().getWebHelper()
		WebUI.verifyAllLinksOnCurrentPageAccessible(true, [],FailureHandling.STOP_ON_FAILURE)
	}

	String userName=""
	String password=""
	Enum_Language lang
	Enum_Role role
	public LogIn_Page authenticate_User(Enum_Language lang,Enum_Role role){

		this.lang=lang
		this.role=role

		check_Logos_Title()

		fill_UserName_Password()

		chooseLanguage()

		click_LogIn_With_UserName_And_Password()

		return this
	}

	public Kms_Page logIn_To_KMS() {

		select_View()

		check_Layout_Title()

		click_Submit()

		return _nav.getKms()
	}

	private check_Layout_Title() {

		switch(lang){
			case Enum_Language.RUSSIAN:
				_web.verify_text_visibility('Login_Page_OR/Ru/Title_Choose_Layout_Ru', 'ВЫБРАТЬ МАКЕТ')
				break

			default :
				_web.verify_text_visibility('Login_Page_OR/En/Title_Choose_layout_En', 'CHOOSE A LAYOUT')
		}
	}

	private check_Logos_Title() {

		assert 'LOGIN'==WebUI.getWindowTitle()

		_web.verify_text_visibility('Login_Page_OR/Shared/Visual/Title_Login', 'LOGIN')

		WebUI.verifyElementVisible(findTestObject('Login_Page_OR/Shared/Visual/Kms_logo'), FailureHandling.CONTINUE_ON_FAILURE)

		WebUI.verifyElementVisible(findTestObject('Login_Page_OR/Shared/Visual/lang_Logo'), FailureHandling.CONTINUE_ON_FAILURE)

		_web.verify_text_visibility('Login_Page_OR/Shared/Visual/kms_ltd', '© 2017 KMS lighthouse Ltd')
	}

	private chooseLanguage() {

		_web.verify_text_clicability('Login_Page_OR/Shared/span_caret_Languages', 'Change Language')
		GlobalVariable.G_Language=lang
		switch(lang){
			case Enum_Language.RUSSIAN:
				WebUI.click(findTestObject('Login_Page_OR/Ru/span_Russian'))
				break
			case Enum_Language.ENGLISH:WebUI.click(findTestObject('Login_Page_OR/En/span_English'))

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
				WebUI.click(findTestObject('Login_Page_OR/En/span_English'))
		}
	}

	private click_LogIn_With_UserName_And_Password() {

		_web.verify_text_clicability('Login_Page_OR/Shared/button_Login','LOGIN')

		WebUI.click(findTestObject('Login_Page_OR/Shared/button_Login'))
	}

	private click_Submit_En() {

		_web.verify_text_clicability('Login_Page_OR/En/button_EnterKms_Login','LOGIN')

		WebUI.click(findTestObject('Login_Page_OR/En/button_EnterKms_Login'))
	}

	private click_Submit_Ru() {

		_web.verify_text_clicability('Login_Page_OR/Ru/button_LogIn_Kms_Ru','ВОЙТИ')

		WebUI.click(findTestObject('Login_Page_OR/Ru/button_LogIn_Kms_Ru'))
	}


	private click_Submit() {

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



	private select_View() {

		_web.verify_text_clicability('Login_Page_OR/Shared/span_caret_View')

		WebUI.click(findTestObject('Login_Page_OR/Shared/span_caret_View'))

		switch(role){

			case Enum_Role.ADMINISTRATOR:
				_web.verify_text_clicability('LoginPage_English_OR/Roles/Administrator/a_Administrator','ADMINISTRATOR')
				WebUI.click(findTestObject('LoginPage_English_OR/Roles/Administrator/a_Administrator'))
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


	private fill_UserName_Password() {
		visual_check_login_form()
		GlobalVariable.G_Cur_User_Role=role
		int row=0
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

	private visual_check_login_form() {

		_web.verify_text_visibility('Login_Page_OR/Shared/input_username',true)

		_web.verify_text_visibility('Login_Page_OR/Shared/input_password',true)

		_web.verify_text_visibility('Login_Page_OR/Shared/Visual/pass_Logo')

		_web.verify_text_visibility('Login_Page_OR/Shared/Visual/userName_Logo')

		_web.verify_text_clicability('Login_Page_OR/Shared/button_Login')

		_web.verify_text_visibility('Login_Page_OR/Shared/Visual/span_Username', 'Username')

		_web.verify_text_visibility('Login_Page_OR/Shared/Visual/span_Password', 'Password')
	}
}
