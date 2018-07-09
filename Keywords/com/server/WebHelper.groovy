package com.server

import static com.kms.katalon.core.testdata.TestDataFactory.findTestData

import java.text.DateFormat
import java.text.SimpleDateFormat

import org.openqa.selenium.Keys as Keys

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.driver.WebUIDriverType
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.server.enums.Enum_Create_Item
import com.server.enums.Enum_Language
import com.server.enums.Enum_Login_Data
import com.server.enums.Enum_Tables

import internal.GlobalVariable

public  class WebHelper {
	static private final int DEFAULT=1
	static private final int START=0
	static private final int END=220
	static private DateFormat dateFormat_simple = new SimpleDateFormat("dd/MM/yyyy")
	static private DateFormat dateFormat_h_m_s = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss")
	static MyElement tree_dots=new MyElement("tree_dots","//span[@class = 'action-bar__button-icon glyphicon glyphicon-option-horizontal']")


	public static delay(){
		WebUI.delay(GlobalVariable.G_Small_Wait)
	}

	public static check_three_dots(){

		if(WebUI.waitForElementVisible(tree_dots, GlobalVariable.G_Small_Wait, FailureHandling.OPTIONAL)){
			tree_dots.MouseOver()
			return true
		}
		return false
	}

	public static delay_medium(){
		WebUI.delay(GlobalVariable.G_Middle_Wait)
	}
	public static verify_ints(int actual,int expected){
		WebUI.verifyEqual(actual, expected,FailureHandling.CONTINUE_ON_FAILURE)
	}



	public static screenShoot(String error_Destination){
		String fixed_error=error_Destination.replaceAll("[\\<\\>\\:\\/\\|\\?\\*\" ]", " ")
		String withoutWords=fixed_error.replace("\n"," ")
		def error
		if(withoutWords.length()>END)
			error=withoutWords.substring(START, END)
		WebUI.takeScreenshot(getLocation(error+'.png'));
		WebUI.closeBrowser()
	}

	public static Run_Test(TestCase test){
		WebUI.callTestCase(test, [:],FailureHandling.STOP_ON_FAILURE)
	}

	public static getLocation(def file){
		def folder=DriverFactory.getExecutedBrowser().getName()
		//new File('Reports/'+folder).mkdirs();
		return String.format('Reports/%s/%s', folder,file)
	}

	public static  wait_for_Edge_ie() {
		if (DriverFactory.getExecutedBrowser() == WebUIDriverType.EDGE_DRIVER){
			delay_medium()
		}
	}

	public static   verify_texts(String sourseText,String targetText){
		delay()
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

	public static String get_auth(Enum_Login_Data col_name, int row){
		return findTestData(Enum_Tables.Login_Data.toString()).getValue(col_name.toString(), row)
	}
	public static String get_item_data(Enum_Create_Item col_name, int row=1){

		return findTestData(Enum_Tables.Create_Item.toString()).getValue(col_name.toString(), row)
	}
}
