package com.qixiu.qixiu.utils.audio;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.IBinder;

import java.io.IOException;


public class PlayMusicService extends Service implements MediaPlayer.OnCompletionListener {
    private static final String TAG = "MusicService";
    MediaPlayer player;

    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // TODO Auto-generated method stub
        if(SoundUtils.getSoundResourse()==0){
            player=new MediaPlayer();
            try {
                player.setDataSource(SoundUtils.getFilePath());
                player.setAudioStreamType(AudioManager.STREAM_MUSIC);
                player.setVolume(1.0f, 1.0f);
                player.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            player = MediaPlayer.create(this, SoundUtils.getSoundResourse());
        }
        player.setOnCompletionListener(this);
        player.start();
        return super.onStartCommand(intent, flags, startId);
    }

    //音频播放完成触发此方法，在此处理播放完成要做的操作
    @Override
    public void onCompletion(MediaPlayer mp) {
        // TODO Auto-generated method stub
        mp.stop();//停止MediaPlayer
        stopSelf();//停止Serveice
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        try {
            player.stop();
            player.release();
            player = null;
        }catch (Exception e){

        }
    }
}
