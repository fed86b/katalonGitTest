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
RolesHelper.getCm_En().side_Bar._click_upper_button_create_item()

'choose template  '
RolesHelper.getCm_En().general._choose_Template_By_Typing()

'verify template and updated time'
RolesHelper.getCm_En().general.item_property_tab_general._verify_template_updateDate()

'set name for Item  '
RolesHelper.getCm_En().general.item_property_tab_general._set_Item_Name()

'write short description'
RolesHelper.getCm_En().general.item_property_tab_general._write_short_description()

'choose status  '
RolesHelper.getCm_En().general.item_property_tab_general._choose_Status(Enum_Status.ONLINE)

'set date from when item status will be valid'
RolesHelper.getCm_En().general.item_property_tab_general._set_date_valid_from_status()

'choose status after'
RolesHelper.getCm_En().general.item_property_tab_general._choose_status_after(Enum_Status.ARCHIVE)

'set date when item status will be valid to status after'
RolesHelper.getCm_En().general.item_property_tab_general._set_date_valid_to_status()

'check active version'
RolesHelper.getCm_En().general.item_property_tab_general._check_active_version()

'set active version date from'
RolesHelper.getCm_En().general.item_property_tab_general._set_date_active_version()

'check print'
RolesHelper.getCm_En().general.item_property_tab_general._check_print(true)

'check email'
RolesHelper.getCm_En().general.item_property_tab_general._check_email(true)

'check feedback'
RolesHelper.getCm_En().general.item_property_tab_general._check_feedback(true)

'check bookmarks'
RolesHelper.getCm_En().general.item_property_tab_general._check_bookmarks(true)

'check fax'
RolesHelper.getCm_En().general.item_property_tab_general._check_fax(true)

'check show in search results'
RolesHelper.getCm_En().general.item_property_tab_general._check_search(true)

'check hit list'
RolesHelper.getCm_En().general.item_property_tab_general._check_hit(true)

'check likes'
RolesHelper.getCm_En().general.item_property_tab_general._check_likes(true)

'check follow'
RolesHelper.getCm_En().general.item_property_tab_general._check_follow(true)

'check comments'
RolesHelper.getCm_En().general.item_property_tab_general._check_comment(true)

'check add home page'
RolesHelper.getCm_En().general.item_property_tab_general._check_add_home_page(true)

'write sms description'
RolesHelper.getCm_En().general.item_property_tab_general._write_sms_description()

'browse image'
RolesHelper.getCm_En().general.item_property_tab_general._browse_file()

'click on image and verify'
RolesHelper.getCm_En().general.item_property_tab_general._click_on_image()

'write promoted item keywords'
RolesHelper.getCm_En().general.item_property_tab_general._write_promoted_key_words()

'save to last folder'
RolesHelper.getCm_En().bottom_Bar_CM._click_save_and_relocate_to_lastFolder()

'verify if number of items was incremented in last folder'
RolesHelper.getCm_En().bottom_Bar_CM._click_save_and_check_if_number_of_items_incremented_in_lastFolder()

'find item by each of description word in the search dropdown list'
RolesHelper.getCm_En().general._search_by_first_description_word()

'delete all items'
RolesHelper.getCm_En()._delete_all_created_items_en()

'click home page'
RolesHelper.getCm_En()._click_Home_Button()

'logout from system'
RolesHelper.getCm_En().upper_bar._logout()

'close browser'
WebUI.closeBrowser()

