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



	static MyElement chkbxLikes=new IframeElement("chkbxLikes","//input[@type='checkbox' and @id='collaboration-likes']")
	static MyElement chkbxFollow=new IframeElement("chkbxFollow","//input[@type='checkbox' and @id='collaboration-follow']")
	static IframeElement chkbxComment=new IframeElement("chkbxComment","//input[@type='checkbox' and @id='collaboration-comments']")
	static IframeElement chkbxNewWindow=new IframeElement("chkbxNewWindow","//input[@id='ITEM_IMAGE@new_window']")
	static MyElement btnXClose=new MyElement("btnXClose","//a[@id='fancybox-close']")
	static MyElement imgInlarged=new MyElement("imgInlarged","//div[@id='fancybox-content']/descendant::img")
	static MyElement imgUploadedFile=new IframeElement("imgUploadedFile","//div[@class='file-widget-preview-container']/descendant::img")

	static MyElement btnBrowse

	protected ItemPropertyGeneral(EnumLanguage lang){
		super(lang,EnumTemplate.General)
	}

	protected fill(EnumStatus status_from=EnumStatus.ONLINE,
			EnumStatus status_after=EnumStatus.ARCHIVE, def set_checkBx=true){

		fillPropertyTab()

		//		checkLikes(set_checkBx)
		//
		//		checkFollow(set_checkBx)
		//
		//		checkComment(set_checkBx)

		browseFile()

		clickOnImage()
	}



	protected static  checkComment(boolean check=true){
		if(check)
			chkbxComment.check()
		else
			chkbxComment.uncheck()
	}

	protected static  checkFollow (boolean check=true) {

		if(check)
			chkbxFollow.check()
		else
			chkbxFollow.uncheck()
	}


	protected static  checkLikes (boolean check=true) {
		if(check)
			chkbxLikes.check()
		else
			chkbxLikes.uncheck()
	}

	protected static  checkNewWindow (boolean check=true) {
		if(check)
			chkbxNewWindow.check()
		else
			chkbxNewWindow.uncheck()
	}



	protected static browseFile(def fileImage="download.jpg"){
		try{
			def xpath=String.format("//div[@name='ITEM_IMAGE']/descendant::span[contains(.,'%s')]/parent::button", LanguageHelper.getText("Browse"))
			btnBrowse=new IframeElement("btnBrowse",xpath)
			btnBrowse.clickUntilNotAppear(ChooseFileWindow.btnOpenUploadedFile)
			IframeElement file= ChooseFileWindow.getUploadedFile(fileImage)
			file.quickDoubleClick()
		} catch (Exception e) {
			WebHelper.catchException(e)
		}
	}

	protected static clickOnImage(def fileImage="download.jpg"){
		try{
			imgUploadedFile.clickUntilNotAppear(imgInlarged)
			WebHelper.delay()
			imgInlarged.compareImages(fileImage)
			btnXClose.click()
		} catch (Exception e) {
			WebHelper.catchException(e)
		}
	}
}
