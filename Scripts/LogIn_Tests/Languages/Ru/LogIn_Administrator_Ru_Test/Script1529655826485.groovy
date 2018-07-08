import com.server.RolesHelper



'check if all logos, titles, links and pictures on the page are correct '
RolesHelper.getAdmin_Ru()._check_Logos_Title_Links()

'set username and password, predifined in internal data by roles'
RolesHelper.getAdmin_Ru()._fill_UserName_Password()

'set language '
RolesHelper.getAdmin_Ru()._click_chooseLanguage()

'click on login button, authenticate'
RolesHelper.getAdmin_Ru()._click_LogIn_With_UserName_And_Password()

'set role '
RolesHelper.getAdmin_Ru()._select_View()


'submit form and go to Kms page'
RolesHelper.getAdmin_Ru()._Enter_to_Kms()