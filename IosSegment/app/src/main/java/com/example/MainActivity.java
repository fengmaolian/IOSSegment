package com.example;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.TimePopupWindow;
import com.example.iossearch.SearchEditText;
import com.example.iossegment.R;
import com.example.iossegment.SegmentView;
import com.example.iossegment.SegmentViewThree;
import com.example.switchbutton.UISwitchButton;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends Activity {
    private SegmentView mSegmentView;
    private SegmentViewThree mSegmentViewThree;
    private UISwitchButton mSwitch1;
    private UISwitchButton mSwitch2;

    private SearchEditText mSearchEditTextOne;
    private SearchEditText mSearchEditTextTwo;
    private Context mContext;

    private TextView mTvShow;
    private TimePopupWindow pwTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        initView();
        initViewThree();
        initSearch();
        initSwitch();
        initTimeView();

    }



    /** 两个按钮切换 */
    private void initView() {
        mSegmentView = (SegmentView) findViewById(R.id.segment_view);
        mSegmentView.setSegmentText("你好", 0);
        mSegmentView.setSegmentText("我好", 1);
        mSegmentView.setOnSegmentViewClickListener(new SegmentView.onSegmentViewClickListener() {
            @Override
            public void onSegmentViewClick(View v, int position) {
                switch (position) {
                    case 0:
                        Toast.makeText(mContext, "你好", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Toast.makeText(mContext, "我好", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
            }
        });
    }

    /** 三个按钮切换 */
    private void initViewThree() {
        mSegmentViewThree = (SegmentViewThree) findViewById(R.id.segment_view_three);
        mSegmentViewThree.setSegmentText("你好", 0);
        mSegmentViewThree.setSegmentText("我好", 1);
        mSegmentViewThree.setSegmentText("他好", 2);
        mSegmentViewThree.setOnSegmentViewClickListener(new SegmentViewThree.onSegmentViewClickListener() {
            @Override
            public void onSegmentViewClick(View v, int position) {
            switch (position) {
                case 0:
                    Toast.makeText(mContext, "你好", Toast.LENGTH_SHORT).show();
                    break;
                case 1:
                    Toast.makeText(mContext, "我好", Toast.LENGTH_SHORT).show();
                    break;
                case 2:
                    Toast.makeText(mContext, "他好", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
            }
        });
    }

    /** 搜索框实现 */
    private void initSearch() {
        mSearchEditTextOne = (SearchEditText) findViewById(R.id.m_search_one);
        mSearchEditTextTwo = (SearchEditText) findViewById(R.id.m_search_two);
        // 实现TextWatcher监听即可
        mSearchEditTextOne.setOnSearchClickListener(new SearchEditText.OnSearchClickListener() {
            @Override
            public void onSearchClick(View view) {
                Toast.makeText(MainActivity.this, "i'm going to seach", Toast.LENGTH_SHORT).show();
            }
        });
        // 实现TextWatcher监听即可
        mSearchEditTextTwo.setOnSearchClickListener(new SearchEditText.OnSearchClickListener() {
            @Override
            public void onSearchClick(View view) {
                Toast.makeText(MainActivity.this, "i'm going to seach", Toast.LENGTH_SHORT).show();
            }
        });
    }
    /** 时间选择效果实现 */
    private void initTimeView() {

        mTvShow = (TextView) findViewById(R.id.tv_show);
        pwTime = new TimePopupWindow(this, TimePopupWindow.Type.ALL);
        pwTime.setTime(new Date());
        //时间选择后回调
        pwTime.setOnTimeSelectListener(new TimePopupWindow.OnTimeSelectListener() {

            @Override
            public void onTimeSelect(Date date) {
                mTvShow.setText(getTime(date));
            }
        });
        mTvShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //弹出时间选择器
                pwTime.showAtLocation(mSwitch1, Gravity.BOTTOM, 0, 0, new Date());
            }
        });

    }

    /** 开关效果实现 */
    private void initSwitch() {
        mSwitch1 = (UISwitchButton) findViewById(R.id.switch1);
        mSwitch2 = (UISwitchButton) findViewById(R.id.switch2);
        mSwitch2.setChecked(false);
        mSwitch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
//                    Toast.makeText(mContext, "开启", Toast.LENGTH_SHORT).show();
                    //弹出时间选择器
                    pwTime.showAtLocation(mSwitch1, Gravity.BOTTOM, 0, 0, new Date());
                } else {
                    //结束时间选择器
                    pwTime.dismiss();
//                    Toast.makeText(mContext, "关闭", Toast.LENGTH_SHORT).show();
                }
            }
        });
        mSwitch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(mContext, "开启", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(mContext, "关闭", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    public static String getTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return format.format(date);
    }
}
