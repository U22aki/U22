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
		if(siteUrl.indexOf("amazon.com") == -1){
			//amazonのスクレイピング可能ページか調べる
			regex = "(https:\\/\\/|http:\\/\\/)www\\.amazon\\.co\\.jp\\/(.+?)\\/dp\\/(.+?)\\/ref=(.+?)\\?ie=UTF8&qid=[0-9]+?&sr=(.+?)&keywords=(.+?)$";
			checkPattern(regex, siteUrl);
		}else if(siteUrl.indexOf("rakuten.com") == -1){
			//楽天のスクレイピング可能ページか調べる
			
		}else if(siteUrl.indexOf("kakaku.com") == -1){
			//価格ドットコムのスクレイピング可能ページか調べる
			
		}else{
			//スクレイピング不可能なページ
			
		}
	}
	
	private static void checkPattern(String regex, String siteUrl){
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(siteUrl);

		if(m.find()){
			System.out.println("マッチしています");
		}else{
			System.out.println("マッチしていません");
		}
	}
}
