import com.server.RolesHelper

'check if all logos, titles, links and pictures on the page are correct '

RolesHelper.getCm_En()._check_Logos_Title_Links()

'set username and password, predifined in internal data by roles'
RolesHelper.getCm_En()._fill_UserName_Password()

'set language to english'
RolesHelper.getCm_En()._click_chooseLanguage()

'click on login button, authenticate'
RolesHelper.getCm_En()._click_LogIn_With_UserName_And_Password()

'set role to content manager'
RolesHelper.getCm_En()._select_View()


'submit form and go to Kms page'
RolesHelper.getCm_En()._Enter_to_Kms()

