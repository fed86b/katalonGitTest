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

	static click_general_tab() {

		WebHelper.delay()
		def general=LanguageHelper.getText('General')
		a_General_tab.quick_double_click(general)
	}

	protected static General_tab getGeneral_tab() {
		try{
			if(general_tab==null){
				general_tab=new General_tab(lang)
				click_general_tab()
			}
		} catch (Exception e) {
			my_exeption=e
			fail=true
			WebHelper.delay()
			click_general_tab()
			fail=false
		}
		finally{
			if(fail)
				WebHelper.screenShoot(my_exeption.getMessage())
		}
		return general_tab
	}

	public static ItemProperty_General getItem_property_tab_general() {
		try{
			if(item_property_tab_general==null){
				item_property_tab_general=new ItemProperty_General(lang)
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
		return item_property_tab_general
	}
}
