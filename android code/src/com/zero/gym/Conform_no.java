package com.zero.gym;


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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;
import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

public class Conform_no extends Activity {

	
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
	String ready="";
	String ready2="";
	String check="";
	String g3="";
	String student1="";
	String student2="";
	int i=0;
	int Available=0;
	
	ArrayList<String> data;
	ArrayAdapter<String> adapter;
	private static final String SERVER_ADDRESS = "http://115.144.76.143";
	
	
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_conform_no);
		data = new ArrayList<String>();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	    StrictMode.setThreadPolicy(policy);
		
	    Intent intent = getIntent();
		ID = intent.getStringExtra("ID");		// ID값을 받아옴
		Password = intent.getStringExtra("Password");		// Password값을 받아옴
		
		
		ok= (Button)findViewById(R.id.button1);
        available= (Button)findViewById(R.id.button2);
   	    unavailable= (Button)findViewById(R.id.button3);
   	    refresh= (Button)findViewById(R.id.button4);
   	    ss= (Button)findViewById(R.id.button5);
   	 queue= (Button)findViewById(R.id.button6);
   	ticket= (Button)findViewById(R.id.button7);
   	    
   	    
   	 try{//////////////////////////////예약 신청 / 취소
			URL url = new URL(SERVER_ADDRESS + "/ticket/admin_check_t.php?"
					
					);					
			url.openStream();		
			String result = getXmlData("admin_check_t.xml", "result");
			t = result;
		} catch(Exception e) {
			Toast.makeText(Conform_no.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
			Log.e("Error", e.getMessage());
		}
   	    
   	    
   	 try{//////////////////////////////예약 신청 / 취소
			URL url = new URL(SERVER_ADDRESS + "/ticket/reservation_check_q1.php?"
					+ "ID=" + URLEncoder.encode(ID,"UTF-8")
					+ "&t=" + URLEncoder.encode(t,"UTF-8")						
					);					
			url.openStream();		
			
			year  = getXmlData("reservation_check_q1.xml", "result");
			month= getXmlData("reservation_check_q1.xml", "result2");
		} catch(Exception e) {
			Toast.makeText(Conform_no.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
			Log.e("Error", e.getMessage());
		}
  // 	Toast.makeText(Conform_no.this, month, Toast.LENGTH_SHORT).show();
   	 
   	 
   	 
   	 
        try{//////////////////////////////예약 신청 / 취소
			URL url = new URL(SERVER_ADDRESS + "/ticket/admin_check_ready2.php?"
					
					);					
			url.openStream();		
			String result = getXmlData("admin_check_ready2.xml", "result");
			ready2 = result;
		} catch(Exception e) {
			Toast.makeText(Conform_no.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
			Log.e("Error", e.getMessage());
		}
     
        try{//////////////////////////////예약 신청 / 취소
			URL url = new URL(SERVER_ADDRESS + "/ticket/admin_check_ready.php?"
					
					);					
			url.openStream();		
			String result = getXmlData("admin_check_ready.xml", "result");
			ready = result;
		} catch(Exception e) {
			Toast.makeText(Conform_no.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
			Log.e("Error", e.getMessage());
		}
		if(ready.equals("0")&&ready2.equals("0"))
		{
			i=1;
			Toast.makeText(Conform_no.this, "예약신청이 없습니다.", Toast.LENGTH_SHORT).show();
		}
        
        if(i==0)
	    {
        	
	        
	        
			
	        
	        
	        
			if(year.equals(month))
			{
				Toast.makeText(Conform_no.this, "You can't teach this student. please wait until this student disapear.", Toast.LENGTH_SHORT).show();
				i=1;
				 name="Wait";
			}
			else
			{
				try{//////////////////////////////예약 성공 / 실패
					URL url = new URL(SERVER_ADDRESS + "/ticket/reservation_check_name.php?"
							+ "name=" + URLEncoder.encode(name,"UTF-8")
							+ "&t=" + URLEncoder.encode(t,"UTF-8")				
							);					
					url.openStream();		
					String result = getXmlData("reservation_check_name.xml", "result");
					name = result;
				} catch(Exception e) {
					Log.e("Error", e.getMessage());
				}
				
				
				try{//////////////////////////////seat_check
					URL url = new URL(SERVER_ADDRESS + "/ticket/reservation_check_seat.php?"
							+ "Cname=" + URLEncoder.encode(Cname,"UTF-8")
							+ "&t=" + URLEncoder.encode(t,"UTF-8")				
							);					
					url.openStream();		
					String result = getXmlData("reservation_check_seat.xml", "result");
					seat = result;
				} catch(Exception e) {
					Log.e("Error", e.getMessage());
				}
				
				
			
				
				
			}
	    }
        else if(i==1)
        {
        name="Wait";
        			
        }
        
        available.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				
				 
		        try{//////////////////////////////예약 신청 / 취소
					URL url = new URL(SERVER_ADDRESS + "/ticket/available.php?"
							+ "no=" + URLEncoder.encode(month,"UTF-8")
							+ "&t=" + URLEncoder.encode(t,"UTF-8")						
							);					
					url.openStream();		
					
					day  = getXmlData("available.xml", "result");
					Toast.makeText(Conform_no.this, "Set avaliable.", Toast.LENGTH_SHORT).show();
					
					 try{//////////////////////////////예약 신청 / 취소
							URL url2 = new URL(SERVER_ADDRESS + "/ticket/t_check_available.php?"
									+ "no=" + URLEncoder.encode(month,"UTF-8")
									+ "&t=" + URLEncoder.encode(t,"UTF-8")						
									);					
							url2.openStream();		
							
							phone  = getXmlData("t_check_available.xml", "result");
							
						} catch(Exception e) {
							Toast.makeText(Conform_no.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
							Log.e("Error", e.getMessage());
						}
					Available=0;
					
					if(phone.equals("1"))
						Cname="Unavaliable";
					else if(phone.equals("0"))
						Cname="Avaliable";
					TextView testView3 = (TextView)findViewById(R.id.textView3);
					testView3.setText(Cname);
				} catch(Exception e) {
					Toast.makeText(Conform_no.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
					Log.e("Error", e.getMessage());
				}
				if(day.equals("1"))
				{
				
				}
				
			}
		});
		
        unavailable.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				
				
				
				 try{//////////////////////////////예약 신청 / 취소
						URL url = new URL(SERVER_ADDRESS + "/ticket/unavailable.php?"
								+ "no=" + URLEncoder.encode(month,"UTF-8")
								+ "&t=" + URLEncoder.encode(t,"UTF-8")						
								);					
						url.openStream();		
						
						day  = getXmlData("unavailable.xml", "result");
						Toast.makeText(Conform_no.this, "Set unavaliable.", Toast.LENGTH_SHORT).show();
						
						 try{//////////////////////////////예약 신청 / 취소
								URL url2 = new URL(SERVER_ADDRESS + "/ticket/t_check_available.php?"
										+ "no=" + URLEncoder.encode(month,"UTF-8")
										+ "&t=" + URLEncoder.encode(t,"UTF-8")						
										);					
								url2.openStream();		
								
								phone  = getXmlData("t_check_available.xml", "result");
								
							} catch(Exception e) {
								Toast.makeText(Conform_no.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
								Log.e("Error", e.getMessage());
							}
						 Available=1;
						if(phone.equals("1"))
							Cname="Unavaliable";
						else if(phone.equals("0"))
							Cname="Avaliable";
						TextView testView3 = (TextView)findViewById(R.id.textView3);
						testView3.setText(Cname);
					} catch(Exception e) {
						Toast.makeText(Conform_no.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
						Log.e("Error", e.getMessage());
					}
					if(day.equals("1"))
					{
					
					}
				
				
			}
		});
        
        
        
        refresh.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				i=0;
				
				
				try{//////////////////////////////예약 신청 / 취소
					URL url = new URL(SERVER_ADDRESS + "/ticket/admin_check_t.php?"
							
							);					
					url.openStream();		
					String result = getXmlData("admin_check_t.xml", "result");
					t = result;
				} catch(Exception e) {
					Toast.makeText(Conform_no.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
					Log.e("Error", e.getMessage());
				}
		   	    
		   	    
		   	 try{//////////////////////////////예약 신청 / 취소
					URL url = new URL(SERVER_ADDRESS + "/ticket/reservation_check_q1.php?"
							+ "ID=" + URLEncoder.encode(ID,"UTF-8")
							+ "&t=" + URLEncoder.encode(t,"UTF-8")						
							);					
					url.openStream();		
					
					year  = getXmlData("reservation_check_q1.xml", "result");
					month= getXmlData("reservation_check_q1.xml", "result2");
				} catch(Exception e) {
					Toast.makeText(Conform_no.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
					Log.e("Error", e.getMessage());
				}
		  // 	Toast.makeText(Conform_no.this, month, Toast.LENGTH_SHORT).show();
		   	 
		   	 
		   	 
		   	 
		        try{//////////////////////////////예약 신청 / 취소
					URL url = new URL(SERVER_ADDRESS + "/ticket/admin_check_ready2.php?"
							
							);					
					url.openStream();		
					String result = getXmlData("admin_check_ready2.xml", "result");
					ready2 = result;
				} catch(Exception e) {
					Toast.makeText(Conform_no.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
					Log.e("Error", e.getMessage());
				}
		     
		        try{//////////////////////////////예약 신청 / 취소
					URL url = new URL(SERVER_ADDRESS + "/ticket/admin_check_ready.php?"
							
							);					
					url.openStream();		
					String result = getXmlData("admin_check_ready.xml", "result");
					ready = result;
				} catch(Exception e) {
					Toast.makeText(Conform_no.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
					Log.e("Error", e.getMessage());
				}
				if(ready.equals("0")&&ready2.equals("0"))
				{
					i=1;
					Toast.makeText(Conform_no.this, "예약신청이 없습니다.", Toast.LENGTH_SHORT).show();
				}
		        
		        if(i==0)
			    {
		        	
			        
			        
					
			        
			        
			        
					if(year.equals(month))
					{
						Toast.makeText(Conform_no.this, "You can't teach this student. please wait until this student disapear.", Toast.LENGTH_SHORT).show();
						i=1;
						 name="Wait";
						 seat="Wait";
					//	 Toast.makeText(Conform_no.this, "여기도.", Toast.LENGTH_SHORT).show();
					}
					else
					{
						try{//////////////////////////////예약 성공 / 실패
							URL url = new URL(SERVER_ADDRESS + "/ticket/reservation_check_name.php?"
									+ "name=" + URLEncoder.encode(name,"UTF-8")
									+ "&t=" + URLEncoder.encode(t,"UTF-8")				
									);					
							url.openStream();		
							String result = getXmlData("reservation_check_name.xml", "result");
							name = result;
						} catch(Exception e) {
							Log.e("Error", e.getMessage());
						}
						
						
						try{//////////////////////////////seat_check
							URL url = new URL(SERVER_ADDRESS + "/ticket/reservation_check_seat.php?"
									+ "Cname=" + URLEncoder.encode(Cname,"UTF-8")
									+ "&t=" + URLEncoder.encode(t,"UTF-8")				
									);					
							url.openStream();		
							String result = getXmlData("reservation_check_seat.xml", "result");
							seat = result;
						} catch(Exception e) {
							Log.e("Error", e.getMessage());
						}
						
						
					
						
						
					}
			    }
		        else if(i==1)
		        {
		        name="Wait";
		        seat="Wait";
		   //     Toast.makeText(Conform_no.this, "여기.", Toast.LENGTH_SHORT).show();
		        }
		        
		        try{//////////////////////////////예약 신청 / 취소
					URL url2 = new URL(SERVER_ADDRESS + "/ticket/t_check_available.php?"
							+ "no=" + URLEncoder.encode(month,"UTF-8")
							+ "&t=" + URLEncoder.encode(t,"UTF-8")						
							);					
					url2.openStream();		
					
					phone  = getXmlData("t_check_available.xml", "result");
					
				} catch(Exception e) {
					Toast.makeText(Conform_no.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
					Log.e("Error", e.getMessage());
				}
		        
		        try{//////////////////////////////예약 신청 / 취소
					URL url2 = new URL(SERVER_ADDRESS + "/ticket/reservation_count.php?"
							+ "no=" + URLEncoder.encode(month,"UTF-8")
							+ "&t=" + URLEncoder.encode(t,"UTF-8")						
							);					
					url2.openStream();		
					
					day  = getXmlData("reservation_count.xml", "result");
					
				} catch(Exception e) {
					Toast.makeText(Conform_no.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
					Log.e("Error", e.getMessage());
				}
		        
		        
		        
				TextView testView1 = (TextView)findViewById(R.id.textView1);
				testView1.setText(name);
				TextView testView2 = (TextView)findViewById(R.id.textView2);
				testView2.setText(seat);
				if(phone.equals("1"))
					Cname="Unavaliable";
				else if(phone.equals("0"))
					Cname="Avaliable";
				TextView testView3 = (TextView)findViewById(R.id.textView3);
				testView3.setText(Cname);
				TextView testView4 = (TextView)findViewById(R.id.textView4);
				testView4.setText(day+"명");
			
				
				
			}
		});
        
        
        ok.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				
				
				try {//////////////////////////////예약 신청 / 취소
					URL url = new URL(SERVER_ADDRESS + "/ticket/t_check_student.php?"
							+ "Cname=" + URLEncoder.encode(Cname, "UTF-8")
							+ "&no=" + URLEncoder.encode(month, "UTF-8")
							+ "&t=" + URLEncoder.encode(t, "UTF-8")
							
					);
					url.openStream();
					student1 = getXmlData("t_check_student.xml", "result");
					student2 = getXmlData("t_check_student.xml", "result2");
				} catch (Exception e) {
					Toast.makeText(Conform_no.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
					Log.e("Error", e.getMessage());
				}

				
				if(student1.equals("0")&&student2.equals("0"))
				{
				if(Available==0)
				{
				
				if(i==1)
				{
					Toast.makeText(Conform_no.this, "You can't teach this student. please wait until this student disapear.", Toast.LENGTH_SHORT).show();										
				}
				else if(i==0) {
					//		Toast.makeText(Conform_no.this, Cname +"/"+ name+"/"+t, Toast.LENGTH_SHORT).show();
					
					//		Toast.makeText(Conform_no.this, Cname +"/"+ name+"/"+t, Toast.LENGTH_SHORT).show();

					/////////////////////////////////////////////////////////////////////////////////


					try {//////////////////////////////예약 신청 / 취소
						URL url = new URL(SERVER_ADDRESS + "/ticket/ok_before_check.php?"
								+ "Cname=" + URLEncoder.encode(Cname, "UTF-8")
								+ "&name=" + URLEncoder.encode(name, "UTF-8")
								+ "&t=" + URLEncoder.encode(t, "UTF-8")
								+ "&seat=" + URLEncoder.encode(seat, "UTF-8")
								+ "&month=" + URLEncoder.encode(month, "UTF-8")
								+ "&day=" + URLEncoder.encode(day, "UTF-8")
								+ "&time=" + URLEncoder.encode(time, "UTF-8")
						);
						url.openStream();
						String result = getXmlData("ok_before_check.xml", "result");
						check = result;
					} catch (Exception e) {
						Toast.makeText(Conform_no.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
						Log.e("Error", e.getMessage());
					}


					if (check.equals("1")) {


						
						
						try {//////////////////////////////예약 신청 / 취소
							URL url = new URL(SERVER_ADDRESS + "/ticket/g_check3.php?"
									+ "Cname=" + URLEncoder.encode(Cname, "UTF-8")
									+ "&name=" + URLEncoder.encode(name, "UTF-8")
									+ "&t=" + URLEncoder.encode(t, "UTF-8")
							);
							url.openStream();
							 g3 = getXmlData("g_check3.xml", "result1");
						} catch (Exception e) {
							Toast.makeText(Conform_no.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
							Log.e("Error", e.getMessage());
						}
						if(g3.equals("0"))
						{
							
							////////////////////////////////////////////////////////////////////////////////
							try {//////////////////////////////예약 신청 / 취소
								URL url = new URL(SERVER_ADDRESS + "/ticket/ok.php?"
										+ "Cname=" + URLEncoder.encode(Cname, "UTF-8")
										+ "&name=" + URLEncoder.encode(name, "UTF-8")
										+ "&month=" + URLEncoder.encode(month, "UTF-8")
										+ "&t=" + URLEncoder.encode(t, "UTF-8")
								);
								url.openStream();
								ok1 = getXmlData("ok.xml", "result");
	
							} catch (Exception e) {
								Toast.makeText(Conform_no.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
								Log.e("Error", e.getMessage());
							}
							
							try{//////////////////////////////예약 신청 / 취소
								URL url = new URL(SERVER_ADDRESS + "/ticket/unavailable.php?"
										+ "no=" + URLEncoder.encode(month,"UTF-8")
										+ "&t=" + URLEncoder.encode(t,"UTF-8")						
										);					
								url.openStream();		
								
								day  = getXmlData("unavailable.xml", "result");
								Toast.makeText(Conform_no.this, "Set unavaliable.", Toast.LENGTH_SHORT).show();
								
								 try{//////////////////////////////예약 신청 / 취소
										URL url2 = new URL(SERVER_ADDRESS + "/ticket/t_check_available.php?"
												+ "no=" + URLEncoder.encode(month,"UTF-8")
												+ "&t=" + URLEncoder.encode(t,"UTF-8")						
												);					
										url2.openStream();		
										
										phone  = getXmlData("t_check_available.xml", "result");
										
									} catch(Exception e) {
										Toast.makeText(Conform_no.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
										Log.e("Error", e.getMessage());
									}
								 Available=1;
								if(phone.equals("1"))
									Cname="Unavaliable";
								else if(phone.equals("0"))
									Cname="Avaliable";
								TextView testView3 = (TextView)findViewById(R.id.textView3);
								testView3.setText(Cname);
							} catch(Exception e) {
								Toast.makeText(Conform_no.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
								Log.e("Error", e.getMessage());
							}
							if(day.equals("1"))
							{
							
							}
							Intent intent1 = new Intent(Conform_no.this,T_queue.class);
							intent1.putExtra("Password",Password);//여긴 아이디를 보냅니다.
							intent1.putExtra("ID",ID);//여긴 아이디를 보냅니다.
							intent1.putExtra("no",month);//여긴 아이디를 보냅니다.
							startActivity(intent1);		
							Toast.makeText(Conform_no.this, "수락하였습니다.", Toast.LENGTH_SHORT).show();
								
							
						} else
							{
								try {//////////////////////////////예약 신청 / 취소
									URL url = new URL(SERVER_ADDRESS + "/ticket/ok2.php?"
											+ "Cname=" + URLEncoder.encode(Cname, "UTF-8")
											+ "&name=" + URLEncoder.encode(name, "UTF-8")
											+ "&month=" + URLEncoder.encode(month, "UTF-8")
											+ "&t=" + URLEncoder.encode(t, "UTF-8")
									);
									url.openStream();
									ok1 = getXmlData("ok2.xml", "result");
		
								} catch (Exception e) {
									Toast.makeText(Conform_no.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
									Log.e("Error", e.getMessage());
								}
								
								try{//////////////////////////////예약 신청 / 취소
									URL url = new URL(SERVER_ADDRESS + "/ticket/unavailable.php?"
											+ "no=" + URLEncoder.encode(month,"UTF-8")
											+ "&t=" + URLEncoder.encode(t,"UTF-8")						
											);					
									url.openStream();		
									
									day  = getXmlData("unavailable.xml", "result");
									Toast.makeText(Conform_no.this, "Set unavaliable.", Toast.LENGTH_SHORT).show();
									
									 try{//////////////////////////////예약 신청 / 취소
											URL url2 = new URL(SERVER_ADDRESS + "/ticket/t_check_available.php?"
													+ "no=" + URLEncoder.encode(month,"UTF-8")
													+ "&t=" + URLEncoder.encode(t,"UTF-8")						
													);					
											url2.openStream();		
											
											phone  = getXmlData("t_check_available.xml", "result");
											
										} catch(Exception e) {
											Toast.makeText(Conform_no.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
											Log.e("Error", e.getMessage());
										}
									 Available=1;
									if(phone.equals("1"))
										Cname="Unavaliable";
									else if(phone.equals("0"))
										Cname="Avaliable";
									TextView testView3 = (TextView)findViewById(R.id.textView3);
									testView3.setText(Cname);
								} catch(Exception e) {
									Toast.makeText(Conform_no.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
									Log.e("Error", e.getMessage());
								}
								if(day.equals("1"))
								{
								
								}
								//	finish();
									Intent intent1 = new Intent(Conform_no.this,T_queue.class);
									intent1.putExtra("Password",Password);//여긴 아이디를 보냅니다.
									intent1.putExtra("ID",ID);//여긴 아이디를 보냅니다.
									intent1.putExtra("no",month);//여긴 아이디를 보냅니다.
									startActivity(intent1);		
									Toast.makeText(Conform_no.this, "수락하였습니다.", Toast.LENGTH_SHORT).show();
							
							
							
							
							}
						
						
					}
					else if(check.equals("0"))
						Toast.makeText(Conform_no.this, "Another teacher already accept please re-connect this app.", Toast.LENGTH_SHORT).show();

				}
				
				}
				else if(Available==1)
					Toast.makeText(Conform_no.this, "Your status is Unavaliable. change it.", Toast.LENGTH_SHORT).show();
				
				}
				else
					Toast.makeText(Conform_no.this, "You already have student. check it please.", Toast.LENGTH_SHORT).show();
				
			}
		});
        
        
        try{//////////////////////////////예약 신청 / 취소
			URL url2 = new URL(SERVER_ADDRESS + "/ticket/t_check_available.php?"
					+ "no=" + URLEncoder.encode(month,"UTF-8")
					+ "&t=" + URLEncoder.encode(t,"UTF-8")						
					);					
			url2.openStream();		
			
			phone  = getXmlData("t_check_available.xml", "result");
			if(phone.equals("1"))
				Available=1;
			else if(phone.equals("0"))
				Available=0;
		} catch(Exception e) {
			Toast.makeText(Conform_no.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
			Log.e("Error", e.getMessage());
		}
        
        try{//////////////////////////////예약 신청 / 취소
			URL url2 = new URL(SERVER_ADDRESS + "/ticket/reservation_count.php?"
					+ "no=" + URLEncoder.encode(month,"UTF-8")
					+ "&t=" + URLEncoder.encode(t,"UTF-8")						
					);					
			url2.openStream();		
			
			day  = getXmlData("reservation_count.xml", "result");
			
		} catch(Exception e) {
			Toast.makeText(Conform_no.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
			Log.e("Error", e.getMessage());
		}
        
        
        
		TextView testView1 = (TextView)findViewById(R.id.textView1);
		testView1.setText(name);
		TextView testView2 = (TextView)findViewById(R.id.textView2);
		testView2.setText(seat);
		if(phone.equals("1"))
			Cname="Unavaliable";
		else if(phone.equals("0"))
			Cname="Avaliable";
		TextView testView3 = (TextView)findViewById(R.id.textView3);
		testView3.setText(Cname);
		TextView testView4 = (TextView)findViewById(R.id.textView4);
		testView4.setText(day+"명");

		
		
		
		
		  
        ss.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				if(Available==0)
					reservation_search();
				else if(Available==1)
					Toast.makeText(Conform_no.this, "Your status is Unavaliable. change it.", Toast.LENGTH_SHORT).show();
				
			}
		});
        
        
        queue.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				
				
				Toast.makeText(Conform_no.this, "This Tab is Queue.", Toast.LENGTH_SHORT).show();
				
				
				
			}
		});
		
		
		
		
        
        
        
		
        ticket.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				
				
				
				Intent intent1 = new Intent(Conform_no.this,T_queue.class);
				
				intent1.putExtra("ID",ID);//여긴 아이디를 보냅니다.
				intent1.putExtra("Password",Password);//여긴 비밀번호를 보냅니다.
				intent1.putExtra("no",month);//여긴 비밀번호를 보냅니다.
				startActivity(intent1);		
				
				
				
				
			}
		});
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

	
	


	@SuppressLint("NewApi")
	public void reservation_search()
	{
if(Available==0)
{
//	Intent intent = new Intent(BackgroundSearch.this, Reservation_check.class);
//	intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//	PendingIntent content = PendingIntent.getActivity(BackgroundSearch.this,0, intent,0);

	
	
	
		NotificationManager nm = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
		Notification noti = new Notification.Builder(Conform_no.this)
				.setTicker("대기중")
				.setContentTitle("대기중")
				.setContentText("대기중")
				.setSmallIcon(R.drawable.ic_launcher)
				.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher))


//	.setContentIntent(content)
				.build();
		noti.flags = noti.flags | noti.FLAG_AUTO_CANCEL;
		nm.notify(1235,noti);

		
		try{//////////////////////////////예약 신청 / 취소
			URL url2 = new URL(SERVER_ADDRESS + "/ticket/t_check_available.php?"
					+ "no=" + URLEncoder.encode(month,"UTF-8")
					+ "&t=" + URLEncoder.encode(t,"UTF-8")						
					);					
			url2.openStream();		
			
			phone  = getXmlData("t_check_available.xml", "result");
			if(phone.equals("1"))
				Available=1;
			else if(phone.equals("0"))
				Available=0;
		} catch(Exception e) {
			Toast.makeText(Conform_no.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
			Log.e("Error", e.getMessage());
		}
	 
		
		 try{//////////////////////////////예약 신청 / 취소
				URL url = new URL(SERVER_ADDRESS + "/ticket/admin_check_ready2.php?"
						
						);					
				url.openStream();		
				String result = getXmlData("admin_check_ready2.xml", "result");
				ready2 = result;
			} catch(Exception e) {
				Toast.makeText(Conform_no.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
				Log.e("Error", e.getMessage());
			}

		try{
			URL url_3 = new URL(SERVER_ADDRESS + "/ticket/admin_check_ready_upgrade.php?"
					//+ "&birth=" + birth
					//+ "&seat=" + seat);
					+ "name=" + URLEncoder.encode(name,"UTF-8")
					+ "&age=" + URLEncoder.encode(age,"UTF-8")
					+ "&seat=" + URLEncoder.encode(seat,"UTF-8")
				
			);

			url_3.openStream();
		}
		catch(Exception e){
			Toast.makeText(Conform_no.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
			//Log.e("Error", e.getMessage()); //onDestory();
		}// try catch 臾�醫낅즺
		String result2 = getXmlData("admin_check_ready_upgrade.xml", "result");
	
		if(result2.equals("0")&&ready2.equals("0")) {
			//리턴받은경우

			Handler handler = new Handler();
			handler.postDelayed(new Runnable() {
				public void run() {
					reservation_search();
				}
			},2000);//핸들러


			

		}
		else{
			
			try{///////////////////////////////////////////////////////////////예약 성공
				URL url_5 = new URL(SERVER_ADDRESS + "/ticket/search_success.php?"
						+ "name=" + URLEncoder.encode(name,"UTF-8")
						+ "&age=" + URLEncoder.encode(age,"UTF-8")
						+ "&seat=" + URLEncoder.encode(seat,"UTF-8")
					
				);

				url_5.openStream();
			}
			catch(Exception e){
				Toast.makeText(Conform_no.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
				//Log.e("Error", e.getMessage());
			}// try catch 臾�醫낅즺
			String result3 = getXmlData("search_success.xml", "result");
			String result4 = getXmlData("search_success.xml", "result2");
			if(result3.equals("0")&&result4.equals("0")){
				
				
			}
			else
			{
				success();
			}

		}
}
else{
	
	NotificationManager nm = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
	Notification noti = new Notification.Builder(Conform_no.this)
			.setTicker("Unavaliable상태")
			.setContentTitle("Unavaliable상태")
			.setContentText("Unavaliable상태")
			.setSmallIcon(R.drawable.ic_launcher)
			.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher))


//.setContentIntent(content)
			.build();
	noti.flags = noti.flags | noti.FLAG_AUTO_CANCEL;
	nm.notify(1235,noti);

	
	 try{//////////////////////////////예약 신청 / 취소
			URL url2 = new URL(SERVER_ADDRESS + "/ticket/t_check_available.php?"
					+ "no=" + URLEncoder.encode(month,"UTF-8")
					+ "&t=" + URLEncoder.encode(t,"UTF-8")						
					);					
			url2.openStream();		
			
			phone  = getXmlData("t_check_available.xml", "result");
			if(phone.equals("1"))
				Available=1;
			else if(phone.equals("0"))
				Available=0;
		} catch(Exception e) {
			Toast.makeText(Conform_no.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
			Log.e("Error", e.getMessage());
		}
	 
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
		Notification noti = new Notification.Builder(Conform_no.this)
				.setTicker("학생등장")
				.setContentTitle("학생등장")
				.setSmallIcon(R.drawable.ic_launcher)
				.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher))
				

//	.setContentIntent(content)
				.build();
		noti.flags = noti.flags | noti.FLAG_AUTO_CANCEL;
		nm.notify(1234, noti);
		Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
			public void run() {
				reservation_search();
			}
		},2000);//핸들러

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
		getMenuInflater().inflate(R.menu.conform_no, menu);
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
