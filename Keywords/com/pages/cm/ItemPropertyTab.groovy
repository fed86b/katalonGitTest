package com.pages.cm

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.pages.ItemAbstract
import com.system.LanguageHelper
import com.system.CMHelper
import com.system.IframeElement
import com.system.WebHelper
import com.system.enums.EnumCreateItem
import com.system.enums.EnumLanguage
import com.system.enums.EnumStatus
import com.system.enums.EnumTemplate
import com.system.roles.CM

import internal.GlobalVariable

public class ItemPropertyTab extends ItemAbstract{


	static IframeElement lbl_itemType=new IframeElement("lbl_itemType","//*[@id='itemType']")
	static IframeElement lbl_update_date=new IframeElement("lbl_update_date","//span[@id = 'update_date']")
	static IframeElement lbl_itemId=new IframeElement("lbl_itemId","//span[@id = 'itemId']")
	static IframeElement itemId=new IframeElement("hidden_itemId","//input[@type='hidden' and @name='item_id']")

	static IframeElement txt_itemTitleForEdit=new IframeElement("txt_itemTitleForEdit","//input[@name = 'inplace_value' and @type = 'text']")
	static IframeElement txt_STATUS_DATE_TO=new IframeElement("txt_STATUS_DATE_TO","//input[@id = 'ST_DATE_TO']")
	static IframeElement txt_ST_DATE_FROM=new IframeElement("txt_ST_DATE_FROM","//input[@id = 'ST_DATE_FROM']")
	static IframeElement textarea_descriptionEdit=new IframeElement("textarea_descriptionEdit","//*[@name = 'inplace_value']")
	static IframeElement textarea_sms_desc=new IframeElement("textarea_sms_desc","//textarea[@name='SMS_DESCRIPTION']")
	static IframeElement txt_promoted_words=new IframeElement("txt_promoted_words","//div[@id='item-update-keyword-container']/descendant::input[@type='text']")
	static IframeElement txt_act_date_from=new IframeElement("txt_act_date_from","//input[@id = 'ACT_DATE_TO']")


	static IframeElement div_ShortDescriptionEdit=new IframeElement("div_ShortDescriptionEdit","//div[@id= 'itemShortDescriptionForEdit']")
	static IframeElement div_itemTitleForEdit=new IframeElement("div_itemTitleForEdit","//*[@id = 'itemTitleForEdit']")

	static IframeElement select_status_from=new IframeElement("select_status_from","//select[@name = 'STATUS']")
	static IframeElement select_status_to=new IframeElement("select_status_to","//select[@name='STATUS_AFTER']")

	static IframeElement checkbox_active_ver=new IframeElement("checkbox_active_ver","//input[@type='checkbox' and @id='VERSION_CHECKBOX']")
	static IframeElement checkbox_print=new IframeElement("checkbox_print","//input[@type='checkbox' and @id='item-action-Print']")
	static IframeElement checkbox_email=new IframeElement("checkbox_email","//input[@type='checkbox' and @id='item-action-Email']")
	static IframeElement checkbox_feedback=new IframeElement("checkbox_feedback","//input[@type='checkbox' and @id='item-action-Feedback']")
	static IframeElement checkbox_bookmarks=new IframeElement("checkbox_bookmarks","//input[@type='checkbox' and @id='item-action-Bookmark']")
	static IframeElement checkbox_fax=new IframeElement("checkbox_fax","//input[@type='checkbox' and @id='item-action-Fax']")
	static IframeElement checkbox_search=new IframeElement("checkbox_search","//input[@type='checkbox' and @id='show_search_result']")
	static IframeElement checkbox_hit=new IframeElement("checkbox_hit","//input[@type='checkbox' and @id='countHitsCheckbox']")
	static IframeElement chkbx_add_homepage=new IframeElement("chkbx_add_homepage","//input[@type='checkbox' and @name='ADD_TO_HOME_PAGE']")
	static EnumTemplate template

	protected ItemPropertyTab(EnumLanguage language, EnumTemplate template){
		super(language)
		this.template=template
	}

	protected static fillPropertyTab(EnumStatus status_from=EnumStatus.ONLINE,
			EnumStatus status_after=EnumStatus.ARCHIVE, def set_checkBx=true){

		verifyTemplateUpdateDate()

		setItemName()

		writeShortDescription()

		chooseStatus(status_from)

		setDateValidFromStatus()

		chooseStatusAfter(status_after)

		setDateValidToStatus()

		checkActiveVersion(set_checkBx)
		if(set_checkBx){
			setDateActiveVersion()
		}

		checkPrint(set_checkBx)

		checkEmail(set_checkBx)

		checkFeedback(true)

		checkBookmarks(set_checkBx)

		checkFax(set_checkBx)

		checkSearch(set_checkBx)

		checkHit(set_checkBx)

		checkAddHomePage(set_checkBx)

		writeSmsDescription()

		writePromotedKeyWords()
	}

