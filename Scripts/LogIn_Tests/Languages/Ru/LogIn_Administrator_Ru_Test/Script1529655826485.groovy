import com.pages.LogIn_Page as LogIn_Page
import com.server.enums.Enum_Language as Enum_Language
import com.server.enums.Enum_Role as Enum_Role

'check if all logos, titles, links and pictures on the page are correct '
LogIn_Page.check_Logos_Title_Links()

'set username and password, predifined in internal data by roles'
LogIn_Page.fill_UserName_Password(Enum_Role.ADMINISTRATOR)

'set language to english'
LogIn_Page.chooseLanguage(Enum_Language.RUSSIAN)

'click on login button, authenticate'
LogIn_Page.click_LogIn_With_UserName_And_Password()

'check layout title'
LogIn_Page.check_Layout_Title(Enum_Language.RUSSIAN)

'set role to administrator'
LogIn_Page.select_View(Enum_Role.ADMINISTRATOR)


'submit form and go to Kms page'
LogIn_Page.click_Submit(Enum_Language.RUSSIAN)

