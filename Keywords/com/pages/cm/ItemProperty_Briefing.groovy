package com.pages.cm

import com.system.Iframe_Element
import com.system.MyElement
import com.system.enums.Enum_Language
import com.system.enums.Enum_Status
import com.system.enums.Enum_Template

public class ItemProperty_Briefing extends ItemProperty_Tab {

	static MyElement rbtn_signature_yes
	static MyElement rbtn_signature_no

	protected ItemProperty_Briefing(Enum_Language lang){
		super(lang,Enum_Template.Briefing)
	}

	protected _fill_property_tab(Enum_Status status_from=Enum_Status.ONLINE,
			Enum_Status status_after=Enum_Status.ARCHIVE, def set_checkBx=true){
		_fillProperty_tab()

		'set signature'
		_click_signature_yes()
	}



	protected static  _click_signature_yes(boolean check=true){
		rbtn_signature_yes=new Iframe_Element("rbtn_signature_yes","//input[@id='signatureRadioY']")
		rbtn_signature_no=new Iframe_Element("rbtn_signature_no","//input[@id='signatureRadioN']")
		if(check)
			rbtn_signature_yes.click_with_hover()
		else
			rbtn_signature_no.click_with_hover()
	}
}
