package com.pages
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.driver.WebUIDriverType
import com.system.LanguageHelper
import com.system.MyElement
import com.system.RolesHelper
import com.system.WebHelper
import com.system.enums.EnumCreateItem
import com.system.enums.EnumLanguage

import internal.GlobalVariable

public class TopToolBar extends ItemAbstract{
	static MyElement aLogout=new MyElement("aLogout","//a[contains(@href,'logout')]")
	static MyElement aProfileAvatar=new MyElement("profile-avatar","//*[@class = 'top-toolbar__section top-toolbar__profile']")
	static MyElement aKmsHomeIcon=new MyElement("aKmsHomeIcon","//*[@class = 'kms-icon kms-icon--home']")

	static MyElement searchBar=new MyElement("searchBar","//input[@id='search-input']")

	protected TopToolBar(EnumLanguage lang){
		super(lang)
	}

	protected static  logout(){
		def Logout=LanguageHelper.getText('Logout')
		try {
			aProfileAvatar.MouseOverUntilNotVisible(aLogout)

			aLogout.clickWithHover(Logout)
		}
		catch (Exception e) {
			WebHelper.catchException(e)
		}
	}


	protected static  clickHomeButton(){
		try {
			aKmsHomeIcon.click()
		}
		catch (Exception e) {
			WebHelper.catchException(e)
		}
	}

	protected static  searchByFirstDescriptionWord(def template=""){
		try {
			String text=WebHelper.get_item_data(EnumCreateItem.short_description)
			String[] words=text.split(" ")
			for(def word in words){
				CharSequence cs = word
				searchBar.writeSlowly(cs)
				def item_text="item_"+template
				def searched_word=String.format("//a[contains(.,'%s')]",item_text)
				MyElement itemSERP=new MyElement("aSearchedWord",searched_word)
				def times=0
				while(!itemSERP.isVisible(false)&&times<GlobalVariable.G_Wait){}
				if(times>=GlobalVariable.G_Wait||!itemSERP.verifyText(item_text,false))
					throw new Exception(String.format("Cannot find element %s by promoted Word %S in SERP"),item_text,word)
			}
		} catch (Exception e) {
			WebHelper.catchException(e)
		}
	}
}
