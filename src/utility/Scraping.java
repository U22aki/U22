package utility;

import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * スクレイピングクラス
 */

public class Scraping {
	private static String regex = "";//正規表現格納用
	private String baseUrl = "";
	
	/**
	 * スクレイピングを実行
	 * @param siteUrl オプションメニューのカートに入れるボタンが押されたときに表示しているURL
	 */
	public static void executeScraping(String siteUrl){
		//URLチェック
		CheckURL.checkURL(siteUrl);
		
		
	}
	
}
