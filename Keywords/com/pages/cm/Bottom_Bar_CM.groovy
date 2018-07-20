package com.pages.cm

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.pages.Bottom_Bar
import com.system.CMHelper
import com.system.LanguageHelper
import com.system.MyElement
import com.system.WebHelper
import com.system.enums.Enum_Language
import com.system.enums.Enum_Operation
import com.system.roles.CM
public class Bottom_Bar_CM extends Bottom_Bar {

	static MyElement btn_saveAndRelocate=new MyElement("btn_saveAndRelocate","//span[@class = 'kms-icon kms-icon--SaveAndRelocate action-bar__button-icon']")
	static MyElement btn_save_in_last_folder
	static MyElement btn_remove_in_three_dots=new MyElement("btn_remove_in_three_dots","//li[@data-id = 'Remove' and @class='action-bar__button action-bar__button--grouped']")
	static MyElement btn_remove_bar=new MyElement("btn_remove_bar","//li[@class='action-bar__button']/span[@class='kms-icon kms-icon--Remove action-bar__button-icon']")
	static MyElement a_choose_lastFolder_in_modal=new MyElement("a_choose_lastFolder_in_modal","//span[@class = 'dynatree-node dynatree-folder dynatree-lastsib dynatree-exp-cl dynatree-ico-cf']")
	static MyElement btn_save=new MyElement("btn_save","//li[@data-id='Save' and @class='action-bar__button']")


	protected Bottom_Bar_CM(Enum_Language lang){
		super(lang)
	}

	protected static  _click_save_and_relocate_to_lastFolder(){
		try {
			btn_saveAndRelocate.click_until_not_appear(a_choose_lastFolder_in_modal)
			save_check_if_number_of_items_incremented_in_lastFolder()
		} catch (Exception e) {
			my_exeption=e
			fail=true
			WebHelper.delay_medium()
			btn_saveAndRelocate.click_with_hover()
			save_check_if_number_of_items_incremented_in_lastFolder()
			fail=false
		}
		finally{
			if(fail)
				WebHelper.screenShoot(my_exeption.getMessage())
		}
	}

	protected static  _click_save(){
		try {
			save()
		} catch (Exception e) {
			my_exeption=e
			fail=true
			WebHelper.delay_medium()
			save()
			fail=false
		}
		finally{
			if(fail)
				WebHelper.screenShoot(my_exeption.getMessage())
		}
	}

	private static  save_check_if_number_of_items_incremented_in_lastFolder(){
		def name=CMHelper.generate_Name()
		def save=LanguageHelper.getText('Save')
		int before_creating=CMHelper.items_in_lastFolder()
		before_creating++
		a_choose_lastFolder_in_modal.click_with_hover()
		btn_save_in_last_folder=new MyElement("btn_save_in_last_folder",String.format("//span[(. = '%s')]",save))
		btn_save_in_last_folder.click_with_hover()
		CMHelper.verify_lastFolder(before_creating,Enum_Operation.SAVE,name)
	}

	private static verify_delete_by_btn_remove_bar() {
		def yes=LanguageHelper.getText('Yes')
		int before_deleting=CMHelper.items_in_lastFolder()
		def xpath="//*[@id = 'recycle-view-linked-items']//following::button[ ( . = '%s')]"
		def yes_remove_button=new MyElement("yes_remove_button",String.format(xpath, yes))
		before_deleting--
		def name=CMHelper.generate_Name()
		if(btn_remove_bar.isVisible(false))
			btn_remove_bar.click_until_not_appear(yes_remove_button)
		else if(WebHelper.check_three_dots())
			btn_remove_in_three_dots.click_with_delay()

		yes_remove_button.click_until_not_disappear(yes_remove_button)
		CMHelper.verify_lastFolder(before_deleting,Enum_Operation.DELETE,name)
	}

	static save() {
		btn_save.double_click()
		def name=CMHelper.generate_Name()
		MyElement item=CMHelper.find_item(name)
		WebUI.verifyEqual(item.isVisible(), true, FailureHandling.STOP_ON_FAILURE)
		KeywordUtil.markWarning(String.format("item %s is in the knowledge tree ", item.generate_Name()))
	}
}
