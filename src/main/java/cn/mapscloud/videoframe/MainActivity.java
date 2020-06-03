package cn.mapscloud.videoframe;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.mapscloud.videoframe.Utils.PermissionUtils;
import pub.devrel.easypermissions.EasyPermissions;

public class MainActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks {

    @BindView(R.id.bt_qiniu_video)
    Button btQiniuVideo;
    @BindView(R.id.bt_exoplayer)
    Button btExoplayer;
    @BindView(R.id.bt_gsyvideoplayer)
    Button btGsyvideoplayer;
    @BindView(R.id.bt_systemplayer)
    Button btSystemplayer;
    @BindView(R.id.bt_systemvideo)
    Button btSystemvideo;
    @BindView(R.id.bt_videoView)
    Button btVideoView;
    private Intent intent;

    private final String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        PermissionUtils.applyPermission(this);

    }

    @OnClick({R.id.bt_qiniu_video, R.id.bt_exoplayer, R.id.bt_gsyvideoplayer, R.id.bt_systemplayer, R.id.bt_systemvideo, R.id.bt_videoView})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_qiniu_video:
                startActivity(new Intent(this, QiniuVideoActivity.class));
                break;
            case R.id.bt_exoplayer:
                startActivity(new Intent(this, ExoplayerActivity.class));
                break;
            case R.id.bt_gsyvideoplayer:
                intent = new Intent(this, GSYVideoPlayerActivity.class);
                intent.putExtra(GSYVideoPlayerActivity.TRANSITION, true);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Pair pair = new Pair<>(view, GSYVideoPlayerActivity.IMG_TRANSITION);
                    ActivityOptionsCompat activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(
                            this, pair);
                    ActivityCompat.startActivity(this, intent, activityOptions.toBundle());
                } else {
                    startActivity(intent);
                    overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
                }
                break;

            case R.id.bt_systemplayer:
                startActivity(new Intent(this, SystemPlayerActivity.class));
                break;

            case R.id.bt_systemvideo:
                startActivity(new Intent(this, SystemVoiceActivity.class));
                break;

            case R.id.bt_videoView:
                startActivity(new Intent(this, VideoViewActivity.class));
                break;

        }

    }


    //下面两个方法是实现EasyPermissions的EasyPermissions.PermissionCallbacks接口
    //分别返回授权成功和失败的权限
    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        Log.e(TAG, "获取成功的权限:" + perms);
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        Log.e(TAG, "获取失败的权限:" + perms);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        //把申请权限的回调交由EasyPermissions处理
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

}


