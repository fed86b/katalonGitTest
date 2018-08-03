package com.pages.cm

import com.pages.ItemAbstract
import com.system.IframeElement
import com.system.LanguageHelper
import com.system.MyElement
import com.system.WebHelper
import com.system.enums.EnumLanguage
public class HTMLEditor extends ItemAbstract {

	static MyElement btnEditHtmlContent=new IframeElement("btn_Edit_html","//div[@data-element-name='CONTENT']/descendant::span[text()='Edit HTML']/parent::button")
	static MyElement textAreaContent=new IframeElement("textAreaContent","//textarea[@name='CONTENT']")
	static MyElement divTextAreaContent=new IframeElement("divTextAreaContent","//textarea[@name='CONTENT']/parent::div")

	protected HTMLEditor(EnumLanguage lang){
		super(lang)
	}

	protected static clickEditHTMLContent() {
		def edit_html=LanguageHelper.getText('Edit HTML')
		try {
			btnEditHtmlContent.modify('Edit HTML',edit_html)
			while (!divTextAreaContent.getAtribute('style').contains('block')) {
				btnEditHtmlContent.clickWithHover()
			}
		} catch (Exception e) {
			WebHelper.catchException(e)
		}
	}
}