	protected static verifyTemplateUpdateDate() {

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



	protected static  checkAddHomePage(boolean check=true){
		if(check)
			chkbx_add_homepage.check()
		else
			chkbx_add_homepage.uncheck()
	}

	protected static  checkActiveVersion(boolean check=true){

		if(check)
			checkbox_active_ver.check()
		else
			checkbox_active_ver.uncheck()
	}

	protected static  checkPrint(boolean check=true){

		if(check)
			checkbox_print.check()
		else
			checkbox_print.uncheck()
	}

	protected static  checkEmail(boolean check=true){

		if(check)
			checkbox_email.check()
		else
			checkbox_email.uncheck()
	}

	protected static  checkFeedback(boolean check=true){

		if(check)
			checkbox_feedback.check()
		else
			checkbox_feedback.uncheck()
	}

	protected static  checkBookmarks(boolean check=true){

		if(check)
			checkbox_bookmarks.check()
		else
			checkbox_bookmarks.uncheck()
	}

	protected static  checkFax(boolean check=true){

		if(check)
			checkbox_fax.check()
		else
			checkbox_fax.uncheck()
	}

	protected static  checkSearch(boolean check=true){

		if(check)
			checkbox_search.check()
		else
			checkbox_search.uncheck()
	}

	protected static  checkHit(boolean check=true){
		if(check)
			checkbox_hit.check()
		else
			checkbox_hit.uncheck()
	}

	protected static  writeShortDescription(){

		try {
			div_ShortDescriptionEdit.double_click().click_until_not_appear(textarea_descriptionEdit)
			set_description(EnumCreateItem.short_description,textarea_descriptionEdit)
		} catch (Exception e) {
			my_exeption=e
			fail=true
			WebHelper.delay_medium()
			set_description(EnumCreateItem.short_description,this.textarea_descriptionEdit)
			fail=false
		}
		finally{
			if(fail)
				WebHelper.screenShoot(my_exeption.getMessage())
		}
	}


	protected static  writeSmsDescription(){
		try {

			set_description(EnumCreateItem.sms_desc, this.textarea_sms_desc)
		} catch (Exception e) {
			my_exeption=e
			fail=true
			WebHelper.delay_medium()
			set_description(EnumCreateItem.sms_desc, this.textarea_sms_desc)
			fail=false
		}
		finally{
			if(fail)
				WebHelper.screenShoot(my_exeption.getMessage())
		}
	}

	protected static  writePromotedKeyWords(){
		try {

			set_description(EnumCreateItem.promoted_item_words, this.txt_promoted_words)
		} catch (Exception e) {
			my_exeption=e
			fail=true
			WebHelper.delay_medium()
			set_description(EnumCreateItem.promoted_item_words,this.txt_promoted_words)
			fail=false
		}
		finally{
			if(fail)
				WebHelper.screenShoot(my_exeption.getMessage())
		}
	}

	protected static  setDateValidToStatus(int days=0){
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

	protected static  setDateActiveVersion(int days=0){
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

	protected static  setDateValidFromStatus( int days=0){
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

	protected static  chooseStatus(EnumStatus status) {
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

	protected static  chooseStatusAfter(EnumStatus status) {

		set_status(status,select_status_to)
	}



	protected static  setItemName(){
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
	static set_status(EnumStatus status,IframeElement element) {
		switch(status){
			case EnumStatus.OFFLINE:
				element.select_by_index(0)
				break
			case EnumStatus.ONLINE:
				element.select_by_index(1)
				break
			case EnumStatus.HIDDEN:
				element.select_by_index(2)
				break
			case EnumStatus.ARCHIVE:
				element.select_by_index(3)
				break
		}
	}

	static  String set_description(EnumCreateItem desc_enum,IframeElement element) {
		String description=WebHelper.get_item_data(desc_enum)
		if(desc_enum==EnumCreateItem.promoted_item_words){
			element.write_key_chord_promote(description)
		}
		else
			element.write_key_chord(description)
	}

	static set_item_name() {
		String title=CMHelper.generateName()
		div_itemTitleForEdit.double_click().click_until_not_appear(txt_itemTitleForEdit)
		txt_itemTitleForEdit.write_key_chord( title)
	}


	static set_date(int days,int add_days, IframeElement element) {
		def date
		if(days==0)
			date=WebHelper.add_to_dateNow_day(add_days)
		else
			date=WebHelper.add_to_dateNow_day(days)
		element.write_text( date)
		element.press_Esc()
	}

	static verify_Template_Name_Update(String briefing){
		String actual_template=lbl_itemType.generate_Name()
		String actual_date=lbl_update_date.generate_Name()
		WebHelper.verify_texts(actual_template, briefing)
		WebHelper.verify_texts(WebHelper.get_date(actual_date), WebHelper.get_date())
	}
}
