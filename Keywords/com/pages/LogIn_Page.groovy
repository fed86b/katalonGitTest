package com.pages

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.server.WebHelper
import com.server.enums.Enum_Create_Item
import com.server.enums.Enum_Language
import com.server.enums.Enum_Login_Data
import com.server.enums.Enum_Role
import com.server.enums.Enum_Tables

import internal.GlobalVariable

public class LogIn_Page {
	static def link_change_lang=WebHelper.createElement("link_change_lang","//a[@href='#login-change-language']")
	static def caret_lang=WebHelper.createElement("caret_lang","//span[@class='caret']")
	static def en_lang=WebHelper.createElement("en_lang","//span[(text() = 'English' or . = 'English')]")
	static def ru_lang=WebHelper.createElement("ru_lang","//span[@class = 'text' and (text() = 'Русский' or . = 'Русский')]")
	static def auth_Login_button=WebHelper.createElement("auth_Login_button","//button[@type = 'submit']")
	static def login_Kms_button=WebHelper.createElement("login_Kms_button","//button[@class = 'kms-login__form-submit ladda-button']")
	static def password_txt=WebHelper.createElement("password_txt","//input[@id = 'login-password']")
	static def userName_txt=WebHelper.createElement("userName_txt","//input[@id = 'login-username']")
	static def forgot_password=WebHelper.createElement("forgot_password","//a[@data-target = '#forgot-password-modal']")
	static def dropDown_roles=WebHelper.createElement("dropDown_roles","//button[@class = 'btn dropdown-toggle btn-default']")
	static def administative_role=WebHelper.createElement("administative_role","//a[@role = 'option' and (contains(text(), 'Administrator') or contains(., 'Administrator'))]")
	static def cm_role=WebHelper.createElement("cm_role","//a[@role = 'option' and (contains(., 'Content Manager'))]")
	static  def title_layout=WebHelper.createElement("title_layout","//h1[@class = 'kms-login__header']")
	static  def loginForm=WebHelper.createElement("loginForm","//form[@name = 'loginForm']")




	static String userName=""
	static String password=""

	public static check_Layout_Title(Enum_Language language) {
		switch(language){
			case Enum_Language.RUSSIAN:
				WebHelper.verify_text_visibility(title_layout, 'ВЫБРАТЬ МАКЕТ')
				break
			case Enum_Language.ENGLISH:WebHelper.verify_text_visibility(title_layout, 'CHOOSE A LAYOUT')
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
				WebHelper.verify_text_visibility(title_layout, 'CHOOSE A LAYOUT')
		}
	}

	public static check_Login_Title(Enum_Language language) {
		check_Layout_Title( language)
	}

	public static check_Logos_Title_Links() {
		try{
			WebUI.waitForPageLoad(GlobalVariable.G_Wait)

			WebUI.verifyAllLinksOnCurrentPageAccessible(true, [],FailureHandling.CONTINUE_ON_FAILURE)


			WebHelper.verify_texts(WebUI.getWindowTitle(), 'KMS lighthouse')

			check_Login_Title()

			WebHelper.verify_text_visibility(findTestObject('Login_Page_OR/Visual/Kms_logo'))

			WebHelper.verify_text_visibility(findTestObject('Login_Page_OR/Visual/lang_Logo'))

			WebHelper.verify_text_visibility(findTestObject('Login_Page_OR/Visual/kms_ltd'), '© 2017 KMS lighthouse Ltd')
		}
		catch (Exception e) {

			WebHelper.screenShoot(e.getMessage())
		}
	}

	public static chooseLanguage(Enum_Language language) {
		try {
			WebHelper.verify_text_click(link_change_lang, 'Change Language')
			WebHelper.verify_text_click(caret_lang)
			switch(language){
				case Enum_Language.RUSSIAN:
					WebHelper.verify_text_click_with_hover(ru_lang, 'Русский')
					break
				case Enum_Language.ENGLISH:
					WebHelper.verify_text_click_with_hover(en_lang, 'English')
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
					WebUI.click(en_lang)
			}
		}
		catch (Exception e) {
			WebHelper.screenShoot(e.getMessage())
			throw e
		}
	}

