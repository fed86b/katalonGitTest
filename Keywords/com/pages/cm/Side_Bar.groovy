package com.pages.cm
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.pages.My_Item
import com.system.CMHelper
import com.system.LanguageHelper
import com.system.MyElement
import com.system.WebHelper
import com.system.enums.Enum_Language
import com.system.enums.Enum_Operation
import com.system.roles.CM
public class Side_Bar  extends My_Item {

	static MyElement a_item_in_last_folder=new MyElement("a_item_in_last_folder",CMHelper.BYPATH)
	static MyElement a_last_folder_in_cm_tree=new MyElement("a_last_folder_in_cm_tree","id('cmTree')/ul[@class='dynatree-container']/li[@class='dynatree-lastsib']")
	static MyElement btn_create_new_item=new MyElement("btn_create_new_item","//div[@id='cm-tree-item-create-button']")

	static MyElement li_new_item=new MyElement("li_new_item","//li[@id='cmTreeMenu_create']")
	static MyElement li_delete=new MyElement("li_delete","//li[@id='cmTreeMenu_delete']")
	static MyElement last_folder_content=new MyElement("last_folder_content","//li[@class='dynatree-lastsib']/ul")
	static MyElement expander=new MyElement("expander","//li[@class='dynatree-lastsib']//span[@class='dynatree-expander']")

	protected Side_Bar(Enum_Language lang){
		super(lang)
	}


	protected static  _open_lastFolder(){
		try {
			a_last_folder_in_cm_tree.click_until_not_appear(last_folder_content)
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

	protected static _create_new_item_by_right_cick() {
		def text=LanguageHelper.getText('New Item')
		try {
			a_last_folder_in_cm_tree.right_click_until_not_appear(li_new_item)
			li_new_item.click_until_not_appear(CM.getTemplate().txt_create_item_template)
		} catch (Exception e) {
			my_exeption=e
			fail=true
			a_last_folder_in_cm_tree.right_click()
			li_new_item.click_with_hover(text)
			fail=false
		}
		finally{
			if(fail)
				WebHelper.screenShoot(my_exeption.getMessage())
		}
	}

	protected static _delete_by_id(){
		try{
			CMHelper.delete_by_id()
		}
		catch (Exception e) {
			my_exeption=e
			fail=true
			WebHelper.delay_medium()
			CMHelper.delete_by_id()
			fail=false
		}
		finally{
			if(fail)
				WebHelper.screenShoot(my_exeption.getMessage())
		}
	}
}
