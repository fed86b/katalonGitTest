package com.pages
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.system.Compare_Images
import com.system.LanguageHelper
import com.system.MyElement
import com.system.WebHelper
import com.system.enums.Enum_Create_Item
import com.system.enums.Enum_Language
import com.system.enums.Enum_Position
import com.system.enums.Enum_Status
import com.system.enums.Enum_Template

import internal.GlobalVariable

public class My_Template extends My_Item{

	static MyElement lbl_choose_template=new MyElement("lbl_choose_template","//input[@id = 'cm-tree-item-create-dialog-dtd-filter' and @type = 'text']")
	static MyElement lbl_itemType=new MyElement(findTestObject('Kms_Page_OR/Roles/Content_Manager/Item_Properties_tab/lbl_itemType'))
	static MyElement lbl_update_date=new MyElement(findTestObject('Kms_Page_OR/Roles/Content_Manager/Item_Properties_tab/lbl_update_date'))
	static MyElement lbl_itemId=new MyElement(findTestObject('Kms_Page_OR/Roles/Content_Manager/Item_Properties_tab/lbl_itemId'))

	static MyElement txt_create_item_template=new MyElement("txt_create_item_template","//input[@id = 'cm-tree-item-create-dialog-dtd-filter' and @type = 'text']")
	static MyElement txt_itemTitleForEdit=new MyElement(findTestObject('Kms_Page_OR/Roles/Content_Manager/Item_Properties_tab/txt_title_name'))
	static MyElement txt_STATUS_DATE_TO=new MyElement(findTestObject('Kms_Page_OR/Roles/Content_Manager/Item_Properties_tab/txt_STATUS_DATE_TO'))
	static MyElement txt_ST_DATE_FROM=new MyElement(findTestObject('Kms_Page_OR/Roles/Content_Manager/Item_Properties_tab/txt_ST_DATE_FROM'))
	static MyElement textarea_descriptionEdit=new MyElement(findTestObject('Kms_Page_OR/Roles/Content_Manager/Item_Properties_tab/textarea_descriptionEdit'))
	static MyElement textarea_sms_desc=new MyElement(findTestObject('Kms_Page_OR/Roles/Content_Manager/Item_Properties_tab/textarea_sms_desc'))
	static MyElement txt_promoted_words=new MyElement(findTestObject('Kms_Page_OR/Roles/Content_Manager/Item_Properties_tab/txt_promoted_words'))
	static MyElement txt_act_date_from=new MyElement(findTestObject('Kms_Page_OR/Roles/Content_Manager/Item_Properties_tab/txt_ACT_DATE_TO'))

	static MyElement a_last_folder_in_cm_tree=new MyElement("a_last_folder_in_cm_tree","id('cmTree')/ul[@class='dynatree-container']/li[@class='dynatree-lastsib']")
	static MyElement a_choose_lastFolder_in_modal=new MyElement("a_choose_lastFolder_in_modal","//span[@class = 'dynatree-node dynatree-folder dynatree-lastsib dynatree-exp-cl dynatree-ico-cf']")
	static MyElement a_item_in_last_folder=new MyElement("a_item_in_last_folder","//a[starts-with(text(), 'item_')]")
	static MyElement a_Item_Properties=new MyElement(findTestObject('Kms_Page_OR/Roles/Content_Manager/Item_Properties_tab/a_Item Properties'))
	static MyElement a_Edit_Tab=new MyElement(findTestObject('Kms_Page_OR/Roles/Content_Manager/Edit_tab/a_Edit_tab'))
	static MyElement a_uploaded_file=new MyElement(findTestObject('Kms_Page_OR/Roles/Content_Manager/Item_Properties_tab/uploaded_file/a_download.jpg'))


	static MyElement btn_x_close=new MyElement("btn_x_close","//a[@id='fancybox-close']")

