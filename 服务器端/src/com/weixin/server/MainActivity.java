package com.weixin.server;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	ServerSocket serverSock;
    TextView tv;
    TextView tv1;
    EditText edit;
    Button receive;
    boolean b;
    String str;
    String str1;
	Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			if (msg.what == 0x123){
				str  = msg.obj.toString();
			}
		}
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		edit = (EditText)findViewById(R.id.edit);
		receive = (Button)findViewById(R.id.btn);
		tv = (TextView)findViewById(R.id.tv);
		tv1 = (TextView)findViewById(R.id.tv1);
		receive.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						InetAddress localAddress;
						try {
							localAddress = InetAddress.getByName("192.168.1.11");
							if (serverSock == null){
								serverSock = new ServerSocket(9090, 50, localAddress);	
							}
						    while (true){
							Socket clientSock = null;
							clientSock = serverSock.accept();
							str1 = new String(clientSock.getInetAddress().getAddress());
							b = clientSock.isConnected();
							InputStream in=  clientSock.getInputStream();
							InputStreamReader input = new InputStreamReader(in);
					        BufferedReader reader = new BufferedReader(input);
					        if (reader.readLine() == null){
					        	str = "1111111111";
					        }
					        else
					        	str = "222222222";
						    
					        Message msg = new Message();
					        msg.obj = reader.readLine();
					        Log.v("111111", msg.obj.toString());
					        msg.what = 0x123;
					        handler.sendMessage(msg);
					        clientSock.close();
					        serverSock.close();
						    }
							//BufferedReader reader = new 
						} 
						catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
				}).start();
				
			}
		});
		tv.setText("111111");
		//tv1.setText("2222222");
	}

}
