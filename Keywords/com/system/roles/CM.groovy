package com.system.roles
import com.pages.My_Item
import com.pages.My_Template
import com.pages.cm.Bottom_Bar_CM
import com.pages.cm.Briefing_Page
import com.pages.cm.General_Page
import com.pages.cm.Side_Bar
import com.pages.cm.Upper_Bar_CM
import com.system.LanguageHelper
import com.system.WebHelper
import com.system.enums.Enum_Language
import com.system.enums.Enum_Role

public abstract  class CM extends  User{
	private static Briefing_Page briefing
	private static General_Page general
	private static Upper_Bar_CM upper_bar
	private static Bottom_Bar_CM bottom_Bar_CM
	private static Side_Bar side_Bar
	protected CM(Enum_Language lang ){
		super(Enum_Role.CONTENT_MANAGER,lang)
	}
	protected static Briefing_Page getBriefing() {
		return briefing=new Briefing_Page(lang);
	}

	protected static Upper_Bar_CM getUpper_bar() {

		return upper_bar=new Upper_Bar_CM(lang);
	}

	protected static Side_Bar getSide_Bar() {
		return side_Bar=new Side_Bar(lang);
	}

	protected static Bottom_Bar_CM getBottom_Bar_CM() {
		return bottom_Bar_CM=new Bottom_Bar_CM(lang);
	}

	protected static General_Page getGeneral() {
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
		side_Bar._open_lastFolder()
		bottom_Bar_CM._DeleteAll()
	}
}
