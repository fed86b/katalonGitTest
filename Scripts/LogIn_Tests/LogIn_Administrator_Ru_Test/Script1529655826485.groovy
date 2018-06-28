import com.server.AppManager as AppManager
import com.server.Enum_Language as Enum_Language
import com.server.Enum_Role as Enum_Role

'check if all logos, titles, links and pictures on the page are correct '
AppManager.getInstance().getNavHelper().getLogin().check_Logos_Title_Links()

'set username and password, predifined in internal data by roles'
AppManager.getInstance().getNavHelper().getLogin().fill_UserName_Password(Enum_Role.ADMINISTRATOR)

'set language to russian'
AppManager.getInstance().getNavHelper().getLogin().chooseLanguage(Enum_Language.RUSSIAN)

'click on login button, authenticate'
AppManager.getInstance().getNavHelper().getLogin().click_LogIn_With_UserName_And_Password()

'set role to administrator'
AppManager.getInstance().getNavHelper().getLogin().select_View(Enum_Role.ADMINISTRATOR)

'check layout title'
AppManager.getInstance().getNavHelper().getLogin().check_Layout_Title(Enum_Language.RUSSIAN)

'submit form and go to Kms page'
AppManager.getInstance().getNavHelper().getLogin().click_Submit(Enum_Language.RUSSIAN)

'logout from system'
AppManager.getInstance().getNavHelper().getKms().init_Kms_Page(Enum_Role.ADMINISTRATOR, Enum_Language.RUSSIAN).logout()

