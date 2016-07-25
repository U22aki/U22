package utility;

import java.io.IOException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * �X�N���C�s���O�N���X
 */

public class Scraping {
	private static String regex = "";//���K�\���i�[�p
	private String baseUrl = "";
	private static Elements elements;
	
	/**
	 * �X�N���C�s���O�����s
	 * @param siteUrl �I�v�V�������j���[�̃J�[�g�ɓ����{�^���������ꂽ�Ƃ��ɕ\�����Ă���URL
	 * @throws IOException 
	 */
	public static void executeScraping(String siteUrl) throws IOException{
		//URL�`�F�b�N
		boolean correctUrl = CheckURL.checkURL(siteUrl);
			
		if(correctUrl){
			Document doc = Jsoup.connect(siteUrl).get();
			//URL���X�N���C�s���O�\�ȃy�[�W�Ȃ�X�N���C�s���O���s��
			if(siteUrl.indexOf("amazon") == -1){
				elements = doc.select("#productTitle, .a-color-price, landingImage");
			}else if(siteUrl.indexOf("rakuten") == -1){
				elements = doc.select(".item_name, .price2, landingImage");
			}else{
				elements = doc.select("#titleBox .boxL h2, #minPrice a span");
			}
			
			for(Element element : elements){
				System.out.println(element.text());
			}
			
		}else{
			
		}
		
		
		
		
	}
	
}
