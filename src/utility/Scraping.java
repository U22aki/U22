package utility;

import java.io.IOException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * スクレイピングクラス
 */

public class Scraping {
	private static String regex = "";//正規表現格納用
	private String baseUrl = "";
	private static Elements elements;
	
	/**
	 * スクレイピングを実行
	 * @param siteUrl オプションメニューのカートに入れるボタンが押されたときに表示しているURL
	 * @throws IOException 
	 */
	public static void executeScraping(String siteUrl) throws IOException{
		//URLチェック
		boolean correctUrl = CheckURL.checkURL(siteUrl);
			
		if(correctUrl){
			Document doc = Jsoup.connect(siteUrl).get();
			//URLがスクレイピング可能なページならスクレイピングを行う
			if(siteUrl.indexOf("amazon") == -1){
				elements = doc.select("#productTitle, .a-color-price, landingImage");
			}else if(siteUrl.indexOf("rakuten") == -1){
				elements = doc.select(".item_name, .price2, landingImage");
			}else{
				elements = doc.select("#titleBox .boxL h2, #minPrice a span");
			}
			
			for(Element element : elements){
				System.out.println(element.text());
			}
			
		}else{
			
		}
		
		
		
		
	}
	
}
