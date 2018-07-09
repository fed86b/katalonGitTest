package com.pages
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.driver.WebUIDriverType
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.server.MyElement
import com.server.WebHelper
import com.server.enums.Enum_Create_Item
import com.server.enums.Enum_Status
import com.server.enums.Enum_Template

import internal.GlobalVariable

public class My_Template extends My_Item{

	static MyElement button_create_new_item=new MyElement("button_create_new_item","//div[@id='cm-tree-item-create-button']")
	static MyElement input_create_item_template=new MyElement("input_create_item_template","//input[@id = 'cm-tree-item-create-dialog-dtd-filter' and @type = 'text']")
	static MyElement button_ok_create_template=new MyElement("button_ok_create_template","//button[ @id = 'cm-tree-item-create-dialog-button-create']")
	static MyElement last_folder_in_cm_tree=new MyElement("last_folder_in_cm_tree","id('cmTree')/ul[@class='dynatree-container']/li[@class='dynatree-lastsib']")
	static MyElement saveAndRelocate=new MyElement("saveAndRelocate","//span[@class = 'kms-icon kms-icon--SaveAndRelocate action-bar__button-icon']")
	static MyElement choose_lastFolder_in_modal=new MyElement("choose_lastFolder_in_modal","//span[@class = 'dynatree-node dynatree-folder dynatree-lastsib dynatree-exp-cl dynatree-ico-cf']")
	static MyElement btn_save_in_last_folder
	static MyElement label_choose_template=new MyElement("label_choose_template","//input[@id = 'cm-tree-item-create-dialog-dtd-filter' and @type = 'text']")
	static MyElement remove_in_three_dots=new MyElement("remove_in_three_dots","//li[@data-id = 'Remove' and @class='action-bar__button action-bar__button--grouped']")
	static MyElement remove_bar_button=new MyElement("remove_bar_button","//li[@class='action-bar__button']/span[@class='kms-icon kms-icon--Remove action-bar__button-icon']")
	static MyElement item_in_last_folder=new MyElement("item_in_last_folder","//a[starts-with(text(), 'item_')]")




	static MyElement span_itemType=new MyElement(findTestObject('Kms_Page_OR/Roles/Content_Manager/Item_Properties_tab/template_Briefing'))
	static MyElement span_update_date=new MyElement(findTestObject('Kms_Page_OR/Roles/Content_Manager/Item_Properties_tab/span_update_date'))
	static MyElement span_itemId=new MyElement(findTestObject('Kms_Page_OR/Roles/Content_Manager/Item_Properties_tab/itemId_generated_by_template'))
	static MyElement itemTitleForEdit=new MyElement(findTestObject('Kms_Page_OR/Roles/Content_Manager/Item_Properties_tab/title_new_item'))
	static MyElement input_itemTitleForEdit=new MyElement(findTestObject('Kms_Page_OR/Roles/Content_Manager/Item_Properties_tab/input_title_name'))
	static MyElement input_STATUS_DATE_TO=new MyElement(findTestObject('Kms_Page_OR/Roles/Content_Manager/Item_Properties_tab/input_STATUS_DATE_TO'))
	static MyElement input_ST_DATE_FROM=new MyElement(findTestObject('Kms_Page_OR/Roles/Content_Manager/Item_Properties_tab/input_ST_DATE_FROM'))
	static MyElement select_status_from=new MyElement(findTestObject('Kms_Page_OR/Roles/Content_Manager/Item_Properties_tab/select_OfflineOnlineHiddenArch'))
	static MyElement select_status_to=new MyElement(findTestObject('Kms_Page_OR/Roles/Content_Manager/Item_Properties_tab/select_Status_after'))
	static MyElement a_Item_Properties=new MyElement(findTestObject('Kms_Page_OR/Roles/Content_Manager/Item_Properties_tab/a_Item Properties'))
	static MyElement a_Edit_Tab=new MyElement(findTestObject('Kms_Page_OR/Roles/Content_Manager/Edit_tab/a_Edit_tab'))
	static MyElement div_ShortDescriptionEdit=new MyElement(findTestObject('Kms_Page_OR/Roles/Content_Manager/Item_Properties_tab/div_ShortDescriptionEdit'))
	static MyElement textarea_descriptionEdit=new MyElement(findTestObject('Kms_Page_OR/Roles/Content_Manager/Item_Properties_tab/textarea_descriptionEdit'))




