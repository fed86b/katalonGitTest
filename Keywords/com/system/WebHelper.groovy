package com.system

import static com.kms.katalon.core.testdata.TestDataFactory.findTestData

import java.nio.file.Paths;
import java.text.DateFormat
import java.text.SimpleDateFormat

import org.junit.After

import com.kms.katalon.core.exception.StepFailedException
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.driver.WebUIDriverType
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.system.enums.EnumCreateItem
import com.system.enums.EnumLoginData
import com.system.enums.EnumTables

import internal.GlobalVariable

public  class WebHelper {
	static private final int DEFAULT=1
	static private final int START=0
	static private final int END=220
	static private final int HEADER=300
	static private final int WINDOW_SIZE=500

	static MyElement progress=new MyElement("progress","//div[@id = 'progress-overlay']")
	static private DateFormat dateFormat_simple = new SimpleDateFormat("dd/MM/yyyy")
	static private DateFormat dateFormat_h_m_s = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss")
	static MyElement tree_dots=new MyElement("tree_dots","//span[@class = 'action-bar__button-icon glyphicon glyphicon-option-horizontal']")

	public static delay(){
		WebUI.delay(GlobalVariable.G_Small_Wait)
	}

	public  static scroll(int pos=START){
		if(pos==START)
			WebUI.scrollToPosition(START,START)
		else
			WebUI.scrollToPosition(START,pos,FailureHandling.STOP_ON_FAILURE)
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

	public static catchException(Exception e,def fail=true){
		def errorDesc=e.getMessage()
		def error = clean_path(errorDesc)
		WebUI.takeScreenshot(getLocation(error))
		if(fail){
			WebUI.closeBrowser(FailureHandling.OPTIONAL)
			throw e
		}
		if(e instanceof NullPointerException ){
			println("-----------------NullPointerException------------------")
		}else
		if(e instanceof StepFailedException ){
			println("-----------------StepFailedException------------------")
		}else{
			println("-----------------Not recoginezed Exception: "+e.getClass().getName()+"------------------")
		}
		WebUI.closeBrowser(FailureHandling.OPTIONAL)
	}

	public static String clean_path(String error_Destination) {
		String fixed_error=error_Destination.replaceAll("[\\<\\>\\:\\/\\|\\?\\*\" ]", "_")
		String withoutWords=fixed_error.replace("\n"," ")
		def error=withoutWords
		if(withoutWords.length()>END)
			error=withoutWords.substring(START, END)
		return error
	}

	public static Run_Test(TestCase test){
		WebUI.callTestCase(test, [:],FailureHandling.STOP_ON_FAILURE)
	}

	public static getCurDir(){
		return Paths.get(".").toAbsolutePath().normalize().toString();
	}

	public static getLocation(def img_name){
		def folder=DriverFactory.getExecutedBrowser().getName()
		new File('Reports/'+folder).mkdirs();
		return String.format('Reports/%s/%s.png', folder,img_name)
	}

	public static def IsProcessWait(boolean login=false){
		def wasProcess=false
		if(!login){
			def times=0
			try {

				if(WebUI.waitForElementPresent(progress, GlobalVariable.G_Wait, FailureHandling.OPTIONAL))
					while(!WebUI.waitForElementNotVisible(progress, GlobalVariable.G_Small_Wait, FailureHandling.OPTIONAL)&&times<GlobalVariable.G_Wait){
						delay()
						times+=1
						wasProcess=true
					}
			} catch (Exception e) {	}
			if(times>=GlobalVariable.G_Wait){
				throw new Exception(String.format("Extremely long loading time : %d sec",times))
			}
		}
		return wasProcess
	}

	public static  wait_for_Edge() {
		if (DriverFactory.getExecutedBrowser() == WebUIDriverType.EDGE_DRIVER){
			delay_medium()
		}
	}

	public static  if_ie() {
		return DriverFactory.getExecutedBrowser() == WebUIDriverType.IE_DRIVER
	}

	public static   verifyTexts(String sourseText,String targetText){
		WebUI.verifyEqual(sourseText.toLowerCase(), targetText.toLowerCase(), FailureHandling.CONTINUE_ON_FAILURE)
	}

	public static String getDate(Date d=new Date()){
		return dateFormat_simple.format(d)
	}

	public static String getDate(String d){
		return dateFormat_simple.format(dateFormat_simple.parse(d))
	}

	public static String addToDatenowDays(int days){
		Date now=new Date()
		now.setDate(now.getDay()+days)
		return dateFormat_h_m_s.format(now)
	}

	public static String get_auth(EnumLoginData col_name, int row){
		return findTestData(EnumTables.Login_Data.toString()).getValue(col_name.toString(), row)
	}

	public static String get_item_data(EnumCreateItem col_name, int row=1){

		return findTestData(EnumTables.Create_Item.toString()).getValue(col_name.toString(), row)
	}
}
