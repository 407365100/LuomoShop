package com.luomo.shopping.activity.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import com.luomo.shopping.R;
import com.luomo.shopping.widget.systembar.SystemBarTintManager;
import com.luomo.shopping.widget.systembar.SystemBarWrap;

public abstract class BaseFragmentActivity extends FragmentActivity {
    protected Context mContext;
    /**
     * tab页对应的fragment
     */
    protected Fragment[] mTabFragments = null;
    private FragmentTransaction mFragmentTransaction;
    /**
     * 自定义标题栏相关类
     */
    private SystemBarTintManager mTintManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        com.luomo.shopping.utils.ActivityManager.getActivityManager().addActivity(this);//添加当前activity到activity管理列表
        mTintManager = new SystemBarWrap(this).initSystemBar();
    }

    /**
     * 初始化控件
     */
    protected abstract void initViews();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 将fragment与底部tab关联起来
     * @param fragments
     */
    protected void relatedTabWithFragments(Fragment[] fragments){
        this.mTabFragments = fragments;
        mFragmentTransaction = getSupportFragmentManager().beginTransaction();
        for (Fragment fragment:mTabFragments){
            mFragmentTransaction.add(R.id.rl_fragment_container, fragment);
            mFragmentTransaction.hide(fragment);
        }
        mFragmentTransaction.show(mTabFragments[0]).commit();
    }

    /**
     * 切换frament
     * @param selectedIndex    选中的fragment
     * @param currentIndex     当前的fragment
     */
    protected boolean switchTabFragment(int selectedIndex,int currentIndex){
        if (null == mTabFragments || selectedIndex >= mTabFragments.length || currentIndex < 0 ||currentIndex>=mTabFragments.length){//没有fragment、选中的序号大于个数
            return false;
        }
        mFragmentTransaction = getSupportFragmentManager().beginTransaction();
        mFragmentTransaction.hide(mTabFragments[currentIndex]);
        if (!mTabFragments[selectedIndex].isAdded()) {
            mFragmentTransaction.add(R.id.rl_fragment_container, mTabFragments[selectedIndex]);
        }
        mFragmentTransaction.show(mTabFragments[selectedIndex]).commit();
        return true;
    }

    @Override
    protected void onDestroy() {
        mTintManager = null;
        super.onDestroy();
    }
}
