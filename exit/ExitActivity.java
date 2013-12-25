package com.avic;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.widget.Toast;

public class ExitActivity extends Activity {
	private int exitNum = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(event.getKeyCode() == KeyEvent.KEYCODE_BACK){
			Handler handler = new Handler();
			if(exitNum >= 1){
				finish();
				System.exit(0);
			}else{
				Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_LONG).show();
				exitNum = 1;
				handler.postDelayed(new Runnable(){
					@Override
					public void run() {
						exitNum = 0;
					}
				}, 2000);
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}
