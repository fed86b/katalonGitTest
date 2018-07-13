package com.pages.cm

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.apache.poi.ss.formula.functions.Today

import com.pages.My_Item
import com.system.LanguageHelper
import com.system.MyElement
import com.system.WebHelper
import com.system.enums.Enum_Language
import com.system.enums.Enum_Position
public class Edit_Tab extends My_Item {

	static MyElement txt_DATE_Edit_item= new MyElement( findTestObject('Kms_Page_OR/Roles/Content_Manager/Edit_tab/txt_DATE_Edit_item'))

	static MyElement  btn_Today=new MyElement(findTestObject('Kms_Page_OR/Roles/Content_Manager/Edit_tab/btn_Today'))

	static MyElement rbtn_signature_yes=new MyElement(findTestObject('Kms_Page_OR/Roles/Content_Manager/Item_Properties_tab/rbtn_signature_yes'))
	static MyElement rbtn_signature_no=new MyElement(findTestObject('Kms_Page_OR/Roles/Content_Manager/Item_Properties_tab/rbtn_signature_no'))

	static MyElement a_Edit_Tab=new MyElement(findTestObject('Kms_Page_OR/Roles/Content_Manager/Edit_tab/a_Edit_tab'))


	protected Edit_Tab(Enum_Language lang){
		super(lang)
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
		try{
			WebHelper.scroll(Enum_Position.TOP)
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


	protected static  _click_signature_yes(boolean check=true){
		WebHelper.scroll(Enum_Position.BOTTOM)
		if(check)
			rbtn_signature_yes.click_with_hover()
		else
			rbtn_signature_no.click_with_hover()
	}

	private static choose_date_edit_tab(String today) {
		txt_DATE_Edit_item.click_with_hover()
		btn_Today.click_with_hover(today)
	}
}
