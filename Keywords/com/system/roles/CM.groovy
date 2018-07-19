package com.system.roles

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.jsoup.select.Evaluator.Id
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.pages.cm.Bottom_Bar_CM
import com.pages.cm.Briefing_Page
import com.pages.cm.General_Page
import com.pages.My_Template
import com.pages.cm.Side_Bar
import com.pages.cm.Upper_Bar_CM
import com.system.LanguageHelper
import com.system.MyElement
import com.system.WebHelper
import com.system.enums.Enum_Language
import com.system.enums.Enum_Role

public  class CM extends  User{

	static MyElement iframe_item_scope=new MyElement(findTestObject('Kms_Page_OR/Roles/Content_Manager/iframe_itemscope'))
	static def isIframe=false
	static Briefing_Page briefing
	static General_Page general
	static My_Template template
	static Upper_Bar_CM upper_bar_CM
	static Bottom_Bar_CM bottom_Bar
	static Side_Bar side_Bar

	protected CM(Enum_Language lang ){
		super(Enum_Role.CONTENT_MANAGER,lang)
	}

	protected static _delete_all_created_items(){
		try{
			getSide_Bar().delete_items()
		}
		catch (Exception e) {
			WebHelper.delay_medium()
			my_exeption=e
			fail=true
			getSide_Bar().delete_items()
			fail=false
		}
		finally{
			if(fail){
				WebHelper.screenShoot(my_exeption.getMessage())
			}
		}
	}

	public static My_Template getTemplate() {
		if(template==null)
			template=new General_Page(lang)
		return template;
	}

	protected static Bottom_Bar_CM getBottom_Bar() {
		if(bottom_Bar==null)
			bottom_Bar=new Bottom_Bar_CM(lang)
		return bottom_Bar
	}

	protected static Briefing_Page getBriefing() {
		if(briefing==null){
			briefing=new Briefing_Page(lang)
			template=briefing;
		}
		return briefing
	}

	protected static Upper_Bar_CM getUpper_bar() {
		if(upper_bar_CM==null)
			upper_bar_CM=new Upper_Bar_CM(lang)
		return upper_bar_CM
	}

	protected static General_Page getGeneral() {
		if(general==null){
			general=new General_Page(lang)
			template=general;
		}
		return general
	}

	public static Side_Bar getSide_Bar() {
		if(side_Bar==null)
			side_Bar=new Side_Bar(lang)
		return side_Bar
	}
}
