package com.pages
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.driver.WebUIDriverType
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.server.WebHelper
import com.server.enums.Enum_Create_Item
import com.server.enums.Enum_Status
import com.server.enums.Enum_Tables
import com.server.enums.Enum_Template

import internal.GlobalVariable

public class My_Template extends My_Item{
	static protected def button_create_new_item=WebHelper.createElement("button_create_new_item","//div[@id='cm-tree-item-create-button']/span")
	static protected def input_create_item_template=WebHelper.createElement("input_create_item_template","//input[@id = 'cm-tree-item-create-dialog-dtd-filter' and @type = 'text']")
	static protected def button_ok_create_template=WebHelper.createElement("button_ok_create_template","//button[ @id = 'cm-tree-item-create-dialog-button-create']")
	static protected def last_folder_in_cm_tree=WebHelper.createElement("last_folder_in_cm_tree","id('cmTree')/ul[@class='dynatree-container']/li[@class='dynatree-lastsib']")
	static protected def saveAndRelocate=WebHelper.createElement("saveAndRelocate","//span[@class = 'kms-icon kms-icon--SaveAndRelocate action-bar__button-icon']")
	static protected def choose_lastFolder_in_modal=WebHelper.createElement("choose_lastFolder_in_modal","//span[@class = 'dynatree-node dynatree-folder dynatree-lastsib dynatree-exp-cl dynatree-ico-cf']")
	static protected def btn_save_in_last_folder
	static protected def label_choose_template=WebHelper.createElement("label_choose_template","//input[@id = 'cm-tree-item-create-dialog-dtd-filter' and @type = 'text']")
	static protected def remove_in_three_dots=WebHelper.createElement("remove_in_three_dots","//li[@data-id = 'Remove' and @class='action-bar__button action-bar__button--grouped']")
	static protected def remove_bar_button=WebHelper.createElement("remove_bar_button","//li[@class='action-bar__button']/span[@class='kms-icon kms-icon--Remove action-bar__button-icon']")
	static protected def item_in_last_folder=WebHelper.createElement("item_in_last_folder","//a[starts-with(text(), 'item_')]")




	static protected def span_itemType=findTestObject('Kms_Page_OR/Roles/Content_Manager/Item_Properties_tab/template_Briefing')
	static protected def span_update_date=findTestObject('Kms_Page_OR/Roles/Content_Manager/Item_Properties_tab/span_update_date')
	static protected def span_itemId=findTestObject('Kms_Page_OR/Roles/Content_Manager/Item_Properties_tab/itemId_generated_by_template')
	static protected def itemTitleForEdit=findTestObject('Kms_Page_OR/Roles/Content_Manager/Item_Properties_tab/title_new_item')
	static protected def input_itemTitleForEdit=findTestObject('Kms_Page_OR/Roles/Content_Manager/Item_Properties_tab/input_title_name')
	static protected def input_STATUS_DATE_TO=findTestObject('Kms_Page_OR/Roles/Content_Manager/Item_Properties_tab/input_STATUS_DATE_TO')
	static protected def input_ST_DATE_FROM=findTestObject('Kms_Page_OR/Roles/Content_Manager/Item_Properties_tab/input_ST_DATE_FROM')
	static protected def select_status_from=findTestObject('Kms_Page_OR/Roles/Content_Manager/Item_Properties_tab/select_OfflineOnlineHiddenArch')
	static protected def select_status_to=findTestObject('Kms_Page_OR/Roles/Content_Manager/Item_Properties_tab/select_Status_after')
	static protected def a_Item_Properties=findTestObject('Kms_Page_OR/Roles/Content_Manager/Item_Properties_tab/a_Item Properties')
	static protected def a_Edit_Item=findTestObject('Kms_Page_OR/Roles/Content_Manager/Edit_tab/a_Edit Item')
	static protected def div_ShortDescriptionEdit=findTestObject('Kms_Page_OR/Roles/Content_Manager/Item_Properties_tab/div_ShortDescriptionEdit')
	static protected def textarea_descriptionEdit=findTestObject('Kms_Page_OR/Roles/Content_Manager/Item_Properties_tab/textarea_descriptionEdit')




	static final int ALL=0
	static final BYPATH="(//a[starts-with(text(), '/item_'/)])[last()]"
	static Enum_Template template
	protected My_Template (Enum_Template template){
		super()
		this.template=template
	}

