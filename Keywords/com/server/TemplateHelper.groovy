package com.server
import com.pages.cm.Briefing_Page

import com.pages.My_Template
import com.pages.cm.languages.Briefing_En

public class TemplateHelper {

	private static Briefing_En briefingEn
	private static My_Template template
	public static Briefing_En getBriefing_En() {
		return briefingEn=new Briefing_En();
	}

	public static My_Template getTemplate() {
		return template=new My_Template();
	}
}
