package com.pages.cm
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.pages.My_Template
import com.server.MyElement
import com.server.WebHelper
import com.server.enums.Enum_Template
public  class Briefing_Page extends My_Template {

	 static MyElement input_DATE_Edit_item= new MyElement( findTestObject('Kms_Page_OR/Roles/Content_Manager/Edit_tab/input_DATE_Edit_item'))
	 static MyElement  button_Today=new MyElement(findTestObject('Kms_Page_OR/Roles/Content_Manager/Edit_tab/button_Today'))

	protected Briefing_Page() {
		super(Enum_Template.BRIEFING)
	}

	protected static choose_Date_Edit_Item(String today){
		try {
			input_DATE_Edit_item.click_with_hover()
			button_Today.click_with_hover()
			button_Today.verifyText(today)
		}
		catch (Exception e) {
			WebHelper.screenShoot(e.getMessage())
			throw e
		}
	}
}
