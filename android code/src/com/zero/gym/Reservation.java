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
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Reservation extends Activity implements OnClickListener {

	String year = "";		// 받아올 아이들 미리 변수선언함
	String month = "";
	String dayOfMonth = "";
	String time = "";
	String name = "";
	String t[]={"0"};
	ArrayList<String> data;
	ArrayList<String> array_id;
	ArrayAdapter<String> adapter;
	private static final String SERVER_ADDRESS = "http://115.144.76.143";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reservation);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	    StrictMode.setThreadPolicy(policy);
		
		//intent 결과 등록
		Intent intent = getIntent();
		year = intent.getStringExtra("year");		// year값을 받아옴
		month = intent.getStringExtra("month");		// month값을 받아옴
		dayOfMonth = intent.getStringExtra("dayOfMonth");		// dayOfMonth값을 받아옴
		name = intent.getStringExtra("name");		// name값을 받아옴
		  Toast.makeText(Reservation.this, ""+year+"/"+month+"////"+dayOfMonth+"////"+name, 0).show(); // 값이 제대로 넘어왔는지 확인
		  setTitle(year+"년"+month+"월"+dayOfMonth+"일 체육관 시간정보");
		  

		  ///////////////////여기서부터 해야할 일 = 예약 DB 구조짜야하고 이름,나이,사용할시간(몇시부터 몇시까지) 입력받아서 DB로넘기기 그 후에 예약 됐는지 확인/거절
		  /////////////////// 보내야 되고, 레이아웃은 예약 된 시간 안된시간 이렇게 표시해야하고 (이 정보는 일단 DB에 날짜정보를 보내서 받아와야 할듯
		  /////////////////// 그리고 시간은 한시간단위로 나누어 놓든지 해야할듯해 )
	
		  
		    Button bt1 = (Button)findViewById(R.id.button1);
			bt1.setOnClickListener(this);
			Button bt2 = (Button)findViewById(R.id.button2);
			bt2.setOnClickListener(this);
			Button bt3 = (Button)findViewById(R.id.button3);
			bt3.setOnClickListener(this);
			Button bt4 = (Button)findViewById(R.id.button4);
			bt4.setOnClickListener(this);
			Button bt5 = (Button)findViewById(R.id.button5);
			bt5.setOnClickListener(this);
			Button bt6 = (Button)findViewById(R.id.button6);
			bt6.setOnClickListener(this);
			Button bt7 = (Button)findViewById(R.id.button7);
			bt7.setOnClickListener(this);
			Button bt8 = (Button)findViewById(R.id.button8);
			bt8.setOnClickListener(this);
			Button bt9 = (Button)findViewById(R.id.button9);
			bt9.setOnClickListener(this);
			Button bt10 = (Button)findViewById(R.id.button10);
			bt10.setOnClickListener(this);
			Button bt11 = (Button)findViewById(R.id.button11);
			bt11.setOnClickListener(this);
			Button bt12 = (Button)findViewById(R.id.button12);
			bt12.setOnClickListener(this);
			
		    
			
			
			try{//////////////////////////////예약 신청 / 취소
				URL url = new URL(SERVER_ADDRESS + "/reservation_check_9.php?"
						+ "Cname=" + URLEncoder.encode(name,"UTF-8")
						+ "&month=" + URLEncoder.encode(month,"UTF-8")
						+ "&day=" + URLEncoder.encode(dayOfMonth,"UTF-8")
						);					
				url.openStream();		
				String result = getXmlData("reservation_check_9.xml", "result");
				t[0] = result;
			} catch(Exception e) {
				Toast.makeText(Reservation.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
				Log.e("Error", e.getMessage());
			}
			
			
			
				try{//////////////////////////////예약 신청 / 취소
					URL url = new URL(SERVER_ADDRESS + "/reservation_check_10.php?"
							+ "Cname=" + URLEncoder.encode(name,"UTF-8")
							+ "&month=" + URLEncoder.encode(month,"UTF-8")
							+ "&day=" + URLEncoder.encode(dayOfMonth,"UTF-8")
							);					
					url.openStream();		
					String result = getXmlData("reservation_check_10.xml", "result");
					t[1] = result;
				} catch(Exception e) {
					Toast.makeText(Reservation.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
					Log.e("Error", e.getMessage());
				}
				
				
				
				try{//////////////////////////////예약 성공 / 실패
					URL url = new URL(SERVER_ADDRESS + "/reservation_check_11.php?"
							+ "Cname=" + URLEncoder.encode(name,"UTF-8")
							+ "&month=" + URLEncoder.encode(month,"UTF-8")
							+ "&day=" + URLEncoder.encode(dayOfMonth,"UTF-8")
						
							);					
					url.openStream();		
					String result = getXmlData("reservation_check_11.xml", "result");
					t[2] = result;
				} catch(Exception e) {
					Log.e("Error", e.getMessage());
				}
				
				
				try{//////////////////////////////seat_check
					URL url = new URL(SERVER_ADDRESS + "/reservation_check_12.php?"
							+ "Cname=" + URLEncoder.encode(name,"UTF-8")
							+ "&month=" + URLEncoder.encode(month,"UTF-8")
							+ "&day=" + URLEncoder.encode(dayOfMonth,"UTF-8")
							
							);					
					url.openStream();		
					String result = getXmlData("reservation_check_12.xml", "result");
					t[3] = result;
				} catch(Exception e) {
					Log.e("Error", e.getMessage());
				}
				
				
				try{//////////////////////////////예약 일시
					URL url = new URL(SERVER_ADDRESS + "/reservation_check_13.php?"
							+ "Cname=" + URLEncoder.encode(name,"UTF-8")
							+ "&month=" + URLEncoder.encode(month,"UTF-8")
							+ "&day=" + URLEncoder.encode(dayOfMonth,"UTF-8")
							
							);					
					url.openStream();		
					String result = getXmlData("reservation_check_13.xml", "result");
					t[4] = result;
				} catch(Exception e) {
					Log.e("Error", e.getMessage());
				}
				
				
				try{//////////////////////////////PC방 번호
					URL url = new URL(SERVER_ADDRESS + "/reservation_check_14.php?"
							+ "Cname=" + URLEncoder.encode(name,"UTF-8")
							+ "&month=" + URLEncoder.encode(month,"UTF-8")
							+ "&day=" + URLEncoder.encode(dayOfMonth,"UTF-8")
							
							);					
					url.openStream();		
					String result = getXmlData("reservation_check_14.xml", "result");
					t[5] = result;
				} catch(Exception e) {
					Log.e("Error", e.getMessage());
				}
				
				try{//////////////////////////////PC방 번호
					URL url = new URL(SERVER_ADDRESS + "/reservation_check_15.php?"
							+ "Cname=" + URLEncoder.encode(name,"UTF-8")
							+ "&month=" + URLEncoder.encode(month,"UTF-8")
							+ "&day=" + URLEncoder.encode(dayOfMonth,"UTF-8")
						
							);					
					url.openStream();		
					String result = getXmlData("reservation_check_15.xml", "result");
					t[6] = result;
				} catch(Exception e) {
					Log.e("Error", e.getMessage());
				}
				
				try{//////////////////////////////PC방 번호
					URL url = new URL(SERVER_ADDRESS + "/reservation_check_16.php?"
							+ "Cname=" + URLEncoder.encode(name,"UTF-8")
							+ "&month=" + URLEncoder.encode(month,"UTF-8")
							+ "&day=" + URLEncoder.encode(dayOfMonth,"UTF-8")
							
							);					
					url.openStream();		
					String result = getXmlData("reservation_check_16.xml", "result");
					t[7] = result;
				} catch(Exception e) {
					Log.e("Error", e.getMessage());
				}
				
				try{//////////////////////////////PC방 번호
					URL url = new URL(SERVER_ADDRESS + "/reservation_check_17.php?"
							+ "Cname=" + URLEncoder.encode(name,"UTF-8")
							+ "&month=" + URLEncoder.encode(month,"UTF-8")
							+ "&day=" + URLEncoder.encode(dayOfMonth,"UTF-8")
							
							);					
					url.openStream();		
					String result = getXmlData("reservation_check_17.xml", "result");
					t[8] = result;
				} catch(Exception e) {
					Log.e("Error", e.getMessage());
				}
	
				try{//////////////////////////////PC방 번호
					URL url = new URL(SERVER_ADDRESS + "/reservation_check_18.php?"
							+ "Cname=" + URLEncoder.encode(name,"UTF-8")
							+ "&month=" + URLEncoder.encode(month,"UTF-8")
							+ "&day=" + URLEncoder.encode(dayOfMonth,"UTF-8")
							
							);					
					url.openStream();		
					String result = getXmlData("reservation_check_18.xml", "result");
					t[9] = result;
				} catch(Exception e) {
					Log.e("Error", e.getMessage());
				}
			
				try{//////////////////////////////PC방 번호
					URL url = new URL(SERVER_ADDRESS + "/reservation_check_19.php?"
							+ "Cname=" + URLEncoder.encode(name,"UTF-8")
							+ "&month=" + URLEncoder.encode(month,"UTF-8")
							+ "&day=" + URLEncoder.encode(dayOfMonth,"UTF-8")
							
							);					
					url.openStream();		
					String result = getXmlData("reservation_check_19.xml", "result");
					t[10] = result;
				} catch(Exception e) {
					Log.e("Error", e.getMessage());
				}
			
				try{//////////////////////////////PC방 번호
					URL url = new URL(SERVER_ADDRESS + "/reservation_check_20.php?"
							+ "Cname=" + URLEncoder.encode(name,"UTF-8")
							+ "&month=" + URLEncoder.encode(month,"UTF-8")
							+ "&day=" + URLEncoder.encode(dayOfMonth,"UTF-8")
							
							);					
					url.openStream();		
					String result = getXmlData("reservation_check_20.xml", "result");
					t[11] = result;
				} catch(Exception e) {
					Log.e("Error", e.getMessage());
				}
			
				
				Toast.makeText(Reservation.this, t[0], Toast.LENGTH_SHORT).show();
				if(t[0]==null)
					t[0]="예약없음";
				else if(t[0].equals('1'))
					t[0]="예약있음";
				TextView testView3 = (TextView)findViewById(R.id.textView3);
				testView3.setText(t[0]);	
				/*
				if(t[1].equals("0"))
					t[1]="예약없음";
				else if(t[1].equals("1"))
					t[1]="예약있음";
				TextView testView4 = (TextView)findViewById(R.id.textView4);
				testView4.setText(t[1]);
				
				if(t[2].equals("0"))
					t[2]="예약없음";
				else if(t[2].equals("1"))
					t[2]="예약있음";
				TextView testView5 = (TextView)findViewById(R.id.textView5);
				testView5.setText(t[2]);
				
				if(t[3].equals("0"))
					t[3]="예약없음";
				else if(t[3].equals("1"))
					t[3]="예약있음";
				TextView testView6 = (TextView)findViewById(R.id.textView6);
				testView6.setText(t[3]);
				
				if(t[4].equals("0"))
					t[4]="예약없음";
				else if(t[4].equals("1"))
					t[4]="예약있음";
				TextView testView7 = (TextView)findViewById(R.id.textView7);
				testView7.setText(t[4]);
				
				if(t[5].equals("0"))
					t[5]="예약없음";
				else if(t[5].equals("1"))
					t[5]="예약있음";
				TextView testView8 = (TextView)findViewById(R.id.textView8);
				testView8.setText(t[5]);
				
				if(t[6].equals("0"))
					t[6]="예약없음";
				else if(t[6].equals("1"))
					t[6]="예약있음";
				TextView testView9 = (TextView)findViewById(R.id.textView9);
				testView9.setText(t[6]);
				
				if(t[7].equals("0"))
					t[7]="예약없음";
				else if(t[7].equals("1"))
					t[7]="예약있음";
				TextView testView10 = (TextView)findViewById(R.id.textView10);
				testView10.setText(t[7]);
				
				if(t[8].equals("0"))
					t[8]="예약없음";
				else if(t[8].equals("1"))
					t[8]="예약있음";
				TextView testView11 = (TextView)findViewById(R.id.textView11);
				testView11.setText(t[8]);
				
				if(t[9].equals("0"))
					t[9]="예약없음";
				else if(t[9].equals("1"))
					t[9]="예약있음";
				TextView testView12 = (TextView)findViewById(R.id.textView12);
				testView12.setText(t[9]);
				
				if(t[10].equals("0"))
					t[10]="예약없음";
				else if(t[10].equals("1"))
					t[10]="예약있음";
				TextView testView13 = (TextView)findViewById(R.id.textView13);
				testView13.setText(t[10]);
				
				if(t[11].equals("0"))
					t[11]="예약없음";
				else if(t[11].equals("1"))
					t[11]="예약있음";
				TextView testView14 = (TextView)findViewById(R.id.textView14);
				testView14.setText(t[11]);
				
				
				*/
				
				  
		  
		  
		  
		  
		  
		  
	}

	
	
	

	
	
	
	
	 private String getXmlData(String filename, String str){
			String rss = SERVER_ADDRESS + "/";
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
			String rss = SERVER_ADDRESS + "/";
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
			Intent intent1 = new Intent(this,Send.class);
			intent1.putExtra("year",year);//여긴 년도를 보냅니다.
			intent1.putExtra("month",month);//여긴 달을 보냅니다.
			intent1.putExtra("dayOfMonth",dayOfMonth);//여긴 날짜를 보냅니다.
			intent1.putExtra("time","09:00~10:00");//여긴 시간을 보냅니다.	
			intent1.putExtra("name",name);//여긴 체육관이름을 보냅니다.
			startActivity(intent1);			
			break;

		case R.id.button2:
			Intent intent2 = new Intent(this,Send.class);
			intent2.putExtra("year",year);//여긴 년도를 보냅니다.
			intent2.putExtra("month",month);//여긴 달을 보냅니다.
			intent2.putExtra("dayOfMonth",dayOfMonth);//여긴 날짜를 보냅니다.
			intent2.putExtra("time","10:00~11:00");//여긴 시간을 보냅니다.
			intent2.putExtra("name",name);//여긴 체육관이름을 보냅니다.
			startActivity(intent2);		
			break;
				
		case R.id.button3:
			Intent intent3 = new Intent(this,Send.class);
			intent3.putExtra("year",year);//여긴 년도를 보냅니다.
			intent3.putExtra("month",month);//여긴 달을 보냅니다.
			intent3.putExtra("dayOfMonth",dayOfMonth);//여긴 날짜를 보냅니다.
			intent3.putExtra("time","11:00~12:00");//여긴 시간을 보냅니다.
			intent3.putExtra("name",name);//여긴 체육관이름을 보냅니다.
			startActivity(intent3);		
			break;
				
		case R.id.button4:
			Intent intent4 = new Intent(this,Send.class);
			intent4.putExtra("year",year);//여긴 년도를 보냅니다.
			intent4.putExtra("month",month);//여긴 달을 보냅니다.
			intent4.putExtra("dayOfMonth",dayOfMonth);//여긴 날짜를 보냅니다.
			intent4.putExtra("time","12:00~13:00");//여긴 시간을 보냅니다.
			intent4.putExtra("name",name);//여긴 체육관이름을 보냅니다.
			startActivity(intent4);		
			break;
		case R.id.button5:
			Intent intent5 = new Intent(this,Send.class);
			intent5.putExtra("year",year);//여긴 년도를 보냅니다.
			intent5.putExtra("month",month);//여긴 달을 보냅니다.
			intent5.putExtra("dayOfMonth",dayOfMonth);//여긴 날짜를 보냅니다.
			intent5.putExtra("time","13:00~14:00");//여긴 시간을 보냅니다.
			intent5.putExtra("name",name);//여긴 체육관이름을 보냅니다.
			startActivity(intent5);		
			break;
		case R.id.button6:
			Intent intent6 = new Intent(this,Send.class);
			intent6.putExtra("year",year);//여긴 년도를 보냅니다.
			intent6.putExtra("month",month);//여긴 달을 보냅니다.
			intent6.putExtra("dayOfMonth",dayOfMonth);//여긴 날짜를 보냅니다.
			intent6.putExtra("time","14:00~15:00");//여긴 시간을 보냅니다.
			intent6.putExtra("name",name);//여긴 체육관이름을 보냅니다.
			startActivity(intent6);		
			break;
		case R.id.button7:
			Intent intent7 = new Intent(this,Send.class);
			intent7.putExtra("year",year);//여긴 년도를 보냅니다.
			intent7.putExtra("month",month);//여긴 달을 보냅니다.
			intent7.putExtra("dayOfMonth",dayOfMonth);//여긴 날짜를 보냅니다.
			intent7.putExtra("time","15:00~16:00");//여긴 시간을 보냅니다.
			intent7.putExtra("name",name);//여긴 체육관이름을 보냅니다.
			startActivity(intent7);		
			break;
		case R.id.button8:
			Intent intent8 = new Intent(this,Send.class);
			intent8.putExtra("year",year);//여긴 년도를 보냅니다.
			intent8.putExtra("month",month);//여긴 달을 보냅니다.
			intent8.putExtra("dayOfMonth",dayOfMonth);//여긴 날짜를 보냅니다.
			intent8.putExtra("time","16:00~17:00");//여긴 시간을 보냅니다.
			intent8.putExtra("name",name);//여긴 체육관이름을 보냅니다.
			startActivity(intent8);		
			break;
		case R.id.button9:
			Intent intent9 = new Intent(this,Send.class);
			intent9.putExtra("year",year);//여긴 년도를 보냅니다.
			intent9.putExtra("month",month);//여긴 달을 보냅니다.
			intent9.putExtra("dayOfMonth",dayOfMonth);//여긴 날짜를 보냅니다.
			intent9.putExtra("time","17:00~18:00");//여긴 시간을 보냅니다.
			intent9.putExtra("name",name);//여긴 체육관이름을 보냅니다.
			startActivity(intent9);		
			break;
		case R.id.button10:
			Intent intent10 = new Intent(this,Send.class);
			intent10.putExtra("year",year);//여긴 년도를 보냅니다.
			intent10.putExtra("month",month);//여긴 달을 보냅니다.
			intent10.putExtra("dayOfMonth",dayOfMonth);//여긴 날짜를 보냅니다.
			intent10.putExtra("time","18:00~19:00");//여긴 시간을 보냅니다.
			intent10.putExtra("name",name);//여긴 체육관이름을 보냅니다.
			startActivity(intent10);		
			break;
		case R.id.button11:
			Intent intent11 = new Intent(this,Send.class);
			intent11.putExtra("year",year);//여긴 년도를 보냅니다.
			intent11.putExtra("month",month);//여긴 달을 보냅니다.
			intent11.putExtra("dayOfMonth",dayOfMonth);//여긴 날짜를 보냅니다.
			intent11.putExtra("time","19:00~20:00");//여긴 시간을 보냅니다.
			intent11.putExtra("name",name);//여긴 체육관이름을 보냅니다.
			startActivity(intent11);		
			break;
		case R.id.button12:
			Intent intent12 = new Intent(this,Send.class);
			intent12.putExtra("year",year);//여긴 년도를 보냅니다.
			intent12.putExtra("month",month);//여긴 달을 보냅니다.
			intent12.putExtra("dayOfMonth",dayOfMonth);//여긴 날짜를 보냅니다.
			intent12.putExtra("time","20:00~21:00");//여긴 시간을 보냅니다.
			intent12.putExtra("name",name);//여긴 체육관이름을 보냅니다.
			startActivity(intent12);		
			break;
			
		}		
		
		
		
		
		
		
	}
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.reservation, menu);
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
