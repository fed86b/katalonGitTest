package com.pages.cm

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.pages.TaskBar
import com.system.CMHelper
import com.system.LanguageHelper
import com.system.MyElement
import com.system.WebHelper
import com.system.enums.EnumLanguage
import com.system.enums.EnumOperation
import com.system.roles.CM
public class TaskBarCM extends TaskBar {

	static MyElement btn_saveAndRelocate=new MyElement("btn_saveAndRelocate","//span[@class = 'kms-icon kms-icon--SaveAndRelocate action-bar__button-icon']")
	static MyElement btn_save_in_last_folder
	static MyElement btn_remove_in_three_dots=new MyElement("btn_remove_in_three_dots","//li[@data-id = 'Remove' and @class='action-bar__button action-bar__button--grouped']")
	static MyElement btn_remove_bar=new MyElement("btn_remove_bar","//li[@class='action-bar__button']/span[@class='kms-icon kms-icon--Remove action-bar__button-icon']")
	static MyElement a_choose_lastFolder_in_modal=new MyElement("a_choose_lastFolder_in_modal","//div[@id='relocate-tree']//li[@class='dynatree-lastsib']")
	static MyElement btn_save=new MyElement("btn_save","//li[@data-id='Save' and @class='action-bar__button']")


	protected TaskBarCM(EnumLanguage lang){
		super(lang)
	}

	protected static  clickSaveAndRelocateToLastFolder(){
		try {
			btn_saveAndRelocate.click_until_not_appear(a_choose_lastFolder_in_modal)
			saveAndCheckIfNumberOfItemsIncrementedInLastFolder()
		} catch (Exception e) {
			my_exeption=e
			fail=true
			WebHelper.delay_medium()
			btn_saveAndRelocate.click_with_hover()
			saveAndCheckIfNumberOfItemsIncrementedInLastFolder()
			fail=false
		}
		finally{
			if(fail)
				WebHelper.screenShoot(my_exeption.getMessage())
		}
	}

	protected static  clickSave(){
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

	static  saveAndCheckIfNumberOfItemsIncrementedInLastFolder(){
		def name=CMHelper.generateName()
		def save=LanguageHelper.getText('Save')
		int before_creating=CMHelper.itemsInLastFolder()
		before_creating++
		a_choose_lastFolder_in_modal.click_with_hover().click_with_delay()
		btn_save_in_last_folder=new MyElement("btn_save_in_last_folder",String.format("//span[(. = '%s')]",save))
		btn_save_in_last_folder.click_until_not_disappear()
		CMHelper.verifyLastFolder(before_creating,EnumOperation.SAVE,name)
	}


	static verifyDeleteByBtnRemoveBar() {
		def yes=LanguageHelper.getText('Yes')
		int before_deleting=CMHelper.itemsInLastFolder()
		def xpath="//*[@id = 'recycle-view-linked-items']//following::button[ ( . = '%s')]"
		def yes_remove_button=new MyElement("yes_remove_button",String.format(xpath, yes))
		before_deleting--
		def name=CMHelper.generateName()
		if(btn_remove_bar.isVisible(false))
			btn_remove_bar.click_until_not_appear(yes_remove_button)
		else if(WebHelper.check_three_dots())
			btn_remove_in_three_dots.click_with_delay()

		yes_remove_button.click_until_not_disappear()
		CMHelper.verifyLastFolder(before_deleting,EnumOperation.DELETE,name)
	}

	static save() {
		btn_save.double_click()
		def name=CMHelper.generateName()
		MyElement item=CMHelper.findItem(name)
		WebUI.verifyEqual(item.isVisible(), true, FailureHandling.STOP_ON_FAILURE)
		KeywordUtil.markWarning(String.format("item %s is in the knowledge tree ", item.generate_Name()))
	}
}
