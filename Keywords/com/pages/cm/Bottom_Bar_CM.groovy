package com.pages.cm

import com.pages.Bottom_Bar
import com.system.LanguageHelper
import com.system.MyElement
import com.system.WebHelper
import com.system.enums.Enum_Language

public class Bottom_Bar_CM extends Bottom_Bar {

	static MyElement btn_saveAndRelocate=new MyElement("btn_saveAndRelocate","//span[@class = 'kms-icon kms-icon--SaveAndRelocate action-bar__button-icon']")
	static MyElement btn_save_in_last_folder
	static MyElement btn_remove_in_three_dots=new MyElement("btn_remove_in_three_dots","//li[@data-id = 'Remove' and @class='action-bar__button action-bar__button--grouped']")
	static MyElement btn_remove_bar=new MyElement("btn_remove_bar","//li[@class='action-bar__button']/span[@class='kms-icon kms-icon--Remove action-bar__button-icon']")
	static MyElement a_choose_lastFolder_in_modal=new MyElement("a_choose_lastFolder_in_modal","//span[@class = 'dynatree-node dynatree-folder dynatree-lastsib dynatree-exp-cl dynatree-ico-cf']")



	static Side_Bar side_bar
	protected Bottom_Bar_CM(Enum_Language lang){
		super(lang)
		side_bar=new  Side_Bar(lang)
	}

	protected static  _click_save_and_relocate_to_lastFolder(){
		try {
			btn_saveAndRelocate.click_with_hover()
		} catch (Exception e) {
			my_exeption=e
			fail=true
			WebHelper.delay_medium()
			btn_saveAndRelocate.click_with_hover()
			fail=false
		}
		finally{
			if(fail)
				WebHelper.screenShoot(my_exeption.getMessage())
		}
	}

	protected static _click_save_and_check_if_number_of_items_incremented_in_lastFolder(){
		def Save=LanguageHelper.getText('Save')
		try{
			save_check_if_number_of_items_incremented_in_lastFolder(Save)
		} catch (Exception e) {
			my_exeption=e
			fail=true
			save_check_if_number_of_items_incremented_in_lastFolder(Save)
			fail=false
		}
		finally{
			if(fail)
				WebHelper.screenShoot(my_exeption.getMessage())
		}
	}

	protected static _DeleteAll(){
		def Yes=LanguageHelper.getText('Yes')
		try{
			delete_items(Yes)
		} catch (Exception e) {
			my_exeption=e
			fail=true
			delete_items(Yes)
			fail=false
		}
		finally{
			if(fail)
				WebHelper.screenShoot(my_exeption.getMessage())
		}
	}

	protected static _delete_by_id(int Id=0,String yes){

		(new MyElement("itemId",String.format("//li[@itemid = '%d']",Id))).click_with_delay()
		verify_delete(yes)
	}

	protected static _delete_first_item_(String yes){
		side_bar.a_item_in_last_folder.click_with_delay()
		verify_delete(yes)
	}

	private static verify_delete(String yes) {
		int before_deleting=side_bar._items_in_lastFolder()
		def xpath="//*[@id = 'recycle-view-linked-items']//following::button[ ( . = '%s')]"
		def yes_remove_button=new MyElement("yes_remove_button",String.format(xpath, yes))
		before_deleting--
		if(btn_remove_bar.isVisible(false))
			btn_remove_bar.click_with_delay().click_until_not_appear(yes_remove_button)
		else(WebHelper.check_three_dots()){ btn_remove_in_three_dots.click_with_delay() }
		yes_remove_button.click_with_delay()
		WebHelper.delay_medium()
		int after_deleting=side_bar._items_in_lastFolder()
		if(!WebHelper.verify_ints(after_deleting,before_deleting)){
			WebHelper.delay_medium()
			after_deleting=side_bar._items_in_lastFolder()
			WebHelper.verify_ints(after_deleting,before_deleting)
		}
	}

	private static delete_items(int count=side_bar.ALL,String yes){
		if(count==side_bar.ALL){
			while (side_bar.a_item_in_last_folder.isVisible(false)) {
				_delete_first_item_(yes)
			}
		}
		else {
			for(int i=0;i<count;i++){
				_delete_first_item_(yes)
			}
		}
	}

	private static  save_check_if_number_of_items_incremented_in_lastFolder(String save){
		int before_creating=side_bar._items_in_lastFolder()
		before_creating++
		a_choose_lastFolder_in_modal.click_with_hover()
		btn_save_in_last_folder=new MyElement("btn_save_in_last_folder",String.format("//span[(. = '%s')]",save))
		btn_save_in_last_folder.click_with_hover()
		WebHelper.delay_medium()
		int after_creating=side_bar._items_in_lastFolder()
		if(!WebHelper.verify_ints(after_creating,before_creating)){
			WebHelper.delay_medium()
			after_creating=side_bar._items_in_lastFolder()
			WebHelper.verify_ints(after_creating,before_creating)
		}
	}
}
