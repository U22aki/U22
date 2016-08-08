package utility;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import cart.AsyncTaskCallbacks;
import cart.ProductsActivity;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.Toast;

public class AsyncScraping extends AsyncTask<String, Integer, String> {

	private static Elements elements;
	private static Elements element;
	private static List<String> array;
	private String siteUrl;
	private AsyncTaskCallbacks callback = null;

	/**
	 * コンストラクター
	 * 
	 * @param activity
	 */
	public AsyncScraping(String siteUrl, AsyncTaskCallbacks callback) {
		// 呼び出し元アクティビティ
		this.siteUrl = siteUrl;
		this.callback = callback;
	}

	/**
	 * バックグラウンド処理
	 * @return
	 */
	@Override
	protected String doInBackground(String... params) {
		System.out.println("asyncAAAAAAAAA");
		// スクレイピング可能なURLならスクレイピングを行い、メッセージを返す
		Document doc;
		array = new ArrayList<String>();
		try {
			doc = Jsoup.connect(siteUrl).get();
			String titleTag = "";
			String priceTag = "";
			String thumbnailTag = "";

		
			if (siteUrl.indexOf("amazon") != -1) {
				System.out.println("amazon");
				
				// アマゾン
				titleTag = "#product-title";
				priceTag = "html body font font";
				thumbnailTag = "html body center a img";
			} else if (siteUrl.indexOf("rakuten") != -1) {
				/*
				 * // 楽天 elements =
				 * doc.select(".item_name, .price2, landingImage");
				 */
			} else {
				/*
				 * // 価格ドットコム tags = "#titleBox .boxL h2, #minPrice a span";
				 * thumbnailTag = "#imgBox a img";
				 */
			}

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
			System.out.println("good");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("bat");
		}
		System.out.println("cheeek");
		String a = "カートに入れました";
		return a;
	}
	
	@Override
	protected void onProgressUpdate(Integer... progress){
		
	}
	
	//AsyncScrapig終了時にコールされる
	@Override
	protected void onPostExecute(String a){
		callback.onTaskFinished();
		//Toast.makeText(productsActivity, a, Toast.LENGTH_LONG).show();
	}
	
	//AsyncScrapigキャンセル時にコールされる
	@Override
	protected void onCancelled(){
		callback.onTaskCancelled();
	}
	
	
}
