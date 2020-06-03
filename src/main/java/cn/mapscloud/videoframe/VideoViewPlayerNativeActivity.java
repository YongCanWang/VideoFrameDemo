package cn.mapscloud.videoframe;

import android.app.Activity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.FileProvider;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.mapscloud.videoframe.Utils.Config;

/**
 * Created by wangyongcan on 2018/6/23.
 */

public class VideoViewPlayerNativeActivity extends Activity {

    @BindView(R.id.vv_videoview)
    VideoView vvVideoview;


    private String VideoPath = Config.INTERIOR_STORAGE + "/mapplus" + "/app" + "/VideoTest.mp4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videoviewplayernative);
        ButterKnife.bind(this);

    }



    @Override
    protected void onStart() {
        super.onStart();
    }


    @Override
    protected void onResume() {
        super.onResume();
        startPlay(VideoPath);
    }

    private void startPlay(String VideoPath) {



        Uri uri = Uri.parse( VideoPath );
//        File file = new File(VideoPath);
//        Uri uri = FileProvider.getUriForFile(this, getApplicationContext().getPackageName() + ".provider", file);

        //设置视频控制器
        vvVideoview.setMediaController(new MediaController(this));

        //播放完成回调
        vvVideoview.setOnCompletionListener( new MyPlayerOnCompletionListener());

        //设置视频路径
        vvVideoview.setVideoURI(uri);

        //开始播放视频
        vvVideoview.start();
    }


    @Override
    protected void onPause() {
        super.onPause();
    }


    @Override
    protected void onStop() {
        super.onStop();
    }


    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }



    class MyPlayerOnCompletionListener implements MediaPlayer.OnCompletionListener {

        @Override
        public void onCompletion(MediaPlayer mp) {
            Toast.makeText(VideoViewPlayerNativeActivity.this, "播放完成了", Toast.LENGTH_SHORT).show();
        }
    }
}
