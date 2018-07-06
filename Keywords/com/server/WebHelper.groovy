package com.server
import java.io.File
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData

import java.text.DateFormat
import java.text.SimpleDateFormat

import org.openqa.selenium.Keys as Keys

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.driver.WebUIDriverType
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.server.enums.Enum_Create_Item
import com.server.enums.Enum_Login_Data
import com.server.enums.Enum_Tables

import internal.GlobalVariable

public  class WebHelper {
	static def tree_dots=createElement("tree_dots","//span[@class = 'action-bar__button-icon glyphicon glyphicon-option-horizontal']")
	//static def progress=createElement("progress","//div[@id = 'progress-overlay']")
	static def progress=createElement("progress","//div[@id = 'progress-icon']")
	static private final int DEFAULT=1
	static private final int START=0
	static private final int END=220
	static private DateFormat dateFormat_simple = new SimpleDateFormat("dd/MM/yyyy")
	static private DateFormat dateFormat_h_m_s = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss")
	public static acceptAlert(){
		if(WebUI.verifyAlertPresent(GlobalVariable.G_Middle_Wait))
			WebUI.acceptAlert()
	}

	public static submit(TestObject element){
		WebUI.submit(element)
	}

	public static press_Enter(TestObject element){
		WebUI.sendKeys(element,Keys.chord(Keys.ENTER) )
	}

	public static press_Del(TestObject element){
		WebUI.sendKeys(element,Keys.chord(Keys.DELETE))
	}

	public static press_Esc(TestObject element){
		WebUI.sendKeys(element,Keys.chord(Keys.ESCAPE) )
	}
	public static press_tab(TestObject element){
		WebUI.sendKeys(element,Keys.chord(Keys.TAB) )

	}

	public static screenShoot(String error_Destination){
		String fixed_error=error_Destination.replaceAll("[\\<\\>\\:\\/\\|\\?\\*\" ]", " ")
		String withoutWords=fixed_error.replace("\n", " ")
		def error
		if(withoutWords.length()>END)
			error=withoutWords.substring(START, END)
		def folder_webDriver=DriverFactory.getExecutedBrowser().getName()
		new File("\\Reports\\"+folder_webDriver).mkdir()
		WebUI.takeScreenshot(String.format("Reports/%s/%s.png",folder_webDriver,error));
	}

	public static boolean check_three_dots(){

		if(WebUI.waitForElementVisible(tree_dots, GlobalVariable.G_Small_Wait, FailureHandling.OPTIONAL)){
			verify_text_MouseOver(tree_dots)
			return true
		}
		return false
	}



	public static verify_ints(int actual,int expected){
		WebUI.verifyEqual(actual, expected,FailureHandling.STOP_ON_FAILURE)
	}

	public static delay(){
		WebUI.delay(GlobalVariable.G_Small_Wait)
	}

	public static delay_medium(){
		WebUI.delay(GlobalVariable.G_Middle_Wait)
	}
	public static  boolean  verify_text_visibility(TestObject element,String text="", boolean fail=false){
		boolean visible=false
		try{
			WebUI.scrollToPosition(WebUI.getViewportLeftPosition(),WebUI.getViewportTopPosition())
			if(fail){
				visible= WebUI.waitForElementVisible(element, GlobalVariable.G_Middle_Wait, FailureHandling.STOP_ON_FAILURE)
			}
			else
				visible= WebUI.waitForElementVisible(element, GlobalVariable.G_Middle_Wait, FailureHandling.OPTIONAL)
			if(visible&&!text.isEmpty())
				verify_text(element, text)
		} catch (Exception e) {
			delay_medium()
			findElement(element)
			visible= WebUI.waitForElementVisible(element, GlobalVariable.G_Middle_Wait, FailureHandling.STOP_ON_FAILURE)
		}
		return visible
	}

	public static modify_xpath(TestObject element,String xpath){
		WebUI.modifyObjectProperty(element, 'xpath', 'equals', xpath, false)
	}

	private static is_firefox_ie(TestObject element,String str){
		return (DriverFactory.getExecutedBrowser() == WebUIDriverType.FIREFOX_DRIVER||
				DriverFactory.getExecutedBrowser() == WebUIDriverType.FIREFOX_HEADLESS_DRIVER||
				DriverFactory.getExecutedBrowser() == WebUIDriverType.IE_DRIVER)
	}



	private static findElement(TestObject element) {
		try {
			WebUI.scrollToElement(element, GlobalVariable.G_Middle_Wait, FailureHandling.CONTINUE_ON_FAILURE)
		} catch (Exception ex) {
			WebUI.scrollToPosition(WebUI.getElementLeftPosition(element, FailureHandling.CONTINUE_ON_FAILURE), WebUI.getPageHeight()*0.8)
		}
	}

	public static verify_text(TestObject element, String text) {
		if (!DriverFactory.getExecutedBrowser() == WebUIDriverType.EDGE_DRIVER)
			return WebUI.verifyElementText(element,text,FailureHandling.CONTINUE_ON_FAILURE)
		return true
	}

