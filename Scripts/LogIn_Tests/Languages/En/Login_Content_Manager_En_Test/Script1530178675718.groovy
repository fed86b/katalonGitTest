import java.nio.file.Files as Files
import com.system.CompareImages as CompareImages
import com.system.RolesHelper as RolesHelper

'set username and password, predifined in internal data by roles'
RolesHelper.getCm_En().getLoginPage().fillUserNamePassword()

'set language to english'
RolesHelper.getCm_En().getLoginPage().clickСhooseLanguage()

'verify visual login form'

CompareImages.detailedComparison('login form',true)
'click on login button, authenticate'
RolesHelper.getCm_En().getLoginPage().clickLogInWithUserNameAndPassword()

'set role to content manager'
RolesHelper.getCm_En().getLoginPage().selectView()

'verify visual chosing role form'

CompareImages.detailedComparison('chosing role form',true)
'submit form and go to Kms page'
RolesHelper.getCm_En().getLoginPage().enterToKms()

