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
		CheckURL.checkURL(siteUrl);
		
		
	}
	
}
