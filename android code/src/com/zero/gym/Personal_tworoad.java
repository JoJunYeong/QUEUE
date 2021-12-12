package com.zero.gym;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Personal_tworoad extends Activity implements OnClickListener {

	String i ="0";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_personal_tworoad);
		
		startActivity(new Intent(this, Intro.class));
		Button bt1 = (Button)findViewById(R.id.button1);
		bt1.setOnClickListener(this);
		Button bt2 = (Button)findViewById(R.id.button2);
		bt2.setOnClickListener(this);
		
		
		
		
	}

	public void onClick(View v)
	{
		switch (v.getId()) {
		case R.id.button1:		// 체육관찾기
			Intent intent1 = new Intent(this,Gym_list.class);
			intent1.putExtra("i",i);//여긴 이름을 보냅니다.
			startActivity(intent1);			
			break;

		case R.id.button2:		//예약관리
			Intent intent2 = new Intent(this,Reservation_check_login.class);
			intent2.putExtra("i",i);//여긴 이름을 보냅니다.
			startActivity(intent2);		
			break;
				
		
		}		
		
		
		
		
		
		
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.personal_tworoad, menu);
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
