package com.pages.cm

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.system.Iframe_Element
import com.system.LanguageHelper
import com.system.MyElement
import com.system.WebHelper
import com.system.enums.Enum_Language
import com.system.enums.Enum_Status
import com.system.enums.Enum_Template

public class ItemProperty_General extends ItemProperty_Tab {



	static MyElement chkbx_likes=new Iframe_Element("chkbx_likes","//input[@type='checkbox' and @id='collaboration-likes']")
	static MyElement chkbx_follow=new Iframe_Element("chkbx_follow","//input[@type='checkbox' and @id='collaboration-follow']")
	static Iframe_Element chkbx_comment=new Iframe_Element("chkbx_comment","//input[@type='checkbox' and @id='collaboration-comments']")
	static Iframe_Element chkbx_new_window=new Iframe_Element("chkbx_new_window","//input[@id='ITEM_IMAGE@new_window']")

	static MyElement img_inlarged=new MyElement("img_inlarged","//div[@id='fancybox-content']/descendant::img")
	static MyElement img_uploaded_file=new Iframe_Element("img_uploaded_file","//div[@class='file-widget-preview-container']/descendant::img")
	static MyElement iframe_uploaded=new MyElement(findTestObject('Kms_Page_OR/Roles/Content_Manager/iframe_uploaded_file'))


	static MyElement a_uploaded_file

	static MyElement btn_x_close=new MyElement("btn_x_close","//a[@id='fancybox-close']")
	static MyElement btn_open_uploaded_file=new MyElement("btn_open_uploaded_file","(//span[@class='ui-button-text']/parent::button[@id='openExternalFile'])[last()]")
	static MyElement btn_browse

	protected ItemProperty_General(Enum_Language lang){
		super(lang,Enum_Template.General)
	}

	protected _fill_property_tab(Enum_Status status_from=Enum_Status.ONLINE,
			Enum_Status status_after=Enum_Status.ARCHIVE, def set_checkBx=true){

		_fillProperty_tab()
		'check likes'
		_check_likes(set_checkBx)

		'check follow'
		_check_follow(set_checkBx)

		'check comments'
		_check_comment(set_checkBx)

		'browse image'
		_browse_file()

		'click on image and verify'
		_click_on_image()
	}



	protected static  _check_comment(boolean check=true){
		if(check)
			chkbx_comment.check()
		else
			chkbx_comment.uncheck()
	}


	protected static  _check_follow (boolean check=true) {

		if(check)
			chkbx_follow.check()
		else
			chkbx_follow.uncheck()
	}


	protected static  _check_likes (boolean check=true) {

		if(check)
			chkbx_likes.check()
		else
			chkbx_likes.uncheck()
	}

	protected static  _check_new_window (boolean check=true) {

		if(check)
			chkbx_new_window.check()
		else
			chkbx_new_window.uncheck()
	}



	protected static _browse_file(){
		try{
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

	private static browse_file(def file_image="download.jpg"){
		def xpath=String.format("//div[@name='ITEM_IMAGE']/descendant::span[contains(.,'%s')]/parent::button", LanguageHelper.getText("Browse"))
		btn_browse=new Iframe_Element("btn_browse",xpath)
		btn_browse.click_until_not_appear(btn_open_uploaded_file)
		xpath=String.format("(//li[@filename='%s'])[last()]",file_image)
		a_uploaded_file=new Iframe_Element("a_uploaded_file",xpath,iframe_uploaded.getElement())
		a_uploaded_file.quick_double_click()
	}

	protected static _click_on_image(def file_image="download.jpg"){
		try{
			click_on_image(file_image)
		} catch (Exception e) {
			my_exeption=e
			fail=true
			WebHelper.delay_medium()
			click_on_image(file_image)
			fail=false
		}
		finally{
			if(fail)
				WebHelper.screenShoot(my_exeption.getMessage())
		}
	}

	private static click_on_image(file_image) {
		img_uploaded_file.click()
		WebHelper.delay()
		img_inlarged.compareImages(file_image)
		btn_x_close.click()
	}
}
