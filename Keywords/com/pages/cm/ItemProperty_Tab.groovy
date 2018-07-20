package com.pages.cm

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.pages.My_Item
import com.system.LanguageHelper
import com.system.CMHelper
import com.system.Iframe_Element
import com.system.WebHelper
import com.system.enums.Enum_Create_Item
import com.system.enums.Enum_Language
import com.system.enums.Enum_Status
import com.system.enums.Enum_Template
import com.system.roles.CM

import internal.GlobalVariable

public class ItemProperty_Tab extends My_Item{


	static Iframe_Element lbl_itemType=new Iframe_Element("lbl_itemType","//*[@id='itemType']")
	static Iframe_Element lbl_update_date=new Iframe_Element("lbl_update_date","//span[@id = 'update_date']")
	static Iframe_Element lbl_itemId=new Iframe_Element("lbl_itemId","//span[@id = 'itemId']")


	static Iframe_Element txt_itemTitleForEdit=new Iframe_Element("txt_itemTitleForEdit","//input[@name = 'inplace_value' and @type = 'text']")
	static Iframe_Element txt_STATUS_DATE_TO=new Iframe_Element("txt_STATUS_DATE_TO","//input[@id = 'ST_DATE_TO']")
	static Iframe_Element txt_ST_DATE_FROM=new Iframe_Element("txt_ST_DATE_FROM","//input[@id = 'ST_DATE_FROM']")
	static Iframe_Element textarea_descriptionEdit=new Iframe_Element("textarea_descriptionEdit","//*[@name = 'inplace_value']")
	static Iframe_Element textarea_sms_desc=new Iframe_Element("textarea_sms_desc","//textarea[@name='SMS_DESCRIPTION']")
	static Iframe_Element txt_promoted_words=new Iframe_Element("txt_promoted_words","//div[@id='item-update-keyword-container']/descendant::input[@type='text']")
	static Iframe_Element txt_act_date_from=new Iframe_Element("txt_act_date_from","//input[@id = 'ACT_DATE_TO']")


	static Iframe_Element div_ShortDescriptionEdit=new Iframe_Element("div_ShortDescriptionEdit","//div[@id= 'itemShortDescriptionForEdit']")
	static Iframe_Element div_itemTitleForEdit=new Iframe_Element("div_itemTitleForEdit","//*[@id = 'itemTitleForEdit']")

	static Iframe_Element select_status_from=new Iframe_Element("select_status_from","//select[@name = 'STATUS']")
	static Iframe_Element select_status_to=new Iframe_Element("select_status_to","//select[@name='STATUS_AFTER']")

	static Iframe_Element checkbox_active_ver=new Iframe_Element("checkbox_active_ver","//input[@type='checkbox' and @id='VERSION_CHECKBOX']")
	static Iframe_Element checkbox_print=new Iframe_Element("checkbox_print","//input[@type='checkbox' and @id='item-action-Print']")
	static Iframe_Element checkbox_email=new Iframe_Element("checkbox_email","//input[@type='checkbox' and @id='item-action-Email']")
	static Iframe_Element checkbox_feedback=new Iframe_Element("checkbox_feedback","//input[@type='checkbox' and @id='item-action-Feedback']")
	static Iframe_Element checkbox_bookmarks=new Iframe_Element("checkbox_bookmarks","//input[@type='checkbox' and @id='item-action-Bookmark']")
	static Iframe_Element checkbox_fax=new Iframe_Element("checkbox_fax","//input[@type='checkbox' and @id='item-action-Fax']")
	static Iframe_Element checkbox_search=new Iframe_Element("checkbox_search","//input[@type='checkbox' and @id='show_search_result']")
	static Iframe_Element checkbox_hit=new Iframe_Element("checkbox_hit","//input[@type='checkbox' and @id='countHitsCheckbox']")
	static Iframe_Element chkbx_add_homepage=new Iframe_Element("chkbx_add_homepage","//input[@type='checkbox' and @name='ADD_TO_HOME_PAGE']")
	static Enum_Template template

	protected ItemProperty_Tab(Enum_Language language, Enum_Template template){
		super(language)
		this.template=template
	}

