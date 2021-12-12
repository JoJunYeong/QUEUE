package com.zero.gym;



		import android.annotation.SuppressLint;
		import android.app.Activity;
		import android.app.Notification;
		import android.app.NotificationManager;
		import android.content.Intent;
		import android.graphics.BitmapFactory;
		import android.os.Bundle;
		import android.os.StrictMode;
		import android.util.Log;
		import android.view.Menu;
		import android.view.MenuItem;
		import android.view.View;
		import android.widget.ArrayAdapter;
		import android.widget.Button;
		import android.widget.EditText;
		import android.widget.TextView;
		import android.widget.Toast;
		import android.os.Handler;
		import org.xmlpull.v1.XmlPullParser;
		import org.xmlpull.v1.XmlPullParserFactory;

		import java.io.InputStream;
		import java.net.URL;
		import java.net.URLEncoder;
		import java.util.ArrayList;

public class Send extends Activity {

	private final String TAG ="Send.class";

	int q=0;
	String name = "";
	String age = "";		// 받아올 아이들 미리 변수선언함
	String seat = "";
	NotificationManager mNotiManager;
	EditText edtname, edtage , edtseat;
	ArrayList<String> data;
	Button ok,no;
	static final int NAPNOTI = 1;
	ArrayList<String> array_id;
	ArrayAdapter<String> adapter;



	private static final String SERVER_ADDRESS = "http://115.144.76.143";

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_send);

		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);


	

		edtname = (EditText)findViewById(R.id.name);
		edtage = (EditText)findViewById(R.id.age);
		edtseat = (EditText)findViewById(R.id.seat);


		data = new ArrayList<String>();


		ok= (Button)findViewById(R.id.button1);
		no= (Button)findViewById(R.id.button2);


		no.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Send.this.finish();
			}
		});



		ok.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub



			

				if(edtname.getText().toString().equals("")||edtage.getText().toString().equals("")||
						edtseat.getText().toString().equals("")){
					Toast.makeText(Send.this, "다 적어주세요.", Toast.LENGTH_SHORT).show();
					return;

				}
				runOnUiThread(new Runnable() {
					public void run() {

						name = edtname.getText().toString();
						age = edtage.getText().toString();
						seat = edtseat.getText().toString();
					




						try{
							URL url_2 = new URL(SERVER_ADDRESS + "/ticket/s_join.php?"
									+ "name=" + URLEncoder.encode(name,"UTF-8")
									+ "&age=" + URLEncoder.encode(age,"UTF-8")
									+ "&seat=" + URLEncoder.encode(seat,"UTF-8")
									
							);

							url_2.openStream();
						}
						catch(Exception e){
							Toast.makeText(Send.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
							Log.e("Error", e.getMessage());
						}// try catch 臾�醫낅즺 */



						reservation_search();


											/*
											Intent intent;
											intent = new Intent(Send.this, BackgroundSearch.class);
											intent.putExtra("name", name);
											intent.putExtra("age", age);
											intent.putExtra("perpose", perpose);
											intent.putExtra("phone", phone);
											intent.putExtra("year", year);
											intent.putExtra("month", month);
											intent.putExtra("day", dayOfMonth);
											intent.putExtra("time", time);
											intent.putExtra("Cname", Cname);
											startService(intent);
																	*/
						Toast.makeText(Send.this, "전송 완료", Toast.LENGTH_SHORT).show();
						edtname.setText("");
						edtage.setText("");
						edtseat.setText("");
							finish();

					} //run 醫낅즺
				});

	
			







			}
		});






	}



	@SuppressLint("NewApi")
	public void reservation_search()
	{

//	Intent intent = new Intent(BackgroundSearch.this, Reservation_check.class);
//	intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//	PendingIntent content = PendingIntent.getActivity(BackgroundSearch.this,0, intent,0);

		NotificationManager nm = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
		Notification noti = new Notification.Builder(Send.this)
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
			Toast.makeText(Send.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
			//Log.e("Error", e.getMessage()); //onDestory();
		}// try catch 臾�醫낅즺
		String result2 = getXmlData("reservation_search.xml", "result");
		Log.d(TAG, "reservation_search() result2 : "+result2);//debug
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
				Toast.makeText(Send.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
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
		Notification noti = new Notification.Builder(Send.this)
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

	private ArrayList<String> getXmlDataList(String filename, String str) {
		String rss = SERVER_ADDRESS + "/ticket/";
		ArrayList<String> ret = new ArrayList<String>();

		try {
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
		getMenuInflater().inflate(R.menu.send, menu);
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
