package com.pages.cm

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.pages.My_Item
import com.system.LanguageHelper
import com.system.MyElement
import com.system.WebHelper
import com.system.enums.Enum_Create_Item
import com.system.enums.Enum_Language
import com.system.enums.Enum_Position
import com.system.enums.Enum_Status
import com.system.enums.Enum_Template

import internal.GlobalVariable

public class ItemProperty_Tab extends My_Item{


	static MyElement lbl_itemType=new MyElement(findTestObject('Kms_Page_OR/Roles/Content_Manager/Item_Properties_tab/lbl_itemType'))
	static MyElement lbl_update_date=new MyElement(findTestObject('Kms_Page_OR/Roles/Content_Manager/Item_Properties_tab/lbl_update_date'))
	static MyElement lbl_itemId=new MyElement(findTestObject('Kms_Page_OR/Roles/Content_Manager/Item_Properties_tab/lbl_itemId'))


	static MyElement txt_itemTitleForEdit=new MyElement(findTestObject('Kms_Page_OR/Roles/Content_Manager/Item_Properties_tab/txt_title_name'))
	static MyElement txt_STATUS_DATE_TO=new MyElement(findTestObject('Kms_Page_OR/Roles/Content_Manager/Item_Properties_tab/txt_STATUS_DATE_TO'))
	static MyElement txt_ST_DATE_FROM=new MyElement(findTestObject('Kms_Page_OR/Roles/Content_Manager/Item_Properties_tab/txt_ST_DATE_FROM'))
	static MyElement textarea_descriptionEdit=new MyElement(findTestObject('Kms_Page_OR/Roles/Content_Manager/Item_Properties_tab/textarea_descriptionEdit'))
	static MyElement textarea_sms_desc=new MyElement(findTestObject('Kms_Page_OR/Roles/Content_Manager/Item_Properties_tab/textarea_sms_desc'))
	static MyElement txt_promoted_words=new MyElement(findTestObject('Kms_Page_OR/Roles/Content_Manager/Item_Properties_tab/txt_promoted_words'))
	static MyElement txt_act_date_from=new MyElement(findTestObject('Kms_Page_OR/Roles/Content_Manager/Item_Properties_tab/txt_ACT_DATE_TO'))


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

	static MyElement iframe_items_scope=new MyElement(findTestObject('Object Repository/Kms_Page_OR/Roles/Content_Manager/iframe_itemscope'))

	static Enum_Template template

	protected ItemProperty_Tab(Enum_Language language, Enum_Template template){
		super(language)
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



	protected static  _check_add_home_page(boolean check=true){
		WebHelper.scroll(Enum_Position.BOTTOM)
		if(check)
			chkbx_add_homepage.check()
		else
			chkbx_add_homepage.uncheck()
	}

	protected static  _check_active_version(boolean check=true){
		WebHelper.scroll(Enum_Position.TOP)
		if(check)
			checkbox_active_ver.check()
		else
			checkbox_active_ver.uncheck()
	}

	protected static  _check_print(boolean check=true){
		WebHelper.scroll(Enum_Position.BOTTOM)
		if(check)
			checkbox_print.check()
		else
			checkbox_print.uncheck()
	}

	protected static  _check_email(boolean check=true){
		WebHelper.scroll(Enum_Position.BOTTOM)
		if(check)
			checkbox_email.check()
		else
			checkbox_email.uncheck()
	}

	protected static  _check_feedback(boolean check=true){
		WebHelper.scroll(Enum_Position.BOTTOM)
		if(check)
			checkbox_feedback.check()
		else
			checkbox_feedback.uncheck()
	}

	protected static  _check_bookmarks(boolean check=true){
		WebHelper.scroll(Enum_Position.BOTTOM)
		if(check)
			checkbox_bookmarks.check()
		else
			checkbox_bookmarks.uncheck()
	}

	protected static  _check_fax(boolean check=true){
		WebHelper.scroll(Enum_Position.BOTTOM)
		if(check)
			checkbox_fax.check()
		else
			checkbox_fax.uncheck()
	}

	protected static  _check_search(boolean check=true){
		WebHelper.scroll(Enum_Position.BOTTOM)
		if(check)
			checkbox_search.check()
		else
			checkbox_search.uncheck()
	}

	protected static  _check_hit(boolean check=true){
		WebHelper.scroll(Enum_Position.BOTTOM)
		if(check)
			checkbox_hit
		else
			checkbox_hit
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

	private static set_item_name() {
		String id=lbl_itemId.generate_Name()
		String title="item_"+template+"_"+id
		div_itemTitleForEdit.click_with_delay().click_until_not_appear(txt_itemTitleForEdit)
		txt_itemTitleForEdit.write_key_chord( title)
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

	private static verify_Template_Name_Update(String briefing){
		String actual_template=lbl_itemType.generate_Name()
		String actual_date=lbl_update_date.generate_Name()
		WebHelper.verify_texts(actual_template, briefing)
		WebHelper.verify_texts(WebHelper.get_date(actual_date), WebHelper.get_date())
	}
}
