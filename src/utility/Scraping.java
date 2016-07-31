package utility;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * スクレイピングクラス
 */

public class Scraping {
	private static Elements elements;
	private static Elements element;
	private static List<String> array;

	/**
	 * スクレイピングを実行
	 * 
	 * @param siteUrl
	 *            オプションメニューのカートに入れるボタンが押されたときに表示しているURL
	 * @throws IOException
	 */
	public static List<String> run(String siteUrl)
			throws IOException {
		System.out.println(siteUrl);
		// URLチェック
		boolean correctUrl = CheckURL.checkURL(siteUrl);
		
		array = new ArrayList<String>();
		
		if (correctUrl) {
			// スクレイピング可能なURLならスクレイピングを行い、メッセージを返す
			Document doc = Jsoup.connect(siteUrl).get();
			//int pattern = 0;
			
			String titleTag = "";
			String priceTag = "";
			String thumbnailTag = "";
			
			if (siteUrl.indexOf("amazon") != -1) {
				// アマゾン
				titleTag = "#product-title";
				priceTag = "html body font font";
				thumbnailTag = "html body center a img";
			} else if (siteUrl.indexOf("rakuten") != -1) {
				/*
				// 楽天
				elements = doc.select(".item_name, .price2, landingImage");
				*/
			} else {
				/*
				// 価格ドットコム
				tags = "#titleBox .boxL h2, #minPrice a span";
				thumbnailTag = "#imgBox a img";
				*/
			}
			array.add(siteUrl);
			setData(doc, titleTag, priceTag, thumbnailTag);

			//executeScraping(pattern);
		} else {
			array.add("商品ページでタップしてください");
		}
		System.out.println(siteUrl);
		for (int i = 0; i < array.size(); i++) {
			System.out.println(array.get(i));
		}
		return array;
	}

	/**
	 * リストに商品情報をセット
	 * 
	 * @param doc
	 *            サイトドキュメント
	 * @param tags
	 *            　商品名と商品価格を表すタグ
	 * @param thumbnailTag
	 *            　商品画像を表すタグ
	 */
	private static void setData(Document doc, String titleTag, String priceTag, String thumbnailTag) {
		// 商品名を取得して格納
		element = doc.select(titleTag);
		array.add(element.text());
		
		// 価格を取得して格納
		element = doc.select(priceTag);
		String price = element.get(0).text().toString();
		array.add(price);
		
		// 商品画像URLを取得して格納
		element = doc.select(thumbnailTag);
		array.add(element.attr("src"));
	}
	
	private void executeScraping(int pattern){
		
	}
}