	static MyElement btn_ok_create_template=new MyElement("btn_ok_create_template","//button[ @id = 'cm-tree-item-create-dialog-button-create']")
	static MyElement btn_saveAndRelocate=new MyElement("btn_saveAndRelocate","//span[@class = 'kms-icon kms-icon--SaveAndRelocate action-bar__button-icon']")
	static MyElement btn_save_in_last_folder
	static MyElement btn_create_new_item=new MyElement("btn_create_new_item","//div[@id='cm-tree-item-create-button']")
	static MyElement btn_remove_in_three_dots=new MyElement("btn_remove_in_three_dots","//li[@data-id = 'Remove' and @class='action-bar__button action-bar__button--grouped']")
	static MyElement btn_remove_bar=new MyElement("btn_remove_bar","//li[@class='action-bar__button']/span[@class='kms-icon kms-icon--Remove action-bar__button-icon']")
	static MyElement btn_open_uploaded_file=new MyElement("btn_open_uploaded_file","id('openExternalFile')/span[@class='ui-button-text']/parent::button")
	static MyElement btn_browse=new MyElement(findTestObject('Kms_Page_OR/Roles/Content_Manager/Item_Properties_tab/btn_browse'))

	static MyElement div_ShortDescriptionEdit=new MyElement(findTestObject('Kms_Page_OR/Roles/Content_Manager/Item_Properties_tab/div_ShortDescriptionEdit'))
	static MyElement div_itemTitleForEdit=new MyElement(findTestObject('Kms_Page_OR/Roles/Content_Manager/Item_Properties_tab/div_title_new_item'))

	static MyElement select_status_from=new MyElement(findTestObject('Kms_Page_OR/Roles/Content_Manager/Item_Properties_tab/select_status_from'))
	static MyElement select_status_to=new MyElement(findTestObject('Kms_Page_OR/Roles/Content_Manager/Item_Properties_tab/select_Status_after'))

	static MyElement checkbox_active_ver=new MyElement(findTestObject('Kms_Page_OR/Roles/Content_Manager/Item_Properties_tab/checkbox_active_version'))
	static MyElement checkbox_print=new MyElement(findTestObject('Kms_Page_OR/Roles/Content_Manager/Item_Properties_tab/checkbox_print'))
	static MyElement checkbox_email=new MyElement(findTestObject('Kms_Page_OR/Roles/Content_Manager/Item_Properties_tab/checkbox_email'))
	static MyElement checkbox_feedback=new MyElement(findTestObject('Kms_Page_OR/Roles/Content_Manager/Item_Properties_tab/checkbox_feedback'))
	static MyElement checkbox_bookmarks=new MyElement(findTestObject('Kms_Page_OR/Roles/Content_Manager/Item_Properties_tab/checkbox_bookmarks'))
	static MyElement checkbox_fax=new MyElement(findTestObject('Kms_Page_OR/Roles/Content_Manager/Item_Properties_tab/checkbox_fax'))
	static MyElement checkbox_search=new MyElement(findTestObject('Kms_Page_OR/Roles/Content_Manager/Item_Properties_tab/checkbox_search'))
	static MyElement checkbox_hit=new MyElement(findTestObject('Kms_Page_OR/Roles/Content_Manager/Item_Properties_tab/checkbox_hit'))
	static MyElement chkbx_add_homepage=new MyElement(findTestObject('Kms_Page_OR/Roles/Content_Manager/Item_Properties_tab/chkbx_add_homepage'))

	static MyElement img_uploaded_file=new MyElement(findTestObject('Kms_Page_OR/Roles/Content_Manager/Item_Properties_tab/img_uploaded'))
	static MyElement iframe_uploaded=new MyElement(findTestObject('Kms_Page_OR/Roles/Content_Manager/Item_Properties_tab/uploaded_file/iframe'))



	static final int ALL=0
	static final BYPATH="(//a[starts-with(text(), '/item_'/)])[last()]"
	static Enum_Template template

	protected My_Template (Enum_Template template,Enum_Language lang){
		super(lang)
		this.template=template
	}


	protected static _verify_template_updateDate() {
		WebHelper.scroll(Enum_Position.TOP)
		def templt=LanguageHelper.getText(template.toString())
		try{
			verify_Template_Name_Update(templt)
		} catch (Exception e) {
			my_exeption=e
			fail=true
			verify_Template_Name_Update(templt)
			fail=false
		}
		finally{
			if(fail)
				WebHelper.screenShoot(my_exeption.getMessage())
		}
	}

