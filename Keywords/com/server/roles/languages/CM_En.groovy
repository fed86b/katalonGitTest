package com.server.roles.languages

import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import com.server.WebHelper
import com.pages.cm.languages.Briefing_En
import com.server.TemplateHelper
import com.server.enums.Enum_Language
import com.server.roles.CM
public class CM_En extends CM  {

	static final Enum_Language lang=Enum_Language.ENGLISH
	static Briefing_En briefing


	protected CM_En(){
		super(lang)

		briefing= TemplateHelper.getBriefing_En()
	}

	static protected  _Login_En(){
		Run_Test(findTestCase('LogIn_Tests/Languages/En/Login_Content_Manager_En_Test'))
	}

	static protected  _Create_Briefing_Online_Test_En(){
		Run_Test(findTestCase('Test Cases/Kms_Tests/Languages/En/Item_Creation/Create_Briefing_Online_Test_En'))
	}

	protected static _delete_all_created_items_en(){
		try{
			delete_all_created_items("Yes")
		}
		catch (Exception e) {
			WebHelper.delay_medium()
			my_exeption=e
			fail=true
			delete_all_created_items("Yes")
		}
		finally{
			if(fail){
				WebHelper.screenShoot(my_exeption.getMessage())
				throw my_exeption
			}
		}
	}
}
