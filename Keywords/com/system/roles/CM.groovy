package com.system.roles

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.pages.cm.Bottom_Bar_CM
import com.pages.cm.Briefing_Page
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.pages.cm.General_Page
import com.pages.cm.Side_Bar
import com.pages.cm.Upper_Bar_CM
import com.system.LanguageHelper
import com.system.WebHelper
import com.system.enums.Enum_Language
import com.system.enums.Enum_Role
import com.system.MyElement
import internal.GlobalVariable

public abstract  class CM extends  User{

	static MyElement iframe_item_scope=new MyElement(findTestObject('Kms_Page_OR/Roles/Content_Manager/iframe_itemscope'))
	static def isIframe=false
	static Briefing_Page briefing
	static General_Page general
	static Upper_Bar_CM upper_bar_CM
	static Bottom_Bar_CM bottom_Bar
	static Side_Bar side_Bar
	protected CM(Enum_Language lang ){
		super(Enum_Role.CONTENT_MANAGER,lang)
	}
	protected static _delete_all_created_items(){
		def Yes=LanguageHelper.getText('Yes')
		try{
			delete_all_created_items(Yes)
		}
		catch (Exception e) {
			WebHelper.delay_medium()
			my_exeption=e
			fail=true
			delete_all_created_items(Yes)
			fail=false
		}
		finally{
			if(fail){
				WebHelper.screenShoot(my_exeption.getMessage())
			}
		}
	}
	protected static Bottom_Bar_CM getBottom_Bar() {
		if(bottom_Bar==null)
			bottom_Bar=new Bottom_Bar_CM(lang)
		return bottom_Bar
	}

	protected static Briefing_Page getBriefing() {
		if(briefing==null)
			briefing=new Briefing_Page(lang)
		return briefing
	}

	protected static Upper_Bar_CM getUpper_bar() {
		if(upper_bar_CM==null)
			upper_bar_CM=new Upper_Bar_CM(lang)
		return upper_bar_CM
	}

	protected static General_Page getGeneral() {
		if(general==null)
			general=new General_Page(lang)
		return general
	}

	public static Side_Bar getSide_Bar() {
		if(side_Bar==null)
			side_Bar=new Side_Bar(lang)
		return side_Bar
	}


	private static delete_all_created_items(String yes){
		getSide_Bar()._open_lastFolder()
		deleteAll()
	}

	static _click_save_and_check_if_number_of_items_incremented_in_lastFolder(){
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


	static deleteAll(){
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

	static delete_by_id(int Id=0,String yes){

		(new MyElement("itemId",String.format("//li[@itemid = '%d']",Id))).click_with_delay()
		verify_delete(yes)
	}

	static delete_first_item_(String yes){
		getSide_Bar().a_item_in_last_folder.click_with_delay()
		verify_delete(yes)
	}

	private static verify_delete(String yes) {
		int before_deleting=getSide_Bar()._items_in_lastFolder()
		def xpath="//*[@id = 'recycle-view-linked-items']//following::button[ ( . = '%s')]"
		def yes_remove_button=new MyElement("yes_remove_button",String.format(xpath, yes))
		before_deleting--
		if(getBottom_Bar().btn_remove_bar.isVisible(false))
			getBottom_Bar().btn_remove_bar.click_with_delay().click_until_not_appear(yes_remove_button)
		else if(WebHelper.check_three_dots())
			getBottom_Bar().btn_remove_in_three_dots.click_with_delay()

		yes_remove_button.click_with_delay()
		WebHelper.delay_medium()
		int after_deleting=getSide_Bar()._items_in_lastFolder()
		if(!WebHelper.verify_ints(after_deleting,before_deleting)){
			WebHelper.delay_medium()
			after_deleting=getSide_Bar()._items_in_lastFolder()
			WebHelper.verify_ints(after_deleting,before_deleting)
		}
	}

	private static delete_items(int count=getSide_Bar().ALL,String yes){
		if(count==getSide_Bar().ALL){
			while (getSide_Bar().a_item_in_last_folder.isVisible(false)) {
				delete_first_item_(yes)
			}
		}
		else {
			for(int i=0;i<count;i++){
				delete_first_item_(yes)
			}
		}
	}

	private static  save_check_if_number_of_items_incremented_in_lastFolder(String save){
		int before_creating=getSide_Bar()._items_in_lastFolder()
		before_creating++
		getBottom_Bar().a_choose_lastFolder_in_modal.click_with_hover()
		getBottom_Bar().btn_save_in_last_folder=new MyElement("btn_save_in_last_folder",String.format("//span[(. = '%s')]",save))
		getBottom_Bar().btn_save_in_last_folder.click_with_hover()
		WebHelper.delay_medium()
		int after_creating=getSide_Bar()._items_in_lastFolder()
		if(!WebHelper.verify_ints(after_creating,before_creating)){
			WebHelper.delay_medium()
			after_creating=getSide_Bar()._items_in_lastFolder()
			WebHelper.verify_ints(after_creating,before_creating)
		}
	}
}
