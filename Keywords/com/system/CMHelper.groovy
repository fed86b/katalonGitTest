package com.system

import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.system.enums.EnumOperation
import com.system.roles.CM

import internal.GlobalVariable

public class CMHelper {

	static final int ALL=0
	static final BYPATH="(//a[contains(., 'item_')])[last()]"

	static itemsInLastFolder() {
		String num_with_left_bracket= WebUI.getText(CM.getItemsTree().a_last_folder_in_cm_tree).split("]")[0]
		String pure_num=num_with_left_bracket.substring(1)
		return Integer.parseInt(pure_num)
	}



	static findItem(String name){
		CM.getItemsTree().openLastFolder()
		def xpath=String.format("//a[contains(., '%s')]",name)
		return new MyElement(name, xpath)
	}

	private static generateName(String id="") {
		if(id.isEmpty())
			id=CM.getTemplate().getItemPropertyTab().itemId.generate_Name(true)
		return "item_"+CM.template.template+"_"+id
	}

	private static deleteById() {
		int before_deleting=itemsInLastFolder()
		before_deleting--
		def yes=LanguageHelper.getText('Yes')
		def delete=LanguageHelper.getText('Delete')
		def name=generateName()
		findItem(name)
		CM.getItemsTree().a_item_in_last_folder.right_click_until_not_appear(CM.getItemsTree().li_delete)
		def xpath="//*[@id = 'recycle-view-linked-items']//following::button[ ( . = '%s')]"
		def yes_remove_button=new MyElement("yes_remove_button",String.format(xpath, yes))
		CM.getItemsTree().li_delete.click_until_not_appear(yes_remove_button)
		yes_remove_button.click()
		WebHelper.delay_medium()
		verifyLastFolder(before_deleting,EnumOperation.DELETE,name)
	}



	static deleteFirstItem_(){
		try{
			CM.getItemsTree().a_item_in_last_folder.click_with_delay()
			CM.getTaskBar().verifyDeleteByBtnRemoveBar()
		}catch(Exception e){
			println(e.getMessage())
		}
	}

	protected static deleteItems(int count=ALL){

		CM.getItemsTree().openLastFolder()
		if(count==ALL){
			while (CM.getItemsTree().a_item_in_last_folder.isVisible()) {
				deleteFirstItem_()
			}
		}
		else {
			for(int i=0;i<count;i++){
				deleteFirstItem_()
			}
		}
	}


	static verifyLastFolder(int before_creating,EnumOperation operation,String name) {
		verifyLastFolderNumber(before_creating)
		CM.getItemsTree().openLastFolder()
		MyElement item=findItem(name)
		if(operation==EnumOperation.DELETE){
			if(!item.isVisible(false))
				KeywordUtil.markWarning(String.format("item %s not in the knowledge tree ", name))
			else
				KeywordUtil.markWarning(String.format("item %s still in the knowledge tree ", name))
		}else if(operation==EnumOperation.SAVE) {
			item.click_with_hover()
			KeywordUtil.markWarning(String.format("item %s  in the knowledge tree ", item.generate_Name()))
		}
	}

	private static verifyLastFolderNumber(int before_creating) {
		WebHelper.delay_medium()
		int after_creating=itemsInLastFolder()
		if(!WebHelper.verify_ints(after_creating,before_creating)){
			WebHelper.delay_medium()
			after_creating=itemsInLastFolder()
			WebHelper.verify_ints(after_creating,before_creating)
		}
	}
}
