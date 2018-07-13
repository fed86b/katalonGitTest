package com.pages.cm

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.system.MyElement
import com.system.WebHelper
import com.system.enums.Enum_Language
import com.system.enums.Enum_Position
import com.system.enums.Enum_Template

public class ItemProperty_General extends ItemProperty_Tab {

	static MyElement chkbx_likes=new MyElement(findTestObject('Kms_Page_OR/Roles/Content_Manager/Item_Properties_tab/chkbx_likes'))
	static MyElement chkbx_follow=new MyElement(findTestObject('Kms_Page_OR/Roles/Content_Manager/Item_Properties_tab/chkbx_follow'))
	static MyElement chkbx_comment=new MyElement(findTestObject('Kms_Page_OR/Roles/Content_Manager/Item_Properties_tab/chkbx_comment'))

	static MyElement img_inlarged=new MyElement("img_inlarged","//div[@id='fancybox-content']/descendant::img")
	static MyElement img_uploaded_file=new MyElement(findTestObject('Kms_Page_OR/Roles/Content_Manager/Item_Properties_tab/img_uploaded'))
	static MyElement iframe_uploaded=new MyElement(findTestObject('Kms_Page_OR/Roles/Content_Manager/Item_Properties_tab/uploaded_file/iframe'))

	static MyElement a_uploaded_file=new MyElement(findTestObject('Kms_Page_OR/Roles/Content_Manager/Item_Properties_tab/uploaded_file/a_download.jpg'))


	static MyElement btn_x_close=new MyElement("btn_x_close","//a[@id='fancybox-close']")
	static MyElement btn_open_uploaded_file=new MyElement("btn_open_uploaded_file","id('openExternalFile')/span[@class='ui-button-text']/parent::button")
	static MyElement btn_browse=new MyElement(findTestObject('Kms_Page_OR/Roles/Content_Manager/Item_Properties_tab/btn_browse'))

	protected ItemProperty_General(Enum_Language lang){
		super(lang,Enum_Template.General)
	}




	protected static  _check_comment(boolean check=true){
		WebHelper.scroll(Enum_Position.BOTTOM)
		if(check)
			chkbx_comment.check()
		else
			chkbx_comment.uncheck()
	}


	protected static  _check_follow (boolean check=true) {
		WebHelper.scroll(Enum_Position.BOTTOM)
		if(check)
			chkbx_follow.check()
		else
			chkbx_follow.uncheck()
	}


	protected static  _check_likes (boolean check=true) {
		WebHelper.scroll(Enum_Position.BOTTOM)
		if(check)
			chkbx_likes.check()
		else
			chkbx_likes.uncheck()
	}



	protected static _browse_file(){
		try{
			WebHelper.scroll(Enum_Position.BOTTOM)
			browse_file()
		} catch (Exception e) {
			my_exeption=e
			fail=true
			WebHelper.delay_medium()
			browse_file()
			fail=false
		}
		finally{
			if(fail)
				WebHelper.screenShoot(my_exeption.getMessage())
		}
	}

	private static browse_file(){
		btn_browse.click_until_not_appear(iframe_uploaded)
		WebHelper.delay_medium()
		a_uploaded_file.click_with_hover()
		btn_open_uploaded_file.click_with_hover()
	}

	protected static _click_on_image(def file_image="download.jpg"){
		try{

			img_uploaded_file.click()
			WebHelper.delay()
			img_inlarged.compareImages(file_image)
			btn_x_close.click()
		} catch (Exception e) {
			my_exeption=e
			fail=true
			WebHelper.delay_medium()
			img_uploaded_file.compareImages(file_image)
			img_uploaded_file.click()
			WebHelper.delay()
			btn_x_close.click()
			fail=false
		}
		finally{
			if(fail)
				WebHelper.screenShoot(my_exeption.getMessage())
		}
	}
}
