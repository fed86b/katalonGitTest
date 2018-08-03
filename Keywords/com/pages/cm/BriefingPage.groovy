package com.pages.cm
import com.system.IframeElement
import com.system.LanguageHelper
import com.system.MyElement
import com.system.WebHelper
import com.system.enums.EnumLanguage
import com.system.enums.EnumTemplate
public  class BriefingPage extends ContentArea {

	static MyElement aEditTab=new IframeElement("aEditTab","//a[@href = '#item-update-tab-edit' and @class = 'ui-tabs-anchor']")

	static EditTab editTab
	static ItemPropertyBriefing itemPropertyBriefing
	protected BriefingPage(EnumLanguage lang) {
		super(EnumTemplate.Briefing,lang)
	}

	protected static EditTab getEditTab() {
		try{
			if(editTab==null)
				editTab=new EditTab(lang)
			clickEditTab()
		} catch (Exception e) {
			WebHelper.catchException(e)
		}
		return editTab
	}

	static clickEditTab(){
		def edit=LanguageHelper.getText('Edit Item')
		aEditTab.clickUntilNotDisappear(itemPropertyBriefing.lblItemId,edit)
	}


	protected static ItemPropertyBriefing getItemPropertyBriefing() {
		try{
			if(itemPropertyBriefing==null){
				itemPropertyBriefing=new ItemPropertyBriefing(lang)
			}
			clickItemProperties()
		} catch (Exception e) {
			WebHelper.catchException(e)
		}
		return itemPropertyBriefing
	}
}
