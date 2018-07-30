package com.pages.cm

import com.pages.ItemAbstract
import com.system.IframeElement
import com.system.LanguageHelper
import com.system.MyElement
import com.system.WebHelper
import com.system.enums.EnumLanguage
public class EditTab extends ItemAbstract {

	static IframeElement	txt_DATE_Edit_item= new IframeElement("txt_DATE_Edit_item","//input[@name = 'DATE' and @type = 'text']" )
	static	IframeElement btn_Today=new IframeElement("btn_Today","//button[@type = 'button' and @data-handler = 'today']")

	protected EditTab(EnumLanguage lang){
		super(lang)
	}

	protected static chooseDateEditItem() {
		String Today=LanguageHelper.getText('Today')
		try {
			choose_date_edit_tab(Today)
		} catch (Exception e) {
			my_exeption=e
			fail=true
			choose_date_edit_tab(Today)
			fail=false
		}
		finally{
			if(fail)
				WebHelper.screenShoot(my_exeption.getMessage())
		}
	}

	static choose_date_edit_tab(String today) {
		txt_DATE_Edit_item.click_with_hover()
		btn_Today.click_with_hover(today)
	}
}
