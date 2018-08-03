package com.pages.cm

import com.pages.ItemAbstract
import com.system.IframeElement
import com.system.LanguageHelper
import com.system.MyElement
import com.system.WebHelper
import com.system.enums.EnumLanguage
public class EditTab extends ItemAbstract {

	static IframeElement	txtDateEditItem= new IframeElement("txtDateEditItem","//input[@name = 'DATE' and @type = 'text']" )
	static	IframeElement btnToday=new IframeElement("btnToday","//button[@type = 'button' and @data-handler = 'today']")

	protected EditTab(EnumLanguage lang){
		super(lang)
	}

	protected static chooseDateEditItem() {
		String Today=LanguageHelper.getText('Today')
		try {
			txtDateEditItem.clickUntilNotAppear(btnToday)
			btnToday.clickUntilNotDisappear(btnToday,Today)
		} catch (Exception e) {
			WebHelper.catchException(e)
		}
	}
}
