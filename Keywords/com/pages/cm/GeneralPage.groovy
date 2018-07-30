package com.pages.cm
import com.pages.ItemAbstract
import com.system.IframeElement
import com.system.LanguageHelper
import com.system.MyElement
import com.system.WebHelper
import com.system.enums.EnumLanguage
import com.system.enums.EnumTemplate



public  class GeneralPage extends ContentArea {

	static  ItemPropertyGeneral itemPropertyTabGeneral
	static GeneralTab generalTab

	protected GeneralPage(EnumLanguage lang) {
		super(EnumTemplate.General,lang)
	}



	protected static GeneralTab getGeneralTab() {
		try{
			if(generalTab==null){
				generalTab=new GeneralTab(lang)
			}
		} catch (Exception e) {
			my_exeption=e
			fail=true
			WebHelper.delay()
			generalTab.clickGeneralTab()
			fail=false
		}
		finally{
			if(fail)
				WebHelper.screenShoot(my_exeption.getMessage())
		}
		generalTab.clickGeneralTab()
		return generalTab
	}

	protected static ItemPropertyGeneral getItemPropertyTabGeneral() {
		try{
			if(itemPropertyTabGeneral==null){
				itemPropertyTabGeneral=new ItemPropertyGeneral(lang)
			}
		} catch (Exception e) {
			my_exeption=e
			fail=true
			WebHelper.delay()
			clickItemProperties()
			fail=false
		}
		finally{
			if(fail)
				WebHelper.screenShoot(my_exeption.getMessage())
		}
		clickItemProperties()
		return itemPropertyTabGeneral
	}
}
