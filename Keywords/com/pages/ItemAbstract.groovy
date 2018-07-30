package com.pages
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.driver.WebUIDriverType
import com.system.LanguageHelper
import com.system.MyElement
import com.system.WebHelper
import com.system.enums.EnumCreateItem
import com.system.enums.EnumLanguage

public abstract class ItemAbstract  {



	static protected boolean fail=false
	static protected Exception my_exeption
	static protected LanguageHelper langHelper
	static protected EnumLanguage lang
	protected ItemAbstract(EnumLanguage lang) {
		langHelper=new LanguageHelper(lang)
		this.lang=lang
	}
}



