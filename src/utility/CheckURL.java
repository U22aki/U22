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
			//パターン１
			regex = "(https:\\/\\/|http:\\/\\/)www\\.amazon\\.co\\.jp\\/(.+?)\\/dp\\/(.+?)\\/ref=(.+?)\\?ie=UTF8&qid=[0-9]+?&sr=(.+?)&keywords=(.+?)$";
			checkPattern(regex, siteUrl);
			//パターン２
			regex = "(https:\\/\\/|http:\\/\\/)www\\.amazon\\.co\\.jp\\/gp\\/product\\/(.+?)\\/ref=(.+?)\\?(.+?)=[0-9A-Z]+?&pf_rd_s=desktop-[0-9]+?&pf_rd_r=[0-9A-Z]+?&pf_rd_t=[0-9]+?&pf_rd_p=(.+?)&pf_rd_i=desktop";
			checkPattern(regex, siteUrl);
		}else if(siteUrl.indexOf("rakuten.com") == -1 && !correctURL){
			//楽天のスクレイピング可能ページか調べる
			//パターン１
			regex = "(https:\\/\\/|http:\\/\\/)item\\.rakuten\\.co\\.jp\\/[^/]+?\\/[^/]+?";
			checkPattern(regex, siteUrl);
			//パターン２
			regex = "(https:\\/\\/|http:\\/\\/)item\\.rakuten\\.co\\.jp\\/(.+?)\\/(.+?)\\?s-id=(.+?)&xuseflg_ichiba[0-9]+?=[0-9]+?";
			checkPattern(regex, siteUrl);
			//パターン３
			regex = "(https:\\/\\/|http:\\/\\/)www\\.rakuten\\.co\\.jp\\/[^/]+?\\/\\?s-id=(.+?)";
			checkPattern(regex, siteUrl);
			//パターン４
			regex = "(https:\\/\\/|http:\\/\\/)item\\.rakuten\\.co\\.jp\\/[^/]+?\\/[^/]+?\\/\\?iasid=[a-z0-9]+?_[a-z0-9]+?__[a-z0-9]+?_[0-9a-z]+?-(.+?)-(.+?)";
			checkPattern(regex, siteUrl);
		}else if(siteUrl.indexOf("kakaku.com") == -1 && !correctURL){
			//価格ドットコムのスクレイピング可能ページか調べる
			//パターン１
			regex = "(https:\\/\\/|http:\\/\\/)kakaku\\.com\\/item\\/[0-9A-Z]+?\\/";
			checkPattern(regex, siteUrl);
			//パターン２
			regex = "(https:\\/\\/|http:\\/\\/)kakaku\\.com\\/item/[0-9A-Z]+?\\/\\?(lid|id)=[^/]+?$";
			checkPattern(regex, siteUrl);
			//パターン３
			regex = "(https:\\/\\/|http:\\/\\/)bbs\\.kakaku\\.com\\/bbs/[0-9A-Z]+?\\/SortID=[0-9]+?\\/\\?(.+?)id=(.+?)#tab";
			checkPattern(regex, siteUrl);
		}else{
			//スクレイピング不可能なページ
			correctURL = false;
		}
		return correctURL;
	}
	
	/**
	 * 取得したサイトURLが正規表現（商品詳細ページ）に該当していたらcorrectURLをtrueにする
	 * @param regex 正規表現
	 * @param siteUrl　取得したサイトURL
	 */
	private static void checkPattern(String regex, String siteUrl){
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(siteUrl);

		if(m.find()){
			correctURL = true;
		}else{
			correctURL = false;
		}
	}
}
