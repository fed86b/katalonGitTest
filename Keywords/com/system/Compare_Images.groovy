package com.system
import java.awt.*;
import java.awt.image.*;
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils
import org.openqa.selenium.OutputType
import org.openqa.selenium.Point
import org.openqa.selenium.TakesScreenshot
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.sun.image.codec.jpeg.*;
import com.system.enums.Enum_Role

import internal.GlobalVariable
import net.coobird.thumbnailator.Thumbnails;


public class Compare_Images {

	public Compare_Images(){
	}
	static ImageComparison  imageComparison

	public static boolean img_compare(def imgOriginal,def imgToCompareWithOriginal,String imgOutputDifferences="", int size=20, int threshhold=0.05) {
		imageComparison = new ImageComparison(size,size,threshhold)
		def flag= imageComparison.fuzzyEqual(imgOriginal,imgToCompareWithOriginal,imgOutputDifferences)
		WebUI.verifyEqual(flag, true, FailureHandling.CONTINUE_ON_FAILURE)
		KeywordUtil.markWarning(String.format("Comparing  %s image : %s",imgOriginal,flag))
	}

	public static compare_webElement_pic(TestObject element,Enum_Role role,def name="download.jpg",def pic_center=true) {
		WebUiCommonHelper.switchToParentFrame(element)
		WebElement ele=WebUiCommonHelper.findWebElement(element,GlobalVariable.G_Wait)

		WebDriver  driver =DriverFactory.getWebDriver()
		def desired=String.format("%s\\img\\%s", System.getProperty("user.dir"),name)
		def actual=String.format("%s\\img\\actual\\%s", System.getProperty("user.dir"),name)

		File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		BufferedImage  fullImg = ImageIO.read(screenshot);

		// Get the location of element on the page
		Point point = ele.getLocation();

		// Get width and height of the element

		int eleWidth = ele.getSize().getWidth();
		int eleHeight = ele.getSize().getHeight();
		def y=point.getY()
		def x=point.getX()
		//if login than not need to pass
		if(role==Enum_Role.CONTENT_MANAGER&&!pic_center){
			x+=301
			y+=140
		}

		if(point.getY()>800){
			y-=546
		}

		// Crop the entire page screenshot to get only element screenshot
		BufferedImage eleScreenshot= fullImg.getSubimage(x, y,
				eleWidth, eleHeight);
		ImageIO.write(eleScreenshot, "png", screenshot);

		// Copy the element screenshot to disk
		File screenshotLocation = new File(actual);
		FileUtils.copyFile(screenshot, screenshotLocation);

		Compare_Images.img_compare(desired, actual,WebHelper.getLocation("difference"))
		WebUI.switchToDefaultContent()
	}



	public static detailed_comparison(def stage="",def isLogin=false){
		WebHelper.verify_process_wait(isLogin)
		def curDir=WebHelper.getCurDir()
		def driver_folder= DriverFactory.getExecutedBrowser().toString()
		def testId=RunConfiguration.getExecutionSourceId()
		def path=String.format("%s_%s_%s",driver_folder,stage,testId)
		def name=WebHelper.clean_path(path)
		if(name.length()>WebHelper.END)
			name=name.substring(WebHelper.START,(WebHelper.END-8))
		def differences_loc=WebHelper.getLocation(name)
		def location=String.format("%s\\img\\%s\\%s\\",curDir,driver_folder,stage)
		def desired_loc=String.format("%sdesired.png",location)
		Path p=Paths.get(desired_loc)
		if (!Files.exists(p)||GlobalVariable.G_Take_Pic) {
			WebUI.takeScreenshot(desired_loc)
		}
		def actual_loc=String.format("%sactual.png",location)
		WebUI.takeScreenshot(actual_loc)
		img_compare(desired_loc, actual_loc,differences_loc)
	}
}
















public class ImageComparison {
	private BufferedImage imgOut = null;
	private int pixelPerBlockX;
	private int pixelPerBlockY;
	private double threshold;

