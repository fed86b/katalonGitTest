import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.system.CompareImages as CompareImages
import com.system.RolesHelper as RolesHelper
import com.system.enums.EnumStatus as EnumStatus
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import internal.GlobalVariable as GlobalVariable

'login as content manager'
RolesHelper.getCm_En().Login_En()

'Right click the last folder and choose "New Item" '
RolesHelper.getCm_En().getItemsTree().newItemAction()

'choose template  '
RolesHelper.getCm_En().getGeneral().chooseTemplateByTyping(true)

'set name'
RolesHelper.getCm_En().getGeneral().getItemPropertyTabGeneral().setItemName()

'set updated date'
RolesHelper.getCm_En().getGeneral().getGeneralTab().getHtmlEditor().clickEditHTMLContent()

'Click Save and verify if saved in last folder'
RolesHelper.getCm_En().getTaskBar().clickSave()

'Click Delete by id and verify if deleted from last folder'
RolesHelper.getCm_En().getItemsTree().deleteById()

'click home page'
RolesHelper.getCm_En().getTopToolBar().clickHomeButton()

'logout from system'
RolesHelper.getCm_En().getTopToolBar().logout()

'close browser'
WebUI.closeBrowser()

