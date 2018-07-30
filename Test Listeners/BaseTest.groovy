import org.apache.commons.io.FileUtils

import com.kms.katalon.core.annotation.AfterTestCase
import com.kms.katalon.core.annotation.AfterTestSuite
import com.kms.katalon.core.annotation.BeforeTestCase
import com.kms.katalon.core.annotation.BeforeTestSuite
import com.kms.katalon.core.context.TestCaseContext
import com.kms.katalon.core.context.TestSuiteContext
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.system.AppManager
class BaseTest {


	@BeforeTestSuite
	def beforeTestSuite(TestSuiteContext testSuite){

		KeywordUtil.markWarning("Before Test Suite Listener : " + testSuite.getTestSuiteId())
	}

	@BeforeTestCase
	def beforeTestCase(TestCaseContext testCase) {
		KeywordUtil.markWarning("Before Test case : " + testCase.getTestCaseId())
		AppManager.instance
		
	}


	@AfterTestCase
	def afterTestCase(TestCaseContext testCase){
		//		'login as content manager'
		//		RolesHelper.getCm_En().Login()
		//		if(testCase.getTestCaseId().contains("Item_Creation"))
		//			WebUI.callTestCase(findTestCase( 'Test Cases/Kms_Tests/Item_Delete/Running_After_Tests'), [:], FailureHandling.STOP_ON_FAILURE)
		WebUI.switchToDefaultContent()

		
	}

	private clearData() {
		def dir="C:\\Users\\fed86\\AppData\\Local\\Temp"
		File f=new File(dir)
		File[] files = f.listFiles()
		String lowercaseName=""
		for (File file : files) {
			try{
				lowercaseName= file.name.toLowerCase()
				if (lowercaseName.startsWith("katalon")||lowercaseName.startsWith("anonymous")||lowercaseName.startsWith("scoped"))
					FileUtils.deleteDirectory(new File(dir+"\\"+file.name))
				else if(lowercaseName.endsWith(".tmp")||lowercaseName.endsWith(".png")||lowercaseName.startsWith("aria-debug"))
					file.delete()
			}catch(Exception e){}
		}
	}


	@AfterTestSuite
	def closeBrowser(TestSuiteContext testSuite) {
		//WebUI.callTestCase(findTestCase( 'Test Cases/Kms_Tests/Item_Delete/Delete_Items_Test_En'), [:], FailureHandling.STOP_ON_FAILURE)
		//		if("FAILED".equalsIgnoreCase(testSuite.getStatus()))
		//			WebUI.takeScreenshot()
		AppManager.getInstance().dismiss()
		KeywordUtil.markWarning("After Test Suite Listener : " + testSuite.getTestSuiteId())
		clearData()
	}
}







//
//		if("PASSED".equalsIgnoreCase(testCase.getTestCaseStatus())){
//			println WebUI.takeScreenshot()
//		}



//	private closeTab(int i) {
//		def tabs=(Map)GlobalVariable.G_Tabs
//		int  currentWindow =  WebUI.getWindowIndex()
//		//close last opened tab and go to first tab
//		if(tabs.size()>1){
//			int index=tabs.indexOf(currentWindow)
//			tabs.remove(currentWindow)
//			int indexToMove=0;
//			for(int i=0;i<tabs.size();i++)
//				if(tabs.containsKey(i)){
//					indexToMove=i
//					break
//				}
//			WebUI.switchToWindowIndex(i)
//			WebUI.closeWindowIndex(currentWindow)
//		}
//	}