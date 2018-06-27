package com.server

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

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
						WebUI.getUrl().isEmpty()
					} catch (Exception e) {
						WebUI.openBrowser('')
						WebUI.maximizeWindow()
						WebUI.deleteAllCookies()
					}
				}
			}
		}
		return app
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
}