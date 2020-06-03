package cn.mapscloud.videoframe;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.mapscloud.videoframe.Utils.Config;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created by wangyongcan on 2018/6/22.
 */

public class SystemPlayerActivity extends Activity  {

    @BindView(R.id.bt_playNative)
    Button btPlayNative;
    @BindView(R.id.bt_playNetwork)
    Button btPlayNetwork;

    private final String TAG = this.getClass().getSimpleName();

    private String Url = Config.VIDEOURL;
    private String Path = Config.INTERIOR_STORAGE + "/mapplus" + "/app" + "/VideoTest.mp4";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_systemplayer);
        ButterKnife.bind(this);

    }


    @OnClick({R.id.bt_playNative, R.id.bt_playNetwork})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_playNative:
                CallSystemPlayerPlayNative(Path);
                break;
            case R.id.bt_playNetwork:
                CallSystemPlayerPlayNetwork(Url);
                break;
        }
    }



    private void CallSystemPlayerPlayNative(String path) {
        Uri uri;
        Intent intent = new Intent(Intent.ACTION_VIEW);
//        String path = Environment.getExternalStorageDirectory().getPath() + "/1.mp4";//该路径可以自定义
        File file = new File(path);
//        Uri uri = Uri.fromFile(file);
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT_WATCH) {
             uri = FileProvider.getUriForFile(this, getApplicationContext().getPackageName() + ".provider", file);     // 7.0系统使用
        } else {
             uri = Uri.parse("file://" + path);      // 4.4系统使用
        }
        intent.setDataAndType(uri, "video/*");
//        intent.setDataAndType(uri, "video/map4");
        startActivity(intent);

    }


    private void CallSystemPlayerPlayNetwork(String url) {
//        String url = "http://9890.vod.myqcloud.com/9890_4e292f9a3dd011e6b4078980237cc3d3.f30.mp4";//示例，实际填你的网络视频链接
        String extension = MimeTypeMap.getFileExtensionFromUrl(url);
        String mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
        Intent mediaIntent = new Intent(Intent.ACTION_VIEW);
        mediaIntent.setDataAndType(Uri.parse(url), mimeType);
        startActivity(mediaIntent);
    }





//    Uri uri = Uri.parse("网络视频地址");
//    VideoView videoView = (VideoView)this.findViewById(R.id.video_view);
//        videoView.setMediaController(new MediaController(this));
//        videoView.setVideoURI(uri);
//        videoView.start();
//        videoView.requestFocus();（来至网络，未测试时候能播放）


}
