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

	public void verifyText(String text) {
		if(!text.isEmpty()&&!DriverFactory.getExecutedBrowser() == WebUIDriverType.EDGE_DRIVER)
			WebUI.verifyElementText(element,text,FailureHandling.OPTIONAL)
	}


	public  submit(){
		isVisible()
		WebUI.submit(element)
	}

	public  press_Enter(){
		isVisible()
		WebUI.sendKeys(element,Keys.chord(Keys.ENTER))
	}

	public  press_Del(){
		isVisible()
		WebUI.sendKeys(element,Keys.chord(Keys.DELETE))
	}

	public  press_Esc(){
		isVisible()
		WebUI.sendKeys(element,Keys.chord(Keys.ESCAPE))
	}
	public  press_tab(){
		isVisible()
		WebUI.sendKeys(element,Keys.chord(Keys.TAB))
	}


	public  select_by_index(int index){
		isVisible()
		WebUI.selectOptionByIndex(element,index)
	}


	public  click_with_hover(){
		MouseOver()
		WebUI.click(element)
	}

	public  click(){
		isVisible()
		WebUI.click(element)
	}
	public modify(def xpath){
		WebUI.modifyObjectProperty(element, 'xpath', 'equals', xpath, false)
	}





	public  image_click(String text=""){
		isVisible()
		WebUI.clickImage(element)
	}

	public  click_with_delay(){
		delay()
		isVisible()
		WebUI.click(element)
		delay()
	}

	public   double_click(){
		isVisible()
		WebUI.click(element)
		delay()
		WebUI.click(element)
	}

	public  String generate_Name(){
		isVisible()
		return WebUI.getText(element)
	}

	public  write_text(String text){
		isVisible()
		WebUI.clearText(element)
		WebUI.setText(element, text)
	}

	//for ie and firefox
	public write_key_chord(String text){
		CharSequence cs = text
		isVisible()
		WebUI.sendKeys(element,Keys.chord(cs) )
	}



	public MouseOver(){
		isVisible()
		WebUI.mouseOver(element)
	}

	public boolean isVisible(boolean fail=true){
		WebUI.scrollToPosition(WebUI.getViewportLeftPosition(),WebUI.getViewportTopPosition())
		boolean visible=false
		if(!isLogin)
			verify_process_wait()
		try{
			if(fail){
				if(!WebUI.waitForElementVisible(element, GlobalVariable.G_Middle_Wait))
					throw new Exception()

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

	private  findElement() {
		try {
			WebUI.scrollToElement(element, GlobalVariable.G_Middle_Wait,FailureHandling.OPTIONAL)
		} catch (Exception ex) {
			WebUI.scrollToPosition(WebUI.getElementLeftPosition(element), WebUI.getPageHeight()*0.8)
		}
	}

	private  delay(){
		WebUI.delay(GlobalVariable.G_Small_Wait)
	}

	private  delay_medium(){
		WebUI.delay(GlobalVariable.G_Middle_Wait)
	}
}



