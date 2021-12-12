package com.zero.gym;


import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class T_queue extends Activity {

	
	String ID = "";
	String Password = "";		// 받아올 아이들 미리 변수선언함
	Button ok,available,unavailable,refresh,ss,ticket,queue;
	String ok1,no1;
	String year = "";		// 받아올 아이들 미리 변수선언함
	String month = "";
	String day = "";
	String time = "";
	String t="";
	String Cname = "";
	String name = "";
	String age = "";		// 받아올 아이들 미리 변수선언함
	String seat = "";
	String phone = "";
	String yesno="";
	String no="";
	String ready="";
	String ready2="";
	String check="";
	String g3="";
	String q1="";
	String q2="";
	String seat1="";
	String seat2="";
	int i=0;
	int Available=0;
	
	ArrayList<String> data;
	ArrayAdapter<String> adapter;
	private static final String SERVER_ADDRESS = "http://115.144.76.143";
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_t_queue);
		data = new ArrayList<String>();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	    StrictMode.setThreadPolicy(policy);
		
		
		 Intent intent = getIntent();
			ID = intent.getStringExtra("ID");		// ID값을 받아옴
			Password = intent.getStringExtra("Password");		// Password값을 받아옴
			no = intent.getStringExtra("no");		// Password값을 받아옴
		
			
		
		
			 queue= (Button)findViewById(R.id.button1);
			 ticket= (Button)findViewById(R.id.button2);
		
		
			 try{//////////////////////////////예약 신청 / 취소
					URL url2 = new URL(SERVER_ADDRESS + "/ticket/ticket_check.php?"
							+ "no=" + URLEncoder.encode(no,"UTF-8")
							+ "&t=" + URLEncoder.encode(t,"UTF-8")						
							);					
					url2.openStream();		
					
					q1  = getXmlData("ticket_check.xml", "result");
					q2  = getXmlData("ticket_check.xml", "result2");
					seat1  = getXmlData("ticket_check.xml", "result3");
					seat2  = getXmlData("ticket_check.xml", "result4");
				} catch(Exception e) {
					Toast.makeText(T_queue.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
					Log.e("Error", e.getMessage());
				}
		        
			   	
			   	
			   	if(q1.equals("")&&q2.equals(""))
			   	{
			   		name="No exsist";
			   		TextView testView1 = (TextView)findViewById(R.id.textView1);
					testView1.setText(name);
			   	}
			   	else
			   	{
			   		TextView testView1 = (TextView)findViewById(R.id.textView1);
					testView1.setText(q1+q2);
			   	}
			   	
				if(seat1.equals("")&&seat2.equals(""))
			   	{
			   		phone="No exsist";
			   		TextView testView1 = (TextView)findViewById(R.id.textView2);
					testView1.setText(phone);
			   	}
			   	else
			   	{
			   		TextView testView1 = (TextView)findViewById(R.id.textView2);
					testView1.setText(seat1+seat2);
			   	}
			   	

				if(seat1.equals("")&&seat2.equals(""))
			   	{
			   		
			   		TextView testView1 = (TextView)findViewById(R.id.textView3);
					testView1.setText("No Exsist");
			   	}
			   	else
			   	{
			   		TextView testView1 = (TextView)findViewById(R.id.textView3);
					testView1.setText("Now Service");
			   	}
			   	
			   	
			   	
			   	
			   	
			   	
			   	
			   	
			    
		        queue.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						
						
						
						Intent intent1 = new Intent(T_queue.this,Conform_no.class);
						
						intent1.putExtra("ID",ID);//여긴 아이디를 보냅니다.
						intent1.putExtra("Password",Password);//여긴 비밀번호를 보냅니다.
						startActivity(intent1);	
						
						
						
						
					}
				});
				

				
		        ticket.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						
						
						
						Toast.makeText(T_queue.this, "This Tab is Ticket.", Toast.LENGTH_SHORT).show();
						
						
						
						
					}
				});
			   	
			   	
			   	
			   	
		
		
		
	}
	
	
	
	
	
	
	
	
	

	 private String getXmlData(String filename, String str){
			String rss = SERVER_ADDRESS + "/ticket/";
			String ret = "";
			
			try{
				XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
				factory.setNamespaceAware(true);
				XmlPullParser xpp = factory.newPullParser();
				URL server = new URL(rss + filename);
				InputStream is = server.openStream();
				xpp.setInput(is, "UTF-8");
				
				int eventType = xpp.getEventType();
				
				while(eventType != XmlPullParser.END_DOCUMENT) {
					if(eventType == XmlPullParser.START_TAG) {
						if(xpp.getName().equals(str)) {
							ret = xpp.nextText();
						}
					}
					eventType = xpp.next();
					
				}
			} catch(Exception e) {
				Log.e("Error", e.getMessage());
			}
			
			return ret;
		}
		
	    private ArrayList<String> getXmlDataList(String filename, String str) { //占승그곤옙 占쏙옙占쏙옙占쏙옙占쏙옙 占쌨아울옙占쏙옙占쏙옙占쏙옙 ArrayList<String>占쏙옙 占쏙옙占쏙옙
			String rss = SERVER_ADDRESS + "/ticket/";
			ArrayList<String> ret = new ArrayList<String>();
			
			try { //XML 占식쏙옙占쏙옙 占쏙옙占쏙옙 占쏙옙占쏙옙
				XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
				factory.setNamespaceAware(true);
				XmlPullParser xpp = factory.newPullParser();
				URL server = new URL(rss + filename);
				InputStream is = server.openStream();
				xpp.setInput(is, "UTF-8");
				
				int eventType = xpp.getEventType();
				
				while(eventType != XmlPullParser.END_DOCUMENT) {
					if(eventType == XmlPullParser.START_TAG) {
						if(xpp.getName().equals(str)) { //占승깍옙 占싱몌옙占쏙옙 str 占쏙옙占쌘곤옙占쏙옙 占쏙옙占쏙옙 占쏙옙占�
							ret.add(xpp.nextText());
						}
					}
					eventType = xpp.next();
				}
			} catch(Exception e) {
				Log.e("Error", e.getMessage());
			}
			
			return ret;
	    }
		
	
	
	
	
	
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.t_queue, menu);
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
