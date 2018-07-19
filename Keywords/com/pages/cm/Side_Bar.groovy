package com.pages.cm
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.pages.My_Item
import com.system.LanguageHelper
import com.system.MyElement
import com.system.WebHelper
import com.system.enums.Enum_Language
import com.system.roles.CM
public class Side_Bar  extends My_Item {
	static final int ALL=0
	static final BYPATH="//a[contains(., 'New Item')]"//"(//a[starts-with(text(), '/item_'/)])[last()]"
	static MyElement a_item_in_last_folder=new MyElement("a_item_in_last_folder",BYPATH)
	static MyElement a_last_folder_in_cm_tree=new MyElement("a_last_folder_in_cm_tree","id('cmTree')/ul[@class='dynatree-container']/li[@class='dynatree-lastsib']")
	static MyElement btn_create_new_item=new MyElement("btn_create_new_item","//div[@id='cm-tree-item-create-button']")

	static MyElement li_new_item=new MyElement("li_new_item","//li[@id='cmTreeMenu_create']")
	static MyElement li_delete=new MyElement("li_delete","//li[@id='cmTreeMenu_delete']")
	static MyElement last_folder_content=new MyElement("last_folder_content","//li[@class='dynatree-lastsib']/ul")
	static MyElement expander=new MyElement("expander","//li[@class='dynatree-lastsib']//span[@class='dynatree-expander']")

	protected Side_Bar(Enum_Language lang){
		super(lang)
	}


	protected static _delete_by_id(){
		try{
			delete_by_id()
		}
		catch (Exception e) {
			my_exeption=e
			fail=true
			WebHelper.delay_medium()
			delete_by_id()
			fail=false
		}
		finally{
			if(fail)
				WebHelper.screenShoot(my_exeption.getMessage())
		}
	}

	protected static  _open_lastFolder(){
		try {
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

	protected static _click_upper_button_create_item(){
		def text=LanguageHelper.getText('Create New Item')
		try {
			btn_create_new_item.click_with_hover(text)
		} catch (Exception e) {
			my_exeption=e
			fail=true
			btn_create_new_item.click_with_hover(text)
			fail=false
		}
		finally{
			if(fail)
				WebHelper.screenShoot(my_exeption.getMessage())
		}
	}

	protected static _items_in_lastFolder() {
		String num_with_left_bracket= WebUI.getText(a_last_folder_in_cm_tree).split("]")[0]
		String pure_num=num_with_left_bracket.substring(1)
		return Integer.parseInt(pure_num)
	}

	protected static _create_new_item_by_right_cick() {
		def text=LanguageHelper.getText('New Item')
		try {
			a_last_folder_in_cm_tree.right_click_until_not_appear(li_new_item)
			li_new_item.click_until_not_appear(CM.getTemplate().txt_create_item_template)
		} catch (Exception e) {
			my_exeption=e
			fail=true
			a_last_folder_in_cm_tree.right_click()
			li_new_item.click_with_hover(text)
			fail=false
		}
		finally{
			if(fail)
				WebHelper.screenShoot(my_exeption.getMessage())
		}
	}

	static find_item(String name=""){
		_open_lastFolder()
		def xpath=String.format("//a[contains(., '%s')]",name)
		if(name.isEmpty()){
			String id=CM.getTemplate().getItem_property_tab().lbl_itemId.generate_Name()
			xpath=String.format("//li[@itemid = '%s']",id)
		}
		a_item_in_last_folder.modify(xpath)
		return a_item_in_last_folder
	}

	private static generate_Name(String id="") {
		if(id.isEmpty())
			id=CM.getTemplate().getItem_property_tab().lbl_itemId.generate_Name()
		return "item_"+CM.template.template+"_"+id
	}

	private static delete_by_id() {
		int before_deleting=_items_in_lastFolder()
		before_deleting--
		def yes=LanguageHelper.getText('Yes')
		def delete=LanguageHelper.getText('Delete')
		def name=generate_Name()
		find_item(name)
		a_item_in_last_folder.right_click_until_not_appear(li_delete)
		def xpath="//*[@id = 'recycle-view-linked-items']//following::button[ ( . = '%s')]"
		def yes_remove_button=new MyElement("yes_remove_button",String.format(xpath, yes))
		li_delete.click_until_not_appear(yes_remove_button)
		yes_remove_button.click()
		WebHelper.delay_medium()
		verify_lastFolder(before_deleting,name)
	}

	static verify_lastFolder(int before_creating,String name="") {
		verify_lastFolder_number(before_creating)
		_open_lastFolder()
		MyElement item=CM.getSide_Bar().find_item(name)
		'if item was deleted than name contains item name, otherwise not'
		if(!name.isEmpty()){
			item.isVisible(false)
			KeywordUtil.markWarning(String.format("item %s not in the knowledge tree ", name))
		}else {
			item.click_with_hover()
			KeywordUtil.markWarning(String.format("item %s  in the knowledge tree ", item.generate_Name()))
		}
	}

	private static verify_lastFolder_number(int before_creating) {
		WebHelper.delay_medium()
		int after_creating=_items_in_lastFolder()
		if(!WebHelper.verify_ints(after_creating,before_creating)){
			WebHelper.delay_medium()
			after_creating=_items_in_lastFolder()
			WebHelper.verify_ints(after_creating,before_creating)
		}
	}

	static delete_first_item_(){
		a_item_in_last_folder.click_with_delay()
		CM.getBottom_Bar().verify_delete_by_btn_remove_bar()
	}

	private static delete_items(int count=ALL){
		_open_lastFolder()
		if(count==ALL){
			while (a_item_in_last_folder.isVisible(false)) {
				a_item_in_last_folder.modify(BYPATH)
				delete_first_item_()
				
			}
		}
		else {
			for(int i=0;i<count;i++){
				delete_first_item_()
			}
		}
	}
}
