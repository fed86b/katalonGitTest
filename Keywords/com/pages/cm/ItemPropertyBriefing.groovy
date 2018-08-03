package com.pages.cm

import com.system.IframeElement
import com.system.MyElement
import com.system.enums.EnumLanguage
import com.system.enums.EnumStatus
import com.system.enums.EnumTemplate

public class ItemPropertyBriefing extends ItemPropertyTab {

	static MyElement rbtnSignatureYes
	static MyElement rbtnSignatureNo

	protected ItemPropertyBriefing(EnumLanguage lang){
		super(lang,EnumTemplate.Briefing)
	}

	protected fill(def statusFrom=EnumStatus.ONLINE,
			def statusAfter=EnumStatus.ARCHIVE, def setCheckBx=true){
		fillPropertyTab(statusFrom,statusAfter,setCheckBx)
		clickSignature(setCheckBx)
	}



	protected static  clickSignature(boolean check=true){
		rbtnSignatureYes=new IframeElement("rbtnSignatureYes","//input[@id='signatureRadioY']")
		rbtnSignatureNo=new IframeElement("rbtnSignatureNo","//input[@id='signatureRadioN']")
		if(check)
			rbtnSignatureYes.clickWithHover()
		else
			rbtnSignatureNo.clickWithHover()
	}
}
