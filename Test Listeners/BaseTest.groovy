import java.text.BreakIterator

import org.eclipse.persistence.jpa.jpql.parser.ElseExpressionBNF

import com.kms.katalon.core.annotation.AfterTestCase
import com.kms.katalon.core.annotation.AfterTestSuite
import com.kms.katalon.core.annotation.BeforeTestCase
import com.kms.katalon.core.annotation.BeforeTestSuite
import com.kms.katalon.core.context.TestCaseContext
import com.kms.katalon.core.context.TestSuiteContext
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.server.AppManager

import internal.GlobalVariable as GlobalVariable

class BaseTest {


	@BeforeTestSuite
	def beforeTestSuite(TestSuiteContext testSuite){
		KeywordUtil.markWarning("Before Test Suite Listener : " + testSuite.getTestSuiteId())
	}

	@BeforeTestCase
	def beforeTestCase(TestCaseContext testCase) {
		AppManager.getInstance()
		
		//for saving time load browser, only opening new tab
		if(WebUI.getUrl().startsWith(GlobalVariable.G_Url))
			goToNewTab(testCase)
			//if browser opened, but not in the kms host, navigate there
		else if(!WebUI.getUrl().contains('http'))
			WebUI.navigateToUrl(GlobalVariable.G_Url)
		KeywordUtil.markWarning("Before Test case : " + testCase.getTestCaseId())
	}


	@AfterTestCase
	def afterTestCase(TestCaseContext testCase){

		KeywordUtil.markWarning("After Test case : " + testCase.getTestCaseId())
	}


	@AfterTestSuite
	def closeBrowser(TestSuiteContext testSuite) {
		AppManager.getInstance().dismiss()
		KeywordUtil.markWarning("After Test Suite Listener : " + testSuite.getTestSuiteId())
	}

	private goToNewTab(TestCaseContext testCase) {
		WebUI.executeJavaScript('window.open();', [])
		def tabs=(Map)GlobalVariable.G_Tabs
		int  currentWindow =  WebUI.getWindowIndex()
		int newTabIndex=currentWindow + 1
		WebUI.switchToWindowIndex(newTabIndex)
		tabs.put( testCase.getTestCaseId(), newTabIndex)
		WebUI.navigateToUrl(GlobalVariable.G_Url)
		KeywordUtil.markWarning("Opened new tab with number "+newTabIndex+" on case : "+testCase.getTestCaseId() )
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