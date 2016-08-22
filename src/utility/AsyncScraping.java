package utility;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import cart.AsyncTaskCallbacks;
import cart.ProductsActivity;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.Toast;

public class AsyncScraping extends AsyncTask<String, Integer, List<String>> {

	private static Elements elements;
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
	 * 
	 * @return
	 */
	@Override
	protected List<String> doInBackground(String... params) {
		System.out.println(siteUrl);
		
		try {
			//HTMLのドキュメントを取得する（HTTPへリクエストを飛ばす）
			Document doc = Jsoup.connect(siteUrl).timeout(0).get();
			
			String titleTag = "";
			String priceTag = "";
			String thumbnailTag = "";
			Elements element = null;

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

				array = new ArrayList<String>();
				// 商品名を取得して格納
				System.out.println("priceName");
				element = doc.select(titleTag);
				if(element != null){
					array.add(element.text().toString());
				}else{
					System.out.println("商品名が取得できませんでした");
				}

				// 価格を取得して格納
				element = doc.select(priceTag);
				if(element != null){
				String price = element.get(0).text().toString();
				array.add(price);
				}else{
					System.out.println("価格が取得できませんでした");
				}

			
				// 商品画像URLを取得して格納
				element = doc.select(thumbnailTag);
				if(element != null){
				array.add(element.attr("src").toString());
				}else{
					System.out.println("商品画像が取得できませんでした");
				}
			
			System.out.println("good");
			for(String a: array){
				System.out.println(a);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("bat");
		}
		System.out.println("cheeek");
		return array;
	}

	// 処理中の処理
	@Override
	protected void onProgressUpdate(Integer... progress) {

	}

	// AsyncScrapig終了時にコールされる
	@Override
	protected void onPostExecute(List<String> array) {
		
		for(String ab : array){
			System.out.println(ab);
		}
		callback.onTaskFinished();
		// Toast.makeText(productsActivity, a, Toast.LENGTH_LONG).show();
	}

	// AsyncScrapigキャンセル時にコールされる
	@Override
	protected void onCancelled() {
		callback.onTaskCancelled();
	}

}
