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
	 * �R���X�g���N�^�[
	 * 
	 * @param activity
	 */
	public AsyncScraping(String siteUrl, AsyncTaskCallbacks callback) {
		// �Ăяo�����A�N�e�B�r�e�B
		this.siteUrl = siteUrl;
		this.callback = callback;
		array = new ArrayList<String>();
	}

	/**
	 * �o�b�N�O���E���h����
	 * 
	 * @return
	 */
	@Override
	protected String doInBackground(String... params) {
		System.out.println("asyncAAAAAAAAA");
		// �X�N���C�s���O�\��URL�Ȃ�X�N���C�s���O���s���A���b�Z�[�W��Ԃ�
		try {
			//HTML�̃h�L�������g���擾����iHTTP�փ��N�G�X�g���΂��j
			Document doc = Jsoup.connect(siteUrl).get();
			
			String titleTag = "";
			String priceTag = "";
			String thumbnailTag = "";

			if (siteUrl.indexOf("amazon") != -1) {
				System.out.println("amazon");

				// �A�}�]��
				titleTag = "#product-title";
				priceTag = "html body font font";
				thumbnailTag = "html body center a img";
			} else if (siteUrl.indexOf("rakuten") != -1) {
				/*
				 * // �y�V elements =
				 * doc.select(".item_name, .price2, landingImage");
				 */
			} else {
				/*
				 * // ���i�h�b�g�R�� tags = "#titleBox .boxL h2, #minPrice a span";
				 * thumbnailTag = "#imgBox a img";
				 */
			}

			try {
				// ���i�����擾���Ċi�[
				element = doc.select(titleTag);
				array.add(element.text().toString());
				System.out.println(element.text().toString());
			} catch (Exception e) {
				System.out.println("���i���擾���s");
				e.printStackTrace();
			}

			try {
				// ���i���擾���Ċi�[
				element = doc.select(priceTag);
				String price = element.get(0).text().toString();
				array.add(price);
			} catch (Exception e) {
				System.out.println("���i�擾���s");
				e.printStackTrace();
			}

			try {
				// ���i�摜URL���擾���Ċi�[
				element = doc.select(thumbnailTag);
				array.add(element.attr("src").toString());
			} catch (Exception e) {
				System.out.println("���i�摜URL�擾���s");
				e.printStackTrace();
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
		String a = "�J�[�g�ɓ���܂���";
		return a;
	}

	// �������̏���
	@Override
	protected void onProgressUpdate(Integer... progress) {

	}

	// AsyncScrapig�I�����ɃR�[�������
	@Override
	protected void onPostExecute(String a) {
		callback.onTaskFinished();
		// Toast.makeText(productsActivity, a, Toast.LENGTH_LONG).show();
	}

	// AsyncScrapig�L�����Z�����ɃR�[�������
	@Override
	protected void onCancelled() {
		callback.onTaskCancelled();
	}

}
