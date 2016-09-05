package com.luomo.shopping.activity;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.text.TextUtils;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import com.luomo.shopping.Constant;
import com.luomo.shopping.R;
import com.luomo.shopping.activity.base.BaseActivity;
import com.luomo.shopping.utils.LogUtils;
import com.luomo.shopping.utils.ViewUtils;
import com.luomo.shopping.widget.zxing.camera.CameraManager;
import com.luomo.shopping.widget.zxing.decoding.CaptureActivityHandler;
import com.luomo.shopping.widget.zxing.decoding.InactivityTimer;
import com.luomo.shopping.widget.zxing.view.ViewfinderView;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

/**
 * Initial the camera
 *
 * @author Ryan.Tang
 */
public class ScannerCaptureActivity extends BaseActivity implements Callback {
    private CaptureActivityHandler handler;
    private ViewfinderView viewfinderView;
    private Vector<BarcodeFormat> decodeFormats;
    private InactivityTimer inactivityTimer;
    private MediaPlayer mediaPlayer;
    /**
     * mTvFinish 完成按钮<br/>
     * mTvTitle 标题栏<br/>
     */
    private TextView mTvFinish,mTvTitle;

    private Map<String,Integer> returnResult;
    private String characterSet;
    /**
     * 从其他界面跳过来的标识
     */
    private int pagerFlag;
    private boolean playBeep;
    private boolean hasSurface;
    private boolean vibrate;
    private static final float BEEP_VOLUME = 0.10f;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_scanner_capture);
        returnResult = new HashMap<String,Integer>();
        mContext = this;
        CameraManager.init(getApplication());
        initViews();
    }

    @Override
    protected void initViews() {
        viewfinderView = (ViewfinderView) findViewById(R.id.vfv_viewfinder);
        mTvFinish = ViewUtils.f(this, R.id.tv_right);
        mTvTitle = ViewUtils.f(this, R.id.tv_title);
        initData();
    }

    @Override
    protected void initData() {
        hasSurface = false;
        inactivityTimer = new InactivityTimer(this);
        pagerFlag = getIntent().getIntExtra(Constant.ARGUMENT_ONE,0);
        switch (pagerFlag) {
            case 1://扫描多件商品
                mTvFinish.setVisibility(View.VISIBLE);
                mTvFinish.setOnClickListener(this);//点击完成按钮
                mTvTitle.setText("扫一扫");
                break;
            case 3://获取条形码
                mTvFinish.setVisibility(View.GONE);
                mTvTitle.setText("获取条形码");
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        SurfaceView surfaceView = (SurfaceView) findViewById(R.id.sv_preview);
        SurfaceHolder surfaceHolder = surfaceView.getHolder();
        if (hasSurface) {
            initCamera(surfaceHolder);
        } else {
            surfaceHolder.addCallback(this);
            surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        }
        decodeFormats = null;
        characterSet = null;

        playBeep = true;
        AudioManager audioService = (AudioManager) getSystemService(AUDIO_SERVICE);
        if (audioService.getRingerMode() != AudioManager.RINGER_MODE_NORMAL) {
            playBeep = false;
        }
        initBeepSound();
        vibrate = true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (handler != null) {
            handler.quitSynchronously();
            handler = null;
        }
        CameraManager.get().closeDriver();
    }

    @Override
    protected void onDestroy() {
        inactivityTimer.shutdown();
        super.onDestroy();
    }

    /**
     * TODO:处理扫描结果
     * @param result
     * @param barcode
     */
    public void handleDecode(Result result, Bitmap barcode) {
        inactivityTimer.onActivity();
        playBeepSoundAndVibrate();
        String resultString = result.getText();
        if (TextUtils.isEmpty(resultString)) {//扫描失败
            Toast.makeText(ScannerCaptureActivity.this, "Scan failed!", Toast.LENGTH_SHORT).show();
        } else {
            if(pagerFlag == 3){
                Intent intent = new Intent();
                intent.putExtra(Constant.ARGUMENT_ONE, resultString);
                setResult(Constant.FLAG_RESULT_CODE_1000,intent);
                finish();
            }else {
                Intent resultIntent = new Intent(ScannerCaptureActivity.this, GoodsSelectedActivity.class);
                resultIntent.putExtra("result", resultString);
                startActivityForResult(resultIntent, Constant.FLAG_1000);
            /*Bundle bundle = new Bundle();
            bundle.putString("result", resultString);
            bundle.putParcelable("bitmap", barcode);
            resultIntent.putExtras(bundle);
            this.setResult(RESULT_OK, resultIntent);
            startActivityForResult(resultIntent, Constant.FLAG_1000);*/
//            ScannerCaptureActivity.this.finish();
            }
        }
    }

    private void initCamera(SurfaceHolder surfaceHolder) {
        try {
            CameraManager.get().openDriver(surfaceHolder);
        } catch (IOException ioe) {
            return;
        } catch (RuntimeException e) {
            return;
        }
        if (handler == null) {
            handler = new CaptureActivityHandler(this, decodeFormats, characterSet);
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if (!hasSurface) {
            hasSurface = true;
            initCamera(holder);
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        hasSurface = false;
    }

    public ViewfinderView getViewfinderView() {
        return viewfinderView;
    }

    public Handler getHandler() {
        return handler;
    }

    public void drawViewfinder() {
        viewfinderView.drawViewfinder();
    }

    private void initBeepSound() {
        if (playBeep && mediaPlayer == null) {
            // The volume on STREAM_SYSTEM is not adjustable, and users found it
            // too loud,
            // so we now play on the music stream.
            setVolumeControlStream(AudioManager.STREAM_MUSIC);
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setOnCompletionListener(beepListener);

            AssetFileDescriptor file = getResources().openRawResourceFd(R.raw.beep);
            try {
                mediaPlayer.setDataSource(file.getFileDescriptor(), file.getStartOffset(), file.getLength());
                file.close();
                mediaPlayer.setVolume(BEEP_VOLUME, BEEP_VOLUME);
                mediaPlayer.prepare();
            } catch (IOException e) {
                mediaPlayer = null;
            }
        }
    }

    private static final long VIBRATE_DURATION = 200L;

    private void playBeepSoundAndVibrate() {
        if (playBeep && mediaPlayer != null) {
            mediaPlayer.start();
        }
        if (vibrate) {
            Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
            vibrator.vibrate(VIBRATE_DURATION);
        }
    }

    /**
     * When the beep has finished playing, rewind to queue up another one.
     */
    private final OnCompletionListener beepListener = new OnCompletionListener() {
        public void onCompletion(MediaPlayer mediaPlayer) {
            mediaPlayer.seekTo(0);
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_right://点击完成
                finish();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        LogUtils.i(TAG,"按下了back键   onBackPressed()");
        finish();
    }
}