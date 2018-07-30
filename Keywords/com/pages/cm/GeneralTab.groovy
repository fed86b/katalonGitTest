package com.pages.cm

import com.pages.ItemAbstract
import com.system.IframeElement
import com.system.LanguageHelper
import com.system.MyElement
import com.system.WebHelper
import com.system.enums.EnumLanguage
public class GeneralTab extends ItemAbstract {

	static HTMLEditor html_Editor
	static MyElement a_GeneralTab=new IframeElement("a_GeneralTab","//a[@data-template-caption='General']")

	protected GeneralTab(EnumLanguage lang){
		super(lang)
	}

	protected static HTMLEditor getHtmlEditor() {
		if(html_Editor==null)
			html_Editor=new HTMLEditor(lang)
		return html_Editor
	}

	protected clickGeneralTab(){
		WebHelper.delay()
		def general=LanguageHelper.getText('General')
		a_GeneralTab.click_until_not_appear(getHtmlEditor().btn_Edit_html, general)
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
