package com.pages.cm

import com.pages.My_Item
import com.system.Iframe_Element
import com.system.LanguageHelper
import com.system.MyElement
import com.system.WebHelper
import com.system.enums.Enum_Language
public class Edit_Tab extends My_Item {

	static Iframe_Element	txt_DATE_Edit_item= new Iframe_Element("txt_DATE_Edit_item","//input[@name = 'DATE' and @type = 'text']" )
	static	Iframe_Element btn_Today=new Iframe_Element("btn_Today","//button[@type = 'button' and @data-handler = 'today']")

	protected Edit_Tab(Enum_Language lang){
		super(lang)
	}

	protected static _choose_Date_Edit_Item() {
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
