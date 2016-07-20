package entry.u_22;

import android.support.v7.app.ActionBarActivity;
import android.telephony.TelephonyManager;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class CartActivity extends ActionBarActivity {
	private TelephonyManager tel;
	private WebView webview;
	/**
	 * �����ł̃f�o�C�XID�̓X�N���C�s���O��������DB�A�N�Z�X���Ɏg��
	 */
	private String device_id;
	
	@SuppressLint({ "SetJavaScriptEnabled", "JavascriptInterface" }) 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cart);
		
		
		tel=(TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        webview=(WebView)findViewById(R.id.webView2);
        //�f�o�C�X���WEB�\���������邽�߂̐ݒ�
        webview.setWebViewClient(new WebViewClient());
        //javascript�L���ݒ�
        webview.getSettings().setJavaScriptEnabled(true);
        //javascript��UI��ύX����鏈���̐ݒ�
        webview.setWebChromeClient(new WebChromeClient());
        //�]��������
        webview.setVerticalScrollbarOverlay(true);
	}
	
	 //activity�֍s����
    @SuppressLint("SetJavaScriptEnabled") @Override
    protected void onResume(){
    	super.onResume();
    	
    	String check=getIntent().getExtras().getString("check");
    	String url="";
    	if(check.equals("amazon")){
    		url="http://www.amazon.co.jp/";
    	}else if(check.equals("rakuten")){
    		url="http://www.rakuten.co.jp/";
    	}else if(check.equals("kakaku")){
    		url="http://kakaku.com/";
    	}
    	
        deviceid();
        webview.loadUrl(url);
    }
	
    private void deviceid(){
    	device_id=tel.getDeviceId();
    }

    
    
  //Android���ڂ̖߂�{�^�����������Ƀu���E�U����߂��Ƃ�������
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
         if(keyCode == KeyEvent.KEYCODE_BACK && webview.canGoBack()) {
              finish();
         }
         return super.onKeyDown(keyCode, event);
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.cart, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
