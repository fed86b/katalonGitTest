package com.pages.cm
import com.pages.My_Template
import com.system.enums.Enum_Language
import com.system.enums.Enum_Template
public  class Briefing_Page extends My_Template {

	

	static Edit_Tab edit_Tab
	protected Briefing_Page(Enum_Language lang) {
		super(Enum_Template.Briefing,lang)
		
	}

	public static Edit_Tab getEdit_Tab() {
		return edit_Tab=new Edit_Tab(lang)
	}

	
}