	public ImageComparison(int pixelPerBlockX, int pixelPerBlockY, double threshold) {
		this.pixelPerBlockX = pixelPerBlockX;
		this.pixelPerBlockY = pixelPerBlockY ;
		this.threshold = threshold;
	}
	public boolean fuzzyEqual(String path1, String path2, String pathOut) throws IOException {
		return fuzzyEqual(ImageIO.read(new File(path1)), ImageIO.read(new File(path2)), pathOut);
	}

	public boolean fuzzyEqual(File file1, File file2, String pathOut) throws IOException {
		return fuzzyEqual(ImageIO.read(file1), ImageIO.read(file2), pathOut);
	}

	public boolean fuzzyEqual(Image img1, Image img2, String pathOut) throws IOException {
		return fuzzyEqual(imageToBufferedImage(img1), imageToBufferedImage(img2), pathOut);
	}


	public boolean fuzzyEqual(BufferedImage img1, BufferedImage img2, String pathOut) throws IOException {
		boolean fuzzyEqual = true;
		img2 = adaptImageSize(img1,img2);

		imgOut = imageToBufferedImage(img2);
		Graphics2D outImgGraphics = imgOut.createGraphics();
		outImgGraphics.setColor(Color.RED);

		int subImageHeight;
		int subImageWidth;
		//		String debug;

		int blocksx = (int) Math.ceil((float) img1.getWidth()
				/ (float) pixelPerBlockX);
		int blocksy = (int) Math.ceil((float) img1.getHeight()
				/ (float) pixelPerBlockY);

		for (int y = 0; y < blocksy; y++) {
			//			debug = "";
			for (int x = 0; x < blocksx; x++) {
				subImageWidth=calcPixSpan(pixelPerBlockX,x,img1.getWidth());
				subImageHeight=calcPixSpan(pixelPerBlockY,y,img1.getHeight());
				//System.out.println(" Real Width:" +img1.getWidth() + " Real Height:" +img1.getHeight() + " X-Start:"+ x * pixelPerBlockX + " Y-Start:" +y * pixelPerBlockY + " sub width:" +subImageWidth + " sub height:" + subImageHeight);
				//System.out.println(" Real Width:" +img2.getWidth() + " Real Height:" +img2.getHeight() + " X-Start:"+ x * pixelPerBlockX + " Y-Start:" +y * pixelPerBlockY + " sub width:" +subImageWidth + " sub height:" + subImageHeight);
				HashMap<String, Integer> avgRgb1 = getAverageRgb(img1
						.getSubimage(x * pixelPerBlockX, y * pixelPerBlockY,
						subImageWidth, subImageHeight));
				HashMap<String, Integer> avgRgb2 = getAverageRgb(img2
						.getSubimage(x * pixelPerBlockX, y * pixelPerBlockY,
						subImageWidth, subImageHeight));
				//				debug = debug
				//						+ String.format(Locale.ENGLISH, "%1.2f",
				//								calculateRgbDiff(avgRgb1, avgRgb2)) + " | ";
				if (calculateRgbDiff(avgRgb1, avgRgb2) > threshold) {
					outImgGraphics.drawRect(x * pixelPerBlockX, y * pixelPerBlockY,
							pixelPerBlockX - 1, pixelPerBlockY - 1);
					fuzzyEqual = false;
				}
			}
			//System.out.println(debug);
		}
		if(pathOut != null && !pathOut.isEmpty()&&!fuzzyEqual)
			saveImage(imgOut,pathOut);
		return fuzzyEqual;
	}

	private int calcPixSpan(int pixelPerBlock, int n, int overallSpan) {
		if (pixelPerBlock * (n + 1) > overallSpan)
			return overallSpan % pixelPerBlock;
		else
			return pixelPerBlock;
	}