	protected static choose_template_by_Typing(String briefing, String ok) {
		try {

			WebHelper.verify_text_click_with_hover(input_create_item_template)
			WebHelper.write_text(input_create_item_template, briefing)
			WebHelper.verify_text_click_with_hover(button_ok_create_template,ok)
		}
		catch (Exception e) {
			WebHelper.screenShoot(e.getMessage())
			throw e
		}
	}



	protected static verify_Template_Name_Update(String briefing){
		try {
			if(DriverFactory.getExecutedBrowser() != WebUIDriverType.IE_DRIVER){
				String actual_template=WebHelper.generate_Name(span_itemType)
				String actual_date=WebHelper.generate_Name(span_update_date)
				WebHelper.verify_texts(actual_template, briefing)
				WebHelper.verify_texts(WebHelper.get_date(actual_date), WebHelper.get_date())
			}
		}
		catch (Exception e) {
			WebHelper.screenShoot(e.getMessage())
			throw e
		}
	}


	protected static delete_items(int count=ALL,String yes){

		try {
			if(count==ALL){
				while (WebHelper.verify_text_visibility(item_in_last_folder,"",false)) {
					delete_first_item_(yes)
				}
			}
			else {
				for(int i=0;i<count;i++){
					delete_first_item_(yes)
				}
			}
		}
		catch (Exception e) {

			WebHelper.screenShoot(e.getMessage())
			throw e
		}
	}

	protected static delete_first_item_(String yes){
		try {
			WebHelper.verify_text_click_with_hover(item_in_last_folder)
			verify_delete(yes)
		}
		catch (Exception e) {

			WebHelper.screenShoot(e.getMessage())
			throw e
		}
	}

	private static verify_delete(String yes) {
		int before_deleting=items_in_lastFolder()
		before_deleting--

		if(WebHelper.check_three_dots())
			WebHelper.verify_text_click_with_Delay(remove_in_three_dots)
		else
			WebHelper.verify_text_click_with_Delay(remove_bar_button)
		def xpath="//*[@id = 'recycle-view-linked-items']//following::button[ ( . = '%s')]"
		def yes_remove_button=WebHelper.createElement("yes_remove_button",String.format(xpath, yes))
		WebHelper.verify_text_click_with_Delay(yes_remove_button)
		WebHelper.delay_medium()
		int after_deleting=items_in_lastFolder()
		if(!WebHelper.verify_ints(after_deleting,before_deleting)){
			WebHelper.delay_medium()
			after_deleting=items_in_lastFolder()
			WebHelper.verify_ints(after_deleting,before_deleting)
		}
	}

	protected static delete_by_id(int Id=0,String yes){
		try {
			WebHelper.verify_text_click_with_hover(WebHelper.createElement("itemId",String.format("//li[@itemid = '%d']",Id)))
			verify_delete(yes)
		}
		catch (Exception e) {

			WebHelper.screenShoot(e.getMessage())
			throw e
		}
	}


	protected static choose_Template_By_Clicking(String ok) {
		try {
			WebHelper.verify_text_click_with_hover(label_choose_template)
			def btn_ok_xpath_lang="//button[@type = 'button' and @id = 'cm-tree-item-create-dialog-button-create and( .='%s')' "

			WebHelper.verify_text_click_with_hover(WebHelper.createElement("btn_ok",String.format(btn_ok_xpath_lang, ok)))
		}
		catch (Exception e) {
			WebHelper.screenShoot(e.getMessage())
			throw e
		}
	}

	protected static click_Edit(String edit) {
		try {
			WebHelper.verify_text_click_with_hover(a_Edit_Item,edit)
		}
		catch (Exception e) {
			WebHelper.screenShoot(e.getMessage())
			throw e
		}
	}

	protected static click_item_Properties(String itemProperties) {
		try {
			WebHelper.verify_text_double_click(a_Item_Properties,itemProperties)
		}
		catch (Exception e) {
			WebHelper.screenShoot(e.getMessage())
			throw e
		}
	}

	protected static write_short_description(){
		try {
			if(DriverFactory.getExecutedBrowser() != WebUIDriverType.IE_DRIVER){
				def description=WebHelper.get_item_data(Enum_Tables.Create_Item, Enum_Create_Item.short_description)
				WebHelper.verify_text_double_click(div_ShortDescriptionEdit)
				WebHelper.write_key_chord(textarea_descriptionEdit,description)
			}
		}
		catch (Exception e) {
			WebHelper.screenShoot(e.getMessage())
			throw e
		}
	}

