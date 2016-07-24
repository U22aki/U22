package utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckURL {
	private static boolean correctURL = false;
	
	/**
	 * URLをチェック
	 */
	public static boolean checkURL(String siteUrl){
		String regex = "";
		
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
		return correctURL;
	}
	
	private static void checkPattern(String regex, String siteUrl){
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(siteUrl);

		if(m.find()){
			correctURL = true;;
		}else{
			correctURL = false;
		}
	}
}
