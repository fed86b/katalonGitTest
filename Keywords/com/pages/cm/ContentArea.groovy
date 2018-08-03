package com.pages.cm
import com.system.roles.CM
import com.pages.ItemAbstract
import com.system.CMHelper
import com.system.IframeElement
import com.system.LanguageHelper
import com.system.MyElement
import com.system.WebHelper
import com.system.enums.EnumLanguage
import com.system.enums.EnumTemplate

public class ContentArea  extends ItemAbstract{

	static MyElement aItemProperties=new IframeElement("aItemProperties","//li[@aria-controls='item-update-tab-main']")
	static MyElement btnCreateNewItem=new MyElement("btnCreateNewItem","//div[@id='cm-tree-item-create-button']")
	static MyElement btnOkCreateTemplate=new MyElement("btnOkCreateTemplate","//button[ @id = 'cm-tree-item-create-dialog-button-create']")
	static MyElement txtCreateItemTemplate=new MyElement("txtCreateItemTemplate","//input[@id = 'cm-tree-item-create-dialog-dtd-filter' and @type = 'text']")

	static MyElement lblChooseTemplate=new MyElement("lblChooseTemplate","//input[@id = 'cm-tree-item-create-dialog-dtd-filter' and @type = 'text']")

	static EnumTemplate template

	static ItemPropertyTab itemPropertyTab
	protected ContentArea(EnumTemplate template,EnumLanguage lang){
		super(lang)
		this.template=template
	}

	public static ItemPropertyTab getItemPropertyTab() {
		if(itemPropertyTab==null)
			itemPropertyTab=new ItemPropertyTab(lang,template)
		return itemPropertyTab;
	}

	//rightClicking = false, if operation performed by clicking on create new button, otherwise after right clicking on folder
	protected static chooseTemplateByTyping(def rightClicking=false) {
		try {
			def templt=LanguageHelper.getText(template.toString())
			txtCreateItemTemplate.clickWithDelay()
			txtCreateItemTemplate.writeText(templt)
			saveTemplate(rightClicking)
		} catch (Exception e) {
			WebHelper.catchException(e)
		}
	}





	protected static clickUpperButtonCreateItem(){
		def text=LanguageHelper.getText('Create New Item')
		try {
			btnCreateNewItem.clickUntilNotAppear(txtCreateItemTemplate, text)
		} catch (Exception e) {
			WebHelper.catchException(e)
		}
	}

	protected static chooseTemplateByClicking(def rightClicking) {
		def ok=LanguageHelper.getText('Ok')
		try{
			lblChooseTemplate.clickUntilNotDisappear()
			saveTemplate( rightClicking)
		} catch (Exception e) {
			WebHelper.catchException(e)
		}
	}

	protected static clickItemProperties() {
		WebHelper.delay()
		def Properties=LanguageHelper.getText('Item Properties')
		aItemProperties.clickUntilNotAppear(getItemPropertyTab().lblItemId, Properties)
	}


	static saveTemplate(def rightClicking) {
		def ok=LanguageHelper.getText('Ok')
		int before_creating=0
		if(rightClicking){
			before_creating=CMHelper.itemsInLastFolder()
			before_creating++
		}
		btnOkCreateTemplate.clickUntilNotDisappear(btnOkCreateTemplate,ok)
		if(rightClicking){
			CMHelper.verifyLastFolderNumber(before_creating)
		}
	}
}