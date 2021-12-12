package com.zero.gym;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Gym_list extends Activity implements OnClickListener {

	
	String i ="0";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gym_list);
		
		
		
		Button bt1 = (Button)findViewById(R.id.button1);
		bt1.setOnClickListener(this);
		Button bt2 = (Button)findViewById(R.id.button2);
		bt2.setOnClickListener(this);
		Button bt3 = (Button)findViewById(R.id.button2);
		bt3.setOnClickListener(this);
		
		
		
	}

	
	public void onClick(View v)
	{
		switch (v.getId()) {
		case R.id.button1:
			Intent intent1 = new Intent(this,MainActivity.class);
			intent1.putExtra("i",i);//���� �̸��� �����ϴ�.
			intent1.putExtra("name","�������ƿ���");
			startActivity(intent1);			
			break;

		case R.id.button2:
			Intent intent2 = new Intent(this,MainActivity.class);
			intent2.putExtra("i",i);//���� �̸��� �����ϴ�.
			intent2.putExtra("name","�ټ��ʵ��б�");
			startActivity(intent2);		
			break;
				
		case R.id.button3:
			Intent intent3 = new Intent(this,MainActivity.class);
			intent3.putExtra("i",i);//���� �̸��� �����ϴ�.
			intent3.putExtra("name","�����б�");
			startActivity(intent3);		
			break;
		}		
		
		
		
		
		
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.gym_list, menu);
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
