package com.pages

import com.system.LanguageHelper
import com.system.MyElement
import com.system.WebHelper
import com.system.enums.Enum_Language
import com.system.enums.Enum_Role

public abstract  class Kms_Page extends LogIn_Page {


	static MyElement a_logout=new MyElement("a_logout","//a[@href = 'http://kmsqa3:501/kms/lh/logout']")
	static MyElement a_kms_home_icon=new MyElement("a_kms_home_icon","//*[@class = 'kms-icon kms-icon--home']")
	static MyElement a_profile_avatar=new MyElement("profile-avatar","//*[@class = 'top-toolbar__section top-toolbar__profile']")



	protected Kms_Page( Enum_Role role,Enum_Language language){
		super(role,language)
	}

	protected static  _logout(){
		def Logout=LanguageHelper.getText('Logout')
		try {
			a_profile_avatar.MouseOver()
			a_logout.click_with_delay(Logout)
		}
		catch (Exception e) {
			my_exeption=e
			fail=true
			WebHelper.delay_medium()
			a_profile_avatar.MouseOver()
			a_logout.click_with_delay(Logout)
			fail=false
		}
		finally{
			if(fail)
				WebHelper.screenShoot(my_exeption.getMessage())
		}
	}


	protected static  _click_Home_Button(){
		try {
			a_kms_home_icon.click()
		}
		catch (Exception e) {
			my_exeption=e
			fail=true
			WebHelper.delay_medium()
			a_kms_home_icon.click_with_delay()
			fail=false
		}
		finally{
			if(fail)
				WebHelper.screenShoot(my_exeption.getMessage())
		}
	}
}