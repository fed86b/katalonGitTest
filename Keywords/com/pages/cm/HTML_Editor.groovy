package com.pages.cm

import com.pages.My_Item
import com.system.Iframe_Element
import com.system.LanguageHelper
import com.system.MyElement
import com.system.WebHelper
import com.system.enums.Enum_Language
public class HTML_Editor extends My_Item {

	static MyElement btn_Edit_html= new Iframe_Element("btn_Edit_html","//div[@data-element-name='CONTENT']/descendant::span[text()='Edit HTML']/parent::button")



	protected HTML_Editor(Enum_Language lang){
		super(lang)
	}


	protected static _click_Edit_HTML() {
		def edit_html=LanguageHelper.getText('Edit HTML')
		try {
			btn_Edit_html.modify('Edit HTML',edit_html)
			btn_Edit_html.click_with_hover()
		} catch (Exception e) {
			my_exeption=e
			fail=true
			btn_Edit_html.click_with_hover()
			fail=false
		}
		finally{
			if(fail)
				WebHelper.screenShoot(my_exeption.getMessage())
		}
	}
}
