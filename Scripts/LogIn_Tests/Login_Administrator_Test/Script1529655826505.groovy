import com.pages.LogIn_Page
import com.server.AppManager
import com.server.Enum_Language
import com.server.Enum_Role


LogIn_Page loginPage= AppManager.getInstance()
.getNavHelper()
.getLogin()
.authenticate_User(Enum_Language.ENGLISH, Enum_Role.ADMINISTRATOR)


loginPage.logIn_To_KMS()
.logout()


