package com.zero.gym;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import android.content.Intent;
import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;
import android.os.Handler;
import android.util.Log;
import android.widget.ListView;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;


public class BackgroundSearch extends Service {

	private final String TAG = "BackgroundSearch";
	ListView list;
	
	private static final String SERVER_ADDRESS = "http://115.144.76.143"; //�쒕쾭 IP瑜��꾩뿭蹂�닔濡�.
	
	String tagname, content; //xml���쒓렇���댁슜���닿린 �꾪븳 蹂�닔
	ArrayList<String> data;
	ArrayAdapter<String> adapter;
	boolean mQuit;
	int q;
	String year = "";		// 받아올 아이들 미리 변수선언함
	String month = "";
	String day = "";
	String time = "";
	String Cname = "";
	String name = "";
	String age = "";		// 받아올 아이들 미리 변수선언함
	String perpose = "";
	String phone = "";
	
	int count=0;
	String num = "0";
	
	public void onCreate(){
		super.onCreate();	
	}
	
	
	public void onDestory(){
	//	super.onDestroy();
		
		
		Toast.makeText(this, "Service End",Toast.LENGTH_SHORT).show();
		mQuit = true;
		
	}
	
	public int onStartCommand(Intent intent, int flags, int startId) {
		super.onStartCommand(intent, flags, startId);
		
		/* 비정상 백그라운드 생성 방지 */
		//Log.d(TAG,String.valueOf(DBinfo.statement)+"onStartCommand");//debug
	
		
		/* 감지 루틴 */
		Intent notificationIntent = new Intent(this, MainActivity.class);
        notificationIntent.setAction("com.zero.gym.startforeground");
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,notificationIntent, 0);
        Bitmap icon = BitmapFactory.decodeResource(getResources(),R.drawable.ic_launcher);

       
        Notification notification = new Notification.Builder(this)
                .setContentTitle("Gym")
                .setTicker("Gym")
                .setContentText("예약확인중..")
                .setSmallIcon(R.drawable.ic_launcher)
                .setLargeIcon(Bitmap.createScaledBitmap(icon, 128, 128, false))
                .setContentIntent(pendingIntent)
                .setOngoing(true).build();
        startForeground(101,notification);
        
       
		
//		mQuit = false;
		mQuit = intent.getBooleanExtra("serviceValue", false);
		
