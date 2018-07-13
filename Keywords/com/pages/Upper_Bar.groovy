package com.pages
import com.system.LanguageHelper
import com.system.MyElement
import com.system.WebHelper
import com.system.enums.Enum_Language

public class Upper_Bar extends My_Item{
	static MyElement a_logout=new MyElement("a_logout","//a[@href = 'http://kmsqa3:501/kms/lh/logout']")
	static MyElement a_profile_avatar=new MyElement("profile-avatar","//*[@class = 'top-toolbar__section top-toolbar__profile']")
	
	protected Upper_Bar(Enum_Language lang){
		super(lang)
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
}
