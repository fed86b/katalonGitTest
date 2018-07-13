package com.pages
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.pages.cm.ItemProperty_Tab
import com.system.LanguageHelper
import com.system.MyElement
import com.system.WebHelper
import com.system.enums.Enum_Language
import com.system.enums.Enum_Position
import com.system.enums.Enum_Template

public class My_Template extends My_Item{



	static MyElement a_Item_Properties=new MyElement(findTestObject('Kms_Page_OR/Roles/Content_Manager/Item_Properties_tab/a_Item Properties'))

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
		return item_property_tab=new ItemProperty_Tab(lang, template)
	}

	protected static _choose_Template_By_Typing() {
		def templt=LanguageHelper.getText(template.toString())
		def ok=LanguageHelper.getText('Ok')
		try {
			choose_template_by_Typing(templt,ok)
		} catch (Exception e) {
			my_exeption=e
			fail=true
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
		btn_ok_create_template.click(ok)
	}





	protected static choose_Template_By_Clicking(String ok) {
		def btn_ok_xpath_lang="//button[@type = 'button' and @id = 'cm-tree-item-create-dialog-button-create and( .='%s')' "
		lbl_choose_template.click_with_hover()
		(new MyElement("btn_ok",String.format(btn_ok_xpath_lang, ok))).click()
	}



	protected static _click_item_Properties() {
		WebHelper.scroll(Enum_Position.TOP)
		def Properties=LanguageHelper.getText('Properties')
		a_Item_Properties(Properties)
	}
}

