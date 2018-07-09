package com.pages.cm.languages

import com.pages.cm.Briefing_Page
import com.server.WebHelper
import com.server.enums.Enum_Template
public class Briefing_En extends Briefing_Page {
	
	protected Briefing_En( ) {
		super()
	}

	protected static _click_upper_button_create_item(){
		try {
			from_Upper_Button('Create New Item')
		} catch (Exception e) {
			my_exeption=e
			fail=true
			from_Upper_Button('Create New Item')
			fail=false
		}
		finally{
			if(fail)
				WebHelper.screenShoot(my_exeption.getMessage())
		}
	}

	protected static _choose_Briefing_Template_By_Typing() {

		try {
			choose_template_by_Typing('Briefing','Ok')
		} catch (Exception e) {
			my_exeption=e
			fail=true
			choose_template_by_Typing('Briefing','Ok')
			fail=false
		}
		finally{
			if(fail)
				WebHelper.screenShoot(my_exeption.getMessage())
		}
	}
	
	protected static _choose_Date_Edit_Item() {
		
				try {
					choose_Date_Edit_Item("Today")
				} catch (Exception e) {
					my_exeption=e
					fail=true
					choose_Date_Edit_Item("Today")
					fail=false
				}
				finally{
					if(fail)
						WebHelper.screenShoot(my_exeption.getMessage())
				}
			}

	protected static _click_edit_tab() {
		try{
			click_Edit('Edit Item')
		} catch (Exception e) {
			my_exeption=e
			fail=true
			click_Edit('Edit Item')
			fail=false
		}
		finally{
			if(fail)
				WebHelper.screenShoot(my_exeption.getMessage())
		}
	}

	protected static _click_item_properties_tab() {
		try{
			click_item_Properties('Item Properties')
		} catch (Exception e) {
			my_exeption=e
			fail=true
			click_item_Properties('Item Properties')
			fail=false
		}
		finally{
			if(fail)
				WebHelper.screenShoot(my_exeption.getMessage())
			
		}
	}

	protected static _verify_template_updateDate() {
		try{
			verify_Template_Name_Update("Briefing")
		} catch (Exception e) {
			my_exeption=e
			fail=true
			verify_Template_Name_Update("Briefing")
			fail=false
		}
		finally{
			if(fail)
				WebHelper.screenShoot(my_exeption.getMessage())
			
		}
	}

	protected static _click_save_and_check_if_number_of_items_incremented_in_lastFolder(){
		try{
			save_check_if_number_of_items_incremented_in_lastFolder('Save')
		} catch (Exception e) {
			my_exeption=e
			fail=true
			save_check_if_number_of_items_incremented_in_lastFolder('Save')
			fail=false
		}
		finally{
			if(fail)
				WebHelper.screenShoot(my_exeption.getMessage())
		}
	}

	protected static _DeleteAll(){
		try{
			delete_items("Yes")
		} catch (Exception e) {
			my_exeption=e
			fail=true
			delete_items("Yes")
			fail=false
		}
		finally{
			if(fail)
				WebHelper.screenShoot(my_exeption.getMessage())
		}
	}
}