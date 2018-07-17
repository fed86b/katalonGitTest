package com.pages.cm

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.apache.poi.ss.formula.functions.Today

import com.pages.My_Item
import com.system.LanguageHelper
import com.system.MyElement
import com.system.WebHelper
import com.system.enums.Enum_Language
public class General_tab extends My_Item {

	static HTML_Editor html_Editor


	protected General_tab(Enum_Language lang){
		super(lang)
	}

	public static HTML_Editor getHtml_Editor() {
		if(html_Editor==null)
			html_Editor=new HTML_Editor(lang)
		return html_Editor
	}

	protected static _choose_Date_Edit_Item() {
		def Today=LanguageHelper.getText('Today')
		try {
		} catch (Exception e) {
			my_exeption=e
			fail=true
			fail=false
		}
		finally{
			if(fail)
				WebHelper.screenShoot(my_exeption.getMessage())
		}
	}
}
