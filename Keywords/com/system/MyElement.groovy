package com.system
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.Keys as Keys
import org.openqa.selenium.Point
import org.openqa.selenium.WebElement

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.driver.WebUIDriverType
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.system.enums.Enum_Role

import internal.GlobalVariable


public  class Login_Element extends MyElement {
	public Login_Element(String element_name,String xpath,boolean isLogin=true){
		super(element_name,xpath,isLogin)
	}

	public Login_Element(TestObject element, boolean isLogin=true){
		super(element,isLogin)
	}
}

public  class Iframe_Element extends MyElement {

	public Iframe_Element(String element_name,String xpath,
	TestObject iframe=findTestObject('Kms_Page_OR/Roles/Content_Manager/iframe_itemscope')){
		super(element_name,xpath,false,iframe)
	}
}

public  class MyElement extends TestObject {

	boolean isLogin
	TestObject element
	def isIframe=false
	def fail=false
	Exception my_exception

	public MyElement (String element_name,String xpath,boolean isLogin=false,TestObject iframe=null){
		super(element_name)
		element=this
		element.addProperty("xpath", ConditionType.EQUALS, xpath,true)
		this.isLogin=isLogin
		if(iframe!=null){
			this.setParentObject(iframe)
			isIframe=true
		}
	}

	public MyElement (TestObject element,boolean isLogin=false){
		super(element.getObjectId())
		this.element=element
		this.isLogin=isLogin
	}

	public boolean verifyText(String text="",boolean full_compare=true) {
		//return true
		def flag=false
		if(!text.isEmpty()){
			def element_text=WebUI.getText(element)
			if(full_compare)
				flag= WebUI.verifyEqual(element_text.equalsIgnoreCase(text), true, FailureHandling.OPTIONAL)
			else
				flag= WebUI.verifyEqual(element_text.contains(text), true, FailureHandling.OPTIONAL)

			KeywordUtil.markWarning(String.format("Comparing %s texts : %s",text,flag))
		}
		return flag
	}

	public MyElement click_until_not_appear(MyElement item,def text=""){
		for(def i=0;i<GlobalVariable.G_Middle_Wait;i++){
			MouseOver()
			WebUI.click(element,FailureHandling.OPTIONAL)
			if(WebUI.waitForElementPresent(item, GlobalVariable.G_Middle_Wait, FailureHandling.OPTIONAL))
				return this
		}
		return this
	}

	public MyElement right_click_until_not_appear(MyElement item,def text=""){
		for(def i=0;i<GlobalVariable.G_Middle_Wait;i++){
			MouseOver()
			WebUI.rightClick(element,FailureHandling.OPTIONAL)
			if(WebUI.waitForElementPresent(item, GlobalVariable.G_Middle_Wait, FailureHandling.OPTIONAL))
				return this
		}
		return this
	}

	public MyElement click_until_not_disappear(MyElement item,def text=""){
		for(def i=0;i<GlobalVariable.G_Middle_Wait;i++){
			isVisible(false,text)
			WebUI.click(element,FailureHandling.OPTIONAL)
			if(WebUI.waitForElementPresent(item, GlobalVariable.G_Middle_Wait, FailureHandling.OPTIONAL))
				return this
		}
		return this
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
		try{
			isVisible()
			WebUI.selectOptionByIndex(element, index, FailureHandling.STOP_ON_FAILURE)
		}catch(Exception e){
			WebUI.scrollToElement(element,GlobalVariable.G_Wait)
			WebUI.selectOptionByIndex(element, index, FailureHandling.STOP_ON_FAILURE)
		}
		return this
	}

	public MyElement  click_with_hover(String text=""){
		try{
			MouseOver(text)
			WebUI.click(element,FailureHandling.STOP_ON_FAILURE)
		}catch(Exception e){
			WebUI.scrollToElement(element,GlobalVariable.G_Wait)
			WebUI.click(element,FailureHandling.STOP_ON_FAILURE)

		}
		return this
	}

