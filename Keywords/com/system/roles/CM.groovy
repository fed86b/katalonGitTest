package com.system.roles
import com.pages.Kms_Page
import com.pages.My_Template
import com.pages.cm.Briefing_Page
import com.pages.cm.General_Page
import com.system.LanguageHelper
import com.system.WebHelper
import com.system.enums.Enum_Language
import com.system.enums.Enum_Role

public abstract  class CM extends  Kms_Page{
	private static Briefing_Page briefing
	private static General_Page general
	protected CM(Enum_Language lang ){
		super(Enum_Role.CONTENT_MANAGER,lang)
	}
	public static Briefing_Page getBriefing() {
		return briefing=new Briefing_Page(lang);
	}

	public static General_Page getGeneral() {
		return general=new  General_Page(lang) ;
	}
	protected static _delete_all_created_items_en(){
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

	private static delete_all_created_items(String yes){
		My_Template._open_lastFolder()
		My_Template._DeleteAll()
	}
}
