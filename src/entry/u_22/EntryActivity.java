package entry.u_22;

import java.net.URL;

import utility.CheckURL;

import android.support.v7.app.ActionBarActivity;
import android.telephony.TelephonyManager;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;


public class EntryActivity extends ActionBarActivity {
	public static final int MENU_SELECT_titleList = 0;
	private TelephonyManager tel;
	private WebView webview;
	private String device_id;
	private URL webURL;
	
	@SuppressLint({ "SetJavaScriptEnabled", "JavascriptInterface" }) 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);
        tel=(TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        webview=(WebView)findViewById(R.id.webView);
        //デバイス上でWEB表示をさせるための設定
        webview.setWebViewClient(new WebViewClient());
        //javascript有効設定
        webview.getSettings().setJavaScriptEnabled(true);
        //javascript等UIを変更される処理の設定
        webview.setWebChromeClient(new WebChromeClient());
        //余白を消す
        webview.setVerticalScrollbarOverlay(true);
        
        /**
		 * JavaScriptを使うクラスの前にこれをやってあげると JavaScriptからJavaが見れるようになる
		 */
        webview.addJavascriptInterface(new Object() {
			@JavascriptInterface
			public void pushLink(String check) {
				shopping_url(check);
			}
		}, "app");
    }
    
    
    
    //Activityに来た時
    @Override
    protected void onStart(){
    	super.onStart();
    	deviceid();
    }
    
    //activityへ行く時
    @SuppressLint("SetJavaScriptEnabled") @Override
    protected void onResume(){
    	super.onResume();
        deviceid();
        webview.loadUrl("http://hal.ovdesign.jp/u22/php/logincheck.php?id="+device_id);
    }

    private void deviceid(){
    	device_id=tel.getDeviceId();
    }
    
    //遷移した後のWebViewのパスを決める物
    private void shopping_url(String check){
    	Intent intent=new Intent(this,CartActivity.class);
    	intent.putExtra("check", check);
    	startActivity(intent);
    	
    }
    
    
    //Android搭載の戻るボタン押した時にブラウザを一つ戻すという動作
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
         if(keyCode == KeyEvent.KEYCODE_BACK && webview.canGoBack()) {
              webview.goBack();
              return true;
         }
         return super.onKeyDown(keyCode, event);
    }
    
    
    //オプションメニュー項目作成
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.entry, menu);
        //オプションメニュー押したときの表示ボタンの配列番号と名前設定
        menu.add(0,MENU_SELECT_titleList,1,getString(R.string.action_titlelist));
        return true;
    }
    
    //オプションメニュー項目の選択時動作
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
                
        if (id == R.id.action_settings) {
            return true;
        }else if(id == MENU_SELECT_titleList){
        	//ここが「タイトルリストへ」を押した時のクリックリスナー
        	Toast.makeText(EntryActivity.this,"押したで",Toast.LENGTH_LONG).show();
        }else if(id == R.id.in_cart){
        	//Toast.makeText(EntryActivity.this,"カートに入れる",Toast.LENGTH_LONG).show();
        	String url = webview.getUrl();
        	CheckURL cu = new CheckURL();
        	cu.checkURL(url);
        }
        
        return super.onOptionsItemSelected(item);
    }
}
