import java.nio.file.Files as Files
import com.system.Compare_Images as Compare_Images
import com.system.RolesHelper as RolesHelper

'set username and password, predifined in internal data by roles'
RolesHelper.getCm_En().getLoginPage()._fill_UserName_Password()

'set language to english'
RolesHelper.getCm_En().getLoginPage()._click_chooseLanguage()

'verify visual login form'

//Compare_Images.detailed_comparison('login form',true)
'click on login button, authenticate'
RolesHelper.getCm_En().getLoginPage()._click_LogIn_With_UserName_And_Password()

'set role to content manager'
RolesHelper.getCm_En().getLoginPage()._select_View()

'verify visual chosing role form'

//Compare_Images.detailed_comparison('chosing role form',true)
'submit form and go to Kms page'
RolesHelper.getCm_En().getLoginPage()._Enter_to_Kms()

