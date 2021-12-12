package com.zero.gym;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Sendok extends Activity implements OnClickListener {

	
	String year = "";
	String month = "";
	String day = "";
	String time = "";
	String Cname = "";
	String name = "";
	String age = "";
	String perpose = "";
	String phone = "";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sendok);
		
		
		//intent ��� ���
		Intent intent = getIntent();
		year = intent.getStringExtra("year");
		month = intent.getStringExtra("month");
		day = intent.getStringExtra("day");
		time = intent.getStringExtra("time");
		name = intent.getStringExtra("name");
		age = intent.getStringExtra("age");
		perpose = intent.getStringExtra("perpose");
		phone = intent.getStringExtra("phone");
		Cname = intent.getStringExtra("Cname");
		
		
		
		Toast.makeText(Sendok.this, ""+year+"/"+month+"////"+day+"//"+time+"//"+name, Toast.LENGTH_SHORT).show(); // ���� ����� �Ѿ�Դ��� Ȯ��
		
		TextView testView1 = (TextView)findViewById(R.id.textView11);
		testView1.setText(Cname);
		TextView testView2 = (TextView)findViewById(R.id.textView21);
		testView2.setText(time);
		
		
		 Button bt1 = (Button)findViewById(R.id.button1);
			bt1.setOnClickListener(this);
		
		
		
		
		
		
		
	}
	
	
	public void onClick(View v)
	{
		switch (v.getId()) {
		case R.id.button1:
			Intent intent1 = new Intent(this,Reservation_check.class);
			intent1.putExtra("year",year);
			intent1.putExtra("month",month);
			intent1.putExtra("day",day);
			intent1.putExtra("time",time);
			intent1.putExtra("name",name);
			intent1.putExtra("age", age);
			intent1.putExtra("perpose", perpose);
			intent1.putExtra("phone", phone);
			intent1.putExtra("Cname", Cname);
			startActivity(intent1);			
			break;
		}
	}
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sendok, menu);
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
