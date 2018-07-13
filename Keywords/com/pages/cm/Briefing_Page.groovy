package com.pages.cm
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject


import com.pages.My_Template
import com.system.LanguageHelper
import com.system.MyElement
import com.system.WebHelper
import com.system.enums.Enum_Language
import com.system.enums.Enum_Position
import com.system.enums.Enum_Template
public  class Briefing_Page extends My_Template {

	static MyElement txt_DATE_Edit_item= new MyElement( findTestObject('Kms_Page_OR/Roles/Content_Manager/Edit_tab/txt_DATE_Edit_item'))

	static MyElement  btn_Today=new MyElement(findTestObject('Kms_Page_OR/Roles/Content_Manager/Edit_tab/btn_Today'))

	static MyElement rbtn_signature_yes=new MyElement(findTestObject('Kms_Page_OR/Roles/Content_Manager/Item_Properties_tab/rbtn_signature_yes'))
	static MyElement rbtn_signature_no=new MyElement(findTestObject('Kms_Page_OR/Roles/Content_Manager/Item_Properties_tab/rbtn_signature_no'))


	protected Briefing_Page(Enum_Language lang) {
		super(Enum_Template.Briefing,lang)
	}


	protected static _choose_Date_Edit_Item() {
		def Today=LanguageHelper.getText('Today')
		try {
			WebHelper.scroll(Enum_Position.TOP)
			choose_date_edit_tab()
		} catch (Exception e) {
			my_exeption=e
			fail=true
			choose_date_edit_tab()
			fail=false
		}
		finally{
			if(fail)
				WebHelper.screenShoot(my_exeption.getMessage())
		}
	}

	protected static _click_edit_tab() {
		WebHelper.scroll(Enum_Position.TOP)
		def edit=LanguageHelper.getText('Edit Item')
		double_click(a_Edit_Tab,  edit)
	}


	protected static  _click_signature_yes(boolean check=true){
		WebHelper.scroll(Enum_Position.BOTTOM)
		if(check)
			click_button(rbtn_signature_yes)
		else
			click_button(rbtn_signature_no)
	}

	private static choose_date_edit_tab(String today) {
		txt_DATE_Edit_item.click_with_hover()
		btn_Today.click_with_hover(today)
	}
}
