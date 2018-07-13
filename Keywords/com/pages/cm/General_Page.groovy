package com.pages.cm
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.pages.My_Template
import com.system.MyElement
import com.system.WebHelper
import com.system.enums.Enum_Language
import com.system.enums.Enum_Position
import com.system.enums.Enum_Template
public  class General_Page extends My_Template {

	static MyElement chkbx_likes=new MyElement(findTestObject('Kms_Page_OR/Roles/Content_Manager/Item_Properties_tab/chkbx_likes'))
	static MyElement chkbx_follow=new MyElement(findTestObject('Kms_Page_OR/Roles/Content_Manager/Item_Properties_tab/chkbx_follow'))
	static MyElement chkbx_comment=new MyElement(findTestObject('Kms_Page_OR/Roles/Content_Manager/Item_Properties_tab/chkbx_comment'))

	protected General_Page(Enum_Language lang) {
		super(Enum_Template.General,lang)
	}

	protected static  _check_comment(boolean check=true){
		WebHelper.scroll(Enum_Position.BOTTOM)
		if(check)
			check_ckbx(chkbx_comment)
		else
			uncheck(chkbx_comment)
	}


	protected static  _check_follow = {boolean check=true
		WebHelper.scroll(Enum_Position.BOTTOM)
		if(check)
			check_ckbx(chkbx_follow)
		else
			uncheck(chkbx_follow)
	}


	protected static  _check_likes = {boolean check=true
		WebHelper.scroll(Enum_Position.BOTTOM)
		if(check)
			check_ckbx(chkbx_likes)
		else
			uncheck(chkbx_likes)
	}
}
