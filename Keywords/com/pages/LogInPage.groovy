package com.pages

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.system.CompareImages
import com.system.LanguageHelper
import com.system.LoginElement
import com.system.WebHelper
import com.system.enums.EnumLanguage
import com.system.enums.EnumLoginData
import com.system.enums.EnumRole

import internal.GlobalVariable

public  class LogInPage {

	/*
	 * for future 
	 def forgot_password=new LoginElement("forgot_password","//a[@data-target = '#forgot-password-modal']")
	 def loginForm=new LoginElement("loginForm","//form[@name = 'loginForm']")
	 */
	static isBacked=false
	static protected EnumLanguage lang
	static protected EnumRole role
	static protected boolean fail=false
	static protected int interations=0
	static protected Exception my_exeption
	static protected LanguageHelper langHelper
	protected LogInPage (EnumRole role,EnumLanguage language){
		this.lang=language
		this.role=role
		langHelper=new LanguageHelper(lang)
	}

	private static check_Layout_Title() {
		def layout=LanguageHelper.getText('CHOOSE A LAYOUT')
		(new LoginElement("title_layout","//h1[@class = 'kms-login__header']")).verifyText(layout)
	}

	private static check_login_Title() {
		def text="LOGIN"
		if(isBacked)
			text=LanguageHelper.getText('LOGIN')
		(new LoginElement("title","//h1[@class = 'kms-login__header']")).verifyText(text)
	}

	protected static _check_Logos_Title_Links() {
		try{
			WebUI.waitForPageLoad(GlobalVariable.G_Wait)

			WebUI.verifyAllLinksOnCurrentPageAccessible(true, [])

			check_login_Title()

			WebHelper.verify_texts(WebUI.getWindowTitle(), 'KMS lighthouse')

			(new LoginElement(findTestObject('Login_Page_OR/Visual/Kms_logo'))).isVisible(false)
			(new LoginElement(findTestObject('Login_Page_OR/Visual/lang_Logo'))).isVisible(false)
			(new LoginElement(findTestObject('Login_Page_OR/Visual/kms_ltd'))).verifyText('© 2017 KMS lighthouse Ltd')
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

	protected static clickСhooseLanguage() {
		def chooseLang="Change Language"
		def link_change_lang=new LoginElement("link_change_lang","//a[@href='#login-change-language']")
		def caret_lang=new LoginElement("caret_lang","//span[@class='caret']")

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
		(new LoginElement("link_lang",xpath)).click(str_lang)
	}

	protected static clickLogInWithUserNameAndPassword() {
		def auth_Login_button=new LoginElement("auth_Login_button","//button[@type = 'submit']")
		def LOGIN=LanguageHelper.getText("LOGIN")
		try {
			auth_Login_button.click(LOGIN)
			WebHelper.wait_for_Edge()
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



	protected static enterToKms() {
		def login_Kms_button=new LoginElement("login_Kms_button","//button[@class = 'kms-login__form-submit ladda-button']")
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



	protected static selectView() {
		def dropDown_roles=new LoginElement("dropDown_roles","//button[@class = 'btn dropdown-toggle btn-default']")
		def link_role
		def str_role=""
		try {
			check_Layout_Title()
			dropDown_roles.click()

			switch(role){

				case EnumRole.ADMINISTRATOR:str_role="Administrator"
					break
				case EnumRole.SCR:str_role="SCR"
					break
				case EnumRole.MANAGER:assert false==true
					break
				case EnumRole.HELP_DESK:assert false==true
					break
				case EnumRole.CONTENT_CONTRIBUTOR:assert false==true
					break
				case EnumRole.CONTENT_MANAGER:str_role="Content Manager"
					break
			}
			def xpath=String.format("//span[ (contains(., '%s'))]", str_role)
			link_role=new LoginElement("link_role",xpath)
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


	protected static fillUserNamePassword() {
		def userName=""
		def password=""
		def password_txt=new LoginElement("password_txt","//input[@id = 'login-password']")
		def userName_txt=new LoginElement("userName_txt","//input[@id = 'login-username']")
		try {
			visual_check_login_form()
			int row=-1
			switch(role){
				case EnumRole.ADMINISTRATOR :
					row=1
					break
				case EnumRole.SCR:
					row=2
					break
				case EnumRole.MANAGER:assert false==true
					break
				case EnumRole.HELP_DESK:assert false==true
					break
				case EnumRole.CONTENT_CONTRIBUTOR:assert false==true
					break
				case EnumRole.CONTENT_MANAGER:
					row=1
					break
			}
			userName=WebHelper.get_auth(EnumLoginData.userName, row)
			password=WebHelper.get_auth(EnumLoginData.password, row)

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
		def span_password=new LoginElement("span_password","//span[@class='kms-login__label-text'  and contains(text(),'Password')]")
		def span_userName=new LoginElement("span_userName","//span[@class='kms-login__label-text'  and contains(text(),'Username')]")

		try {

			(new LoginElement(findTestObject('Login_Page_OR/Visual/pass_Logo'))).isVisible(false)

			(new LoginElement(findTestObject('Login_Page_OR/Visual/userName_Logo'))).isVisible(false)

			span_userName.isVisible(false)

			span_password.isVisible(false)
		}
		catch (Exception e) {

			WebHelper.screenShoot(e.getMessage())
		}
	}
}