	protected static set_date_valid_to_status(int days=0){

		try {
			def date
			if(days==0)
				date=WebHelper.add_to_dateNow_day(GlobalVariable.G_DAYS_Add_To)
			else
				date=WebHelper.add_to_dateNow_day(days)
			WebHelper.write_text(input_STATUS_DATE_TO, date)
			WebHelper.press_Esc(input_STATUS_DATE_TO)
		}
		catch (Exception e) {
			WebHelper.screenShoot(e.getMessage())
			throw e
		}
	}

	protected static set_date_valid_from_status( int days=0){

		try {
			def date
			if(days==0)
				date=WebHelper.add_to_dateNow_day(GlobalVariable.G_DAYS_Add_From)
			else
				date=WebHelper.add_to_dateNow_day(days)
			WebHelper.write_text(input_ST_DATE_FROM, date)
			WebHelper.press_Esc(input_ST_DATE_FROM)
		}
		catch (Exception e) {
			WebHelper.screenShoot(e.getMessage())
			throw e
		}
	}

	protected static choose_Status(Enum_Status status) {
		try {
			switch(status){
				case Enum_Status.OFFLINE:
					WebHelper.select_by_index(select_status_from,0)
					break
				case Enum_Status.ONLINE:
					WebHelper.select_by_index(select_status_from,1)
					break
				case Enum_Status.HIDDEN:
					WebHelper.select_by_index(select_status_from,2)
					break
				case Enum_Status.ARCHIVE:
					WebHelper.select_by_index(select_status_from,3)
					break
			}
		}
		catch (Exception e) {
			WebHelper.screenShoot(e.getMessage())
			throw e
		}
	}

	protected static choose_status_after(Enum_Status status) {
		try {
			switch(status){
				case Enum_Status.OFFLINE:
					WebHelper.select_by_index(select_status_to,0)
					break
				case Enum_Status.ONLINE:
					WebHelper.select_by_index(select_status_to,1)
					break
				case Enum_Status.HIDDEN:
					WebHelper.select_by_index(select_status_to,2)
					break
				case Enum_Status.ARCHIVE:
					WebHelper.select_by_index(select_status_to,3)
					break
			}
		}
		catch (Exception e) {
			WebHelper.screenShoot(e.getMessage())
			throw e
		}
	}

	protected static click_save_and_relocate_to_lastFolder(){
		try {
			WebHelper.verify_text_click_with_hover(saveAndRelocate)
		}
		catch (Exception e) {
			WebHelper.screenShoot(e.getMessage())
			throw e
		}
	}

	protected static set_Item_Name(){
		try {
			String id=WebHelper.generate_Name(span_itemId)
			String title="item_"+template+"_"+id
			WebHelper.verify_text_click_with_Delay(itemTitleForEdit)
			WebHelper.write_key_chord(input_itemTitleForEdit, title)
		}
		catch (Exception e) {
			WebHelper.screenShoot(e.getMessage())
			throw e
		}
	}



	protected static save_check_if_number_of_items_incremented_in_lastFolder(String save){

		try {
			int before_creating=items_in_lastFolder()
			before_creating++
			WebHelper.verify_text_click_with_hover(this.choose_lastFolder_in_modal)
			btn_save_in_last_folder=WebHelper.createElement("btn_save_in_last_folder",String.format("//span[(. = '%s')]",save))
			WebHelper.verify_text_click_with_hover(btn_save_in_last_folder)
			WebHelper.delay_medium()
			int after_creating=items_in_lastFolder()
			if(!WebHelper.verify_ints(after_creating,before_creating)){
				WebHelper.delay_medium()
				after_creating=items_in_lastFolder()
				WebHelper.verify_ints(after_creating,before_creating)
			}
		}
		catch (Exception e) {
			WebHelper.screenShoot(e.getMessage())
			throw e
		}
	}

	protected static int items_in_lastFolder(){

		String num_with_left_bracket= WebUI.getText(last_folder_in_cm_tree).split("]")[0]
		String pure_num=num_with_left_bracket.substring(1)
		return Integer.parseInt(pure_num)
	}

	protected static open_lastFolder(){
		try {
			WebHelper.verify_text_click_with_hover(last_folder_in_cm_tree)
		}
		catch (Exception e) {
			WebHelper.screenShoot(e.getMessage())
			throw e
		}
	}

	protected static from_Upper_Button(String title ){
		try {
			WebHelper.verify_text_click_with_Delay(button_create_new_item,title)
		}
		catch (Exception e) {

			WebHelper.screenShoot(e.getMessage())
			throw e
		}
	}
}
