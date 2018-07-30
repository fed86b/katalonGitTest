package com.system
import java.util.HashMap;
import java.util.Map;

import com.system.enums.EnumLanguage
public  class LanguageHelper {
	static HashMap<String, String> hmap = new HashMap<String, String>()
	static EnumLanguage lang
	public LanguageHelper(EnumLanguage lang){
		this.lang=lang

		hmap.put(lang.toString(),'')
		hmap.put('CHOOSE A LAYOUT','')
		hmap.put('LOGIN','')
		hmap.put('Change Language','')
		hmap.put('Administrator','')
		hmap.put('SCR','')
		hmap.put('Content Manager','')
		hmap.put('Ok','')
		hmap.put('Create New Item','')
		hmap.put('Save','')
		hmap.put('Yes','')
		hmap.put('Item Properties','')
		hmap.put('Today','')
		hmap.put('Edit Item','')
		hmap.put('Create New Item','')
	}

	static public getText(def key){
		if(lang==EnumLanguage.English)
			return key
		return hmap.get(key)
	}
}
