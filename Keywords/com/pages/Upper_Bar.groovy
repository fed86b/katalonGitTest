package com.pages
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.driver.WebUIDriverType
import com.system.LanguageHelper
import com.system.MyElement
import com.system.WebHelper
import com.system.enums.Enum_Create_Item
import com.system.enums.Enum_Language

public class Upper_Bar extends My_Item{
	static MyElement a_logout=new MyElement("a_logout","//a[@href = 'http://kmsqa3:501/kms/lh/logout']")
	static MyElement a_profile_avatar=new MyElement("profile-avatar","//*[@class = 'top-toolbar__section top-toolbar__profile']")
	static MyElement a_kms_home_icon=new MyElement("a_kms_home_icon","//*[@class = 'kms-icon kms-icon--home']")

	static MyElement txt_search=new MyElement("txt_search","//input[@id='search-input']")

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

	protected Upper_Bar(Enum_Language lang){
		super(lang)
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

	protected static  _search_by_first_description_word(def template=""){
		try {
			search_by_desc(template)
		} catch (Exception e) {
			my_exeption=e
			fail=true
			WebHelper.delay_medium()
			search_by_desc(template)
			fail=false
		}
		finally{
			if(fail)
				WebHelper.screenShoot(my_exeption.getMessage())
		}
	}

	private static search_by_desc(template) {
		if(!((DriverFactory.getExecutedBrowser() == WebUIDriverType.FIREFOX_DRIVER) ||
		(DriverFactory.getExecutedBrowser() ==WebUIDriverType.FIREFOX_HEADLESS_DRIVER))){
			String text=WebHelper.get_item_data(Enum_Create_Item.short_description)
			String[] words=text.split(" ")
			for(def word in words){
				CharSequence cs = word
				txt_search.write_slowly(word)
				WebHelper.delay_medium()
				def item_text="item_"+template
				def searched_word=String.format("//a[contains(.,'%s')]",item_text)
				if(!(new MyElement("a_serched_word",searched_word)).verifyText(item_text,false))
					assert false==true
			}
		}
	}
}
