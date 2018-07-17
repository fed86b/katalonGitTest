package com.pages.cm
import com.pages.My_Template
import com.system.Iframe_Element
import com.system.LanguageHelper
import com.system.MyElement
import com.system.WebHelper
import com.system.enums.Enum_Language
import com.system.enums.Enum_Template


public  class General_Page extends My_Template {

	static MyElement a_General_tab=new Iframe_Element("a_General_tab","//a[@data-template-caption='General']")

	static ItemProperty_General item_property_tab_general
	static General_tab general_tab
	protected General_Page(Enum_Language lang) {
		super(Enum_Template.General,lang)
	}

	protected static _click_general_tab() {

		WebHelper.delay()
		def general=LanguageHelper.getText('General')
		a_General_tab.double_click(general)
	}

	protected static General_tab getGeneral_tab() {
		try{
			_click_general_tab()
		} catch (Exception e) {
			my_exeption=e
			fail=true
			WebHelper.delay()
			_click_general_tab()
			fail=false
		}
		finally{
			if(fail)
				WebHelper.screenShoot(my_exeption.getMessage())
		}
		return general_tab= new General_tab(lang)
	}

	public static ItemProperty_General getItem_property_tab_general() {
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
		if(item_property_tab_general==null)
			item_property_tab_general=new ItemProperty_General(lang)
		return item_property_tab_general
	}
}
