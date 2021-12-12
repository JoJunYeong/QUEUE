package com.zero.gym;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Reservation_check_login extends Activity {

	
	
	String name = "";
	String age = "";		// 받아올 아이들 미리 변수선언함
	EditText edtname, edtage;
	Button ok,no;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reservation_check_login);
		
		ok= (Button)findViewById(R.id.button1);
	        no= (Button)findViewById(R.id.button2);
	      
	        no.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Reservation_check_login.this.finish();
				}
			});     
	        
	        ok.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
				
					
					edtname = (EditText)findViewById(R.id.name);
				    edtage = (EditText)findViewById(R.id.age);
					
					
					if(edtname.getText().toString().equals("")||edtage.getText().toString().equals("")){
						Toast.makeText(Reservation_check_login.this, "다 적어주세요.", Toast.LENGTH_SHORT).show();
						return;
						
					}
					
					name = edtname.getText().toString();
					age = edtage.getText().toString();
					
					 
					 Intent intent1 = new Intent(Reservation_check_login.this,Reservation_check.class);
						intent1.putExtra("name",name);//여긴 이름을 보냅니다.
						intent1.putExtra("age",age);
						startActivity(intent1);		
						Toast.makeText(Reservation_check_login.this, "전송 완료", Toast.LENGTH_SHORT).show();			
						edtname.setText("");
						edtage.setText("");
					//	finish();	
					
					
					
					
				}
			});     
	        
			
	        
	        
			
			
			
	        
			
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.reservation_check_login, menu);
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
