package utility;

import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * �X�N���C�s���O�N���X
 */

public class Scraping {
	private static String regex = "";//���K�\���i�[�p
	private String baseUrl = "";
	
	/**
	 * �X�N���C�s���O�����s
	 * @param siteUrl �I�v�V�������j���[�̃J�[�g�ɓ����{�^���������ꂽ�Ƃ��ɕ\�����Ă���URL
	 */
	public static void executeScraping(String siteUrl){
		//URL�`�F�b�N
		if(siteUrl.indexOf("amazon.com") == -1){
			//amazon�̃X�N���C�s���O�\�y�[�W�����ׂ�
			regex = "(https:\\/\\/|http:\\/\\/)www\\.amazon\\.co\\.jp\\/(.+?)\\/dp\\/(.+?)\\/ref=(.+?)\\?ie=UTF8&qid=[0-9]+?&sr=(.+?)&keywords=(.+?)$";
			checkPattern(regex, siteUrl);
		}else if(siteUrl.indexOf("rakuten.com") == -1){
			//�y�V�̃X�N���C�s���O�\�y�[�W�����ׂ�
			
		}else if(siteUrl.indexOf("kakaku.com") == -1){
			//���i�h�b�g�R���̃X�N���C�s���O�\�y�[�W�����ׂ�
			
		}else{
			//�X�N���C�s���O�s�\�ȃy�[�W
			
		}
	}
	
	private static void checkPattern(String regex, String siteUrl){
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(siteUrl);

		if(m.find()){
			System.out.println("�}�b�`���Ă��܂�");
		}else{
			System.out.println("�}�b�`���Ă��܂���");
		}
	}
}
