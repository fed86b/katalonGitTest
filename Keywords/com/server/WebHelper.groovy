package com.server

import static com.kms.katalon.core.testdata.TestDataFactory.findTestData

import org.openqa.selenium.WebDriver
import org.openqa.selenium.edge.EdgeDriver

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI


import internal.GlobalVariable

public class WebHelper {

	public void verify_text_visibility(TestObject element,String text="", boolean fail=false){
		WebDriver driver=DriverFactory.getWebDriver()
		if(fail)
			WebUI.waitForElementVisible(element, GlobalVariable.G_Small_Wait, FailureHandling.STOP_ON_FAILURE)
		else
			WebUI.waitForElementVisible(element, GlobalVariable.G_Small_Wait, FailureHandling.CONTINUE_ON_FAILURE)

		if(driver instanceof EdgeDriver){}
		else if(!text.isEmpty())
			WebUI.verifyElementText(element,text,FailureHandling.CONTINUE_ON_FAILURE)
	}

	public void verify_text_clicability(TestObject element,String text=""){

		verify_text_visibility(element, text,true)

		WebUI.verifyElementClickable(element, FailureHandling.STOP_ON_FAILURE)
	}
	public void verify_text_click(TestObject element,String text=""){
		WebDriver driver=DriverFactory.getWebDriver()
		if(driver instanceof EdgeDriver) {
			WebUI.delay(GlobalVariable.G_Small_Wait)
		}
		verify_text_clicability(element, text)
		WebUI.click(element)
	}

	public void verify_write(TestObject element,String text=""){

		verify_text_visibility(element)
		WebUI.setText(element, text)
	}

	public void verify_texts(String sourseText="",String targetText=""){

		WebUI.verifyEqual(sourseText.toLowerCase(), targetText.toLowerCase(), FailureHandling.CONTINUE_ON_FAILURE)
	}

	public void verify_text_MouseOver(TestObject element,String text=""){

		verify_text_visibility(element, text,true)
		WebDriver driver=DriverFactory.getWebDriver()
		if(driver instanceof EdgeDriver) {
			WebUI.delay(GlobalVariable.G_Small_Wait)
		}
		WebUI.mouseOver(element)
	}




	public String get_data(int col, int row){
		return findTestData('Login_Data').getValue(col, row);
	}
}
