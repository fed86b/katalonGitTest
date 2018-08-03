package com.system

import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.driver.WebUIDriverType
import com.kms.katalon.core.webui.exception.BrowserNotOpenedException
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable


class AppManager {

	private static volatile AppManager app
	private AppManager(){}

	public static AppManager getInstance(){
		if(app==null){
			synchronized (AppManager.class){
				if(app == null){
					app=new AppManager()
					try {
						if(WebUI.getUrl().equalsIgnoreCase(GlobalVariable.G_Url))
							goToNewTab()
						else
							return app
					}catch (Exception e) {
						openBrowser()
					}
				}
			}
		}


		return app
	}


	private static openBrowser() {


		//RunConfiguration.setExecutionSettingFile(WebHelper.getLocation())
		WebUI.openBrowser(GlobalVariable.G_Url)
		WebUI.setViewPortSize(1400,1000)
		if (!DriverFactory.getExecutedBrowser() == WebUIDriverType.EDGE_DRIVER) {
			WebUI.deleteAllCookies()
		}
	}


	public static void dismiss(){

		WebUI.closeBrowser()
		app=null
	}

	private static goToNewTab() {
		WebUI.executeJavaScript('window.open();', [])
		int  currentWindow =  WebUI.getWindowIndex()
		int newTabIndex=currentWindow + 1
		WebUI.switchToWindowIndex(newTabIndex)
		WebUI.navigateToUrl(GlobalVariable.G_Url)
	}
}