	public static select_by_index(TestObject element,int index){
		try {
			WebUI.selectOptionByIndex(element,index)
		} catch (Exception e) {
			delay_medium()
			WebUI.scrollToElement(element, GlobalVariable.G_Middle_Wait, FailureHandling.CONTINUE_ON_FAILURE)
			WebUI.selectOptionByIndex(element,index)
		}
	}

	public static verify_text_clicability(TestObject element,String text=""){
		verify_process_wait()
		wait_for_Edge_ie()
		try{
			WebUI.scrollToPosition(WebUI.getViewportLeftPosition(),WebUI.getViewportTopPosition())
			WebUI.waitForElementClickable(element, GlobalVariable.G_Middle_Wait, FailureHandling.STOP_ON_FAILURE)
			if(!text.isEmpty())
				verify_text(element, text)
		} catch (Exception e) {
			delay_medium()
			findElement(element)
			WebUI.waitForElementClickable(element, GlobalVariable.G_Middle_Wait, FailureHandling.STOP_ON_FAILURE)
		}
	}

	private static wait_for_Edge_ie() {
		if (!DriverFactory.getExecutedBrowser() == WebUIDriverType.EDGE_DRIVER
		||DriverFactory.getExecutedBrowser() == WebUIDriverType.IE_DRIVER){
			WebUI.delay(GlobalVariable.G_Small_Wait)
		}
	}

	public static verify_text_click_with_hover(TestObject element,String text=""){
		try {
			this.verify_text_MouseOver(element,text)
			WebUI.click(element)
		} catch (Exception e) {
			delay_medium()
			this.verify_text_MouseOver(element)
			WebUI.click(element)
		}
	}

	public static verify_text_click(TestObject element,String text=""){
		try {

			verify_text_clicability(element, text)
			WebUI.click(element)
		} catch (Exception e) {
			delay_medium()
			WebUI.click(element)
		}
	}


	public static verify_image_click(TestObject element,String text=""){
		try {
			verify_text_clicability(element, text)
			WebUI.clickImage(element)
		} catch (Exception e) {
			delay_medium()
			WebUI.clickImage(element)
		}
	}

	public static   verify_text_click_with_Delay(TestObject element,String text=""){
		try {
			delay()
			verify_text_clicability(element, text)
			WebUI.mouseOver(element, FailureHandling.STOP_ON_FAILURE)
			WebUI.click(element)
			delay_medium()
		} catch (Exception e) {
			delay_medium()
			WebUI.click(element)
		}
	}

	public static   verify_text_double_click(TestObject element,String text=""){
		try {
			verify_text_click_with_Delay(element)
			WebUI.click(element)
		} catch (Exception e) {
			delay_medium()
			verify_text_click_with_Delay(element)
			WebUI.click(element)
		}
	}

	public static String generate_Name(TestObject element){

		return WebUI.getText(element, FailureHandling.CONTINUE_ON_FAILURE)
	}

	public static   write_text(TestObject element,String text){
		try {
			verify_text_visibility(element)
			WebUI.clearText(element)
			WebUI.setText(element, text)
		} catch (Exception e) {
			delay_medium()
			WebUI.setText(element, text)
		}
	}

	//for ie and firefox
	public static   write_key_chord(TestObject element,String text){
		CharSequence cs = text
		try {
			verify_text_visibility(element)

			WebUI.sendKeys(element,Keys.chord(cs) )
		} catch (Exception e) {
			delay_medium()
			WebUI.sendKeys(element,Keys.chord(cs) )
		}
	}

	private static verify_process_wait(){
		try {
			WebUI.waitForElementNotVisible(progress, GlobalVariable.G_Small_Wait, FailureHandling.OPTIONAL)
		} catch (Exception e) {	}
	}

	public static TestObject createElement(String objectName,String xpath,boolean active=true){
		TestObject element=new TestObject(objectName)
		element.addProperty("xpath", ConditionType.EQUALS, xpath)
		return element
	}


	public static   verify_texts(String sourseText,String targetText){

		WebUI.verifyEqual(sourseText.toLowerCase(), targetText.toLowerCase(), FailureHandling.CONTINUE_ON_FAILURE)
	}


	public static String get_date(Date d=new Date()){
		return dateFormat_simple.format(d)
	}

	public static String get_date(String d){

		return dateFormat_simple.format(dateFormat_simple.parse(d))
	}

	public static String add_to_dateNow_day(int days){
		Date now=new Date()

		now.setDate(now.getDay()+days)
		return dateFormat_h_m_s.format(now)
	}

	public static   verify_text_MouseOver(TestObject element,String text=""){
		try {
			verify_text_visibility(element, text,true)
			WebUI.mouseOver(element, FailureHandling.STOP_ON_FAILURE)
		} catch (Exception e) {
			delay_medium()
			findElement(element)
			WebUI.mouseOver(element, FailureHandling.STOP_ON_FAILURE)
		}
	}




	public static String get_auth(Enum_Tables table,Enum_Login_Data col_name, int row){
		return findTestData(table.toString()).getValue(col_name.toString(), row)
	}
	public static String get_item_data(Enum_Tables table,Enum_Create_Item col_name, int row=1){
		return findTestData(table.toString()).getValue(col_name.toString(), row)
	}
}
