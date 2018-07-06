package com.pages.cm
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.pages.My_Template
import com.server.WebHelper
import com.server.enums.Enum_Create_Item
import com.server.enums.Enum_Tables
import com.server.enums.Enum_Template
import internal.GlobalVariable
public class Briefing_Page extends My_Template {

	def static input_DATE_Edit_item=findTestObject('Kms_Page_OR/Roles/Content_Manager/Edit_tab/input_DATE_Edit_item')
	def static button_Today=findTestObject('Kms_Page_OR/Roles/Content_Manager/Edit_tab/button_Today')

	protected Briefing_Page(Enum_Template template) {
		super(template)
	}

	protected static choose_Date_Edit_Item(){
		try {
			WebHelper.verify_text_click_with_hover(input_DATE_Edit_item)
			WebHelper.verify_text_click_with_hover(button_Today)
		}
		catch (Exception e) {
			WebHelper.screenShoot(e.getMessage())
			throw e
		}
	}
}
