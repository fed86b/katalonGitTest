package com.pages.cm
import com.system.roles.CM
import com.pages.ItemAbstract
import com.system.CMHelper
import com.system.IframeElement
import com.system.LanguageHelper
import com.system.MyElement
import com.system.WebHelper
import com.system.enums.EnumLanguage
import com.system.enums.EnumTemplate

public class ContentArea  extends ItemAbstract{

	static MyElement a_Item_Properties=new IframeElement("a_Item_Properties","//li[@aria-controls='item-update-tab-main']")
	static MyElement btn_create_new_item=new MyElement("btn_create_new_item","//div[@id='cm-tree-item-create-button']")
	static MyElement btn_ok_create_template=new MyElement("btn_ok_create_template","//button[ @id = 'cm-tree-item-create-dialog-button-create']")
	static MyElement txt_create_item_template=new MyElement("txt_create_item_template","//input[@id = 'cm-tree-item-create-dialog-dtd-filter' and @type = 'text']")

	static MyElement lbl_choose_template=new MyElement("lbl_choose_template","//input[@id = 'cm-tree-item-create-dialog-dtd-filter' and @type = 'text']")

	static EnumTemplate template

	static ItemPropertyTab itemPropertyTab
	protected ContentArea(EnumTemplate template,EnumLanguage lang){
		super(lang)
		this.template=template
	}

	public static ItemPropertyTab getItemPropertyTab() {
		if(itemPropertyTab==null)
			itemPropertyTab=new ItemPropertyTab(lang,template)
		return itemPropertyTab;
	}

	//right_clicking = false, if operation performed by clicking on create new button, otherwise after right clicking on folder
	protected static chooseTemplateByTyping(def right_clicking=false) {
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





	protected static clickUpperButtonCreateItem(){
		def text=LanguageHelper.getText('Create New Item')
		try {
			btn_create_new_item.click_with_hover(text)
		} catch (Exception e) {
			my_exeption=e
			fail=true
			btn_create_new_item.click_with_hover(text)
			fail=false
		}
		finally{
			if(fail)
				WebHelper.screenShoot(my_exeption.getMessage())
		}
	}

	protected static chooseTemplateByClicking(def right_clicking) {
		def ok=LanguageHelper.getText('Ok')
		try{
			lbl_choose_template.click_with_hover()
			saveTemplate( right_clicking)
		} catch (Exception e) {
			my_exeption=e
			fail=true
			WebHelper.delay()
			lbl_choose_template.click_with_hover()
			saveTemplate( right_clicking)
			fail=false
		}
		finally{
			if(fail)
				WebHelper.screenShoot(my_exeption.getMessage())
		}
	}

	protected static clickItemProperties() {
		WebHelper.delay()
		def Properties=LanguageHelper.getText('Item Properties')
		a_Item_Properties.click_until_not_appear(getItemPropertyTab().lbl_itemId, Properties)
	}


	static choose_template_by_Typing(def right_clicking) {
		def templt=LanguageHelper.getText(template.toString())
		txt_create_item_template.click_with_delay()
		txt_create_item_template.write_text(templt)
		saveTemplate( right_clicking)
	}

	static saveTemplate(def right_clicking) {
		def ok=LanguageHelper.getText('Ok')
		int before_creating=0
		if(right_clicking){
			before_creating=CMHelper.itemsInLastFolder()
			before_creating++
		}
		btn_ok_create_template.press_Enter(ok)
		if(right_clicking){
			CMHelper.verifyLastFolderNumber(before_creating)
		}
	}
}