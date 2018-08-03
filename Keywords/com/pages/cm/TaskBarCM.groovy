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

	static MyElement btnSaveAndRelocate=new MyElement("btnSaveAndRelocate","//span[@class = 'kms-icon kms-icon--SaveAndRelocate action-bar__button-icon']")
	static MyElement btnSaveInLastFolder
	static MyElement btnRemoveInThreeDots=new MyElement("btnRemoveInThreeDots","//li[@data-id = 'Remove' and @class='action-bar__button action-bar__button--grouped']")
	static MyElement btnRemoveBar=new MyElement("btnRemoveBar","//li[@class='action-bar__button']/span[@class='kms-icon kms-icon--Remove action-bar__button-icon']")
	static MyElement aChooseLastFolderInModal=new MyElement("aChooseLastFolderInModal","//div[@id='relocate-tree']//li[@class='dynatree-lastsib']")
	static MyElement btnSave=new MyElement("btnSave","//li[@data-id='Save' and @class='action-bar__button']")


	protected TaskBarCM(EnumLanguage lang){
		super(lang)
	}

	protected static  clickSaveAndRelocateToLastFolder(){
		try {
			btnSaveAndRelocate.clickUntilNotAppear(aChooseLastFolderInModal)
			saveAndCheckIfNumberOfItemsIncrementedInLastFolder()
		} catch (Exception e) {
			WebHelper.catchException(e)
		}
	}

	protected static  clickSave(){
		try {
			btnSave.clickWithHover()
			while(!WebHelper.IsProcessWait())
				btnSave.clickWithHover()
			def name=CMHelper.generateName()
			MyElement item=CMHelper.findItem(name)
			WebUI.verifyEqual(item.isVisible(), true, FailureHandling.STOP_ON_FAILURE)
			KeywordUtil.markWarning(String.format("item %s is in the knowledge tree ", item.generateName()))
		} catch (Exception e) {
			WebHelper.catchException(e)
		}
	}

	static  saveAndCheckIfNumberOfItemsIncrementedInLastFolder(){
		def name=CMHelper.generateName()
		def save=LanguageHelper.getText('Save')
		int before_creating=CMHelper.itemsInLastFolder()
		before_creating++
		aChooseLastFolderInModal.clickWithHover().clickWithDelay()
		btnSaveInLastFolder=new MyElement("btnSaveInLastFolder",String.format("//span[(. = '%s')]",save))
		btnSaveInLastFolder.clickUntilNotDisappear()
		CMHelper.verifyLastFolder(before_creating,EnumOperation.SAVE,name)
	}


	static verifyDeleteByBtnRemoveBar() {
		def yes=LanguageHelper.getText('Yes')
		int before_deleting=CMHelper.itemsInLastFolder()
		def xpath="//*[@id = 'recycle-view-linked-items']//following::button[ ( . = '%s')]"
		def yesRemoveButton=new MyElement("yesRemoveButton",String.format(xpath, yes))
		before_deleting--
		def name=CMHelper.generateName()
		if(btnRemoveBar.isVisible(false))
			btnRemoveBar.clickUntilNotAppear(yesRemoveButton)
		else if(WebHelper.check_three_dots())
			btnRemoveInThreeDots.clickUntilNotAppear(yesRemoveButton)

		yesRemoveButton.clickUntilNotDisappear()
		CMHelper.verifyLastFolder(before_deleting,EnumOperation.DELETE,name)
	}
}
