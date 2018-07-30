package com.pages.cm

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.system.IframeElement
import com.system.LanguageHelper
import com.system.MyElement
import com.system.WebHelper
import com.system.enums.EnumLanguage
import com.system.enums.EnumStatus
import com.system.enums.EnumTemplate

public class ItemPropertyGeneral extends ItemPropertyTab {



	static MyElement chkbx_likes=new IframeElement("chkbx_likes","//input[@type='checkbox' and @id='collaboration-likes']")
	static MyElement chkbx_follow=new IframeElement("chkbx_follow","//input[@type='checkbox' and @id='collaboration-follow']")
	static IframeElement chkbx_comment=new IframeElement("chkbx_comment","//input[@type='checkbox' and @id='collaboration-comments']")
	static IframeElement chkbx_new_window=new IframeElement("chkbx_new_window","//input[@id='ITEM_IMAGE@new_window']")

	static MyElement img_inlarged=new MyElement("img_inlarged","//div[@id='fancybox-content']/descendant::img")
	static MyElement img_uploaded_file=new IframeElement("img_uploaded_file","//div[@class='file-widget-preview-container']/descendant::img")
	static MyElement iframe_uploaded=new MyElement(findTestObject('Kms_Page_OR/Roles/Content_Manager/iframe_uploaded_file'))


	static MyElement a_uploaded_file

	static MyElement btn_x_close=new MyElement("btn_x_close","//a[@id='fancybox-close']")
	static MyElement btn_open_uploaded_file=new MyElement("btn_open_uploaded_file","(//span[@class='ui-button-text']/parent::button[@id='openExternalFile'])[last()]")
	static MyElement btn_browse

	protected ItemPropertyGeneral(EnumLanguage lang){
		super(lang,EnumTemplate.General)
	}

	protected fill(EnumStatus status_from=EnumStatus.ONLINE,
			EnumStatus status_after=EnumStatus.ARCHIVE, def set_checkBx=true){

		fillPropertyTab()

		checkLikes(set_checkBx)

		checkFollow(set_checkBx)

		checkComment(set_checkBx)

		browseFile()

		clickOnImage()
	}



	protected static  checkComment(boolean check=true){
		if(check)
			chkbx_comment.check()
		else
			chkbx_comment.uncheck()
	}

	protected static  checkFollow (boolean check=true) {

		if(check)
			chkbx_follow.check()
		else
			chkbx_follow.uncheck()
	}


	protected static  checkLikes (boolean check=true) {
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



	protected static browseFile(){
		try{
			browse_File()
		} catch (Exception e) {
			my_exeption=e
			fail=true
			WebHelper.delay_medium()
			browse_File()
			fail=false
		}
		finally{
			if(fail)
				WebHelper.screenShoot(my_exeption.getMessage())
		}
	}

	protected static  browse_File(def file_image="download.jpg"){
		def xpath=String.format("//div[@name='ITEM_IMAGE']/descendant::span[contains(.,'%s')]/parent::button", LanguageHelper.getText("Browse"))
		btn_browse=new IframeElement("btn_browse",xpath)
		btn_browse.click_until_not_appear(btn_open_uploaded_file)
		xpath=String.format("(//li[@filename='%s'])[last()]",file_image)
		a_uploaded_file=new IframeElement("a_uploaded_file",xpath,iframe_uploaded.getElement())
		a_uploaded_file.quick_double_click()
	}

	protected static clickOnImage(def file_image="download.jpg"){
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

	static click_on_image(file_image) {
		img_uploaded_file.click()
		WebHelper.delay()
		img_inlarged.compareImages(file_image)
		btn_x_close.click()
	}
}
