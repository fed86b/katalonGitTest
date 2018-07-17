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

public class My_Template extends My_Item{

	static MyElement a_Item_Properties=new Iframe_Element("a_Item_Properties","//li[@aria-controls='item-update-tab-main']")

	static MyElement btn_ok_create_template=new MyElement("btn_ok_create_template","//button[ @id = 'cm-tree-item-create-dialog-button-create']")
	static MyElement txt_create_item_template=new MyElement("txt_create_item_template","//input[@id = 'cm-tree-item-create-dialog-dtd-filter' and @type = 'text']")

	static MyElement lbl_choose_template=new MyElement("lbl_choose_template","//input[@id = 'cm-tree-item-create-dialog-dtd-filter' and @type = 'text']")

	static ItemProperty_Tab item_property_tab
	static Enum_Template template


	protected My_Template (Enum_Template template,Enum_Language lang){
		super(lang)
		this.template=template
	}

	public static ItemProperty_Tab getItem_property_tab() {
		try{
			this._click_item_Properties()
		} catch (Exception e) {
			my_exeption=e
			WebHelper.delay()
			fail=true
			this._click_item_Properties()
			fail=false
		}
		finally{
			if(fail)
				WebHelper.screenShoot(my_exeption.getMessage())
		}
		if(item_property_tab==null)
			item_property_tab=new ItemProperty_Tab(lang, template)
		return item_property_tab
	}

	protected static _choose_Template_By_Typing() {
		def templt=LanguageHelper.getText(template.toString())
		def ok=LanguageHelper.getText('Ok')
		try {
			choose_template_by_Typing(templt,ok)
		} catch (Exception e) {
			my_exeption=e
			fail=true
			WebHelper.delay()
			choose_template_by_Typing(templt,ok)
			fail=false
		}
		finally{
			if(fail)
				WebHelper.screenShoot(my_exeption.getMessage())
		}
	}




	protected static choose_template_by_Typing(String briefing, String ok) {
		txt_create_item_template.click_with_delay()
		txt_create_item_template.write_text( briefing)
		if(WebHelper.if_ie())
			btn_ok_create_template.press_Enter(ok)
		else
			btn_ok_create_template.click(ok)
	}





	protected static choose_Template_By_Clicking(String ok) {
		def btn_ok_xpath_lang="//button[@type = 'button' and @id = 'cm-tree-item-create-dialog-button-create and( .='%s')' "
		lbl_choose_template.click_with_hover()
		(new MyElement("btn_ok",String.format(btn_ok_xpath_lang, ok))).click()
		WebUI.switchToFrame(findTestObject('Kms_Page_OR/Roles/Content_Manager/iframe_itemscope'))
	}



	protected static _click_item_Properties() {
		WebHelper.delay()
		def Properties=LanguageHelper.getText('Item Properties')
		a_Item_Properties.double_click(Properties)
	}
}

