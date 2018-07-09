package com.server
import org.openqa.selenium.Keys as Keys

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.driver.WebUIDriverType
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable

public  class Login_Element extends MyElement {
	public Login_Element(String element_name,String xpath,boolean isLogin=true){
		super(element_name,xpath,isLogin)
	}

	public Login_Element(TestObject element, boolean isLogin=true){
		super(element,isLogin)
	}
}


public  class MyElement extends TestObject {

	static MyElement progress=new MyElement("progress","//div[@id = 'progress-overlay']")
	boolean isLogin
	TestObject element

	public MyElement (String element_name,String xpath,boolean isLogin=false){
		super(element_name)
		element=this
		element.addProperty("xpath", ConditionType.EQUALS, xpath,true)
		this.isLogin=isLogin
	}

	public MyElement (TestObject element,boolean isLogin=false){
		super(element.getObjectId())
		this.element=element
		this.isLogin=isLogin
	}

	public boolean verifyText(String text) {
		if(!text.isEmpty()&&!DriverFactory.getExecutedBrowser() == WebUIDriverType.EDGE_DRIVER)
			return WebUI.verifyElementText(element,text,FailureHandling.OPTIONAL)
		return false
	}

	public boolean for_Ie(MyElement folder){
		if(DriverFactory.getExecutedBrowser() == WebUIDriverType.IE_DRIVER){
			for(def i=0;i<2;i++){
				if(!folder.isVisible(false))
					click_with_hover()
			}
			return true
		}
		return false
	}

	public MyElement  submit(def text=""){
		isVisible(true,text)
		WebUI.submit(element,FailureHandling.STOP_ON_FAILURE)
		return this
	}

	public MyElement  press_Enter(def text=""){
		isVisible(true,text)
		WebUI.sendKeys(element,Keys.chord(Keys.ENTER),FailureHandling.STOP_ON_FAILURE)
		return this
	}

	public MyElement  press_Del(def text=""){
		isVisible(true,text)
		WebUI.sendKeys(element,Keys.chord(Keys.DELETE),FailureHandling.STOP_ON_FAILURE)
		return this
	}

	public MyElement  press_Esc(def text=""){
		isVisible(true,text)
		WebUI.sendKeys(element,Keys.chord(Keys.ESCAPE),FailureHandling.STOP_ON_FAILURE)
		return this
	}
	public MyElement  press_tab(def text=""){
		isVisible(true,text)
		WebUI.sendKeys(element,Keys.chord(Keys.TAB),FailureHandling.STOP_ON_FAILURE)
		return this
	}


	public MyElement  select_by_index(int index){
		isVisible()
		WebUI.selectOptionByIndex(element, index, FailureHandling.STOP_ON_FAILURE)
		return this
	}


	public MyElement  click_with_hover(String text=""){
		MouseOver(text)
		WebUI.click(element,FailureHandling.STOP_ON_FAILURE)
		return this
	}

	public MyElement  click(def text=""){
		isVisible(true,text)
		WebUI.click(element,FailureHandling.STOP_ON_FAILURE)
		return this
	}
	public MyElement modify(def xpath){
		WebUI.modifyObjectProperty(element, 'xpath', 'equals', xpath, false)
		return this
	}


	public MyElement  image_click(String text=""){
		isVisible(true,text)
		WebUI.clickImage(element,FailureHandling.STOP_ON_FAILURE)
		return this
	}

	public MyElement  click_with_delay(def text=""){
		delay()
		isVisible(true,text)
		WebUI.click(element,FailureHandling.STOP_ON_FAILURE)
		delay()
		return this
	}

	public MyElement   double_click(def text=""){
		isVisible(true,text)
		WebUI.click(element,FailureHandling.STOP_ON_FAILURE)
		delay()
		WebUI.click(element,FailureHandling.STOP_ON_FAILURE)
		return this
	}

	public String generate_Name(){
		isVisible()
		return WebUI.getText(element,FailureHandling.STOP_ON_FAILURE)
	}

	public MyElement  write_text(String str){
		isVisible()
		WebUI.clearText(element,FailureHandling.STOP_ON_FAILURE)
		WebUI.setText(element, str,FailureHandling.STOP_ON_FAILURE)
		return this
	}

	//for ie and firefox
	public MyElement write_key_chord(String str){
		CharSequence cs = str
		isVisible()
		WebUI.sendKeys(element,Keys.chord(cs),FailureHandling.STOP_ON_FAILURE)
		return this
	}



	public MyElement MouseOver(def text=""){
		isVisible(true,text)
		WebUI.mouseOver(element,FailureHandling.STOP_ON_FAILURE)
		return this
	}

	public boolean isVisible(boolean fail=true,def text=""){
		WebUI.scrollToPosition(WebUI.getViewportLeftPosition(),WebUI.getViewportTopPosition())
		boolean visible=false
		if(!isLogin)
			verify_process_wait()
		try{
			verifyText(text)
			if(fail){
				if(!WebUI.waitForElementVisible(element, GlobalVariable.G_Middle_Wait))
					findElement()

			}
			else
				visible= WebUI.waitForElementVisible(element, GlobalVariable.G_Middle_Wait, FailureHandling.OPTIONAL)
		} catch (Exception e) {
			delay()
			findElement()
			visible= WebUI.waitForElementVisible(element, GlobalVariable.G_Middle_Wait)
		}
		return visible
	}

	private  verify_process_wait(){

		try {
			//			if(WebUI.verifyElementPresent(progress, GlobalVariable.G_Small_Wait, FailureHandling.OPTIONAL)){
			//				while(WebUI.getAttribute(progress, 'style').contains('block'))
			//					delay()
			if(WebUI.waitForElementPresent(progress, GlobalVariable.G_Wait, FailureHandling.OPTIONAL))
				while(!WebUI.waitForElementNotVisible(progress, GlobalVariable.G_Small_Wait, FailureHandling.OPTIONAL))
					delay()

		} catch (Exception e) {	}
	}

	public MyElement findElement() {
		WebUI.scrollToElement(element, GlobalVariable.G_Middle_Wait,FailureHandling.OPTIONAL)
		if(WebUI.waitForElementNotVisible(element, GlobalVariable.G_Small_Wait))
			WebUI.scrollToPosition(WebUI.getElementLeftPosition(element), WebUI.getPageHeight()*0.8,FailureHandling.STOP_ON_FAILURE)
		return this
	}



	private  delay(){
		//Thread.sleep(GlobalVariable.G_Millisec)
		WebUI.delay(GlobalVariable.G_Small_Wait)
	}

	private  delay_medium(){
		WebUI.delay(GlobalVariable.G_Middle_Wait)
	}
}



