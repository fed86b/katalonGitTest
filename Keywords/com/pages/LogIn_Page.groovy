package com.pages

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.system.Compare_Images
import com.system.LanguageHelper
import com.system.Login_Element
import com.system.WebHelper
import com.system.enums.Enum_Language
import com.system.enums.Enum_Login_Data
import com.system.enums.Enum_Role

import internal.GlobalVariable

public  class LogIn_Page {

	/*
	 * for future 
	 def forgot_password=new Login_Element("forgot_password","//a[@data-target = '#forgot-password-modal']")
	 def loginForm=new Login_Element("loginForm","//form[@name = 'loginForm']")
	 */
	static isBacked=false
	static protected Enum_Language lang
	static protected Enum_Role role
	static protected boolean fail=false
	static protected int interations=0
	static protected Exception my_exeption
	static protected LanguageHelper langHelper
	protected LogIn_Page( Enum_Role role,Enum_Language language){
		this.lang=language
		this.role=role
		langHelper=new LanguageHelper(lang)
	}

	private static check_Layout_Title() {
		def layout=LanguageHelper.getText('CHOOSE A LAYOUT')
		(new Login_Element("title_layout","//h1[@class = 'kms-login__header']")).verifyText(layout)
	}

	private static check_login_Title() {
		def text="LOGIN"
		if(isBacked)
			text=LanguageHelper.getText('LOGIN')
		(new Login_Element("title","//h1[@class = 'kms-login__header']")).verifyText(text)
	}

	protected static _check_Logos_Title_Links() {
		try{
			WebUI.waitForPageLoad(GlobalVariable.G_Wait)

			WebUI.verifyAllLinksOnCurrentPageAccessible(true, [])

			check_login_Title()

			WebHelper.verify_texts(WebUI.getWindowTitle(), 'KMS lighthouse')

			(new Login_Element(findTestObject('Login_Page_OR/Visual/Kms_logo'))).isVisible(false)
			(new Login_Element(findTestObject('Login_Page_OR/Visual/lang_Logo'))).isVisible(false)
			(new Login_Element(findTestObject('Login_Page_OR/Visual/kms_ltd'))).verifyText('© 2017 KMS lighthouse Ltd')
		}
		catch (Exception e) {
			WebHelper.screenShoot(e.getMessage())
		}
	}

	protected static _Back(){
		WebUI.back()
		if(WebUI.getUrl().equalsIgnoreCase(GlobalVariable.G_Url))
			isBacked=true;
	}

	protected static _click_chooseLanguage() {
		def chooseLang="Change Language"
		def link_change_lang=new Login_Element("link_change_lang","//a[@href='#login-change-language']")
		def caret_lang=new Login_Element("caret_lang","//span[@class='caret']")

		try {
			if(isBacked)
				chooseLang=LanguageHelper.getText('Change Language')
			link_change_lang.click(chooseLang)
			caret_lang.click()
			select_language()
		}
		catch (Exception e) {
			my_exeption=e
			fail=true
			WebHelper.delay_medium()
			link_change_lang.click_with_delay(chooseLang)
			caret_lang.click()
			select_language()
			fail=false
		}
		finally{
			if(fail)
				WebHelper.screenShoot(my_exeption.getMessage())
		}
	}

	private static select_language() {
		def str_lang=LanguageHelper.getText(lang.toString())

		def xpath=String.format("//span[(contains(., '%s'))]", str_lang)
		(new Login_Element("link_lang",xpath)).click(str_lang)
	}

	protected static _click_LogIn_With_UserName_And_Password() {
		def auth_Login_button=new Login_Element("auth_Login_button","//button[@type = 'submit']")
		def LOGIN=LanguageHelper.getText("LOGIN")
		try {
			auth_Login_button.click(LOGIN)
			WebHelper.wait_for_Edge_ie()
		}catch (Exception e) {
			fail=true
			my_exeption=e
			WebHelper.delay_medium()
			auth_Login_button.click_with_delay(LOGIN)
			fail=false
		}
		finally{
			if(fail)
				WebHelper.screenShoot(my_exeption.getMessage())
		}
	}



	protected static _Enter_to_Kms() {
		def login_Kms_button=new Login_Element("login_Kms_button","//button[@class = 'kms-login__form-submit ladda-button']")
		def LOGIN=LanguageHelper.getText("LOGIN")
		try {
			login_Kms_button.press_Enter(LOGIN)
		}
		catch (Exception e) {
			fail=true
			my_exeption=e
			WebHelper.delay_medium()
			login_Kms_button.click_with_delay(LOGIN)
			fail=false
		}
		finally{
			if(fail)
				WebHelper.screenShoot(my_exeption.getMessage())
		}
	}



	protected static _select_View() {
		def dropDown_roles=new Login_Element("dropDown_roles","//button[@class = 'btn dropdown-toggle btn-default']")
		def link_role
		def str_role=""
		try {
			check_Layout_Title()
			dropDown_roles.click()

			switch(role){

				case Enum_Role.ADMINISTRATOR:str_role="Administrator"
					break
				case Enum_Role.SCR:str_role="SCR"
					break
				case Enum_Role.MANAGER:assert false==true
					break
				case Enum_Role.HELP_DESK:assert false==true
					break
				case Enum_Role.CONTENT_CONTRIBUTOR:assert false==true
					break
				case Enum_Role.CONTENT_MANAGER:str_role="Content Manager"
					break
			}
			def xpath=String.format("//span[ (contains(., '%s'))]", str_role)
			link_role=new Login_Element("link_role",xpath)
			link_role.click(str_role)
		}
		catch (Exception e) {
			fail=true
			my_exeption=e
			WebHelper.delay_medium()
			check_Layout_Title()
			dropDown_roles.click(str_role)
			link_role.click()
			fail=false
		}
		finally{
			if(fail)
				WebHelper.screenShoot(my_exeption.getMessage())
		}
	}


	protected static _fill_UserName_Password() {
		def userName=""
		def password=""
		def password_txt=new Login_Element("password_txt","//input[@id = 'login-password']")
		def userName_txt=new Login_Element("userName_txt","//input[@id = 'login-username']")
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
			userName=WebHelper.get_auth(Enum_Login_Data.userName, row)
			password=WebHelper.get_auth(Enum_Login_Data.password, row)

			userName_txt.write_text(userName)
			password_txt.write_text(password)
		}
		catch (Exception e) {
			fail=true
			my_exeption=e
			WebHelper.delay_medium()
			userName_txt.write_text(userName)
			password_txt.write_text(password)
			fail=false
		}
		finally{
			if(fail)
				WebHelper.screenShoot(my_exeption.getMessage())
		}
	}

	private static visual_check_login_form() {
		def span_password=new Login_Element("span_password","//span[@class='kms-login__label-text'  and contains(text(),'Password')]")
		def span_userName=new Login_Element("span_userName","//span[@class='kms-login__label-text'  and contains(text(),'Username')]")

		try {

			(new Login_Element(findTestObject('Login_Page_OR/Visual/pass_Logo'))).isVisible(false)

			(new Login_Element(findTestObject('Login_Page_OR/Visual/userName_Logo'))).isVisible(false)

			span_userName.isVisible(false)

			span_password.isVisible(false)
		}
		catch (Exception e) {

			WebHelper.screenShoot(e.getMessage())
		}
	}
}
