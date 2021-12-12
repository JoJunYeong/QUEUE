package com.zero.gym;




import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Map extends Activity {
	
	WebView tipView;
	String name = "0";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
		
		
		Intent intent = getIntent();
		name=intent.getStringExtra("name");
		
		
		
		
		   tipView = (WebView) findViewById(R.id.webView1);
	        tipView.getSettings().setJavaScriptEnabled(true);
	        if(name.equals("수원보훈요양원"))
	        tipView.loadUrl("http://map.naver.com/local/siteview.nhn?code=12929514");				
	        else if(name.equals("다솔초등학교"))
	        tipView.loadUrl("http://map.naver.com/local/siteview.nhn?code=32505164");
	        else if(name.equals("경기대학교"))
		    tipView.loadUrl("http://map.naver.com/local/siteview.nhn?code=11591483");
	        
	        tipView.setWebViewClient(new WebViewtestClient());
		
			
		
		
		
		
		
	}

	
	@Override
    public boolean onKeyDown(int keyCode, KeyEvent event) { 
        if ((keyCode == KeyEvent.KEYCODE_BACK) && tipView.canGoBack()) { 
        	tipView.goBack(); 
            return true; 
        } 
        
        else{
        switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			finish();
			break;
		default:
			break;
        }
		}
		return super.onKeyDown(keyCode, event);
    }
     
 
////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	
	public class WebViewtestClient extends WebViewClient {
		
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			view.loadUrl(url);
			return true;
		}

    }
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.map, menu);
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
