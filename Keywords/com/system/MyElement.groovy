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
import com.system.enums.EnumRole

import internal.GlobalVariable


public  class LoginElement extends MyElement {
	public LoginElement(String element_name,String xpath,boolean isLogin=true){
		super(element_name,xpath,isLogin)
	}

	public LoginElement(TestObject element, boolean isLogin=true){
		super(element,isLogin)
	}
}

public  class IframeElement extends MyElement {

	public IframeElement(String element_name,String xpath,
	TestObject iframe=findTestObject('Kms_Page_OR/Roles/Content_Manager/iframe_itemscope')){
		super(element_name,xpath,false,iframe)
	}
}

public  class MyElement extends TestObject {

	boolean isLogin
	TestObject element
	def isIframe=false
	def fail=false

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

	public MyElement clickUntilNotAppear(MyElement item,def text=""){
		MouseOver(text)
		for(def i=0;i<GlobalVariable.G_Middle_Wait;i++){
			try{
				//				def driver=DriverFactory.getExecutedBrowser()
				//				if(driver== WebUIDriverType.IE_DRIVER||driver== WebUIDriverType.EDGE_DRIVER )
				//					clickWithDelay(text)
				//				else
				//
				WebUI.click(element,FailureHandling.OPTIONAL)
				if(item.isVisible(false))
					return this
			}catch(Exception e){
				println("---------------------"+e.getClass().getName()+"-------------------")
			}
			if(i>=GlobalVariable.G_Middle_Wait)
				throw new Exception(String.format("The %d is not clickable",element.getObjectId()))
		}
		return this
	}

	public MyElement rightClickUntilNotAppear(MyElement item,def text=""){
		MouseOver(text)
		for(def i=0;i<GlobalVariable.G_Middle_Wait;i++){
			try{
				//				def driver=DriverFactory.getExecutedBrowser()
				//				if(driver== WebUIDriverType.IE_DRIVER||driver== WebUIDriverType.EDGE_DRIVER ){
				//					rightClickWithDelay(text)
				//					rightClickWithDelay()
				//				}
				//				else
				WebUI.rightClick(element,FailureHandling.OPTIONAL)
				//				def visible=WebUI.waitForElementVisible(item, GlobalVariable.G_Middle_Wait, FailureHandling.OPTIONAL)
				if(item.isVisible(false))
					return this
			}catch(Exception e){
				println("---------------------"+e.getClass().getName()+"-------------------")}
			if(i>=GlobalVariable.G_Middle_Wait)
				throw new Exception(String.format("The %d is not clickable",element.getObjectId()))
		}
		return this
	}

