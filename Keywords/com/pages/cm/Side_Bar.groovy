package com.pages.cm
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.pages.My_Item
import com.system.LanguageHelper
import com.system.MyElement
import com.system.WebHelper
import com.system.enums.Enum_Language
public class Side_Bar  extends My_Item {
	static MyElement a_item_in_last_folder=new MyElement("a_item_in_last_folder","//a[starts-with(text(), 'item_')]")
	static MyElement a_last_folder_in_cm_tree=new MyElement("a_last_folder_in_cm_tree","id('cmTree')/ul[@class='dynatree-container']/li[@class='dynatree-lastsib']")
	static MyElement btn_create_new_item=new MyElement("btn_create_new_item","//div[@id='cm-tree-item-create-button']")

	static final int ALL=0
	static final BYPATH="(//a[starts-with(text(), '/item_'/)])[last()]"

	protected Side_Bar(Enum_Language lang){
		super(lang)
	}

	protected static  _open_lastFolder(){
		try {
			a_last_folder_in_cm_tree.click_until_not_appear(a_item_in_last_folder)
		} catch (Exception e) {
			my_exeption=e
			fail=true
			WebHelper.delay_medium()
			a_last_folder_in_cm_tree.click_with_delay()
			fail=false
		}
		finally{
			if(fail)
				WebHelper.screenShoot(my_exeption.getMessage())
		}
	}

	protected static _click_upper_button_create_item(){
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

	protected static _items_in_lastFolder() {
		String num_with_left_bracket= WebUI.getText(a_last_folder_in_cm_tree).split("]")[0]
		String pure_num=num_with_left_bracket.substring(1)
		return Integer.parseInt(pure_num)
	}
}