	static final int ALL=0
	static final BYPATH="(//a[starts-with(text(), '/item_'/)])[last()]"
	static Enum_Template template
	protected My_Template (Enum_Template template){
		super()
		this.template=template
	}



	protected static  _write_short_description(){
		try {
			if(DriverFactory.getExecutedBrowser() != WebUIDriverType.IE_DRIVER){
				set_description()
			}
		} catch (Exception e) {
			my_exeption=e
			fail=true
			WebHelper.delay_medium()
			set_description()
			fail=false
		}
		finally{
			if(fail)
				WebHelper.screenShoot(my_exeption.getMessage())
		}
	}



	protected static  _set_date_valid_to_status(int days=0){
		try{
			set_date(days,GlobalVariable.G_DAYS_Add_To,input_STATUS_DATE_TO)
		} catch (Exception e) {
			my_exeption=e
			fail=true
			WebHelper.delay_medium()
			set_date(days,GlobalVariable.G_DAYS_Add_To,input_STATUS_DATE_TO)
			fail=false
		}
		finally{
			if(fail)
				WebHelper.screenShoot(my_exeption.getMessage())
		}
	}

	protected static  _set_date_valid_from_status( int days=0){
		try{
			set_date(days,GlobalVariable.G_DAYS_Add_From,input_ST_DATE_FROM)
		} catch (Exception e) {
			my_exeption=e
			fail=true
			WebHelper.delay_medium()
			set_date(days,GlobalVariable.G_DAYS_Add_From,input_ST_DATE_FROM)
			fail=false
		}
		finally{
			if(fail)
				WebHelper.screenShoot(my_exeption.getMessage())
		}
	}

	protected static  _choose_Status(Enum_Status status) {
		try{

			set_status(status,select_status_from)
		} catch (Exception e) {
			my_exeption=e
			fail=true
			WebHelper.delay_medium()
			set_status(status,select_status_from)
			fail=false
		}
		finally{
			if(fail)
				WebHelper.screenShoot(my_exeption.getMessage())
		}
	}

	protected static  _choose_status_after(Enum_Status status) {
		set_status(status,select_status_to)
	}


	protected static  _click_save_and_relocate_to_lastFolder(){
		try {
			saveAndRelocate.click_with_hover()
		} catch (Exception e) {
			my_exeption=e
			fail=true
			WebHelper.delay_medium()
			saveAndRelocate.click_with_hover()
			fail=false
		}
		finally{
			if(fail)
				WebHelper.screenShoot(my_exeption.getMessage())
		}
	}

	protected static  _set_Item_Name(){
		try {
			set_item_name()
		} catch (Exception e) {
			my_exeption=e
			fail=true
			WebHelper.delay_medium()
			set_item_name()
			fail=false
		}
		finally{
			if(fail)
				WebHelper.screenShoot(my_exeption.getMessage())
		}
	}




	protected static  save_check_if_number_of_items_incremented_in_lastFolder(String save){
		int before_creating=_items_in_lastFolder()
		before_creating++
		choose_lastFolder_in_modal.click_with_hover()
		btn_save_in_last_folder=new MyElement("btn_save_in_last_folder",String.format("//span[(. = '%s')]",save))
		btn_save_in_last_folder.click_with_hover()
		WebHelper.delay_medium()
		int after_creating=_items_in_lastFolder()
		if(!WebHelper.verify_ints(after_creating,before_creating)){
			WebHelper.delay_medium()
			after_creating=_items_in_lastFolder()
			WebHelper.verify_ints(after_creating,before_creating)
		}
	}

	protected static int _items_in_lastFolder(){
		try {
			get_items_in_last_folder()
		} catch (Exception e) {
			my_exeption=e
			fail=true
			WebHelper.delay()
			get_items_in_last_folder()
			fail=false
		}
		finally{
			if(fail)
				WebHelper.screenShoot(my_exeption.getMessage())
		}
	}
	protected static  _open_lastFolder(){
		try {
			if(!last_folder_in_cm_tree.for_Ie(item_in_last_folder))
			last_folder_in_cm_tree.click_with_hover()
		} catch (Exception e) {
			my_exeption=e
			fail=true
			WebHelper.delay_medium()
			last_folder_in_cm_tree.click_with_delay()
			fail=false
		}
		finally{
			if(fail)
				WebHelper.screenShoot(my_exeption.getMessage())
		}
	}

