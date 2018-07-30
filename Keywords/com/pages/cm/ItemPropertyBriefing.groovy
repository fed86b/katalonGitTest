package com.pages.cm

import com.system.IframeElement
import com.system.MyElement
import com.system.enums.EnumLanguage
import com.system.enums.EnumStatus
import com.system.enums.EnumTemplate

public class ItemPropertyBriefing extends ItemPropertyTab {

	static MyElement rbtn_signature_yes
	static MyElement rbtn_signature_no

	protected ItemPropertyBriefing(EnumLanguage lang){
		super(lang,EnumTemplate.Briefing)
	}

	protected fill(EnumStatus status_from=EnumStatus.ONLINE,
			EnumStatus status_after=EnumStatus.ARCHIVE, def set_checkBx=true){
		fillPropertyTab()
		clickSignature()
	}



	protected static  clickSignature(boolean check=true){
		rbtn_signature_yes=new IframeElement("rbtn_signature_yes","//input[@id='signatureRadioY']")
		rbtn_signature_no=new IframeElement("rbtn_signature_no","//input[@id='signatureRadioN']")
		if(check)
			rbtn_signature_yes.click_with_hover()
		else
			rbtn_signature_no.click_with_hover()
	}
}
