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
	 * ここでのデバイスIDはスクレイピングした時のDBアクセス時に使う
	 */
	private String device_id;
	
	@SuppressLint({ "SetJavaScriptEnabled", "JavascriptInterface" }) 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cart);
		
		
		tel=(TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        webview=(WebView)findViewById(R.id.webView2);
        //デバイス上でWEB表示をさせるための設定
        webview.setWebViewClient(new WebViewClient());
        //javascript有効設定
        webview.getSettings().setJavaScriptEnabled(true);
        //javascript等UIを変更される処理の設定
        webview.setWebChromeClient(new WebChromeClient());
        //余白を消す
        webview.setVerticalScrollbarOverlay(true);
	}
	
	 //activityへ行く時
    @SuppressLint("SetJavaScriptEnabled") @Override
    protected void onResume(){
    	super.onResume();
    	
    	String check=getIntent().getExtras().getString("check");
    	String url="";
    	if(check.equals("amazon")){
    		url="https://www.amazon.co.jp/";
    	}else if(check.equals("rakuten")){
    		url="http://www.rakuten.co.jp/";
    	}else if(check.equals("kakaku")){
    		url="http://s.kakaku.com/";
    	}
    	
        deviceid();
        webview.loadUrl(url);
    }
	
    private void deviceid(){
    	device_id=tel.getDeviceId();
    }

    
    
  //Android搭載の戻るボタン押した時の動作
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
    	//初めのページへ戻ったならフィニッシュさせる
    	if(webview.getUrl().equals("https://www.amazon.co.jp/") || 
   			 webview.getUrl().equals("http://www.rakuten.co.jp/") ||
   			 webview.getUrl().equals("http://s.kakaku.com/")){
   		 System.out.println("ふぃにしゅ");
   		 finish();
    	}else if(keyCode == KeyEvent.KEYCODE_BACK && webview.canGoBack()) {
        	 System.out.println("aa");
        		 webview.goBack();
             return true;
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