	public MyElement  right_click(def text=""){
		try{
			isVisible(true,text)
			WebUI.rightClick(element,FailureHandling.STOP_ON_FAILURE)
		}catch(Exception e){
			WebUI.scrollToElement(element,GlobalVariable.G_Wait)
			WebUI.click(element,FailureHandling.STOP_ON_FAILURE)

		}
		return this
	}

	public MyElement  click(def text=""){
		try{
			isVisible(true,text)
			WebUI.click(element,FailureHandling.STOP_ON_FAILURE)
		}catch(Exception e){
			WebUI.scrollToElement(element,GlobalVariable.G_Wait)
			WebUI.click(element,FailureHandling.STOP_ON_FAILURE)

		}
		return this
	}

	public MyElement  check(){
		try{
			MouseOver()
			if(!WebUI.verifyElementChecked(element, GlobalVariable.G_Small_Wait, FailureHandling.OPTIONAL))
				WebUI.check(element,FailureHandling.STOP_ON_FAILURE)
		}catch(Exception e){
			fail=true
			my_exception=e
			WebUI.scrollToElement(element,GlobalVariable.G_Wait)
			if(!WebUI.verifyElementChecked(element, GlobalVariable.G_Small_Wait, FailureHandling.OPTIONAL))
				WebUI.check(element,FailureHandling.STOP_ON_FAILURE)
			WebHelper.screenShoot(e.getMessage())
			fail=false
		}
		finally{
			if(fail)
				WebHelper.screenShoot(my_exception.getMessage())
		}
		return this
	}

	public MyElement  uncheck(){
		try{
			MouseOver()
			if(WebUI.verifyElementChecked(element, GlobalVariable.G_Small_Wait, FailureHandling.OPTIONAL))
				WebUI.uncheck(element,FailureHandling.STOP_ON_FAILURE)
		}catch(Exception e){
			fail=true
			my_exception=e
			WebUI.scrollToElement(element,GlobalVariable.G_Wait)
			if(WebUI.verifyElementChecked(element, GlobalVariable.G_Small_Wait, FailureHandling.OPTIONAL))
				WebUI.uncheck(element,FailureHandling.STOP_ON_FAILURE)
			fail=false

		}
		finally{
			if(fail)
				WebHelper.screenShoot(my_exception.getMessage())
		}
		return this
	}

	public MyElement modify(def from_text, def to_text){
		String xpath=element.findProperty("xpath").getValue()
		def newXpath=xpath.replaceAll(from_text, to_text)
		WebUI.modifyObjectProperty(element, 'xpath', 'equals', newXpath, true)
		return this
	}

	public MyElement modify(def xpath){
		WebUI.modifyObjectProperty(element, 'xpath', 'equals', xpath, true)
		return this
	}

	public MyElement compareImages(def name="download.jpg",Enum_Role role=Enum_Role.CONTENT_MANAGER){
		Compare_Images.compare_webElement_pic(element,role, name)
		return this
	}

	public MyElement  click_with_delay(def text=""){
		try{
			delay()
			isVisible(true,text)
			WebUI.click(element,FailureHandling.STOP_ON_FAILURE)
			delay()
		}catch(Exception e){
			WebUI.scrollToElement(element,GlobalVariable.G_Wait)
			WebUI.click(element,FailureHandling.STOP_ON_FAILURE)
		}
		return this
	}

	public MyElement double_click(def text=""){
		try{
			isVisible(true,text)
			WebUI.click(element,FailureHandling.STOP_ON_FAILURE)
			delay()
			WebUI.click(element,FailureHandling.STOP_ON_FAILURE)
		}catch(Exception e){
			WebUI.scrollToElement(element,GlobalVariable.G_Wait)
			WebUI.click(element,FailureHandling.STOP_ON_FAILURE)
			delay()
			WebUI.click(element,FailureHandling.STOP_ON_FAILURE)
		}
		return this
	}

