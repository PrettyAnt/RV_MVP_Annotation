package com.example.mvpstrategy;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mvpstrategy.imp.OnClickListenerImp;
import com.example.mvpstrategy.presenter.MyPresenter;
import com.example.mvpstrategy.utils.MyDialogUtils;
import com.example.mvpstrategy.view.IView;

import java.util.Map;

public class MvpStrategyActivity extends AppCompatActivity implements IView, View.OnClickListener {

    private Button btn_back, btn_next_act;
    private TextView    tv_show_msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvpstrategy);
        initView();
        initEvent();
    }


    private void initView() {
        btn_back = (Button) findViewById(R.id.btn_back);
        btn_next_act = (Button) findViewById(R.id.btn_test);
        tv_show_msg = (TextView) findViewById(R.id.tv_show_msg);
    }

    private void initEvent() {
        btn_back.setOnClickListener(this);
        btn_next_act.setOnClickListener(this);
    }

    private StringBuffer sb = new StringBuffer();

    @Override
    public void onGetDataList(Map<Integer, String> datas)   {
        for (int i = 0; i < datas.size(); i++) {
            sb.append(datas.get(i));
            Log.i("ttt", datas.get(i));
        }
        tv_show_msg.setText("收到了消息-->>  " + sb.toString());
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_back) {
            MyDialogUtils.getInstance().commDialog(this,
                    "提示",
                    "\"确定\"退出当前界面",
                    "取消",
                    new OnClickListenerImp() {
                        @Override
                        public void onClickListener(AlertDialog dialog, View view) {
                            dialog.dismiss();
                        }
                    }, "确定",
                    new OnClickListenerImp() {
                        @Override
                        public void onClickListener(AlertDialog dialog, View view) {
                            dialog.dismiss();
                            finish();
                        }
                    }, new DialogInterface.OnKeyListener() {
                        @Override
                        public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                            if (event.getAction() == KeyEvent.ACTION_DOWN) {
                                Toast.makeText(MvpStrategyActivity.this, "退出111111", Toast.LENGTH_SHORT).show();
                                return true;
                            } else {
                                Toast.makeText(MvpStrategyActivity.this, "没", Toast.LENGTH_SHORT).show();
                                return false;
                            }
                        }
                    });
        } else if (id == R.id.btn_test) {
            MyPresenter.getInstance().loadDatas(this);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Toast.makeText(this, "退出", Toast.LENGTH_SHORT).show();
        return super.onKeyDown(keyCode, event);
    }
}