	protected static _fillProperty_tab(Enum_Status status_from=Enum_Status.ONLINE,
			Enum_Status status_after=Enum_Status.ARCHIVE, def set_checkBx=true){

		'verify template and updated time'
		_verify_template_updateDate()

		'set name for Item  '
		_set_Item_Name()

		'write short description'
		_write_short_description()


		'choose status  '
		_choose_Status(status_from)

		'set date from when item status will be valid'
		_set_date_valid_from_status()

		'choose status after'
		_choose_status_after(status_after)

		'set date when item status will be valid to status after'
		_set_date_valid_to_status()

		'check active version'
		_check_active_version(set_checkBx)
		if(set_checkBx){
			'set active version date from'
			_set_date_active_version()
		}

		'check print'
		_check_print(set_checkBx)

		'check email'
		_check_email(set_checkBx)

		'check feedback'
		_check_feedback(true)

		'check bookmarks'
		_check_bookmarks(set_checkBx)

		'check fax'
		_check_fax(set_checkBx)

		'check show in search results'
		_check_search(set_checkBx)

		'check hit list'
		_check_hit(set_checkBx)

		'check add home page'
		_check_add_home_page(set_checkBx)

		'write sms description'
		_write_sms_description()

		'write promoted item keywords'
		_write_promoted_key_words()
	}

	protected static _verify_template_updateDate() {

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



	protected static  _check_add_home_page(boolean check=true){
		if(check)
			chkbx_add_homepage.check()
		else
			chkbx_add_homepage.uncheck()
	}

	protected static  _check_active_version(boolean check=true){

		if(check)
			checkbox_active_ver.check()
		else
			checkbox_active_ver.uncheck()
	}

	protected static  _check_print(boolean check=true){

		if(check)
			checkbox_print.check()
		else
			checkbox_print.uncheck()
	}

	protected static  _check_email(boolean check=true){

		if(check)
			checkbox_email.check()
		else
			checkbox_email.uncheck()
	}

	protected static  _check_feedback(boolean check=true){

		if(check)
			checkbox_feedback.check()
		else
			checkbox_feedback.uncheck()
	}

	protected static  _check_bookmarks(boolean check=true){

		if(check)
			checkbox_bookmarks.check()
		else
			checkbox_bookmarks.uncheck()
	}

	protected static  _check_fax(boolean check=true){

		if(check)
			checkbox_fax.check()
		else
			checkbox_fax.uncheck()
	}

	protected static  _check_search(boolean check=true){

		if(check)
			checkbox_search.check()
		else
			checkbox_search.uncheck()
	}

	protected static  _check_hit(boolean check=true){
		if(check)
			checkbox_hit.check()
		else
			checkbox_hit.uncheck()
	}

	protected static  _write_short_description(){

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


	protected static  _write_sms_description(){
		try {

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
	private static set_status(Enum_Status status,Iframe_Element element) {
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

	private static  String set_description(Enum_Create_Item desc_enum,Iframe_Element element) {
		String description=WebHelper.get_item_data(desc_enum)
		if(desc_enum==Enum_Create_Item.promoted_item_words){
			element.write_key_chord_promote(description)
		}
		else
			element.write_key_chord(description)
	}

	private static set_item_name() {
		String title=CMHelper.generate_Name()
		div_itemTitleForEdit.double_click().click_until_not_appear(txt_itemTitleForEdit)
		txt_itemTitleForEdit.write_key_chord( title)
	}


	private static set_date(int days,int add_days, Iframe_Element element) {
		def date
		if(days==0)
			date=WebHelper.add_to_dateNow_day(add_days)
		else
			date=WebHelper.add_to_dateNow_day(days)
		element.write_text( date)
		element.press_Esc()
	}

	private static verify_Template_Name_Update(String briefing){
		String actual_template=lbl_itemType.generate_Name()
		String actual_date=lbl_update_date.generate_Name()
		WebHelper.verify_texts(actual_template, briefing)
		WebHelper.verify_texts(WebHelper.get_date(actual_date), WebHelper.get_date())
	}
}
