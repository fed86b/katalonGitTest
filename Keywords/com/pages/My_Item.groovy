package com.pages
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.driver.WebUIDriverType
import com.system.LanguageHelper
import com.system.MyElement
import com.system.WebHelper
import com.system.enums.Enum_Create_Item
import com.system.enums.Enum_Language

public class My_Item  {



	static protected boolean fail=false
	static protected Exception my_exeption
	static protected LanguageHelper langHelper
	static protected Enum_Language lang
	protected My_Item(Enum_Language lang) {
		langHelper=new LanguageHelper(lang)
		this.lang=lang
	}


}



