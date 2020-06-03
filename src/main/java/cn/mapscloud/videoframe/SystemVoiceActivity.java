package cn.mapscloud.videoframe;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.mapscloud.videoframe.Utils.Config;

/**
 * Created by wangyongcan on 2018/6/22.
 */

public class SystemVoiceActivity extends Activity {

    @BindView(R.id.bt_playNative)
    Button btPlayNative;
    @BindView(R.id.bt_playNetwork)
    Button btPlayNetwork;
    private String Path = Config.INTERIOR_STORAGE + "/mapplus" + "/app" + "/VoiceTest.mp3";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_systemvoice);
        ButterKnife.bind(this);



    }



    @OnClick({R.id.bt_playNative, R.id.bt_playNetwork})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_playNative:
                StartSyatemVoiceNative(Path);
                break;
            case R.id.bt_playNetwork:
                break;
        }
    }


//    public void startSyatemVoice(String Path) {
//        Intent intent = new Intent();
//        intent.setAction(android.content.Intent.ACTION_VIEW);
////       Uri uri = Uri.parse("file:///sdcard/a.mp3");  // 替换成audiopath
//        Uri uri = Uri.parse("file:///" + Path);  // 替换成audiopath
//        intent.setDataAndType(uri , "audio/mp3");
//        startActivity(intent);
//    }



    public void StartSyatemVoiceNative(String Path) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
//       Uri uri = Uri.parse("file:///sdcard/a.mp3");  // 替换成audiopath
        Uri uri = Uri.parse("file:///" + Path);  // 替换成audiopath
        intent.setDataAndType(uri, "audio/mp3");
        startActivity(intent);
    }


}
