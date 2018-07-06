package com.pages

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.server.WebHelper
import com.server.enums.Enum_Language

public abstract  class Kms_Page {

	static Enum_Language lang
	static protected def logout_button=WebHelper.createElement("logout_button","//a[@href = 'http://kmsqa3:501/kms/lh/logout']")
	static protected def kms_home_icon=WebHelper.createElement("kms_home_icon","//*[@class = 'kms-icon kms-icon--home']")
	static protected def profile_avatar=WebHelper.createElement("profile-avatar","//*[@class = 'top-toolbar__section top-toolbar__profile']")



	protected Kms_Page(Enum_Language lang){
		this.lang=lang
	}

	protected static  logout(){
		try {
			WebHelper.verify_text_MouseOver(profile_avatar)
			String logout=""
			switch(this.lang){
				case Enum_Language.RUSSIAN:logout='Выход'
					break
				case Enum_Language.ENGLISH:logout='Logout'
					break
				case Enum_Language.ARABIC:assert true==false
					break
				case Enum_Language.BOLGARSKY:assert true==false
					break
				case Enum_Language.CHINESE:assert true==false
					break
				case Enum_Language.DANSK.toString():assert true==false
					break
				case Enum_Language.HEBREW:assert true==false
					break
				case Enum_Language.ITALIANO:assert true==false
					break
				case Enum_Language.ROMANIA:assert true==false
					break
			}


			WebHelper.verify_text_click_with_Delay(logout_button,logout)
		}
		catch (Exception e) {
			WebHelper.screenShoot(e.getMessage())
			throw e
		}
	}


	protected static  click_Home_Button(){
		try {
			WebHelper.verify_text_click_with_Delay(kms_home_icon)
		}
		catch (Exception e) {
			WebHelper.screenShoot(e.getMessage())
		}
	}
}