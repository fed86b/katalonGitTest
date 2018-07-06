import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.server.RolesHelper as RolesHelper
import com.server.WebHelper as WebHelper
import com.server.enums.Enum_Status as Enum_Status
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

'create new item by clicking on CREATE NEW ITEM button'
RolesHelper.getCm_En().briefing.click_upper_button_create_item()

'choose template  '
RolesHelper.getCm_En().briefing.choose_Briefing_Template_By_Typing()

'verify template and updated time'
RolesHelper.getCm_En().briefing.verify_template_updateDate()

'set name for Item  '
RolesHelper.getCm_En().briefing.set_Item_Name()

'write short description'
RolesHelper.getCm_En().briefing.write_short_description()

'choose status  '
RolesHelper.getCm_En().briefing.choose_Status(Enum_Status.ONLINE)

'set date from when item status will be valid'
RolesHelper.getCm_En().briefing.set_date_valid_from_status()

'choose status after'
RolesHelper.getCm_En().briefing.choose_status_after(Enum_Status.HIDDEN)

'set date when item status will be valid to status after'
RolesHelper.getCm_En().briefing.set_date_valid_to_status()

'go to Edit tab '
RolesHelper.getCm_En().briefing.click_edit_tab()

'choose  date time in edit tab '
RolesHelper.getCm_En().briefing.choose_Date_Edit_Item()

'save to last folder'
RolesHelper.getCm_En().briefing.click_save_and_relocate_to_lastFolder()

'check if number of items was incremented in last folder'
RolesHelper.getCm_En().briefing.click_save_and_check_if_number_of_items_incremented_in_lastFolder()

'click home page'
RolesHelper.getCm_En().click_Home_Button()

'delete all items'
RolesHelper.getCm_En().delete_all_created_items_en()

'logout from system'
RolesHelper.getCm_En().logout()

'close browser'
WebUI.closeBrowser()

