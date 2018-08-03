package com.pages

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
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
	static LoginElement passwordTxt=new LoginElement("passwordTxt","//input[@id = 'login-password']")
	static LoginElement userNameTxt=new LoginElement("userNameTxt","//input[@id = 'login-username']")
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
			WebHelper.catchException(e,false)
		}
	}

	protected static _Back(){
		WebUI.back()
		if(WebUI.getUrl().equalsIgnoreCase(GlobalVariable.G_Url))
			isBacked=true;
	}

	protected static clickСhooseLanguage() {
		def chooseLang="Change Language"
		if(isBacked)
			chooseLang=LanguageHelper.getText('Change Language')
		def link_change_lang=new LoginElement("link_change_lang","//a[@href='#login-change-language']")
		def caret_lang=new LoginElement("caret_lang","//span[@class='caret']")
		def str_lang=LanguageHelper.getText(lang.toString())
		def language=LanguageHelper.getText('Language')
		def xpath=String.format("//span[(contains(., '%s'))]", str_lang)
		def xpathLanguage=String.format("//span[(contains(., '%s'))]", language)
		try {
			link_change_lang.clickUntilNotAppear(caret_lang, chooseLang)
			def link_lang=new LoginElement("link_lang",xpath)
			def linkLanguage=new LoginElement("link_lang",xpathLanguage)
			caret_lang.clickUntilNotAppear(link_lang)
			link_lang.clickUntilNotDisappear(linkLanguage,str_lang)
		}
		catch (Exception e) {
			WebHelper.catchException(e)
		}
	}


	protected static clickLogInWithUserNameAndPassword() {
		def auth_Login_button=new LoginElement("auth_Login_button","//button[@type = 'submit']")
		def LOGIN=LanguageHelper.getText("LOGIN")
		try {
			auth_Login_button.clickUntilNotDisappear(userNameTxt,LOGIN)
		}catch (Exception e) {
			WebHelper.catchException(e)
		}
	}



	protected static enterToKms() {
		def login_Kms_button=new LoginElement("login_Kms_button","//button[@class = 'kms-login__form-submit ladda-button']")
		def LOGIN=LanguageHelper.getText("LOGIN")
		try {
			login_Kms_button.clickUntilNotDisappear()
		}
		catch (Exception e) {
			WebHelper.catchException(e)
		}
	}



	protected static selectView() {
		def dropDown_roles=new LoginElement("dropDown_roles","//button[@class = 'btn dropdown-toggle btn-default']")
		def dropDownMenuContent=new LoginElement("dropDownMenuContent","//ul[@class='dropdown-menu inner']")
		def link_role
		def str_role=""
		try {
			check_Layout_Title()
			def times=0
			while(dropDownMenuContent.getAtribute('aria-expanded')=='false'&&times<GlobalVariable.G_Wait){
				dropDown_roles.click()
				times+=1
			}
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
			times=0
			while(dropDownMenuContent.getAtribute('aria-expanded')=='true'&&times<GlobalVariable.G_Wait){
				link_role.click(str_role)
				times+=1
			}
		}
		catch (Exception e) {
			WebHelper.catchException(e)
		}
	}


	protected static fillUserNamePassword() {
		def userName=""
		def password=""
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

			userNameTxt.writeText(userName)
			passwordTxt.writeText(password)
		}
		catch (Exception e) {
			WebHelper.catchException(e)
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
			WebHelper.catchException(e,false)
		}
	}
}
