package com.server

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.google.common.base.Verify
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
import com.thoughtworks.selenium.webdriven.commands.Click

import internal.GlobalVariable

import MobileBuiltInKeywords as Mobile
import WSBuiltInKeywords as WS
import WebUiBuiltInKeywords as WebUI

public class WebHelper {

	public void verify_text_visibility(TestObject element,String text="", boolean fail=false){

		if(fail)
			WebUI.waitForElementVisible(element, GlobalVariable.G_Small_Wait, FailureHandling.STOP_ON_FAILURE)
		else
			WebUI.waitForElementVisible(element, GlobalVariable.G_Small_Wait, FailureHandling.CONTINUE_ON_FAILURE)
		if(!text.isEmpty()){
			WebUI.verifyElementText(element,text,FailureHandling.CONTINUE_ON_FAILURE)
		}
	}

	public void verify_text_clicability(TestObject element,String text=""){

		verify_text_visibility(element, text,true)

		WebUI.verifyElementClickable(element, FailureHandling.STOP_ON_FAILURE)
	}
	public void verify_text_click(TestObject element,String text=""){

		verify_text_clicability(element, text)
		WebUI.click(element)

	}
	
	public void verify_text_MouseOver(TestObject element,String text=""){
		
				verify_text_visibility(element, text,true)

				WebUI.mouseOver(element)
			}
	

	public String get_data(int col, int row){
		return findTestData('Login_Data').getValue(col, row);
	}
}
