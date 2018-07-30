package com.pages.cm

import com.pages.ItemAbstract
import com.system.IframeElement
import com.system.LanguageHelper
import com.system.MyElement
import com.system.WebHelper
import com.system.enums.EnumLanguage
public class HTMLEditor extends ItemAbstract {

	static MyElement btn_Edit_html= new IframeElement("btn_Edit_html","//div[@data-element-name='CONTENT']/descendant::span[text()='Edit HTML']/parent::button")



	protected HTMLEditor(EnumLanguage lang){
		super(lang)
	}


	protected static clickEditHTML() {
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
