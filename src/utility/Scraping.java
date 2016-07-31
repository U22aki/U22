package utility;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * �X�N���C�s���O�N���X
 */

public class Scraping {
	private static Elements elements;
	private static Elements element;
	private static List<String> array;

	/**
	 * �X�N���C�s���O�����s
	 * 
	 * @param siteUrl
	 *            �I�v�V�������j���[�̃J�[�g�ɓ����{�^���������ꂽ�Ƃ��ɕ\�����Ă���URL
	 * @throws IOException
	 */
	public static List<String> run(String siteUrl)
			throws IOException {
		System.out.println(siteUrl);
		// URL�`�F�b�N
		boolean correctUrl = CheckURL.checkURL(siteUrl);
		
		array = new ArrayList<String>();
		
		if (correctUrl) {
			// �X�N���C�s���O�\��URL�Ȃ�X�N���C�s���O���s���A���b�Z�[�W��Ԃ�
			Document doc = Jsoup.connect(siteUrl).get();
			//int pattern = 0;
			
			String titleTag = "";
			String priceTag = "";
			String thumbnailTag = "";
			
			if (siteUrl.indexOf("amazon") != -1) {
				// �A�}�]��
				titleTag = "#product-title";
				priceTag = "html body font font";
				thumbnailTag = "html body center a img";
			} else if (siteUrl.indexOf("rakuten") != -1) {
				/*
				// �y�V
				elements = doc.select(".item_name, .price2, landingImage");
				*/
			} else {
				/*
				// ���i�h�b�g�R��
				tags = "#titleBox .boxL h2, #minPrice a span";
				thumbnailTag = "#imgBox a img";
				*/
			}
			array.add(siteUrl);
			setData(doc, titleTag, priceTag, thumbnailTag);

			//executeScraping(pattern);
		} else {
			array.add("���i�y�[�W�Ń^�b�v���Ă�������");
		}
		System.out.println(siteUrl);
		for (int i = 0; i < array.size(); i++) {
			System.out.println(array.get(i));
		}
		return array;
	}

	/**
	 * ���X�g�ɏ��i�����Z�b�g
	 * 
	 * @param doc
	 *            �T�C�g�h�L�������g
	 * @param tags
	 *            �@���i���Ə��i���i��\���^�O
	 * @param thumbnailTag
	 *            �@���i�摜��\���^�O
	 */
	private static void setData(Document doc, String titleTag, String priceTag, String thumbnailTag) {
		// ���i�����擾���Ċi�[
		element = doc.select(titleTag);
		array.add(element.text());
		
		// ���i���擾���Ċi�[
		element = doc.select(priceTag);
		String price = element.get(0).text().toString();
		array.add(price);
		
		// ���i�摜URL���擾���Ċi�[
		element = doc.select(thumbnailTag);
		array.add(element.attr("src"));
	}
	
	private void executeScraping(int pattern){
		
	}
}
