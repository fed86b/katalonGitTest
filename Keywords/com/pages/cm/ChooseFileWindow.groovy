package com.pages.cm

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords
import com.pages.ItemAbstract
import com.system.IframeElement
import com.system.MyElement
import com.system.enums.EnumLanguage

import internal.GlobalVariable

import MobileBuiltInKeywords as Mobile
import WSBuiltInKeywords as WS
import WebUiBuiltInKeywords as WebUI

public class ChooseFileWindow {

	static IframeElement aUploadedFile
	static MyElement iframeUploaded=new MyElement(findTestObject('Kms_Page_OR/Roles/Content_Manager/iframe_uploaded_file'))

	static MyElement btnOpenUploadedFile=new MyElement("btnOpenUploadedFile","(//span[@class='ui-button-text']/parent::button[@id='openExternalFile'])[last()]")


	static IframeElement getUploadedFile(def fileImage){
		def xpath=String.format("(//li[@filename='%s'])[last()]",fileImage)
		aUploadedFile=new IframeElement("aUploadedFile",xpath,iframeUploaded.getElement())
	}
}
