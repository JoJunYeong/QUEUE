package com.zero.gym;


import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Reservation_check extends Activity implements OnClickListener {

	
int i=0;
	String name = "";
	String age = "";		// 받아올 아이들 미리 변수선언함
	String seat = "";
	String yesno="";
	String check="";
	String no="";
	String no2="";
	ArrayList<String> data;
	ArrayAdapter<String> adapter;
	private static final String SERVER_ADDRESS = "http://115.144.76.143";
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reservation_check);
		
		
		data = new ArrayList<String>();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	    StrictMode.setThreadPolicy(policy);
		
	    
	    
		//intent 결과 등록
				Intent intent = getIntent();
	
				name = intent.getStringExtra("name");		// name값을 받아옴
				age = intent.getStringExtra("age");
				
			
			
				 try{//////////////////////////////예약 신청 / 취소
						URL url = new URL(SERVER_ADDRESS + "/ticket/g_check.php?"
								+ "name=" + URLEncoder.encode(name,"UTF-8")
								+ "&age=" + URLEncoder.encode(age,"UTF-8")
								);					
						url.openStream();		
						check = getXmlData("g_check.xml", "result1");
						yesno= getXmlData("g_check.xml", "result2");
						
					} catch(Exception e) {
						Toast.makeText(Reservation_check.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
						Log.e("Error", e.getMessage());
					}
				
				
				
				if(check.equals("1"))
				{
					i=1;
				}
				
				if(yesno.equals(""))
				{
					yesno="No information of Question.";
				}
				
				
				
				
				
				 Button bt1 = (Button)findViewById(R.id.button1);
					bt1.setOnClickListener(this);
					
					 Button bt2 = (Button)findViewById(R.id.button2);
						bt2.setOnClickListener(this);
						
						Button bt3 = (Button)findViewById(R.id.button3);
						bt3.setOnClickListener(this);

						 Button bt4 = (Button)findViewById(R.id.button4);
							bt4.setOnClickListener(this);			
				
				
					TextView testView8 = (TextView)findViewById(R.id.textView1);
					testView8.setText(yesno);
					
					
				
					
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
		
	
	
	
	
	public void onClick(View v)
	{
		switch (v.getId()) {
		case R.id.button1:
			Intent intent1 = new Intent(this,Personal.class);
			startActivity(intent1);			
		//	finish();
			break;
		case R.id.button2:
			Toast.makeText(Reservation_check.this, "This Tab is Ticket", Toast.LENGTH_SHORT).show();
			break;
		case R.id.button3:
			if(i==0)
			{
				Toast.makeText(Reservation_check.this, "You can't push this button", Toast.LENGTH_SHORT).show();
			}
			else if(i==1)
			{
				
				

				 try{//////////////////////////////예약 신청 / 취소
						URL url = new URL(SERVER_ADDRESS + "/ticket/end.php?"
								+ "name=" + URLEncoder.encode(name,"UTF-8")
								+ "&age=" + URLEncoder.encode(age,"UTF-8")
								);					
						url.openStream();		
						check = getXmlData("end.xml", "result");
						no = getXmlData("end.xml", "result2");
						no2 = getXmlData("end.xml", "result3");
					} catch(Exception e) {
						Toast.makeText(Reservation_check.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
						Log.e("Error", e.getMessage());
					}
				
				 

				 if(no2.equals("0"))
				 {
					 try{//////////////////////////////예약 신청 / 취소
							URL url = new URL(SERVER_ADDRESS + "/ticket/available.php?"
									+ "no=" + URLEncoder.encode(no,"UTF-8")
													
									);					
							url.openStream();		
							
							
							Toast.makeText(Reservation_check.this, "Set avaliable.", Toast.LENGTH_SHORT).show();
							
							 try{//////////////////////////////예약 신청 / 취소
									URL url2 = new URL(SERVER_ADDRESS + "/ticket/t_check_available.php?"
											+ "no=" + URLEncoder.encode(no,"UTF-8")
																
											);					
									url2.openStream();		
									
								
									
								} catch(Exception e) {
									Toast.makeText(Reservation_check.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
									Log.e("Error", e.getMessage());
								}
					 } catch(Exception e) {
							Toast.makeText(Reservation_check.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
							Log.e("Error", e.getMessage());
						}
				 }
				 else
				 {
					 try{//////////////////////////////예약 신청 / 취소
							URL url = new URL(SERVER_ADDRESS + "/ticket/available.php?"
									+ "no=" + URLEncoder.encode(no2,"UTF-8")
													
									);					
							url.openStream();		
							
							
							Toast.makeText(Reservation_check.this, "Set avaliable.", Toast.LENGTH_SHORT).show();
							
							 try{//////////////////////////////예약 신청 / 취소
									URL url2 = new URL(SERVER_ADDRESS + "/ticket/t_check_available.php?"
											+ "no=" + URLEncoder.encode(no2,"UTF-8")
																
											);					
									url2.openStream();		
									
								
									
								} catch(Exception e) {
									Toast.makeText(Reservation_check.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
									Log.e("Error", e.getMessage());
								}
					 } catch(Exception e) {
							Toast.makeText(Reservation_check.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
							Log.e("Error", e.getMessage());
						}
					 
				 }
				 
				 
				 
				 
				 if(check.equals("1"))
				 {
					 Toast.makeText(Reservation_check.this, "done.", Toast.LENGTH_SHORT).show();
					 finish();
				 }				 
				 else
				 {
					 Toast.makeText(Reservation_check.this, "Retry.", Toast.LENGTH_SHORT).show();
					 
				 }	
				 
				 
				 
				 
				 
				 
				 
				 
			}
			break;
		case R.id.button4:
			
			if(i==0)
			{
				Toast.makeText(Reservation_check.this, "You can't push this button", Toast.LENGTH_SHORT).show();
			}
			else if(i==1)
			{
				try{//////////////////////////////예약 신청 / 취소
					URL url = new URL(SERVER_ADDRESS + "/ticket/g_check2.php?"
							+ "name=" + URLEncoder.encode(name,"UTF-8")
							+ "&age=" + URLEncoder.encode(age,"UTF-8")
							);					
					url.openStream();		
					check = getXmlData("g_check2.xml", "result1");
					
					
				} catch(Exception e) {
					Toast.makeText(Reservation_check.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
					Log.e("Error", e.getMessage());
				}
				
				if(check.equals("0"))
				{
				try{//////////////////////////////예약 신청 / 취소
					URL url = new URL(SERVER_ADDRESS + "/ticket/another.php?"
							+ "name=" + URLEncoder.encode(name,"UTF-8")
							+ "&age=" + URLEncoder.encode(age,"UTF-8")
							);					
					url.openStream();		
					check = getXmlData("another.xml", "result");
					no=getXmlData("another.xml", "result2");
					
					 try{//////////////////////////////예약 신청 / 취소
							URL url2 = new URL(SERVER_ADDRESS + "/ticket/available.php?"
									+ "no=" + URLEncoder.encode(no,"UTF-8")
													
									);					
							url2.openStream();		
							
							
							Toast.makeText(Reservation_check.this, "Set avaliable.", Toast.LENGTH_SHORT).show();
							
							 try{//////////////////////////////예약 신청 / 취소
									URL url3 = new URL(SERVER_ADDRESS + "/ticket/t_check_available.php?"
											+ "no=" + URLEncoder.encode(no,"UTF-8")
																
											);					
									url3.openStream();		
									
								
									
								} catch(Exception e) {
									Toast.makeText(Reservation_check.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
									Log.e("Error", e.getMessage());
								}
					 } catch(Exception e) {
							Toast.makeText(Reservation_check.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
							Log.e("Error", e.getMessage());
						}
					
					Toast.makeText(Reservation_check.this, "Another Teacher Call.", Toast.LENGTH_SHORT).show();
					finish();
					
					
					
					
				} catch(Exception e) {
					Toast.makeText(Reservation_check.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
					Log.e("Error", e.getMessage());
				}
				
				reservation_search();
				}
				else
				{
					Toast.makeText(Reservation_check.this, "You can't push this button. click another button", Toast.LENGTH_SHORT).show();
					
				}
				
				
				
			}
			
			break;
		}
	}
	
	


	@SuppressLint("NewApi")
	public void reservation_search()
	{

//	Intent intent = new Intent(BackgroundSearch.this, Reservation_check.class);
//	intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//	PendingIntent content = PendingIntent.getActivity(BackgroundSearch.this,0, intent,0);

		NotificationManager nm = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
		Notification noti = new Notification.Builder(Reservation_check.this)
				.setTicker("대기중")
				.setContentTitle("대기중")
				.setContentText("대기중")
				.setSmallIcon(R.drawable.ic_launcher)
				.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher))


//	.setContentIntent(content)
				.build();
		noti.flags = noti.flags | noti.FLAG_AUTO_CANCEL;
		nm.notify(1235,noti);



		try{
			URL url_3 = new URL(SERVER_ADDRESS + "/ticket/reservation_search.php?"
					//+ "&birth=" + birth
					//+ "&seat=" + seat);
					+ "name=" + URLEncoder.encode(name,"UTF-8")
					+ "&age=" + URLEncoder.encode(age,"UTF-8")
					+ "&seat=" + URLEncoder.encode(seat,"UTF-8")
				
			);

			url_3.openStream();
		}
		catch(Exception e){
			Toast.makeText(Reservation_check.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
			//Log.e("Error", e.getMessage()); //onDestory();
		}// try catch 臾�醫낅즺
		String result2 = getXmlData("reservation_search.xml", "result");
		
		if(result2.equals("1")) {
			//리턴받은경우

			try{///////////////////////////////////////////////////////////////예약 성공
				URL url_5 = new URL(SERVER_ADDRESS + "/ticket/reservation_success.php?"
						+ "name=" + URLEncoder.encode(name,"UTF-8")
						+ "&age=" + URLEncoder.encode(age,"UTF-8")
						+ "&seat=" + URLEncoder.encode(seat,"UTF-8")
					
				);

				url_5.openStream();
			}
			catch(Exception e){
				Toast.makeText(Reservation_check.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
				//Log.e("Error", e.getMessage());
			}// try catch 臾�醫낅즺
			String result3 = getXmlData("reservation_success.xml", "result");
			if(result3.equals("1")){
				success();

			}



		}
		else{
			Handler handler = new Handler();
			handler.postDelayed(new Runnable() {
				public void run() {
					reservation_search();
				}
			},2000);//핸들러


		}
	}

	
	/* 성공시 알림*/
	@SuppressLint("NewApi")
	public void success()
	{
	/* 완료 Notification */
//	Intent intent = new Intent(BackgroundSearch.this, Reservation_check.class);
//	intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//	PendingIntent content = PendingIntent.getActivity(BackgroundSearch.this,0, intent,0);


		NotificationManager nm = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
		Notification noti = new Notification.Builder(Reservation_check.this)
				.setTicker("선생님 오시는중")
				.setContentTitle("선생님 오시는중")
				.setSmallIcon(R.drawable.ic_launcher)
				.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher))
				.setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE)

//	.setContentIntent(content)
				.build();
		noti.flags = noti.flags | noti.FLAG_AUTO_CANCEL;
		nm.notify(1234, noti);


	}



	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.reservation_check, menu);
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
