package com.baidu.activity;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Date;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.format.Time;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
    Button send;
    EditText edit;
    TextView tv;
    InetAddress iiaddress;
    String string;
 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);	
		setContentView(R.layout.activity_main);
		send = (Button)findViewById(R.id.send);
		edit = (EditText)findViewById(R.id.edit);
		tv = (TextView)findViewById(R.id.tv);
		send.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.v("22222", "aaaaaa");
				new SendThread("192.168.1.11").start();
				Log.v("33333", "aaaaaa");
				//tv.setText(iiaddress.getHostName());
				Log.v("44444", "aaaaaa");
				//tv.setText(string);
				
			}
		});
	}
	
	class SendThread extends Thread{
		String ip = null;
		public SendThread(String ip){
			this.ip = ip;
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();
			try {
				DatagramSocket socket = new DatagramSocket(9000);
				InetAddress address = InetAddress.getByName(ip);
				String str = edit.getText().toString();
				byte[] data = str.getBytes("utf-8");
				DatagramPacket pack = new DatagramPacket(data, data.length, address, 9000);
				socket.send(pack);
//				byte[] data1 = new byte[100];
//				DatagramPacket pack1 = new DatagramPacket(data1, data1.length, address, 9000);
//				socket.receive(pack1);
//				string = new String(pack1.getData());
				//iiaddress = socket.getInetAddress();
				socket.close();
				//Log.v("1111111111111", socket.isConnected() + "");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
