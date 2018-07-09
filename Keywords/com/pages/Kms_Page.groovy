package com.pages

import com.server.MyElement
import com.server.WebHelper
import com.server.enums.Enum_Language
import com.server.enums.Enum_Role

public abstract  class Kms_Page extends LogIn_Page {

	static Enum_Language lang
	static MyElement logout_button=new MyElement("logout_button","//a[@href = 'http://kmsqa3:501/kms/lh/logout']")
	static MyElement kms_home_icon=new MyElement("kms_home_icon","//*[@class = 'kms-icon kms-icon--home']")
	static MyElement profile_avatar=new MyElement("profile-avatar","//*[@class = 'top-toolbar__section top-toolbar__profile']")



	protected Kms_Page( Enum_Role role,Enum_Language language){
		super(role,language)
		this.lang=lang
	}

	protected static  _logout(){
		String logout=""
		try {
			profile_avatar.MouseOver()

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
			logout_button.click_with_delay().verifyText(logout)
		}
		catch (Exception e) {
			my_exeption=e
			fail=true
			WebHelper.delay_medium()
			profile_avatar.MouseOver()
			logout_button.click_with_delay().verifyText(logout)
			fail=false
		}
		finally{
			if(fail)
				WebHelper.screenShoot(my_exeption.getMessage())
		}
	}


	protected static  _click_Home_Button(){
		try {
			kms_home_icon.click()
		}
		catch (Exception e) {
			my_exeption=e
			fail=true
			WebHelper.delay_medium()
			kms_home_icon.click_with_delay()
			fail=false
		}
		finally{
			if(fail)
				WebHelper.screenShoot(my_exeption.getMessage())
		}
	}
}