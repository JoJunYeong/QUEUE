package com.zero.gym;


import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.annotation.SuppressLint;
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

public class Personal extends Activity {

	
	ArrayList<String> data;
	ArrayAdapter<String> adapter;
	private static final String SERVER_ADDRESS = "http://115.144.76.143";
	Button queue,ticket,join,refresh;
	String t1="";
	String t2="";
	String t3="";
	String t4="";
	String day="";	
	
	
	
	
	
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_personal);
		data = new ArrayList<String>();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	    StrictMode.setThreadPolicy(policy);
		
		
	    
	    
	    queue= (Button)findViewById(R.id.button1);
        ticket= (Button)findViewById(R.id.button2);
        join= (Button)findViewById(R.id.button3);
        refresh= (Button)findViewById(R.id.button4);
        
        
        
		 try{//////////////////////////////예약 신청 / 취소
				URL url = new URL(SERVER_ADDRESS + "/ticket/t_check.php?"
						
						);					
				url.openStream();		
				t1 = getXmlData("t_check.xml", "result1");
				t2 = getXmlData("t_check.xml", "result2");
				t3 = getXmlData("t_check.xml", "result3");
				t4 = getXmlData("t_check.xml", "result4");
				
			} catch(Exception e) {
				Toast.makeText(Personal.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
				Log.e("Error", e.getMessage());
			}
		
		 if(t1.equals("0"))
				t1="Available";
		 else if(t1.equals("1"))
				t1="Unavailable";
			TextView testView1 = (TextView)findViewById(R.id.textView1);
			testView1.setText(t1);
		
			 if(t2.equals("0"))
					t2="Available";
			 else if(t2.equals("1"))
					t2="Unavailable";
				TextView testView2 = (TextView)findViewById(R.id.textView2);
				testView2.setText(t2);
					
				 if(t3.equals("0"))
						t3="Available";
				 else if(t3.equals("1"))
						t3="Unavailable";
					TextView testView3 = (TextView)findViewById(R.id.textView3);
					testView3.setText(t3);
				
					 if(t4.equals("0"))
							t4="Available";
					 else if(t1.equals("1"))
							t4="Unavailable";
						TextView testView4 = (TextView)findViewById(R.id.textView4);
						testView4.setText(t4);
					
		
						
						
						 try{//////////////////////////////예약 신청 / 취소
								URL url2 = new URL(SERVER_ADDRESS + "/ticket/reservation_count.php?"
													
										);					
								url2.openStream();		
								
								day  = getXmlData("reservation_count.xml", "result");
								
							} catch(Exception e) {
								Toast.makeText(Personal.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
								Log.e("Error", e.getMessage());
							}
						 TextView testView5 = (TextView)findViewById(R.id.textView5);
							testView5.setText(day);
						
						
						
						
						
						
						 join.setOnClickListener(new View.OnClickListener() {
								
								@Override
								public void onClick(View v) {
									// TODO Auto-generated method stub
									
									Intent intent1 = new Intent(Personal.this,Send.class);
									startActivity(intent1);			
									
									
								}
							});
		
						
						 queue.setOnClickListener(new View.OnClickListener() {
								
								@Override
								public void onClick(View v) {
									// TODO Auto-generated method stub
									
									Toast.makeText(Personal.this, "This Tab is Queue", Toast.LENGTH_SHORT).show();
									
									
								}
							});
						
						 ticket.setOnClickListener(new View.OnClickListener() {
								
								@Override
								public void onClick(View v) {
									// TODO Auto-generated method stub
									
									Intent intent1 = new Intent(Personal.this,Reservation_check_login.class);
									startActivity(intent1);	
								//	finish();
									
								}
							});
						
						 refresh.setOnClickListener(new View.OnClickListener() {
								
								@Override
								public void onClick(View v) {
									// TODO Auto-generated method stub
									
								
									 
									 try{//////////////////////////////예약 신청 / 취소
											URL url = new URL(SERVER_ADDRESS + "/ticket/t_check.php?"
													
													);					
											url.openStream();		
											t1 = getXmlData("t_check.xml", "result1");
											t2 = getXmlData("t_check.xml", "result2");
											t3 = getXmlData("t_check.xml", "result3");
											t4 = getXmlData("t_check.xml", "result4");
											
										} catch(Exception e) {
											Toast.makeText(Personal.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
											Log.e("Error", e.getMessage());
										}
									
									 if(t1.equals("0"))
											t1="Available";
									 else if(t1.equals("1"))
											t1="Unavailable";
										TextView testView1 = (TextView)findViewById(R.id.textView1);
										testView1.setText(t1);
									
										 if(t2.equals("0"))
												t2="Available";
										 else if(t2.equals("1"))
												t2="Unavailable";
											TextView testView2 = (TextView)findViewById(R.id.textView2);
											testView2.setText(t2);
												
											 if(t3.equals("0"))
													t3="Available";
											 else if(t3.equals("1"))
													t3="Unavailable";
												TextView testView3 = (TextView)findViewById(R.id.textView3);
												testView3.setText(t3);
											
												 if(t4.equals("0"))
														t4="Available";
												 else if(t1.equals("1"))
														t4="Unavailable";
													TextView testView4 = (TextView)findViewById(R.id.textView4);
													testView4.setText(t4);
												
									
													
													
													 try{//////////////////////////////예약 신청 / 취소
															URL url2 = new URL(SERVER_ADDRESS + "/ticket/reservation_count.php?"
																				
																	);					
															url2.openStream();		
															
															day  = getXmlData("reservation_count.xml", "result");
															
														} catch(Exception e) {
															Toast.makeText(Personal.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
															Log.e("Error", e.getMessage());
														}
													 TextView testView5 = (TextView)findViewById(R.id.textView5);
														testView5.setText(day);
													
													
									
									
									
									
									
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
		getMenuInflater().inflate(R.menu.personal, menu);
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