		//birth = intent.getIntExtra("birth", 0);
		//seat = intent.getIntExtra("seat", 0);
		name = intent.getStringExtra("name");
		age = intent.getStringExtra("age");
		perpose = intent.getStringExtra("perpose");	
		phone = intent.getStringExtra("phone");	
		year = intent.getStringExtra("year");
		month = intent.getStringExtra("month");
		day = intent.getStringExtra("day");	
		time = intent.getStringExtra("time");	
		Cname = intent.getStringExtra("Cname");
	
		
		NewsThread thread = new NewsThread(this, mHandler);
		thread.start();
		return START_NOT_STICKY;
	}
	
	
	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	class NewsThread extends Thread {
		BackgroundSearch mParent;
		Handler mHandler;
		String[] arNews = {
				"일본 독도는 한국땅으로 인정",
				"번데기 맛 초코파이 출시",
				"춘천지역에 초 거대 유전 발견",
				"한국 월드컵 결승 진출",
				"국민소득 6만불 돌파",
				"안드로이드 점유율 아이폰을 앞질렀다."
		};
		public NewsThread(BackgroundSearch parent, Handler handler){
			mParent = parent;
			mHandler = handler;
		}
		public void run() {       ///////////////////////////////////여기다가 예약검색 뭐 할지 넣으면 됨
			for(int idx = 0; mQuit==false; idx++) {
				
				//Log.d(TAG, "run");//debug
				Message msg = new Message();
				msg.what =0;
		//		msg.obj = arNews[idx % arNews.length];
		//		mHandler.sendMessage(msg);
				
				
				Handler mHandler = new Handler(Looper.getMainLooper());////////////////////////////////예약에서 은근히 중요한 부분을 차지하는 수시검색
				mHandler.postDelayed(new Runnable() {
				         public void run() {
				        	 reservation_search();  
				         }
				 }, 1000);
				
				
					
					
				try {
					Thread.sleep(1000*2);//2초간격
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					Toast.makeText(BackgroundSearch.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
					e.printStackTrace();
					
				}			
							
			
			}
			
		}
		
	}
	
	

	Handler mHandler = new Handler() {
		public void handleMessage (Message msg) {
			if (msg.what ==0){
				String news = (String)msg.obj;
				Toast.makeText(BackgroundSearch.this, news, Toast.LENGTH_SHORT).show();
			}
				
		}
	};
	
	
	


private void reservation_search()
{
	
	
	
	Log.d(TAG, "reservation_search() start "+String.valueOf(count++));//debug
	try{
		URL url_3 = new URL(SERVER_ADDRESS + "/reservation_search.php?"
				//+ "&birth=" + birth
				//+ "&seat=" + seat);
				+ "name=" + URLEncoder.encode(name,"UTF-8")
				+ "&age=" + URLEncoder.encode(age,"UTF-8")
				+ "&perpose=" + URLEncoder.encode(perpose,"UTF-8")
				+ "&phone=" + URLEncoder.encode(phone,"UTF-8")
				+ "&year=" + URLEncoder.encode(year,"UTF-8")
				+ "&month=" + URLEncoder.encode(month,"UTF-8")
				+ "&day=" + URLEncoder.encode(day,"UTF-8")
				+ "&time=" + URLEncoder.encode(time,"UTF-8")
				+ "&Cname=" + URLEncoder.encode(Cname,"UTF-8")
				);
		
		url_3.openStream();
		}
		catch(Exception e){
			Toast.makeText(BackgroundSearch.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
			//Log.e("Error", e.getMessage()); //onDestory();
		}// try catch 臾�醫낅즺
	String result2 = getXmlData("reservation_search.xml", "result"); 
	Log.d(TAG, "reservation_search() result2 : "+result2);//debug
	 if(result2.equals("1")) {//리턴받은경우
		 
		 try{///////////////////////////////////////////////////////////////예약 성공
				URL url_5 = new URL(SERVER_ADDRESS + "/reservation_success.php?"
						+ "name=" + URLEncoder.encode(name,"UTF-8")
						+ "&age=" + URLEncoder.encode(age,"UTF-8")
						+ "&perpose=" + URLEncoder.encode(perpose,"UTF-8")
						+ "&phone=" + URLEncoder.encode(phone,"UTF-8")
						+ "&year=" + URLEncoder.encode(year,"UTF-8")
						+ "&month=" + URLEncoder.encode(month,"UTF-8")
						+ "&day=" + URLEncoder.encode(day,"UTF-8")
						+ "&time=" + URLEncoder.encode(time,"UTF-8")
						+ "&Cname=" + URLEncoder.encode(Cname,"UTF-8")
						);
				
				url_5.openStream();
				}
				catch(Exception e){
					Toast.makeText(BackgroundSearch.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
					//Log.e("Error", e.getMessage());	
				}// try catch 臾�醫낅즺
			String result3 = getXmlData("reservation_success.xml", "result");
			if(result3.equals("1")){
			success();
			mQuit =true;			
			}
			
			
			try{///////////////////////////////////////////////////////////////예약 실패
				URL url_6 = new URL(SERVER_ADDRESS + "/reservation_fail.php?"
						+ "name=" + URLEncoder.encode(name,"UTF-8")
						+ "&age=" + URLEncoder.encode(age,"UTF-8")
						+ "&perpose=" + URLEncoder.encode(perpose,"UTF-8")
						+ "&phone=" + URLEncoder.encode(phone,"UTF-8")
						+ "&year=" + URLEncoder.encode(year,"UTF-8")
						+ "&month=" + URLEncoder.encode(month,"UTF-8")
						+ "&day=" + URLEncoder.encode(day,"UTF-8")
						+ "&time=" + URLEncoder.encode(time,"UTF-8")
						+ "&Cname=" + URLEncoder.encode(Cname,"UTF-8")
						);
				
				url_6.openStream();
				}
				catch(Exception e){
					Toast.makeText(BackgroundSearch.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
					//Log.e("Error", e.getMessage());	
				}// try catch 臾�醫낅즺
			String result4 = getXmlData("reservation_fail.xml", "result");
			if(result4.equals("1"))
			fail();
			mQuit =true;			
					
			
	}	 
	
}

/* 실패시 알림*/
@SuppressLint("NewApi")
public void fail()
{
	
//	Intent intent = new Intent(BackgroundSearch.this, Reservation_check.class);
//	intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//	PendingIntent content = PendingIntent.getActivity(BackgroundSearch.this,0, intent,0);
	
	NotificationManager nm = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
	Notification noti = new Notification.Builder(BackgroundSearch.this)
	.setTicker("예약 실패")
	.setContentTitle("예약 실패")
	.setContentText("예약에 실패했습니다!")
	.setSmallIcon(R.drawable.ic_launcher)
	.setLargeIcon(BitmapFactory.decodeResource(getResources(),  R.drawable.ic_launcher))
	.setDefaults(Notification.DEFAULT_SOUND|Notification.DEFAULT_VIBRATE)
	.setVibrate(new long[] {1000,1000,500,500,200,200,200,200,200,200})
	.setLights(0xff00ff00, 500, 500)
//	.setContentIntent(content)
	.build();
	noti.flags = noti.flags | noti.FLAG_AUTO_CANCEL;
	nm.notify(1235,noti);
	
	
	
	
	Handler mHandler = new Handler(Looper.getMainLooper());////////////////////////////////예약에서 은근히 중요한 부분을 차지하는 수시검색
	mHandler.postDelayed(new Runnable() {
	         public void run() {
	        	 try{///////////////////////////////////////////////////////////////예약 초기화
	        			URL url_4 = new URL(SERVER_ADDRESS + "/reservation_end.php?"
	        					+ "name=" + URLEncoder.encode(name,"UTF-8")
	        					+ "&age=" + URLEncoder.encode(age,"UTF-8")
	        					//+ "&password=" + URLEncoder.encode(password,"UTF-8")
	        					//+ "&seat=" + URLEncoder.encode(String.valueOf(seat),"UTF-8")
	        					);
	        			
	        			url_4.openStream();
	        			}
	        			catch(Exception e){
	        				Log.e("Error", e.getMessage());	
	        			}// try catch 臾�醫낅즺  
	         }
	 }, 3600000);
	//7200000
	/*
	*/
	
	
	
	/* 백그라운드 감지 중지 */
	Intent stopIntent = new Intent(this, BackgroundSearch.class);
    stopIntent.setAction("com.zero.gym.startforeground");
    startService(stopIntent);
    //stopService(new Intent("com.example.dustview.ForegroundService")); lollipop version
        Intent intent2 = new Intent("com.zero.gym.BackgroundSearch");
//        intent.setPackage("com.example.zero");
        //getContext().bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
        stopService(intent2);

    stopService(new Intent(getApplicationContext(), BackgroundSearch.class));
	
	
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
	Notification noti = new Notification.Builder(BackgroundSearch.this)
	.setTicker("예약 성공")
	.setContentTitle("예약 성공")
	.setContentText("체육관이름: "+ Cname)
	.setSubText("이름: "+name + "시간: "+time+ " 날짜: "+month+"/"+day)
	.setSmallIcon(R.drawable.ic_launcher)
	.setLargeIcon(BitmapFactory.decodeResource(getResources(),  R.drawable.ic_launcher))
	.setDefaults(Notification.DEFAULT_SOUND|Notification.DEFAULT_VIBRATE)
	.setVibrate(new long[] {1000,1000,500,500,200,200,200,200,200,200})
	.setLights(0xff00ff00, 500, 500)	
//	.setContentIntent(content)
	.build();	
	noti.flags = noti.flags | noti.FLAG_AUTO_CANCEL;
	nm.notify(1234,noti);

	
	
	
	
	Handler mHandler = new Handler(Looper.getMainLooper());////////////////////////////////예약에서 은근히 중요한 부분을 차지하는 수시검색
	mHandler.postDelayed(new Runnable() {
	         public void run() {
	        	 try{///////////////////////////////////////////////////////////////예약 초기화
	        			URL url_4 = new URL(SERVER_ADDRESS + "/reservation_end.php?"
	        					+ "name=" + URLEncoder.encode(name,"UTF-8")
	        					//+ "&birth=" + URLEncoder.encode(String.valueOf(birth),"UTF-8")
	        					//+ "&password=" + URLEncoder.encode(password,"UTF-8")
	        					//+ "&seat=" + URLEncoder.encode(String.valueOf(seat),"UTF-8")
	        					);
	        			
	        			url_4.openStream();
	        			}
	        			catch(Exception e){
	        				Log.e("Error", e.getMessage());	
	        			}// try catch 臾�醫낅즺  
	         }
	 }, 3600000);
	
	/* 백그라운드 감지 중지 */
	Intent stopIntent = new Intent(this, BackgroundSearch.class);
    stopIntent.setAction("com.zero.gym.startforeground");
    startService(stopIntent);
    //stopService(new Intent("com.example.dustview.ForegroundService")); lollipop version
        Intent intent2 = new Intent("com.zero.gym.BackgroundSearch");
//        intent.setPackage("com.example.zero");
        //getContext().bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
        stopService(intent2);

    stopService(new Intent(getApplicationContext(), BackgroundSearch.class));
		 
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


}
	
	
	
	
	




