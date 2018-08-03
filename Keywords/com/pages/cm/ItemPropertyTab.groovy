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


	static IframeElement lblItemType=new IframeElement("lblItemType","//*[@id='itemType']")
	static IframeElement lblUpdateDate=new IframeElement("lblUpdateDate","//span[@id = 'update_date']")
	static IframeElement lblItemId=new IframeElement("lblItemId","//span[@id = 'itemId']")
	static IframeElement itemId=new IframeElement("hidden_itemId","//input[@type='hidden' and @name='item_id']")

	static IframeElement txtItemTitleForEdit=new IframeElement("txtItemTitleForEdit","//input[@name = 'inplace_value' and @type = 'text']")
	static IframeElement txtStatusDateTo=new IframeElement("txtStatusDateTo","//input[@id = 'ST_DATE_TO']")
	static IframeElement txtStatusDateFrom=new IframeElement("txtStatusDateFrom","//input[@id = 'ST_DATE_FROM']")
	static IframeElement textareaDescriptionEdit=new IframeElement("textareaDescriptionEdit","//*[@name = 'inplace_value']")
	static IframeElement textareaSmsDesc=new IframeElement("textareaSmsDesc","//textarea[@name='SMS_DESCRIPTION']")
	static IframeElement txtPromotedWords=new IframeElement("txtPromotedWords","//div[@id='item-update-keyword-container']/descendant::input[@type='text']")
	static IframeElement txtActDateFrom=new IframeElement("txtActDateFrom","//input[@id = 'ACT_DATE_TO']")


	static IframeElement divShortDescriptionEdit=new IframeElement("divShortDescriptionEdit","//div[@id= 'itemShortDescriptionForEdit']")
	static IframeElement divItemTitleForEdit=new IframeElement("divItemTitleForEdit","//*[@id = 'itemTitleForEdit']")

	static IframeElement selectStatusFrom=new IframeElement("selectStatusFrom","//select[@name = 'STATUS']")
	static IframeElement selectStatusTo=new IframeElement("selectStatusTo","//select[@name='STATUS_AFTER']")

	static IframeElement checkboxActiveVer=new IframeElement("checkboxActiveVer","//input[@type='checkbox' and @id='VERSION_CHECKBOX']")
	static IframeElement checkboxPrint=new IframeElement("checkboxPrint","//input[@type='checkbox' and @id='item-action-Print']")
	static IframeElement checkboxEmail=new IframeElement("checkboxEmail","//input[@type='checkbox' and @id='item-action-Email']")
	static IframeElement checkboxFeedback=new IframeElement("checkboxFeedback","//input[@type='checkbox' and @id='item-action-Feedback']")
	static IframeElement checkboxBookmarks=new IframeElement("checkboxBookmarks","//input[@type='checkbox' and @id='item-action-Bookmark']")
	static IframeElement checkboxFax=new IframeElement("checkboxFax","//input[@type='checkbox' and @id='item-action-Fax']")
	static IframeElement checkboxSearch=new IframeElement("checkboxSearch","//input[@type='checkbox' and @id='show_search_result']")
	static IframeElement checkboxHit=new IframeElement("checkboxHit","//input[@type='checkbox' and @id='countHitsCheckbox']")
	static IframeElement chkbxAddHomepage=new IframeElement("chkbxAddHomepage","//input[@type='checkbox' and @name='ADD_TO_HOME_PAGE']")
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
			String actualTemplate=lblItemType.generateName()
			String actualDate=lblUpdateDate.generateName()
			WebHelper.verifyTexts(actualTemplate, templt)
			WebHelper.verifyTexts(WebHelper.getDate(actualDate), WebHelper.getDate())
		} catch (Exception e) {
			WebHelper.catchException(e)
		}
	}



	protected static  checkAddHomePage(boolean check=true){
		if(check)
			chkbxAddHomepage.check()
		else
			chkbxAddHomepage.uncheck()
	}

	protected static  checkActiveVersion(boolean check=true){

		if(check)
			checkboxActiveVer.check()
		else
			checkboxActiveVer.uncheck()
	}

	protected static  checkPrint(boolean check=true){

		if(check)
			checkboxPrint.check()
		else
			checkboxPrint.uncheck()
	}

	protected static  checkEmail(boolean check=true){

		if(check)
			checkboxEmail.check()
		else
			checkboxEmail.uncheck()
	}

	protected static  checkFeedback(boolean check=true){

		if(check)
			checkboxFeedback.check()
		else
			checkboxFeedback.uncheck()
	}

	protected static  checkBookmarks(boolean check=true){

		if(check)
			checkboxBookmarks.check()
		else
			checkboxBookmarks.uncheck()
	}

	protected static  checkFax(boolean check=true){

		if(check)
			checkboxFax.check()
		else
			checkboxFax.uncheck()
	}

	protected static  checkSearch(boolean check=true){

		if(check)
			checkboxSearch.check()
		else
			checkboxSearch.uncheck()
	}

	protected static  checkHit(boolean check=true){
		if(check)
			checkboxHit.check()
		else
			checkboxHit.uncheck()
	}

	protected static  writeShortDescription(){

		try {
			divShortDescriptionEdit.clickWithHover().quickDoubleClick()
			setDescription(EnumCreateItem.short_description,textareaDescriptionEdit)
		} catch (Exception e) {
			WebHelper.catchException(e)
		}
	}


	protected static  writeSmsDescription(){
		try {

			setDescription(EnumCreateItem.sms_desc, this.textareaSmsDesc)
		} catch (Exception e) {
			WebHelper.catchException(e)
		}
	}

	protected static  writePromotedKeyWords(){
		try {

			setDescription(EnumCreateItem.promoted_item_words, this.txtPromotedWords)
		} catch (Exception e) {
			WebHelper.catchException(e)
		}
	}

	protected static  setDateValidToStatus(int days=0){
		try{

			setDate(days,GlobalVariable.G_DAYS_Add_To,txtStatusDateTo)
		} catch (Exception e) {
			WebHelper.catchException(e)
		}
	}

	protected static  setDateActiveVersion(int days=0){
		try{
			setDate(days,GlobalVariable.G_DAYS_Add_From,txtActDateFrom)
		} catch (Exception e) {
			WebHelper.catchException(e)
		}
	}

	protected static  setDateValidFromStatus( int days=0){
		try{

			setDate(days,GlobalVariable.G_DAYS_Add_From,txtStatusDateFrom)
		} catch (Exception e) {
			WebHelper.catchException(e)
		}
	}

	protected static  chooseStatus(EnumStatus status) {
		try{
			setStatus(status,selectStatusFrom)
		} catch (Exception e) {
			WebHelper.catchException(e)
		}
	}

	protected static  chooseStatusAfter(EnumStatus status) {

		setStatus(status,selectStatusTo)
	}



	protected static  setItemName(){
		try {
			String title=CMHelper.generateName()
			divItemTitleForEdit.clickUntilNotAppear(txtItemTitleForEdit)
			txtItemTitleForEdit.writeKeyChord( title)
		} catch (Exception e) {
			WebHelper.catchException(e)
		}
	}
	static setStatus(EnumStatus status,IframeElement element) {
		switch(status){
			case EnumStatus.OFFLINE:
				element.selectByIndex(0)
				break
			case EnumStatus.ONLINE:
				element.selectByIndex(1)
				break
			case EnumStatus.HIDDEN:
				element.selectByIndex(2)
				break
			case EnumStatus.ARCHIVE:
				element.selectByIndex(3)
				break
		}
	}

	static  String setDescription(EnumCreateItem desc_enum,IframeElement element) {
		String description=WebHelper.get_item_data(desc_enum)
		if(desc_enum==EnumCreateItem.promoted_item_words){
			element.writeKeyChordPromote(description)
		}
		else
			element.writeKeyChord(description)
	}



	static setDate(int days,int add_days, IframeElement element) {
		def date
		if(days==0)
			date=WebHelper.addToDatenowDays(add_days)
		else
			date=WebHelper.addToDatenowDays(days)
		element.writeText( date)
		element.pressEsc()
	}
}
