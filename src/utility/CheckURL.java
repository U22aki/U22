package utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckURL {
	private static boolean correctURL = false;
	
	/**
	 * URL���`�F�b�N
	 */
	public static boolean checkURL(String siteUrl){
		String regex = "";
		
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
		return correctURL;
	}
	
	private static void checkPattern(String regex, String siteUrl){
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(siteUrl);

		if(m.find()){
			correctURL = true;;
		}else{
			correctURL = false;
		}
	}
}