	public static click_LogIn_With_UserName_And_Password() {
		try {
			WebHelper.verify_text_click(auth_Login_button)
		}catch (Exception e) {
			WebHelper.screenShoot(e.getMessage())
			throw e
		}
	}



	public static click_Submit(Enum_Language language) {
		try {
			WebHelper.press_Enter(login_Kms_button)
			//WebHelper.submit(this.loginForm)
			//			switch(language){
			//				case Enum_Language.RUSSIAN:WebHelper.verify_text_click_with_hover(login_Kms_button,'ВОЙТИ')
			//					break
			//				case Enum_Language.ENGLISH:WebHelper.verify_text_click_with_hover(login_Kms_button,'LOGIN')
			//					break
			//				case Enum_Language.ARABIC:assert true==false
			//					break
			//				case Enum_Language.BOLGARSKY:assert true==false
			//					break
			//				case Enum_Language.CHINESE:assert true==false
			//					break
			//				case Enum_Language.DANSK:assert true==false
			//					break
			//				case Enum_Language.HEBREW:assert true==false
			//					break
			//				case Enum_Language.ITALIANO:assert true==false
			//					break
			//				case Enum_Language.ROMANIA:assert true==false
			//					break
			//			}
		}
		catch (Exception e) {
			WebHelper.screenShoot(e.getMessage())
			throw e
		}
	}



	public static select_View(Enum_Role role) {
		try {
			WebHelper.verify_text_click(dropDown_roles)

			switch(role){

				case Enum_Role.ADMINISTRATOR:
					WebHelper.verify_text_click(administative_role,'Administrator')
					break
				case Enum_Role.SCR:assert false==true
					break
				case Enum_Role.MANAGER:assert false==true
					break
				case Enum_Role.HELP_DESK:assert false==true
					break
				case Enum_Role.CONTENT_CONTRIBUTOR:assert false==true
					break
				case Enum_Role.CONTENT_MANAGER:
					WebHelper.verify_text_click_with_hover(cm_role,'Content Manager')

					break
			}
		}
		catch (Exception e) {
			WebHelper.screenShoot(e.getMessage())
			throw e
		}
	}


	public static fill_UserName_Password(Enum_Role role) {
		try {
			visual_check_login_form()
			int row=-1
			switch(role){
				case Enum_Role.ADMINISTRATOR :
					row=1
					break
				case Enum_Role.SCR:
					row=2
					break
				case Enum_Role.MANAGER:assert false==true
					break
				case Enum_Role.HELP_DESK:assert false==true
					break
				case Enum_Role.CONTENT_CONTRIBUTOR:assert false==true
					break
				case Enum_Role.CONTENT_MANAGER:
					row=1
					break
			}
			userName=WebHelper.get_auth(Enum_Tables.Login_Data,Enum_Login_Data.userName, row)
			password=WebHelper.get_auth(Enum_Tables.Login_Data,Enum_Login_Data.password, row)

			WebHelper.write_text(userName_txt, userName)

			WebHelper.write_text(password_txt, password)
		}
		catch (Exception e) {
			WebHelper.screenShoot(e.getMessage())
			throw e
		}
	}

	private static visual_check_login_form() {
		try {

			WebHelper.verify_text_visibility(findTestObject('Login_Page_OR/Visual/pass_Logo'))

			WebHelper.verify_text_visibility(findTestObject('Login_Page_OR/Visual/userName_Logo'))


			WebHelper.verify_text_visibility(findTestObject('Login_Page_OR/Languages/En/span_Username'), 'Username')

			WebHelper.verify_text_visibility(findTestObject('Login_Page_OR/Languages/En/span_Password'), 'Password')
		}
		catch (Exception e) {

			WebHelper.screenShoot(e.getMessage())
		}
	}
}
