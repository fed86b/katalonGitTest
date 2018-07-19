package com.pages
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.pages.cm.ItemProperty_Tab
import com.system.Iframe_Element
import com.system.LanguageHelper
import com.system.MyElement
import com.system.WebHelper
import com.system.enums.Enum_Language
import com.system.enums.Enum_Template
import com.system.roles.CM

public class My_Template extends My_Item{

	static MyElement a_Item_Properties=new Iframe_Element("a_Item_Properties","//li[@aria-controls='item-update-tab-main']")

	static MyElement btn_ok_create_template=new MyElement("btn_ok_create_template","//button[ @id = 'cm-tree-item-create-dialog-button-create']")
	static MyElement txt_create_item_template=new MyElement("txt_create_item_template","//input[@id = 'cm-tree-item-create-dialog-dtd-filter' and @type = 'text']")

	static MyElement lbl_choose_template=new MyElement("lbl_choose_template","//input[@id = 'cm-tree-item-create-dialog-dtd-filter' and @type = 'text']")

	static Enum_Template template

	static ItemProperty_Tab item_property_tab
	protected My_Template (Enum_Template template,Enum_Language lang){
		super(lang)
		this.template=template
	}

	public static ItemProperty_Tab getItem_property_tab() {
		if(item_property_tab==null)
			item_property_tab=new ItemProperty_Tab(lang,template)
		return item_property_tab;
	}


	protected static _choose_Template_By_Typing(def right_clicking=false) {
		try {
			choose_template_by_Typing(right_clicking)
		} catch (Exception e) {
			my_exeption=e
			fail=true
			WebHelper.delay()
			choose_template_by_Typing()
			fail=false
		}
		finally{
			if(fail)
				WebHelper.screenShoot(my_exeption.getMessage())
		}
	}




	protected static choose_template_by_Typing(def right_clicking) {
		def templt=LanguageHelper.getText(template.toString())
		txt_create_item_template.click_with_delay()
		txt_create_item_template.write_text(templt)
		commiting_template( right_clicking)
	}

	private static commiting_template(def right_clicking) {
		def ok=LanguageHelper.getText('Ok')
		int before_creating=0
		if(right_clicking){
			before_creating=CM.getSide_Bar()._items_in_lastFolder()
			before_creating++
		}
		btn_ok_create_template.press_Enter(ok)
		if(right_clicking){
			CM.getSide_Bar().verify_lastFolder_number(before_creating)
		}
	}


	protected static _choose_Template_By_Clicking(def right_clicking) {
		def ok=LanguageHelper.getText('Ok')
		try{
		} catch (Exception e) {
			my_exeption=e
			fail=true
			WebHelper.delay()
			lbl_choose_template.click_with_hover()
			commiting_template( right_clicking)
			fail=false
		}
		finally{
			if(fail)
				WebHelper.screenShoot(my_exeption.getMessage())
		}
	}



	static click_item_Properties() {
		WebHelper.delay()
		def Properties=LanguageHelper.getText('Item Properties')
		a_Item_Properties.quick_double_click(Properties)
	}
}