	protected static from_Upper_Button(String title ){
		button_create_new_item.click_with_hover(title)
	}

	protected static choose_template_by_Typing(String briefing, String ok) {
		input_create_item_template.click_with_delay()
		input_create_item_template.write_text( briefing)
		button_ok_create_template.click(ok)
	}




	protected static verify_Template_Name_Update(String briefing){
		String actual_template=span_itemType.generate_Name()
		String actual_date=span_update_date.generate_Name()
		WebHelper.verify_texts(actual_template, briefing)
		WebHelper.verify_texts(WebHelper.get_date(actual_date), WebHelper.get_date())
	}


	protected static delete_items(int count=ALL,String yes){
		if(count==ALL){
			
			while (item_in_last_folder.isVisible(false)) {
				delete_first_item_(yes)
			}
		}
		else {
			for(int i=0;i<count;i++){
				delete_first_item_(yes)
			}
		}
	}

	protected static delete_first_item_(String yes){

		item_in_last_folder.findElement().click_with_delay()
		verify_delete(yes)
	}



	protected static delete_by_id(int Id=0,String yes){
		(new MyElement("itemId",String.format("//li[@itemid = '%d']",Id))).click_with_delay()
		verify_delete(yes)
	}


	protected static choose_Template_By_Clicking(String ok) {
		def btn_ok_xpath_lang="//button[@type = 'button' and @id = 'cm-tree-item-create-dialog-button-create and( .='%s')' "
		label_choose_template.click_with_hover()
		(new MyElement("btn_ok",String.format(btn_ok_xpath_lang, ok))).click()
	}

	protected static click_Edit(String edit) {
		//def xpath=String.format("//a[@href = '#item-update-tab-edit' and (. = '%s')",edit)
		//a_Edit_Tab.modify(xpath)
		a_Edit_Tab.double_click(edit)
	}

	protected static click_item_Properties(String itemProperties) {
		//		def xpath=String.format("//a[@href = '#item-update-tab-main' and (. = '%s')",itemProperties)
		//		a_Item_Properties.modify(xpath)
		a_Item_Properties.double_click(itemProperties)
	}
	private static set_item_name() {
		String id=span_itemId.generate_Name()
		String title="item_"+template+"_"+id
		itemTitleForEdit.click_with_delay()
		input_itemTitleForEdit.write_key_chord( title)
	}

	private static get_items_in_last_folder() {
		String num_with_left_bracket= WebUI.getText(last_folder_in_cm_tree).split("]")[0]
		String pure_num=num_with_left_bracket.substring(1)
		return Integer.parseInt(pure_num)
	}


	private static verify_delete(String yes) {
		int before_deleting=_items_in_lastFolder()
		before_deleting--
		if(remove_bar_button.isVisible(false))
			remove_bar_button.click_with_delay()
		else(WebHelper.check_three_dots()){ remove_in_three_dots.click_with_delay() }
		def xpath="//*[@id = 'recycle-view-linked-items']//following::button[ ( . = '%s')]"
		def yes_remove_button=new MyElement("yes_remove_button",String.format(xpath, yes))
		yes_remove_button.click_with_delay()
		WebHelper.delay_medium()
		int after_deleting=_items_in_lastFolder()
		if(!WebHelper.verify_ints(after_deleting,before_deleting)){
			WebHelper.delay_medium()
			after_deleting=_items_in_lastFolder()
			WebHelper.verify_ints(after_deleting,before_deleting)
		}
	}

	private static set_date(int days,int add_days, MyElement element) {
		def date
		if(days==0)
			date=WebHelper.add_to_dateNow_day(add_days)
		else
			date=WebHelper.add_to_dateNow_day(days)
		element.write_text( date)
		element.press_Esc()
	}

	private static set_status(Enum_Status status,MyElement element) {
		switch(status){
			case Enum_Status.OFFLINE:
				element.select_by_index(0)
				break
			case Enum_Status.ONLINE:
				element.select_by_index(1)
				break
			case Enum_Status.HIDDEN:
				element.select_by_index(2)
				break
			case Enum_Status.ARCHIVE:
				element.select_by_index(3)
				break
		}
	}

	private static  String set_description() {
		def description=WebHelper.get_item_data(Enum_Create_Item.short_description)
		div_ShortDescriptionEdit.double_click()
		textarea_descriptionEdit.write_key_chord(description)
	}
}

