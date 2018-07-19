import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.system.Compare_Images as Compare_Images
import com.system.RolesHelper as RolesHelper
import com.system.enums.Enum_Status as Enum_Status
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
RolesHelper.getCm_En()._Login_En()

'Right click the last folder and choose "New Item" '
RolesHelper.getCm_En().getSide_Bar()._create_new_item_by_right_cick()

'choose template  '
RolesHelper.getCm_En().getGeneral()._choose_Template_By_Typing(true)

'fill property tab '
RolesHelper.getCm_En().getGeneral().getItem_property_tab_general()._set_Item_Name()

'Click Save and verify if saved in last folder'
RolesHelper.getCm_En().getBottom_Bar()._click_save()

'Click Delete by id and verify if deleted from last folder'
RolesHelper.getCm_En().getSide_Bar()._delete_by_id()

'click home page'
RolesHelper.getCm_En().getUpper_bar()._click_Home_Button()

'logout from system'
RolesHelper.getCm_En().getUpper_bar()._logout()

'close browser'
WebUI.closeBrowser()

