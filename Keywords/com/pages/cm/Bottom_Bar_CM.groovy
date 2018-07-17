package com.pages.cm

import com.pages.Bottom_Bar
import com.system.LanguageHelper
import com.system.MyElement
import com.system.WebHelper
import com.system.enums.Enum_Language

public class Bottom_Bar_CM extends Bottom_Bar {

	static MyElement btn_saveAndRelocate=new MyElement("btn_saveAndRelocate","//span[@class = 'kms-icon kms-icon--SaveAndRelocate action-bar__button-icon']")
	static MyElement btn_save_in_last_folder
	static MyElement btn_remove_in_three_dots=new MyElement("btn_remove_in_three_dots","//li[@data-id = 'Remove' and @class='action-bar__button action-bar__button--grouped']")
	static MyElement btn_remove_bar=new MyElement("btn_remove_bar","//li[@class='action-bar__button']/span[@class='kms-icon kms-icon--Remove action-bar__button-icon']")
	static MyElement a_choose_lastFolder_in_modal=new MyElement("a_choose_lastFolder_in_modal","//span[@class = 'dynatree-node dynatree-folder dynatree-lastsib dynatree-exp-cl dynatree-ico-cf']")


	protected Bottom_Bar_CM(Enum_Language lang){
		super(lang)
	}

	protected static  _click_save_and_relocate_to_lastFolder(){
		try {
			btn_saveAndRelocate.click_with_hover()
		} catch (Exception e) {
			my_exeption=e
			fail=true
			WebHelper.delay_medium()
			btn_saveAndRelocate.click_with_hover()
			fail=false
		}
		finally{
			if(fail)
				WebHelper.screenShoot(my_exeption.getMessage())
		}
	}
}
