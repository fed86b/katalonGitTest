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

'create new item by clicking on CREATE NEW ITEM button'
RolesHelper.getCm_En().getSide_Bar()._click_upper_button_create_item()

'choose template  '
RolesHelper.getCm_En().getBriefing()._choose_Template_By_Typing()

'fill property tab'
RolesHelper.getCm_En().getBriefing().getItem_property_tab_Briefing()._fill_property_tab()

'sed updated date'
RolesHelper.getCm_En().getBriefing().getEdit_Tab()._choose_Date_Edit_Item()

'save to last folder'
RolesHelper.getCm_En().getBottom_Bar()._click_save_and_relocate_to_lastFolder()

'verify if number of items was incremented in last folder'
RolesHelper.getCm_En()._click_save_and_check_if_number_of_items_incremented_in_lastFolder()

'find item by each of description word in the search dropdown list'
RolesHelper.getCm_En().getUpper_bar()._search_by_first_description_word()

'delete all items'
RolesHelper.getCm_En()._delete_all_created_items()

'click home page'
RolesHelper.getCm_En().getUpper_bar()._click_Home_Button()

'logout from system'
RolesHelper.getCm_En().getUpper_bar()._logout()

'close browser'
not_run: WebUI.closeBrowser()

