package com.system.roles

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.pages.cm.TaskBarCM
import com.pages.cm.BriefingPage
import com.pages.cm.GeneralPage
import com.pages.cm.ContentArea
import com.pages.cm.ItemsTree
import com.pages.cm.TopToolBarCM
import com.system.CMHelper
import com.system.LanguageHelper
import com.system.MyElement
import com.system.WebHelper
import com.system.enums.EnumLanguage
import com.system.enums.EnumRole



public  class CM extends  User{

	static MyElement iframe_item_scope=new MyElement(findTestObject('Kms_Page_OR/Roles/Content_Manager/iframe_itemscope'))
	static def isIframe=false
	static BriefingPage briefing
	static GeneralPage general
	static ContentArea template
	static TopToolBarCM topToolBar
	static TaskBarCM taskBar
	static ItemsTree itemsTree
	protected CM(EnumLanguage lang ){
		super(EnumRole.CONTENT_MANAGER,lang)
	}

	protected static deleteAllCreatedItems(){
		try{
			CMHelper.deleteItems()
		}
		catch (Exception e) {
			WebHelper.delay_medium()
			my_exeption=e
			fail=true
			CMHelper.deleteItems()
			fail=false
		}
		finally{
			if(fail){
				WebHelper.screenShoot(my_exeption.getMessage())
			}
		}
	}

	public static ContentArea getTemplate() {
		if(template==null)
			template=new GeneralPage(lang)
		return template;
	}

	protected static TaskBarCM getTaskBar() {
		if(taskBar==null)
			taskBar=new TaskBarCM(lang)
		return taskBar
	}

	protected static BriefingPage getBriefing() {
		if(briefing==null){
			briefing=new BriefingPage(lang)
			template=briefing
		}
		return briefing
	}

	protected static TopToolBarCM getTopToolBar() {
		if(topToolBar==null)
			topToolBar=new TopToolBarCM(lang)
		return topToolBar
	}

	protected static GeneralPage getGeneral() {
		if(general==null){
			general=new GeneralPage(lang)
			template=general
		}
		return general
	}

	public static ItemsTree getItemsTree() {
		if(itemsTree==null)
			itemsTree=new ItemsTree(lang)
		return itemsTree
	}
}
