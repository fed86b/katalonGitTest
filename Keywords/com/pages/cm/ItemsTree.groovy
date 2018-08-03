package com.pages.cm
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.pages.ItemAbstract
import com.system.CMHelper
import com.system.LanguageHelper
import com.system.MyElement
import com.system.WebHelper
import com.system.enums.EnumLanguage
import com.system.enums.EnumOperation
import com.system.roles.CM
public class ItemsTree extends ItemAbstract {

	static MyElement aItemInLastFolder=new MyElement("aItemInLastFolder",CMHelper.BYPATH)
	static MyElement aLastFolderInCmTree=new MyElement("aLastFolderInCmTree","(//div[@id='knowledge-tree-container']//ul[@class='dynatree-container']//li[@class='dynatree-lastsib'])[1]")
	static MyElement btnCreateNewItem=new MyElement("btnCreateNewItem","//div[@id='cm-tree-item-create-button']")

	static MyElement liNewItem=new MyElement("liNewItem","//li[@id='cmTreeMenu_create']")
	static MyElement liDelete=new MyElement("liDelete","//li[@id='cmTreeMenu_delete']")
	static MyElement lastFolderContent=new MyElement("lastFolderContent","//li[@class='dynatree-lastsib']/ul")
	static MyElement expander=new MyElement("expander","//li[@class='dynatree-lastsib']//span[@class='dynatree-expander']")

	protected ItemsTree(EnumLanguage lang){
		super(lang)
	}


	protected static  openLastFolder(){
		try {
			if(!lastFolderContent.isVisible(false))
				aLastFolderInCmTree.clickUntilNotAppear(lastFolderContent)
		} catch (Exception e) {
			WebHelper.catchException(e)
		}
	}

	protected static  newItemAction(){
		try {
			aLastFolderInCmTree.rightClickUntilNotAppear(liNewItem)
			liNewItem.clickUntilNotAppear(ContentArea.btnCreateNewItem)
		} catch (Exception e) {
			WebHelper.catchException(e)
		}
	}

	//chooseTemplateByTyping

	protected static deleteById(){
		try{
			CMHelper.deleteById()
		}
		catch (Exception e) {
			WebHelper.catchException(e)
		}
	}
}
