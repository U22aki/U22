package utility;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.provider.MediaStore.Images.Thumbnails;

/**
 * スクレイピングクラス
 */

public class Scraping {
	private static Elements elements;
	private static Elements element;
	private static List<String> array;
	
	/**
	 * スクレイピングを実行
	 * @param siteUrl オプションメニューのカートに入れるボタンが押されたときに表示しているURL
	 * @throws IOException 
	 */
	public static String executeScraping(String siteUrl) throws IOException{
		String message = "";
		//URLチェック
		boolean correctUrl = CheckURL.checkURL(siteUrl);
			
		if(correctUrl){
			//スクレイピング可能なURLならスクレイピングを行い、メッセージを返す
			Document doc = Jsoup.connect(siteUrl).get();
			String tags = "";
			String thumbnailTag = "";
			if(siteUrl.indexOf("amazon") == -1){
				//アマゾン
				tags = "#productTitle, #priceblock_ourprice";
				thumbnailTag = "#imgTagWrapperId img";
			}else if(siteUrl.indexOf("rakuten") == -1){
				//楽天
				elements = doc.select(".item_name, .price2, landingImage");
			}else{
				//価格ドットコム
				tags = "#titleBox .boxL h2, #minPrice a span";
				thumbnailTag = "#imgBox a img";
			}
			
			setData(doc, tags, thumbnailTag);
			
			
			/*テストコード
			for(Element element : elements){
				System.out.println(element.text());
			}
			*/
			
			//外部DBに接続してデータを格納

			
			//メッセージ追加
			message = "商品をカートにいれました";
		}else{
			//スクレイピング不可能なURLならメッセージを返す
			message = "カートに入れたい商品のページでタップしてください";
		}
		return message;
	}
	
	/**
	 * リストに商品情報をセット
	 * @param doc サイトドキュメント
	 * @param tags　商品名と商品価格を表すタグ
	 * @param thumbnailTag　商品画像を表すタグ
	 */
	private static void setData(Document doc, String tags, String thumbnailTag){
		//商品名と価格を取得して格納
		elements = doc.select(tags);
		for(Element element : elements){
			array.add(element.text());
		}
		//商品画像URLを取得して格納
		element = doc.select(thumbnailTag);
		array.add(element.attr("src"));
	}
}
