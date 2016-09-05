package com.luomo.shopping.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.luomo.shopping.Constant;
import com.luomo.shopping.LuomoApplication;
import com.luomo.shopping.R;
import com.luomo.shopping.activity.base.BaseFragmentActivity;
import com.luomo.shopping.fragment.ManagementFragment;
import com.luomo.shopping.fragment.ProfileFragment;
import com.luomo.shopping.fragment.SaleFragment;
import com.luomo.shopping.fragment.base.BaseFragment;
import com.luomo.shopping.utils.ToastUtils;
import com.luomo.shopping.utils.ViewUtils;
import com.luomo.shopping.view.IMainView;

public class MainActivity extends BaseFragmentActivity implements IMainView {
    /**
     * 底部图片
     */
    private ImageView[] mIvImages;
    /**
     * 底部文字
     */
    private TextView[] mTvTexts;
    private BaseFragment[] mFragments;
    /**
     * mCurrentIndex之前选中的tab<br/>
     * mSelectedIndex当前选中的tab<br/>
     */
    private int mSelectedIndex, mCurrentIndex = -1;
    private long mStartTime;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);
        mContext = this;
        initViews();
    }

    @Override
    protected void initViews() {
        //初始化底部按钮
        mIvImages = new ImageView[3];
        mIvImages[0] = ViewUtils.f(this, R.id.iv_sale);
        mIvImages[1] = ViewUtils.f(this, R.id.iv_management);
        mIvImages[2] = ViewUtils.f(this, R.id.iv_profile);
        mTvTexts = new TextView[3];
        mTvTexts[0] = ViewUtils.f(this, R.id.tv_sale);
        mTvTexts[1] = ViewUtils.f(this, R.id.tv_management);
        mTvTexts[2] = ViewUtils.f(this, R.id.tv_profile);
        mIvImages[0].setSelected(true);
        mTvTexts[0].setSelected(true);
        //初始化fragment
        mFragments = new BaseFragment[]{new SaleFragment(), new ManagementFragment(),new ProfileFragment()};
        relatedTabWithFragments(mFragments);
        initData();
    }

    @Override
    protected void initData() {
        //点击扫一扫
        ViewUtils.f(this,R.id.ll_two_code).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //二维码连扫多件
                Intent intent = new Intent(mContext, ScannerCaptureActivity.class);
                intent.putExtra(Constant.ARGUMENT_ONE,1);
                startActivityForResult(intent, Constant.FLAG_1000);
            }
        });
    }

    @Override
    protected void onDestroy() {
        ToastUtils.dismiss();
        super.onDestroy();
    }

    /**
     * 监听Back键按下事件
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            if (mSelectedIndex == 0 && mFragments[0].onFragmentKeyDown(keyCode, event)) {
                return true;
            } else if (System.currentTimeMillis() - mStartTime > Constant.EXIT_DURING_TIME) {
                mStartTime = System.currentTimeMillis();
                ToastUtils.show(mContext,getString(R.string.notice_exist));
                return false;
            } else {
                LuomoApplication.getInstance().exit();
                return true;
            }
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

    /**
     * 底部tab的点击事件
     *
     * @param view
     */
    public void onClickTab(View view) {
        switch (view.getId()) {
            case R.id.rl_sale://前台销售
                mSelectedIndex = 0;
                break;
            case R.id.rl_management://商品信息管理
                mSelectedIndex = 1;
                break;
            case R.id.rl_profile://个人信息管理
                mSelectedIndex = 2;
                break;
        }
        if (mSelectedIndex != mCurrentIndex&&switchTab(mSelectedIndex,mCurrentIndex)) {
            mCurrentIndex = mSelectedIndex;
        }
    }

    /**
     * @param selectedIndex
     * @param currentIndex
     * @return
     */
    public boolean switchTab(int selectedIndex, int currentIndex) {
        if (switchTabFragment(selectedIndex, currentIndex)) {//更换fragment
            //之前选中的变为不选中,当前点击的选中
            mIvImages[currentIndex].setSelected(false);
            mTvTexts[currentIndex].setSelected(false);
            mIvImages[selectedIndex].setSelected(true);
            mTvTexts[selectedIndex].setSelected(true);
            return true;
        }else{
            return false;
        }
    }
}