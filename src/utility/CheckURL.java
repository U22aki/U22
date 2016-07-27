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
			//�p�^�[���P
			regex = "(https:\\/\\/|http:\\/\\/)www\\.amazon\\.co\\.jp\\/(.+?)\\/dp\\/(.+?)\\/ref=(.+?)\\?ie=UTF8&qid=[0-9]+?&sr=(.+?)&keywords=(.+?)$";
			checkPattern(regex, siteUrl);
			//�p�^�[���Q
			regex = "(https:\\/\\/|http:\\/\\/)www\\.amazon\\.co\\.jp\\/gp\\/product\\/(.+?)\\/ref=(.+?)\\?(.+?)=[0-9A-Z]+?&pf_rd_s=desktop-[0-9]+?&pf_rd_r=[0-9A-Z]+?&pf_rd_t=[0-9]+?&pf_rd_p=(.+?)&pf_rd_i=desktop";
			checkPattern(regex, siteUrl);
		}else if(siteUrl.indexOf("rakuten.com") == -1 && !correctURL){
			//�y�V�̃X�N���C�s���O�\�y�[�W�����ׂ�
			//�p�^�[���P
			regex = "(https:\\/\\/|http:\\/\\/)item\\.rakuten\\.co\\.jp\\/[^/]+?\\/[^/]+?";
			checkPattern(regex, siteUrl);
			//�p�^�[���Q
			regex = "(https:\\/\\/|http:\\/\\/)item\\.rakuten\\.co\\.jp\\/(.+?)\\/(.+?)\\?s-id=(.+?)&xuseflg_ichiba[0-9]+?=[0-9]+?";
			checkPattern(regex, siteUrl);
			//�p�^�[���R
			regex = "(https:\\/\\/|http:\\/\\/)www\\.rakuten\\.co\\.jp\\/[^/]+?\\/\\?s-id=(.+?)";
			checkPattern(regex, siteUrl);
			//�p�^�[���S
			regex = "(https:\\/\\/|http:\\/\\/)item\\.rakuten\\.co\\.jp\\/[^/]+?\\/[^/]+?\\/\\?iasid=[a-z0-9]+?_[a-z0-9]+?__[a-z0-9]+?_[0-9a-z]+?-(.+?)-(.+?)";
			checkPattern(regex, siteUrl);
		}else if(siteUrl.indexOf("kakaku.com") == -1 && !correctURL){
			//���i�h�b�g�R���̃X�N���C�s���O�\�y�[�W�����ׂ�
			//�p�^�[���P
			regex = "(https:\\/\\/|http:\\/\\/)kakaku\\.com\\/item\\/[0-9A-Z]+?\\/";
			checkPattern(regex, siteUrl);
			//�p�^�[���Q
			regex = "(https:\\/\\/|http:\\/\\/)kakaku\\.com\\/item/[0-9A-Z]+?\\/\\?(lid|id)=[^/]+?$";
			checkPattern(regex, siteUrl);
			//�p�^�[���R
			regex = "(https:\\/\\/|http:\\/\\/)bbs\\.kakaku\\.com\\/bbs/[0-9A-Z]+?\\/SortID=[0-9]+?\\/\\?(.+?)id=(.+?)#tab";
			checkPattern(regex, siteUrl);
		}else{
			//�X�N���C�s���O�s�\�ȃy�[�W
			correctURL = false;
		}
		return correctURL;
	}
	
	/**
	 * �擾�����T�C�gURL�����K�\���i���i�ڍ׃y�[�W�j�ɊY�����Ă�����correctURL��true�ɂ���
	 * @param regex ���K�\��
	 * @param siteUrl�@�擾�����T�C�gURL
	 */
	private static void checkPattern(String regex, String siteUrl){
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(siteUrl);

		if(m.find()){
			correctURL = true;
		}else{
			correctURL = false;
		}
	}
}
