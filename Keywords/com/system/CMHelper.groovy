package com.system

import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.system.enums.Enum_Operation
import com.system.roles.CM

public class CMHelper {

	static final int ALL=0
	static final BYPATH="//a[contains(., 'item_')]"//"(//a[starts-with(text(), '/item_'/)])[last()]"

	static items_in_lastFolder() {
		String num_with_left_bracket= WebUI.getText(CM.getSide_Bar().a_last_folder_in_cm_tree).split("]")[0]
		String pure_num=num_with_left_bracket.substring(1)
		return Integer.parseInt(pure_num)
	}



	static find_item(String name){
		CM.getSide_Bar()._open_lastFolder()
		def xpath=String.format("//a[contains(., '%s')]",name)
		CM.getSide_Bar().a_item_in_last_folder.modify(xpath)
		return CM.getSide_Bar().a_item_in_last_folder
	}

	private static generate_Name(String id="") {
		if(id.isEmpty())
			id=CM.getTemplate().getItem_property_tab().lbl_itemId.generate_Name()
		return "item_"+CM.template.template+"_"+id
	}

	private static delete_by_id() {
		int before_deleting=items_in_lastFolder()
		before_deleting--
		def yes=LanguageHelper.getText('Yes')
		def delete=LanguageHelper.getText('Delete')
		def name=generate_Name()
		find_item(name)
		CM.getSide_Bar().a_item_in_last_folder.right_click_until_not_appear(CM.getSide_Bar().li_delete)
		def xpath="//*[@id = 'recycle-view-linked-items']//following::button[ ( . = '%s')]"
		def yes_remove_button=new MyElement("yes_remove_button",String.format(xpath, yes))
		CM.getSide_Bar().li_delete.click_until_not_appear(yes_remove_button)
		yes_remove_button.click()
		WebHelper.delay_medium()
		verify_lastFolder(before_deleting,Enum_Operation.DELETE,name)
	}



	static delete_first_item_(){
		CM.getSide_Bar().a_item_in_last_folder.click_with_delay()
		CM.getBottom_Bar().verify_delete_by_btn_remove_bar()
		CM.getSide_Bar().a_item_in_last_folder.modify(BYPATH)
	}

	protected static delete_items(int count=ALL){
		CM.getSide_Bar()._open_lastFolder()
		CM.getSide_Bar().a_item_in_last_folder.modify(BYPATH)
		if(count==ALL){
			while (CM.getSide_Bar().a_item_in_last_folder.isVisible(false)) {
				delete_first_item_()
			}
		}
		else {
			for(int i=0;i<count;i++){
				delete_first_item_()
			}
		}
	}

	static verify_lastFolder(int before_creating,Enum_Operation operation,String name) {
		verify_lastFolder_number(before_creating)
		CM.getSide_Bar()._open_lastFolder()
		MyElement item=find_item(name)
		if(operation==Enum_Operation.DELETE){
			if(!item.isVisible(false))
				KeywordUtil.markWarning(String.format("item %s not in the knowledge tree ", name))
			else
				KeywordUtil.markWarning(String.format("item %s still in the knowledge tree ", name))
		}else if(operation==Enum_Operation.SAVE) {
			item.click_with_hover()
			KeywordUtil.markWarning(String.format("item %s  in the knowledge tree ", item.generate_Name()))
		}
	}

	private static verify_lastFolder_number(int before_creating) {
		WebHelper.delay_medium()
		int after_creating=items_in_lastFolder()
		if(!WebHelper.verify_ints(after_creating,before_creating)){
			WebHelper.delay_medium()
			after_creating=items_in_lastFolder()
			WebHelper.verify_ints(after_creating,before_creating)
		}
	}
}
