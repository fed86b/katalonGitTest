package com.system
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import java.awt.image.BufferedImage

import javax.imageio.ImageIO

import org.openqa.selenium.Keys as Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.driver.WebUIDriverType
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.system.enums.Enum_Position

import internal.GlobalVariable
import ru.yandex.qatools.ashot.AShot
import ru.yandex.qatools.ashot.Screenshot
import ru.yandex.qatools.ashot.comparison.ImageDiff
import ru.yandex.qatools.ashot.comparison.ImageDiffer

public  class Login_Element extends MyElement {
	public Login_Element(String element_name,String xpath,boolean isLogin=true){
		super(element_name,xpath,isLogin)
	}

	public Login_Element(TestObject element, boolean isLogin=true){
		super(element,isLogin)
	}
}


public  class MyElement extends TestObject {

	boolean isLogin
	TestObject element
	Enum_Position position=Enum_Position.NONE

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

	public boolean verifyText(String text,boolean full_compare=true) {
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

	public MyElement click_until_not_appear(MyElement item){
		for(def i=0;i<GlobalVariable.G_Middle_Wait;i++){
			if(!item.isVisible(false))
				click_with_delay()
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
			findElement()
			WebUI.selectOptionByIndex(element, index, FailureHandling.STOP_ON_FAILURE)
		}
		return this
	}


	public MyElement  click_with_hover(String text=""){
		try{
			MouseOver(text)
			WebUI.click(element,FailureHandling.STOP_ON_FAILURE)
		}catch(Exception e){
			findElement()
			WebUI.click(element,FailureHandling.STOP_ON_FAILURE)
		}
		return this
	}


	public MyElement  click(def text=""){
		try{
			isVisible(true,text)
			WebUI.click(element,FailureHandling.STOP_ON_FAILURE)
		}catch(Exception e){
			findElement()
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
			findElement()
			if(!WebUI.verifyElementChecked(element, GlobalVariable.G_Small_Wait, FailureHandling.OPTIONAL))
				WebUI.check(element,FailureHandling.STOP_ON_FAILURE)
		}
		return this
	}

	public MyElement  uncheck(){
		try{
			MouseOver()
			if(WebUI.verifyElementChecked(element, GlobalVariable.G_Small_Wait, FailureHandling.OPTIONAL))
				WebUI.uncheck(element,FailureHandling.STOP_ON_FAILURE)
		}catch(Exception e){
			findElement()
			if(WebUI.verifyElementChecked(element, GlobalVariable.G_Small_Wait, FailureHandling.OPTIONAL))
				WebUI.uncheck(element,FailureHandling.STOP_ON_FAILURE)
		}
		return this
	}

	public MyElement modify(def xpath){
		WebUI.modifyObjectProperty(element, 'xpath', 'equals', xpath, true)
		return this
	}


	public MyElement compareImages(def name, MyElement iframe=null){
		if(iframe!=null)
			WebUI.switchToFrame(iframe, GlobalVariable.G_Middle_Wait)
		WebUI.switchToFrame(findTestObject('Object Repository/Kms_Page_OR/Roles/Content_Manager/iframe_itemscope'), GlobalVariable.G_Middle_Wait)
		WebElement webEl=WebUiCommonHelper.findWebElement(element,GlobalVariable.G_Wait)
		WebDriver  driver =DriverFactory.getWebDriver()
		def path=String.format("%s\\img\\%s", System.getProperty("user.dir"),name)

		BufferedImage expectedImage=ImageIO.read(new File(path))
		Screenshot img=new AShot().takeScreenshot(driver,webEl)
		BufferedImage actualImage=img.getImage();
		ImageDiffer imgDiff=new ImageDiffer();
		ImageDiff diff=imgDiff.makeDiff(expectedImage,actualImage)
		def flag=WebUI.verifyEqual(true, diff.hasDiff(), FailureHandling.OPTIONAL)
		KeywordUtil.markWarning(String.format("Comparing %s images : %s",name,flag))
		WebUI.switchToDefaultContent()
		return this
	}

	public MyElement  click_with_delay(def text=""){
		try{
			delay()
			isVisible(true,text)
			WebUI.click(element,FailureHandling.STOP_ON_FAILURE)
			delay()
		}catch(Exception e){
			findElement()
			WebUI.click(element,FailureHandling.STOP_ON_FAILURE)
		}
		return this
	}

	public MyElement   double_click(def text=""){
		try{
			isVisible(true,text)
			WebUI.click(element,FailureHandling.STOP_ON_FAILURE)
			delay()
			WebUI.click(element,FailureHandling.STOP_ON_FAILURE)
		}catch(Exception e){
			findElement()
			WebUI.click(element,FailureHandling.STOP_ON_FAILURE)
			delay()
			WebUI.click(element,FailureHandling.STOP_ON_FAILURE)
		}
		return this
	}

	public String generate_Name(){
		try{
			isVisible()
		}catch(Exception e){
			findElement()
			WebUI.sendKeys(element,Keys.chord(cs),FailureHandling.STOP_ON_FAILURE)
		}
		return WebUI.getText(element,FailureHandling.STOP_ON_FAILURE)
	}

	public MyElement  write_text(String str){
		try{
			isVisible()
			WebUI.clearText(element,FailureHandling.STOP_ON_FAILURE)
			WebUI.setText(element, str,FailureHandling.STOP_ON_FAILURE)
		}catch(Exception e){
			findElement()
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
			findElement()
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
		if(!DriverFactory.getExecutedBrowser() == WebUIDriverType.FIREFOX_DRIVER ||
		!DriverFactory.getExecutedBrowser() ==WebUIDriverType.FIREFOX_HEADLESS_DRIVER){
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
		isVisible(true,text)
		WebUI.mouseOver(element,FailureHandling.STOP_ON_FAILURE)
		return this
	}

	public boolean isVisible(boolean fail=true,def text=""){
		WebHelper.verify_process_wait(isLogin)
		FailureHandling failure
		if(fail)
			failure=FailureHandling.STOP_ON_FAILURE
		else
			failure=FailureHandling.OPTIONAL
		verifyText(text)
		return WebUI.waitForElementVisible(element, GlobalVariable.G_Middle_Wait, failure )

	}


	public MyElement findElement() {
		WebUI.scrollToElement(element, GlobalVariable.G_Small_Wait,FailureHandling.STOP_ON_FAILURE)
		WebUI.waitForElementVisible(element, GlobalVariable.G_Middle_Wait, FailureHandling.STOP_ON_FAILURE)
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



