package com.pages.cm.languages

import com.pages.cm.Briefing_Page
import com.server.enums.Enum_Template
public class Briefing_En extends Briefing_Page {

	protected Briefing_En( ) {
		super(Enum_Template.BRIEFING)
	}

	protected static click_upper_button_create_item(){

		from_Upper_Button('Create New Item')
	}

	protected static choose_Briefing_Template_By_Typing() {

		choose_template_by_Typing('Briefing','Ok')
	}

	protected static click_edit_tab() {

		click_Edit('Edit Item')
	}

	protected static click_item_properties_tab() {

		click_item_Properties('Item Properties')
	}

	protected static verify_template_updateDate() {

		verify_Template_Name_Update("Briefing","Template")
	}

	protected static click_save_and_check_if_number_of_items_incremented_in_lastFolder(){
		save_check_if_number_of_items_incremented_in_lastFolder('Save')
	}

	protected static DeleteAll(){
		delete_items("Yes")
	}
}