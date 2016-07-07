package 接收系统信号;

import com.sign.systemsign.R;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class SystemService extends Service {
	MediaPlayer player;
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		player = MediaPlayer.create(SystemService.this, R.raw.shaitaiyang);
		player.start();
	}
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

}