	private BufferedImage adaptImageSize(BufferedImage img1, BufferedImage img2) throws IOException {
		int scalePixelWidth;
		int scalePixelHeight;
		//System.out.println("1 Real Width:" +img1.getWidth() + " Real Height:" +img1.getHeight());
		//System.out.println("2 Real Width:" +img2.getWidth() + " Real Height:" +img2.getHeight());

		if(((float)img2.getWidth()/(float)img1.getWidth()) < ((float)img2.getHeight()/(float)img1.getHeight())){
			scalePixelWidth = img1.getWidth();
			scalePixelHeight = (int) (img2.getHeight() * Math.ceil((float)img1.getWidth()/(float)img2.getWidth()));
			//System.out.println("If : Scale Width:" +scalePixelWidth + " Scale Height:" +scalePixelHeight);
		}else {
			scalePixelHeight = img1.getHeight();
			scalePixelWidth = (int) (img2.getWidth() * Math.ceil((float)img1.getHeight()/(float)img2.getHeight()));
			//System.out.println("Else: Scale Width:" +scalePixelWidth + " Scale Height:" +scalePixelHeight);
		}
		//System.out.println("1 Real Width:" +img1.getWidth() + " Real Height:" +img1.getHeight());
		//System.out.println("2 Real Width:" +Thumbnails.of(img2).size(scalePixelWidth, scalePixelHeight).asBufferedImage().getWidth() + " Real Height:" +Thumbnails.of(img2).size(scalePixelWidth, scalePixelHeight).asBufferedImage().getHeight());

		return Thumbnails.of(img2).size(scalePixelWidth, scalePixelHeight).asBufferedImage();
	}
	private double calculateRgbDiff(HashMap<String, Integer> avgRgb1,
			HashMap<String, Integer> avgRgb2) {
		double maxDifference = 255 * 3;
		double difference = Math.abs(avgRgb1.get("r") - avgRgb2.get("r"))
		+ Math.abs(avgRgb1.get("g") - avgRgb2.get("g"))
		+ Math.abs(avgRgb1.get("b") - avgRgb2.get("b"));

		return difference / maxDifference;
	}


	private HashMap<String, Integer> getAverageRgb(BufferedImage img) {
		Raster currentRaster = img.getData();
		HashMap<String, Integer> averageRgb = new HashMap<String, Integer>();
		averageRgb.put("r", 0);
		averageRgb.put("g", 0);
		averageRgb.put("b", 0);

		for (int y = 0; y < img.getHeight(); y++) {
			for (int x = 0; x < img.getWidth(); x++) {
				averageRgb.put("r", averageRgb.get("r") + currentRaster.getSample(x, y, 0));
				averageRgb.put("g", averageRgb.get("g") + currentRaster.getSample(x, y, 1));
				averageRgb.put("b", averageRgb.get("b") + currentRaster.getSample(x, y, 2));

			}
		}

		averageRgb.put("r",
				averageRgb.get("r") / (img.getHeight() * img.getWidth()));
		averageRgb.put("g",
				averageRgb.get("g") / (img.getHeight() * img.getWidth()));
		averageRgb.put("b",
				averageRgb.get("b") / (img.getHeight() * img.getWidth()));

		return averageRgb;
	}

	private BufferedImage imageToBufferedImage(Image img) {
		BufferedImage bi = new BufferedImage(img.getWidth(null),
				img.getHeight(null), BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = bi.createGraphics();
		g2.drawImage(img, null, null);
		return bi;
	}

	public void saveImage(BufferedImage bi, String filename){
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(filename);
		} catch (java.io.FileNotFoundException io) {
			System.out.println("File Not Found");
		}
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
		JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(bi);
		param.setQuality(0.8f, false);
		encoder.setJPEGEncodeParam(param);
		try {
			encoder.encode(bi);
			out.close();
		} catch (java.io.IOException io) {
			System.out.println("IOException");
		}
	}

	public void saveImage(Image img, String filename) {
		BufferedImage bi = imageToBufferedImage(img);
		saveImage( bi,  filename)
	}
}



