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
 * �X�N���C�s���O�N���X
 */

public class Scraping {
	private static Elements elements;
	private static Elements element;
	private static List<String> array;
	
	/**
	 * �X�N���C�s���O�����s
	 * @param siteUrl �I�v�V�������j���[�̃J�[�g�ɓ����{�^���������ꂽ�Ƃ��ɕ\�����Ă���URL
	 * @throws IOException 
	 */
	public static String executeScraping(String siteUrl) throws IOException{
		String message = "";
		//URL�`�F�b�N
		boolean correctUrl = CheckURL.checkURL(siteUrl);
			
		if(correctUrl){
			//�X�N���C�s���O�\��URL�Ȃ�X�N���C�s���O���s���A���b�Z�[�W��Ԃ�
			Document doc = Jsoup.connect(siteUrl).get();
			String tags = "";
			String thumbnailTag = "";
			if(siteUrl.indexOf("amazon") == -1){
				//�A�}�]��
				tags = "#productTitle, #priceblock_ourprice";
				thumbnailTag = "#imgTagWrapperId img";
			}else if(siteUrl.indexOf("rakuten") == -1){
				//�y�V
				elements = doc.select(".item_name, .price2, landingImage");
			}else{
				//���i�h�b�g�R��
				tags = "#titleBox .boxL h2, #minPrice a span";
				thumbnailTag = "#imgBox a img";
			}
			
			setData(doc, tags, thumbnailTag);
			
			
			/*�e�X�g�R�[�h
			for(Element element : elements){
				System.out.println(element.text());
			}
			*/
			
			//�O��DB�ɐڑ����ăf�[�^���i�[

			
			//���b�Z�[�W�ǉ�
			message = "���i���J�[�g�ɂ���܂���";
		}else{
			//�X�N���C�s���O�s�\��URL�Ȃ烁�b�Z�[�W��Ԃ�
			message = "�J�[�g�ɓ��ꂽ�����i�̃y�[�W�Ń^�b�v���Ă�������";
		}
		return message;
	}
	
	/**
	 * ���X�g�ɏ��i�����Z�b�g
	 * @param doc �T�C�g�h�L�������g
	 * @param tags�@���i���Ə��i���i��\���^�O
	 * @param thumbnailTag�@���i�摜��\���^�O
	 */
	private static void setData(Document doc, String tags, String thumbnailTag){
		//���i���Ɖ��i���擾���Ċi�[
		elements = doc.select(tags);
		for(Element element : elements){
			array.add(element.text());
		}
		//���i�摜URL���擾���Ċi�[
		element = doc.select(thumbnailTag);
		array.add(element.attr("src"));
	}
}