	protected static _choose_Template_By_Typing() {
		def templt=LanguageHelper.getText(template.toString())
		def ok=LanguageHelper.getText('Ok')
		try {
			choose_template_by_Typing(templt,ok)
		} catch (Exception e) {
			my_exeption=e
			fail=true
			choose_template_by_Typing(templt,ok)
			fail=false
		}
		finally{
			if(fail)
				WebHelper.screenShoot(my_exeption.getMessage())
		}
	}

	protected static _click_upper_button_create_item(){
		def text=LanguageHelper.getText('Create New Item')
		try {
			from_Upper_Button(text)
		} catch (Exception e) {
			my_exeption=e
			fail=true
			from_Upper_Button(text)
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

	protected static  _check_add_home_page(boolean check=true){
		WebHelper.scroll(Enum_Position.BOTTOM)
		if(check)
			check_ckbx(chkbx_add_homepage)
		else
			uncheck(chkbx_add_homepage)
	}

	protected static  _check_active_version(boolean check=true){
		WebHelper.scroll(Enum_Position.TOP)
		if(check)
			check_ckbx(checkbox_active_ver)
		else
			uncheck(checkbox_active_ver)
	}

	protected static  _check_print(boolean check=true){
		WebHelper.scroll(Enum_Position.BOTTOM)
		if(check)
			check_ckbx(checkbox_print)
		else
			uncheck(checkbox_print)
	}

	protected static  _check_email(boolean check=true){
		WebHelper.scroll(Enum_Position.BOTTOM)
		if(check)
			check_ckbx(checkbox_email)
		else
			uncheck(checkbox_email)
	}

	protected static  _check_feedback(boolean check=true){
		WebHelper.scroll(Enum_Position.BOTTOM)
		if(check)
			check_ckbx(checkbox_feedback)
		else
			uncheck(checkbox_feedback)
	}

	protected static  _check_bookmarks(boolean check=true){
		WebHelper.scroll(Enum_Position.BOTTOM)
		if(check)
			check_ckbx(checkbox_bookmarks)
		else
			uncheck(checkbox_bookmarks)
	}

	protected static  _check_fax(boolean check=true){
		WebHelper.scroll(Enum_Position.BOTTOM)
		if(check)
			check_ckbx(checkbox_fax)
		else
			uncheck(checkbox_fax)
	}

	protected static  _check_search(boolean check=true){
		WebHelper.scroll(Enum_Position.BOTTOM)
		if(check)
			check_ckbx(checkbox_search)
		else
			uncheck(checkbox_search)
	}

	protected static  _check_hit(boolean check=true){
		WebHelper.scroll(Enum_Position.BOTTOM)
		if(check)
			check_ckbx(checkbox_hit)
		else
			uncheck(checkbox_hit)
	}

	protected static  _write_short_description(){
		WebHelper.scroll(Enum_Position.TOP)
		try {
			div_ShortDescriptionEdit.double_click().click_until_not_appear(textarea_descriptionEdit)
			set_description(Enum_Create_Item.short_description,textarea_descriptionEdit)
		} catch (Exception e) {
			my_exeption=e
			fail=true
			WebHelper.delay_medium()
			set_description(Enum_Create_Item.short_description,this.textarea_descriptionEdit)
			fail=false
		}
		finally{
			if(fail)
				WebHelper.screenShoot(my_exeption.getMessage())
		}
	}

	protected static _browse_file(){
		try{
			WebHelper.scroll(Enum_Position.BOTTOM)
			browse_file()
		} catch (Exception e) {
			my_exeption=e
			fail=true
			WebHelper.delay_medium()
			browse_file()
			fail=false
		}
		finally{
			if(fail)
				WebHelper.screenShoot(my_exeption.getMessage())
		}
	}

	private static browse_file(){
		btn_browse.click_until_not_appear(iframe_uploaded)
		WebHelper.delay_medium()
		a_uploaded_file.click_with_hover()
		btn_open_uploaded_file.click_with_hover()
	}

	protected static _click_on_image(def file_image="download.jpg"){
		try{
			img_uploaded_file.compareImages(file_image)
			this.img_uploaded_file.click()
			WebHelper.delay()
			btn_x_close.click()
		} catch (Exception e) {
			my_exeption=e
			fail=true
			WebHelper.delay_medium()
			this.img_uploaded_file.click()
			btn_x_close.click()
			fail=false
		}
		finally{
			if(fail)
				WebHelper.screenShoot(my_exeption.getMessage())
		}
	}

	protected static  _write_sms_description(){
		try {
			WebHelper.scroll(Enum_Position.BOTTOM)
			set_description(Enum_Create_Item.sms_desc, this.textarea_sms_desc)
		} catch (Exception e) {
			my_exeption=e
			fail=true
			WebHelper.delay_medium()
			set_description(Enum_Create_Item.sms_desc, this.textarea_sms_desc)
			fail=false
		}
		finally{
			if(fail)
				WebHelper.screenShoot(my_exeption.getMessage())
		}
	}

	protected static  _write_promoted_key_words(){
		try {
			WebHelper.scroll(Enum_Position.BOTTOM)
			set_description(Enum_Create_Item.promoted_item_words, this.txt_promoted_words)
		} catch (Exception e) {
			my_exeption=e
			fail=true
			WebHelper.delay_medium()
			set_description(Enum_Create_Item.promoted_item_words,this.txt_promoted_words)
			fail=false
		}
		finally{
			if(fail)
				WebHelper.screenShoot(my_exeption.getMessage())
		}
	}

	protected static  _set_date_valid_to_status(int days=0){
		try{
			WebHelper.scroll(Enum_Position.TOP)
			set_date(days,GlobalVariable.G_DAYS_Add_To,txt_STATUS_DATE_TO)
		} catch (Exception e) {
			my_exeption=e
			fail=true
			WebHelper.delay_medium()
			set_date(days,GlobalVariable.G_DAYS_Add_To,txt_STATUS_DATE_TO)
			fail=false
		}
		finally{
			if(fail)
				WebHelper.screenShoot(my_exeption.getMessage())
		}
	}

	protected static  _set_date_active_version(int days=0){
		try{
			WebHelper.scroll(Enum_Position.TOP)
			set_date(days,GlobalVariable.G_DAYS_Add_From,txt_act_date_from)
		} catch (Exception e) {
			my_exeption=e
			fail=true
			WebHelper.delay_medium()
			set_date(days,GlobalVariable.G_DAYS_Add_From,txt_act_date_from)
			fail=false
		}
		finally{
			if(fail)
				WebHelper.screenShoot(my_exeption.getMessage())
		}
	}

	protected static  _set_date_valid_from_status( int days=0){
		try{
			WebHelper.scroll(Enum_Position.TOP)
			set_date(days,GlobalVariable.G_DAYS_Add_From,txt_ST_DATE_FROM)
		} catch (Exception e) {
			my_exeption=e
			fail=true
			WebHelper.delay_medium()
			set_date(days,GlobalVariable.G_DAYS_Add_From,txt_ST_DATE_FROM)
			fail=false
		}
		finally{
			if(fail)
				WebHelper.screenShoot(my_exeption.getMessage())
		}
	}

	protected static  _choose_Status(Enum_Status status) {
		try{
			WebHelper.scroll(Enum_Position.TOP)
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
		WebHelper.scroll(Enum_Position.TOP)
		set_status(status,select_status_to)
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

	protected static  _set_Item_Name(){
		try {
			WebHelper.scroll(Enum_Position.TOP)
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

	private static  save_check_if_number_of_items_incremented_in_lastFolder(String save){
		int before_creating=_items_in_lastFolder()
		before_creating++
		a_choose_lastFolder_in_modal.click_with_hover()
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
			a_last_folder_in_cm_tree.click_until_not_appear(a_item_in_last_folder)
		} catch (Exception e) {
			my_exeption=e
			fail=true
			WebHelper.delay_medium()
			a_last_folder_in_cm_tree.click_with_delay()
			fail=false
		}
		finally{
			if(fail)
				WebHelper.screenShoot(my_exeption.getMessage())
		}
	}

	private static from_Upper_Button(String title ){
		btn_create_new_item.click_with_hover(title)
	}

	protected static choose_template_by_Typing(String briefing, String ok) {
		txt_create_item_template.click_with_delay()
		txt_create_item_template.write_text( briefing)
		btn_ok_create_template.click(ok)
	}

	protected static verify_Template_Name_Update(String briefing){
		String actual_template=lbl_itemType.generate_Name()
		String actual_date=lbl_update_date.generate_Name()
		WebHelper.verify_texts(actual_template, briefing)
		WebHelper.verify_texts(WebHelper.get_date(actual_date), WebHelper.get_date())
	}

	private static delete_items(int count=ALL,String yes){
		if(count==ALL){
			while (a_item_in_last_folder.isVisible(false)) {
				delete_first_item_(yes)
			}
		}
		else {
			for(int i=0;i<count;i++){
				delete_first_item_(yes)
			}
		}
	}

	protected static delete_by_id(int Id=0,String yes){

		(new MyElement("itemId",String.format("//li[@itemid = '%d']",Id))).click_with_delay()
		verify_delete(yes)
	}

	protected static delete_first_item_(String yes){
		a_item_in_last_folder.click_with_delay()
		verify_delete(yes)
	}

	protected static choose_Template_By_Clicking(String ok) {
		def btn_ok_xpath_lang="//button[@type = 'button' and @id = 'cm-tree-item-create-dialog-button-create and( .='%s')' "
		lbl_choose_template.click_with_hover()
		(new MyElement("btn_ok",String.format(btn_ok_xpath_lang, ok))).click()
	}

	protected static double_click(MyElement element, String text=""){
		try{
			element.double_click(text)
		} catch (Exception e) {
			my_exeption=e
			fail=true
			element.double_click(text)
			fail=false
		}
		finally{
			if(fail)
				WebHelper.screenShoot(my_exeption.getMessage())
		}
	}

	protected static _click_item_Properties() {
		WebHelper.scroll(Enum_Position.TOP)
		def Properties=LanguageHelper.getText('Properties')
		double_click(a_Item_Properties,Properties)
	}

	private static set_item_name() {
		String id=lbl_itemId.generate_Name()
		String title="item_"+template+"_"+id
		div_itemTitleForEdit.click_with_delay().click_until_not_appear(txt_itemTitleForEdit)
		txt_itemTitleForEdit.write_key_chord( title)
	}

	private static get_items_in_last_folder() {
		String num_with_left_bracket= WebUI.getText(a_last_folder_in_cm_tree).split("]")[0]
		String pure_num=num_with_left_bracket.substring(1)
		return Integer.parseInt(pure_num)
	}


	private static verify_delete(String yes) {
		int before_deleting=_items_in_lastFolder()
		def xpath="//*[@id = 'recycle-view-linked-items']//following::button[ ( . = '%s')]"
		def yes_remove_button=new MyElement("yes_remove_button",String.format(xpath, yes))
		before_deleting--
		if(btn_remove_bar.isVisible(false))
			btn_remove_bar.click_with_delay().click_until_not_appear(yes_remove_button)
		else(WebHelper.check_three_dots()){ btn_remove_in_three_dots.click_with_delay() }
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

	private static  String set_description(Enum_Create_Item desc_enum,MyElement element) {
		String description=WebHelper.get_item_data(desc_enum)
		if(desc_enum==Enum_Create_Item.promoted_item_words){
			element.write_key_chord_promote(description)
		}
		else
			element.write_key_chord(description)
	}

	private static  uncheck(MyElement element){
		try {
			element.uncheck()
		} catch (Exception e) {
			my_exeption=e
			fail=true
			element.uncheck()
			WebHelper.delay_medium()
			fail=false
		}
		finally{
			if(fail)
				WebHelper.screenShoot(my_exeption.getMessage())
		}
	}

	private static  click_button(MyElement element,text=""){
		try {
			element.click_with_hover(text)
		} catch (Exception e) {
			my_exeption=e
			fail=true
			element.click_with_hover(text)
			WebHelper.delay_medium()
			fail=false
		}
		finally{
			if(fail)
				WebHelper.screenShoot(my_exeption.getMessage())
		}
	}

	private static  check_ckbx(MyElement element){
		try {
			element.check()
		} catch (Exception e) {
			my_exeption=e
			fail=true
			element.check()
			WebHelper.delay_medium()
			fail=false
		}
		finally{
			if(fail)
				WebHelper.screenShoot(my_exeption.getMessage())
		}
	}
}

