package com.example.raudiomodule;

import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class RaudioActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raudio);
        initView();
    }

    private void initView() {
        Button button  = (Button) findViewById(R.id.button);
        Button button2 = (Button) findViewById(R.id.button2);
        Button button3 = (Button) findViewById(R.id.button3);
        button.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);

    }

    private boolean      isRequested = false;
    private AudioManager audioManager;

    /**
     * @param context
     * @param flag    0，1    1 表示降低外部音量
     */
    public void requestAudioFocus(Context context, int flag) {
        LogUtil.warnLog(LogUtil.logStaus, "-------------------声音开始------------------");
        if (audioManager == null) {
            audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        }
        if (audioManager != null) {
            int durationHint = AudioManager.AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK;
            if (flag == 0) {
                durationHint = AudioManager.AUDIOFOCUS_GAIN_TRANSIENT;
            }
//            durationHint = AudioManager.AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK;
            audioManager.requestAudioFocus(onAudioFocusChangeListener, AudioManager.STREAM_MUSIC, durationHint);
            isRequested = true;
        }
    }

    public void abandonAudioFocus() {
        if (audioManager != null && isRequested) {
            isRequested = false;
            audioManager.abandonAudioFocus(onAudioFocusChangeListener);
        }
    }

    protected AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            switch (focusChange) {
                case AudioManager.AUDIOFOCUS_GAIN:
                    // do nothingø
                    LogUtil.warnLog(LogUtil.logStaus, "-------------------结束------------------AUDIOFOCUS_GAIN");
                    break;
                case AudioManager.AUDIOFOCUS_LOSS:
//                    Pause();
                    LogUtil.warnLog(LogUtil.logStaus, "-------------------结束------------------AUDIOFOCUS_LOSS");
                    break;
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                    LogUtil.warnLog(LogUtil.logStaus, "-------------------结束------------------AUDIOFOCUS_LOSS_TRANSIENT");
                    break;
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                    LogUtil.warnLog(LogUtil.logStaus, "-------------------结束------------------AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK");
                    //do nothing
                    break;
            }
        }
    };

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button) {
//            requestAudioFocus(this, 1);
            DmAudioHelper.getInstance().requestAudioFocus(this, 1);
        } else if (v.getId() == R.id.button2) {
//            requestAudioFocus(this, 0);
            DmAudioHelper.getInstance().requestAudioFocus(this, 0);
        } else if (v.getId() == R.id.button3) {
//            abandonAudioFocus();
            DmAudioHelper.getInstance().abandonAudioFocus();
        }
    }
}
