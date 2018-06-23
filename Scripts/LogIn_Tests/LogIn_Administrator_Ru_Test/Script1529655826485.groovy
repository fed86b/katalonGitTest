import com.pages.LogIn_Page as LogIn_Page
import com.server.AppManager as AppManager
import com.server.Enum_Language as Enum_Language
import com.server.Enum_Role as Enum_Role
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

'Authenticate with username and password.\r\nChoose language Russian.'
LogIn_Page loginPage = AppManager.getInstance()
.getNavHelper()
.getLogin()
.authenticate_User(Enum_Language.RUSSIAN, Enum_Role.ADMINISTRATOR)

'Choose Administrator and submit'
loginPage.logIn_To_KMS()


