package com.server

import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.edge.EdgeDriver

import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.server.WebHelper as WebHelper
import internal.GlobalVariable


class AppManager {

	private static volatile AppManager app
	private static volatile NavigationHelper navigationHelper
	private static volatile WebHelper webHelper
	private AppManager(){}

	public static AppManager getInstance(){
		if(app==null){
			synchronized (AppManager.class){
				if(app == null){
					app=new AppManager()
					try {
						WebUI.getUrl()
						if(WebUI.getUrl().equalsIgnoreCase(GlobalVariable.G_Url))
							goToNewTab()
					} catch (Exception e) {
						openBrowser()
					}
				}
			}
		}


		return app
	}

	private static openBrowser() {
		WebUI.openBrowser(GlobalVariable.G_Url)
		WebUI.maximizeWindow()
		WebDriver driver=DriverFactory.getWebDriver()
		if(driver instanceof EdgeDriver){}
		else
			WebUI.deleteAllCookies()
	}



	public NavigationHelper getNavHelper(){
		if(navigationHelper==null){
			synchronized (NavigationHelper.class){
				if(navigationHelper == null){
					navigationHelper=new NavigationHelper(this)
				}
			}
		}
		return navigationHelper
	}

	public WebHelper getWebHelper(){
		if(webHelper==null){
			synchronized (WebHelper.class){
				if(webHelper == null){
					webHelper=new WebHelper()
				}
			}
		}
		return webHelper
	}



	public void dismiss(){

		WebUI.closeBrowser()
		navigationHelper=null
		webHelper=null
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