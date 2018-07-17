package com.pages.cm
import org.junit.After

import com.pages.My_Template
import com.system.Iframe_Element
import com.system.LanguageHelper
import com.system.MyElement
import com.system.WebHelper
import com.system.enums.Enum_Language
import com.system.enums.Enum_Template
public  class Briefing_Page extends My_Template {

	static MyElement a_Edit_Tab

	static Edit_Tab edit_Tab
	static ItemProperty_Briefing item_property_tab_briefing
	protected Briefing_Page(Enum_Language lang) {
		super(Enum_Template.Briefing,lang)
	}

	public static Edit_Tab getEdit_Tab() {
		a_Edit_Tab=new Iframe_Element("a_Edit_Tab","//a[@href = '#item-update-tab-edit' and @class = 'ui-tabs-anchor']")
		if(edit_Tab==null)
			edit_Tab=new Edit_Tab(lang)
		_click_edit_tab()
		return edit_Tab
	}

	protected static _click_edit_tab() {
		try{

			def edit=LanguageHelper.getText('Edit Item')
			a_Edit_Tab.double_click(edit)
		} catch (Exception e) {
			my_exeption=e
			fail=true
		}
		finally{
			if(fail)
				WebHelper.screenShoot(my_exeption.getMessage())
		}
	}

	public static ItemProperty_Briefing getItem_property_tab_Briefing() {
		try{
			_click_item_Properties()
		} catch (Exception e) {
			my_exeption=e
			fail=true
			WebHelper.delay()
			_click_item_Properties()
			fail=false
		}
		finally{
			if(fail)
				WebHelper.screenShoot(my_exeption.getMessage())
		}
		if(item_property_tab_briefing==null)
			item_property_tab_briefing=new ItemProperty_Briefing(lang)
		return item_property_tab_briefing
	}
}
