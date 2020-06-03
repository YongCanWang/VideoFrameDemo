package cn.mapscloud.videoframe;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.pili.pldroid.player.PLOnCompletionListener;
import com.pili.pldroid.player.PLOnErrorListener;
import com.pili.pldroid.player.PLOnInfoListener;
import com.pili.pldroid.player.PLOnVideoSizeChangedListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.mapscloud.videoframe.Utils.Config;

/**
 * Created by wangyongcan on 2018/2/22.
 */

public class QiniuVideoActivity extends Activity implements
        PLOnInfoListener,
        PLOnCompletionListener,
        PLOnVideoSizeChangedListener,
        PLOnErrorListener {
    private String TAG = this.getClass().getSimpleName();

//    @BindView(R.id.PLVideoView)
//    com.pili.pldroid.player.widget.PLVideoView PLVideoView;
    @BindView(R.id.PLVideoTextureView)
    com.pili.pldroid.player.widget.PLVideoTextureView PLVideoTextureView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qiniuvideo);
        ButterKnife.bind(this);

        // 关联播放控制器
        MMediaController mMediaController = new MMediaController(this);
        PLVideoTextureView.setMediaController(mMediaController);

        // 设置加载动画
        View loadingView = findViewById(R.id.loadering);
        PLVideoTextureView.setBufferingIndicator(loadingView);

        // 设置播放状态监听器
        PLVideoTextureView.setOnInfoListener(this);
        PLVideoTextureView.setOnCompletionListener(this);
        PLVideoTextureView.setOnVideoSizeChangedListener(this);
        PLVideoTextureView.setOnErrorListener(this);

        // 各种画面预览模式，包括：原始尺寸、适应屏幕、全屏铺满、16:9、4:3 等，设置方法如下：
        PLVideoTextureView.setDisplayAspectRatio(PLVideoTextureView.ASPECT_RATIO_ORIGIN);
        PLVideoTextureView.setDisplayAspectRatio(PLVideoTextureView.ASPECT_RATIO_FIT_PARENT);
        PLVideoTextureView.setDisplayAspectRatio(PLVideoTextureView.ASPECT_RATIO_PAVED_PARENT);
        PLVideoTextureView.setDisplayAspectRatio(PLVideoTextureView.ASPECT_RATIO_16_9);
        PLVideoTextureView.setDisplayAspectRatio(PLVideoTextureView.ASPECT_RATIO_4_3);

        // 支持画面旋转，支持播放画面以 0度，90度，180度，270度旋转，设置方法如下：
//        PLVideoTextureView.setDisplayOrientation(90); // 旋转90度

        // 画面的镜像变换
        PLVideoTextureView.setMirror(true);

//        PLVideoTextureView.setVideoPath("http://202.107.245.41:5500/file/tmp/1512733286753/c8ebf646-dc0c-11e7-95e7-0242ac110016.mp4?ticket=d992a7c0-176b-11e8-90ad-0242ac110019");
//        PLVideoTextureView.setVideoPath("rtmp://live.hkstv.hk.lxdns.com/live/hks");
//        PLVideoTextureView.setVideoPath("https://res.exexm.com/cw_145225549855002");  // 长视频
        PLVideoTextureView.setVideoPath(Config.VIDEOURL);
//        PLVideoTextureView.setVideoPath("http://mvvideo11.meitudata.com/5a8ab3d423e7f6186_H264_3.mp4?k=6114a6d74c22e5ed26bfe930613c1314&t=5a925183");

        // 开始播放
        PLVideoTextureView.start();
//        PLVideoTextureView.pause();
//        PLVideoTextureView.stopPlayback();

        // 取消并排模式
        PLVideoTextureView.disableSplitMode();





    }

    @Override
    public void onCompletion() {
        Log.e(TAG, "onCompletion");
    }

    @Override
    public boolean onError(int i) {
        Log.e(TAG, "onError：" + i);
        return false;
    }

    @Override
    public void onInfo(int i, int i1) {
        Log.e(TAG, "onInfo：i:" + i +"=== i1:" + i1);
    }

    @Override
    public void onVideoSizeChanged(int i, int i1) {
        Log.e(TAG, "onVideoSizeChanged：i:" + i +"=== i1:" + i1);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        PLVideoTextureView.pause();
    }
}
