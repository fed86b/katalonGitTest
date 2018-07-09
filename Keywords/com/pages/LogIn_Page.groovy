package com.pages

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.server.Login_Element
import com.server.WebHelper
import com.server.enums.Enum_Language
import com.server.enums.Enum_Login_Data
import com.server.enums.Enum_Role
import com.server.enums.Enum_Tables

import internal.GlobalVariable

public abstract class LogIn_Page {

	/*
	 * for future 
	 def forgot_password=new Login_Element("forgot_password","//a[@data-target = '#forgot-password-modal']")
	 def loginForm=new Login_Element("loginForm","//form[@name = 'loginForm']")
	 */
	static isBacked=false
	static protected Enum_Language language
	static protected Enum_Role role
	static protected boolean fail=false
	static protected int interations=0
	static protected Exception my_exeption
	protected LogIn_Page( Enum_Role role,Enum_Language language){
		this.language=language
		this.role=role
	}

	private static check_Layout_Title() {
		def text=""
		switch(language){
			case Enum_Language.RUSSIAN:text="ВЫБРАТЬ МАКЕТ"
				break
			case Enum_Language.ENGLISH:text="CHOOSE A LAYOUT"
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
		}
		(new Login_Element("title_layout","//h1[@class = 'kms-login__header']")).verifyText(text)
	}

	private static check_login_Title() {
		def text="LOGIN"

		if(isBacked)
			switch(language){
				case Enum_Language.RUSSIAN:text= "ВХОД"
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
			}
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
				switch(language){
					case Enum_Language.RUSSIAN:chooseLang='Выбери Язык'
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
				}
			link_change_lang.click().verifyText( chooseLang)
			caret_lang.click()
			select_language()
		}
		catch (Exception e) {
			my_exeption=e
			fail=true
			WebHelper.delay_medium()
			link_change_lang.click_with_delay()
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
		def str_lang=""
		switch(language){
			case Enum_Language.RUSSIAN:str_lang='Русский'
				break
			case Enum_Language.ENGLISH:str_lang='English'
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
		}
		def xpath=String.format("//span[(contains(., '%s'))]", str_lang)
		(new Login_Element("link_lang",xpath)).click()
	}

	protected static _click_LogIn_With_UserName_And_Password() {
		def auth_Login_button=new Login_Element("auth_Login_button","//button[@type = 'submit']")
		try {
			auth_Login_button.click()
			WebHelper.wait_for_Edge_ie()
		}catch (Exception e) {
			fail=true
			my_exeption=e
			WebHelper.delay_medium()
			auth_Login_button.click_with_delay()
			fail=false
		}
		finally{
			if(fail)
				WebHelper.screenShoot(my_exeption.getMessage())
		}
	}



	protected static _Enter_to_Kms() {
		def login_Kms_button=new Login_Element("login_Kms_button","//button[@class = 'kms-login__form-submit ladda-button']")
		try {
			login_Kms_button.press_Enter()
		}
		catch (Exception e) {
			fail=true
			my_exeption=e
			WebHelper.delay_medium()
			login_Kms_button.click_with_delay()
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
		try {
			check_Layout_Title()
			dropDown_roles.click()
			def str_role=""
			switch(role){

				case Enum_Role.ADMINISTRATOR:str_role="Administrator"
					break
				case Enum_Role.SCR:assert false==true
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
			link_role.click()
		}
		catch (Exception e) {
			fail=true
			my_exeption=e
			WebHelper.delay_medium()
			check_Layout_Title()
			dropDown_roles.click()
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
