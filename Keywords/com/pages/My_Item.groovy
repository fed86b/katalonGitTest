package com.pages
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.driver.WebUIDriverType
import com.system.LanguageHelper
import com.system.MyElement
import com.system.WebHelper
import com.system.enums.Enum_Create_Item
import com.system.enums.Enum_Language

public class My_Item  {


	static MyElement txt_search=new MyElement("txt_search","//input[@id='search-input']")
	static MyElement a_kms_home_icon=new MyElement("a_kms_home_icon","//*[@class = 'kms-icon kms-icon--home']")

	static protected boolean fail=false
	static protected Exception my_exeption
	static protected LanguageHelper langHelper
	static protected Enum_Language lang
	protected My_Item(Enum_Language lang) {
		langHelper=new LanguageHelper(lang)
		this.lang=lang
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
			if(!DriverFactory.getExecutedBrowser() == WebUIDriverType.FIREFOX_DRIVER ||
			!DriverFactory.getExecutedBrowser() ==WebUIDriverType.FIREFOX_HEADLESS_DRIVER){
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
		} catch (Exception e) {
			my_exeption=e
			fail=true
			WebHelper.delay_medium()
			fail=false
		}
		finally{
			if(fail)
				WebHelper.screenShoot(my_exeption.getMessage())
		}
	}
}



