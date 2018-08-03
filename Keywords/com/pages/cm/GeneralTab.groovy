package com.pages.cm

import com.pages.ItemAbstract
import com.system.IframeElement
import com.system.LanguageHelper
import com.system.MyElement
import com.system.WebHelper
import com.system.enums.EnumLanguage
public class GeneralTab extends ItemAbstract {

	static HTMLEditor htmlEditor
	static MyElement aGeneralTab=new IframeElement("aGeneralTab","//a[@data-template-caption='General']")

	protected GeneralTab(EnumLanguage lang){
		super(lang)
	}

	protected static HTMLEditor getHtmlEditor() {
		if(htmlEditor==null)
			htmlEditor=new HTMLEditor(lang)
		return htmlEditor
	}

	protected clickGeneralTab(){
		WebHelper.delay()
		def general=LanguageHelper.getText('General')
		aGeneralTab.clickUntilNotAppear(getHtmlEditor().btnEditHtmlContent, general)
	}
}