	public MyElement clickUntilNotDisappear(MyElement item=this,def text=""){
		isVisible(false,text)
		for(def i=0;i<GlobalVariable.G_Middle_Wait;i++){
			try{
				WebUI.click(element,FailureHandling.OPTIONAL)
				def notVisible=WebUI.waitForElementNotVisible(item, GlobalVariable.G_Middle_Wait, FailureHandling.OPTIONAL)||!item.isVisible(false)
				if(notVisible){return this}
			}catch(Exception e){
				println("---------------------"+e.getClass().getName()+"-------------------")}
			if(i>=GlobalVariable.G_Middle_Wait)
				throw new Exception(String.format("The %d is not clickable",element.getObjectId()))
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

	public MyElement  pressEsc(def text=""){
		isVisible(true,text)
		WebUI.sendKeys(element,Keys.chord(Keys.ESCAPE),FailureHandling.STOP_ON_FAILURE)
		return this
	}

	public MyElement  press_tab(def text=""){
		isVisible(true,text)
		WebUI.sendKeys(element,Keys.chord(Keys.TAB),FailureHandling.STOP_ON_FAILURE)
		return this
	}

	public MyElement  selectByIndex(int index){
		isVisible()
		WebUI.selectOptionByIndex(element, index, FailureHandling.STOP_ON_FAILURE)
		return this
	}

	public MyElement  clickWithHover(String text=""){
		MouseOver(text)
		WebUI.click(element,FailureHandling.STOP_ON_FAILURE)
		return this
	}

	public MyElement  right_click(def text=""){
		isVisible(true,text)
		WebUI.rightClick(element,FailureHandling.STOP_ON_FAILURE)
		return this
	}

	public MyElement  click(def text=""){
		isVisible(true,text)
		WebUI.click(element,FailureHandling.STOP_ON_FAILURE)
		return this
	}

	public MyElement  check(){
		MouseOver()
		def times=0
		while(!WebUI.verifyElementChecked(element, GlobalVariable.G_Small_Wait, FailureHandling.OPTIONAL)
		&&times<GlobalVariable.G_Middle_Wait){
			WebUI.check(element,FailureHandling.STOP_ON_FAILURE)
			times+=1
		}
		if(times>=GlobalVariable.G_Middle_Wait)
			throw new Exception(String.format("Unable check %s element", element.getObjectId()))
		return this
	}


	public MyElement  uncheck(){
		MouseOver()
		def times=0
		while(WebUI.verifyElementChecked(element, GlobalVariable.G_Small_Wait, FailureHandling.OPTIONAL)
		&&times<GlobalVariable.G_Middle_Wait){
			WebUI.uncheck(element,FailureHandling.STOP_ON_FAILURE)
			times+=1
		}
		if(times>=GlobalVariable.G_Middle_Wait)
			throw new Exception(String.format("Unable uncheck %s element", element.getObjectId()))
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

	public MyElement compareImages(def name="download.jpg",EnumRole role=EnumRole.CONTENT_MANAGER){
		isVisible(false)
		CompareImages.compareWebElementPic(element,role, name)
		return this
	}

	public MyElement  clickWithDelay(def text=""){
		delay()
		isVisible(true,text)
		WebUI.click(element,FailureHandling.STOP_ON_FAILURE)
		delay()
		return this
	}

	public MyElement  rightClickWithDelay(def text=""){
		delay()
		isVisible(true,text)
		WebUI.rightClick(element,FailureHandling.STOP_ON_FAILURE)
		delay()
		return this
	}

	public MyElement doubleClick(def text=""){
		isVisible(true,text)
		WebUI.click(element,FailureHandling.STOP_ON_FAILURE)
		delay()
		WebUI.click(element,FailureHandling.STOP_ON_FAILURE)
		return this
	}

	public MyElement quickDoubleClick(def text=""){
		isVisible(true,text)
		WebUI.doubleClick(element)
		return this
	}

	public String generateName(def hidden=false){
		def text=""
		if(hidden)
			text = getAtribute('value')
		else
			text=WebUI.getText(element,FailureHandling.STOP_ON_FAILURE)
		return text
	}

	public String getAtribute(def value){
		isVisible()
		return WebUI.getAttribute(element, value)
	}

	//WebUI.getAttribute(findTestObject('Page_CuraHomepage/btn_MakeAppointment'), 'class')

	public MyElement  writeText(String str){
		isVisible()
		WebUI.clearText(element,FailureHandling.STOP_ON_FAILURE)
		WebUI.setText(element, str,FailureHandling.STOP_ON_FAILURE)
		return this
	}

	//for ie and firefox
	public MyElement writeKeyChord(String str){
		CharSequence cs = str
		isVisible()
		WebUI.sendKeys(element,Keys.chord(cs),FailureHandling.STOP_ON_FAILURE)
		return this
	}

	public  MyElement  writeKeyChordPromote(String str){
		isVisible()
		String[] words=str.split(" ")
		for(def word in words){
			CharSequence cs = word
			WebUI.sendKeys(element,Keys.chord(cs),FailureHandling.STOP_ON_FAILURE)
			WebUI.sendKeys(element,Keys.chord(Keys.ENTER),FailureHandling.STOP_ON_FAILURE)
		}

		return this
	}

	public  MyElement  writeSlowly(def cs){
		isVisible()
		WebUI.clearText(element)
		WebUI.sendKeys(element,Keys.chord(cs),FailureHandling.STOP_ON_FAILURE)
		Thread.sleep(GlobalVariable.G_Small_Wait)
		return this
	}


	public MyElement MouseOver(def text=""){
		isVisible(true,text)
		WebUI.mouseOver(element,FailureHandling.STOP_ON_FAILURE)
		return this
	}

	public MyElement MouseOverUntilNotVisible(MyElement item,def text=""){

		for(def i=0;i<GlobalVariable.G_Middle_Wait;i++){
			try{
				MouseOver()
				def visible=WebUI.waitForElementVisible(item, GlobalVariable.G_Middle_Wait, FailureHandling.OPTIONAL)
				if(visible)
					return this
			}catch(Exception e){
				println("---------------------"+e.getClass().getName()+"-------------------")
			}
			if(i>=GlobalVariable.G_Middle_Wait)
				throw new Exception(String.format("The %d is not hoverable",element.getObjectId()))
		}
		return this
	}


	public boolean isVisible(boolean fail=true,def text=""){
		try{
			WebHelper.IsProcessWait(isLogin)
			findElement()
			FailureHandling failure
			if(fail)
				failure=FailureHandling.STOP_ON_FAILURE
			else
				failure=FailureHandling.OPTIONAL
			verifyText(text)
			def flag= WebUI.waitForElementVisible(element, GlobalVariable.G_Middle_Wait, failure )
			return flag
		}catch(Exception e){
			WebUI.scrollToElement(element,GlobalVariable.G_Wait,FailureHandling.STOP_ON_FAILURE)
		}

	}

	public MyElement findElement() {
		try{
			if(isIframe){
				WebUiCommonHelper.switchToParentFrame(element)
				WebElement ele=WebUiCommonHelper.findWebElement(element,GlobalVariable.G_Wait)
				if(ele==null)
					return null
				Point point = ele.getLocation()
				WebUI.switchToDefaultContent()
				if(point.y<WebHelper.WINDOW_SIZE)
					WebHelper.scroll()
				else
					WebHelper.scroll(point.y-WebHelper.HEADER)
			}
		}
		finally{
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



