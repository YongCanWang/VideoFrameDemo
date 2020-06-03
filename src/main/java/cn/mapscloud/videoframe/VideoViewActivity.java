package cn.mapscloud.videoframe;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by wangyongcan on 2018/6/23.
 */

public class VideoViewActivity extends Activity {


    @BindView(R.id.bt_playNative)
    Button btPlayNative;
    @BindView(R.id.bt_playNetwork)
    Button btPlayNetwork;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videoview);
        ButterKnife.bind(this);

    }


    @Override
    protected void onStart() {
        super.onStart();
    }


    @Override
    protected void onResume() {
        super.onResume();

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


    @OnClick({R.id.bt_playNative, R.id.bt_playNetwork})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_playNative:
                startActivity(new Intent(this, VideoViewPlayerNativeActivity.class));
                break;
            case R.id.bt_playNetwork:
                startActivity(new Intent(this, VideoViewPlayerNetworkActivity.class));
                break;
        }
    }
}