	public MyElement quick_double_click(def text=""){
		try{
			isVisible(true,text)
			WebUI.doubleClick(element)
		}catch(Exception e){
			WebUI.scrollToElement(element,GlobalVariable.G_Wait)
			WebUI.doubleClick(element)
		}
		return this
	}

	public String generate_Name(){
		def text=""
		try{
			isVisible()
			text=WebUI.getText(element,FailureHandling.STOP_ON_FAILURE)
		}catch(Exception e){
			WebUI.scrollToElement(element,GlobalVariable.G_Wait)
			text=WebUI.getText(element,FailureHandling.STOP_ON_FAILURE)
		}
		return text
	}

	public MyElement  write_text(String str){
		try{
			isVisible()
			WebUI.clearText(element,FailureHandling.STOP_ON_FAILURE)
			WebUI.setText(element, str,FailureHandling.STOP_ON_FAILURE)
		}catch(Exception e){
			WebUI.scrollToElement(element,GlobalVariable.G_Wait)
			WebUI.clearText(element,FailureHandling.STOP_ON_FAILURE)
			WebUI.setText(element, str,FailureHandling.STOP_ON_FAILURE)
		}
		return this
	}

	//for ie and firefox
	public MyElement write_key_chord(String str){
		CharSequence cs = str
		try{
			isVisible()
			WebUI.sendKeys(element,Keys.chord(cs),FailureHandling.STOP_ON_FAILURE)
		}catch(Exception e){
			WebUI.scrollToElement(element,GlobalVariable.G_Wait)
			WebUI.sendKeys(element,Keys.chord(cs),FailureHandling.STOP_ON_FAILURE)
		}
		return this
	}

	public  MyElement  write_key_chord_promote(String str){
		isVisible(true)
		String[] words=str.split(" ")
		for(def word in words){
			CharSequence cs = word
			WebUI.sendKeys(element,Keys.chord(cs),FailureHandling.STOP_ON_FAILURE)
			WebUI.sendKeys(element,Keys.chord(Keys.ENTER),FailureHandling.STOP_ON_FAILURE)
		}

		return this
	}

	public  MyElement  write_slowly(String str){
		if(!(DriverFactory.getExecutedBrowser() == WebUIDriverType.FIREFOX_DRIVER )||
		!(DriverFactory.getExecutedBrowser() ==WebUIDriverType.FIREFOX_HEADLESS_DRIVER)){
			isVisible(true)
			WebUI.clearText(element)
			for(def chr in str){
				CharSequence cs = chr
				WebUI.sendKeys(element,Keys.chord(cs),FailureHandling.STOP_ON_FAILURE)
				Thread.sleep(GlobalVariable.G_Millisec)
			}
		}
		return this
	}


	public MyElement MouseOver(def text=""){
		try{
			isVisible(true,text)
			WebUI.mouseOver(element,FailureHandling.STOP_ON_FAILURE)
		}catch(Exception e){
			WebUI.scrollToElement(element,GlobalVariable.G_Wait)
			WebUI.mouseOver(element,FailureHandling.STOP_ON_FAILURE)

		}
		return this
	}

	public boolean isVisible(boolean fail=true,def text=""){
		WebHelper.verify_process_wait(isLogin)
		findElement()
		FailureHandling failure
		if(fail)
			failure=FailureHandling.STOP_ON_FAILURE
		else
			failure=FailureHandling.OPTIONAL
		verifyText(text)
		return WebUI.waitForElementVisible(element, GlobalVariable.G_Middle_Wait, failure )

	}

	public MyElement findElement() {
		try{
			if(isIframe){
				WebUiCommonHelper.switchToParentFrame(element)
				WebElement ele=WebUiCommonHelper.findWebElement(element,GlobalVariable.G_Wait)
				Point point = ele.getLocation()
				WebUI.switchToDefaultContent()
				if(point.y<WebHelper.WINDOW_SIZE)
					WebHelper.scroll()
				else
					WebHelper.scroll(point.y-WebHelper.HEADER)
			}

		}finally{
			if(isIframe)
				WebUI.switchToDefaultContent()
		}

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



