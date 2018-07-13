package com.pages.cm
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.pages.My_Template
import com.system.MyElement
import com.system.WebHelper
import com.system.enums.Enum_Language
import com.system.enums.Enum_Position
import com.system.enums.Enum_Template
public  class General_Page extends My_Template {

	static ItemProperty_General item_property_tab_general
	protected General_Page(Enum_Language lang) {
		super(Enum_Template.General,lang)
	}
	
	public ItemProperty_General getItem_property_tab_general() {
		return item_property_tab_general=new ItemProperty_General(lang);
	}
}
