package com.pages
import com.system.LanguageHelper
import com.system.enums.EnumLanguage

public abstract class ItemAbstract  {
	static protected LanguageHelper langHelper
	static protected EnumLanguage lang
	protected ItemAbstract(EnumLanguage lang) {
		langHelper=new LanguageHelper(lang)
		this.lang=lang
	}
}



