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

	static MyElement a_Edit_Tab=new Iframe_Element("a_Edit_Tab","//a[@href = '#item-update-tab-edit' and @class = 'ui-tabs-anchor']")

	static Edit_Tab edit_Tab
	static ItemProperty_Briefing item_property_tab_briefing
	protected Briefing_Page(Enum_Language lang) {
		super(Enum_Template.Briefing,lang)
	}

	protected static Edit_Tab getEdit_Tab() {

		try{
			if(edit_Tab==null){
				click_edit_tab()
			}
		} catch (Exception e) {
			my_exeption=e
			fail=true
			WebHelper.delay()
			click_edit_tab()
			fail=false
		}
		finally{
			if(fail)
				WebHelper.screenShoot(my_exeption.getMessage())
		}
		return edit_Tab
	}

	static click_edit_tab(){
		def edit=LanguageHelper.getText('Edit Item')
		edit_Tab=new Edit_Tab(lang)
		a_Edit_Tab.double_click(edit)
	}


	protected static ItemProperty_Briefing getItem_property_tab_Briefing() {
		try{
			if(item_property_tab_briefing==null){
				item_property_tab_briefing=new ItemProperty_Briefing(lang)
				click_item_Properties()
			}
		} catch (Exception e) {
			my_exeption=e
			fail=true
			WebHelper.delay()
			click_item_Properties()
			fail=false
		}
		finally{
			if(fail)
				WebHelper.screenShoot(my_exeption.getMessage())
		}
		return item_property_tab_briefing
	}
}
