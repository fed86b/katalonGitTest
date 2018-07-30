package com.pages.cm
import com.system.IframeElement
import com.system.LanguageHelper
import com.system.MyElement
import com.system.WebHelper
import com.system.enums.EnumLanguage
import com.system.enums.EnumTemplate
public  class BriefingPage extends ContentArea {

	static MyElement a_EditTab=new IframeElement("a_EditTab","//a[@href = '#item-update-tab-edit' and @class = 'ui-tabs-anchor']")

	static EditTab editTab
	static ItemPropertyBriefing itemPropertyBriefing
	protected BriefingPage(EnumLanguage lang) {
		super(EnumTemplate.Briefing,lang)
	}

	protected static EditTab getEditTab() {

		try{
			if(editTab==null){
			}
		} catch (Exception e) {
			my_exeption=e
			fail=true
			WebHelper.delay()
			clickEditTab()
			fail=false
		}
		finally{
			if(fail)
				WebHelper.screenShoot(my_exeption.getMessage())
		}
		clickEditTab()
		return editTab
	}

	static clickEditTab(){
		def edit=LanguageHelper.getText('Edit Item')
		editTab=new EditTab(lang)
		a_EditTab.double_click(edit)
	}


	protected static ItemPropertyBriefing getItemPropertyBriefing() {
		try{
			if(itemPropertyBriefing==null){
				itemPropertyBriefing=new ItemPropertyBriefing(lang)
			}
		} catch (Exception e) {
			my_exeption=e
			fail=true
			WebHelper.delay()
			clickItemProperties()
			fail=false
		}
		finally{
			if(fail)
				WebHelper.screenShoot(my_exeption.getMessage())
		}
		clickItemProperties()
		return itemPropertyBriefing
	}
}
