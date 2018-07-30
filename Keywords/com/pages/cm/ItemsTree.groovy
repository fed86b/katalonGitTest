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

	static MyElement a_item_in_last_folder=new MyElement("a_item_in_last_folder",CMHelper.BYPATH)
	static MyElement a_last_folder_in_cm_tree=new MyElement("a_last_folder_in_cm_tree","(//div[@id='knowledge-tree-container']//ul[@class='dynatree-container']//li[@class='dynatree-lastsib'])[1]")
	static MyElement btn_create_new_item=new MyElement("btn_create_new_item","//div[@id='cm-tree-item-create-button']")

	static MyElement li_new_item=new MyElement("li_new_item","//li[@id='cmTreeMenu_create']")
	static MyElement li_delete=new MyElement("li_delete","//li[@id='cmTreeMenu_delete']")
	static MyElement last_folder_content=new MyElement("last_folder_content","//li[@class='dynatree-lastsib']/ul")
	static MyElement expander=new MyElement("expander","//li[@class='dynatree-lastsib']//span[@class='dynatree-expander']")

	protected ItemsTree(EnumLanguage lang){
		super(lang)
	}


	protected static  openLastFolder(){
		try {
			if(!last_folder_content.isVisible())
				a_last_folder_in_cm_tree.click_until_not_appear(last_folder_content)
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

	protected static  newItemAction(){
		try {

			a_last_folder_in_cm_tree.right_click_until_not_appear(li_new_item)
			li_new_item.click_until_not_appear(ContentArea.btn_create_new_item)
		} catch (Exception e) {
			my_exeption=e
			fail=true
			WebHelper.delay_medium()
			a_last_folder_in_cm_tree.right_click_until_not_appear(li_new_item)
			li_new_item.click_until_not_appear(ContentArea.btn_create_new_item)
			fail=false
		}
		finally{
			if(fail)
				WebHelper.screenShoot(my_exeption.getMessage())
		}
	}

	//chooseTemplateByTyping

	protected static deleteById(){
		try{
			CMHelper.deleteById()
		}
		catch (Exception e) {
			my_exeption=e
			fail=true
			WebHelper.delay_medium()
			CMHelper.deleteById()
			fail=false
		}
		finally{
			if(fail)
				WebHelper.screenShoot(my_exeption.getMessage())
		}
	}
}
