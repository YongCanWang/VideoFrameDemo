package cn.mapscloud.videoframe;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.mapscloud.videoframe.Utils.Config;

/**
 * Created by wangyongcan on 2018/2/22.
 */

public class ExoplayerActivity extends Activity {

    @BindView(R.id.exo_player_view)
    SimpleExoPlayerView exoPlayerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exoplayer);
        ButterKnife.bind(this);

//        initExoplayer("http://202.107.245.41:5500/file/tmp/1512733286753/c8ebf646-dc0c-11e7-95e7-0242ac110016.mp4?ticket=d992a7c0-176b-11e8-90ad-0242ac110019");
//        initExoplayer("rtmp://live.hkstv.hk.lxdns.com/live/hks");
//        initExoplayer("http://mvvideo11.meitudata.com/5a8ab3d423e7f6186_H264_3.mp4?k=6114a6d74c22e5ed26bfe930613c1314&t=5a925183");
        initExoplayer(Config.VIDEOURL);
    }


    // wyc
    private void initExoplayer(String url) {
        // step1. 创建一个默认的TrackSelector
        Handler mainHandler = new Handler();
        // 创建带宽
        BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        // 创建轨道选择工厂
//        TrackSelection.Factory videoTrackSelectionFactory = new AdaptiveVideoTrackSelection.Factory(bandwidthMeter);
        TrackSelection.Factory videoTrackSelectionFactory = new AdaptiveTrackSelection.Factory(bandwidthMeter);

        // 创建轨道选择器实例
//        TrackSelector trackSelector = new DefaultTrackSelector(mainHandler,videoTrackSelectionFactory);
        TrackSelector trackSelector = new DefaultTrackSelector(videoTrackSelectionFactory);


        //step2. 创建播放器
        SimpleExoPlayer player = ExoPlayerFactory.newSimpleInstance(this, trackSelector, new DefaultLoadControl());

        exoPlayerView.setPlayer(player);


        // 测量播放带宽，如果不需要可以传null
        DefaultBandwidthMeter bandwidthMeter2 = new DefaultBandwidthMeter();

        // 创建加载数据的工厂
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(this,
                Util.getUserAgent(this, "yourApplicationName"), bandwidthMeter2);

        // 创建解析数据的工厂
        ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();

        // 传入Uri、加载数据的工厂、解析数据的工厂，就能创建出MediaSource
        MediaSource videoSource = new ExtractorMediaSource(
                Uri.parse(url),
                dataSourceFactory, extractorsFactory, null, null);

//        "http://202.107.245.41:5500/file/tmp/1512733286753/c8ebf646-dc0c-11e7-95e7-0242ac110016.mp4?ticket=a7f53d36-0a0f-11e8-90ad-0242ac110019"
//        "http://202.107.245.41:
// 5500/file/content/record_video/123/ef4f575c-de36-11e7-b814-0242ac110016.mp4?ticket=a7f53d36-0a0f-11e8-90ad-0242ac110019"
        // Prepare
//        boolean loading = player.isLoading();
//        player.release();
//        player.stop();
        player.setPlayWhenReady(true);
        player.setShuffleModeEnabled(true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
