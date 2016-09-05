package com.luomo.shopping.utils;

import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.view.View;
import android.view.animation.AlphaAnimation;

/**
 * Created by renpan on 2015/11/18.
 */
public class AnimationUtils {
    public final static int DURING_1500 = 1500;

    /**
     * 渐隐、渐显效果
     *
     * @param view
     * @param fromAlpha
     * @param toAlpha
     */
    public static void startAlphaAnimation(View view, float fromAlpha, float toAlpha, int during) {
        AlphaAnimation animation = new AlphaAnimation(fromAlpha, toAlpha);
        animation.setDuration(during);
        view.startAnimation(animation);
    }

    public static void startAlphaAnimation(View view, float fromAlpha, float toAlpha) {
        startAlphaAnimation(view, fromAlpha, toAlpha, DURING_1500);
    }

    /**
     * 图片抖动效果
     *
     * @param view
     */
    public static ObjectAnimator startWobbleAnimator(View view) {
        // 设置在动画开始时,旋转角度为0度
        Keyframe kf0 = Keyframe.ofFloat(0f, 0f);
        // 设置在动画执行50%时,旋转角度为360度
        Keyframe kf1 = Keyframe.ofFloat(.25f, 2f);
        // 设置在动画结束时,旋转角度为0度
        Keyframe kf2 = Keyframe.ofFloat(0.5f, 0f);
        Keyframe kf3 = Keyframe.ofFloat(0.75f, -2f);
        Keyframe kf4 = Keyframe.ofFloat(1f, 0f);
        // 使用PropertyValuesHolder进行属性名称和值集合的封装
        PropertyValuesHolder pvhRotation = PropertyValuesHolder.ofKeyframe("rotation", kf0, kf1, kf2, kf3, kf4);
        // 通过ObjectAnimator进行执行
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(view, pvhRotation).setDuration(500);
        animator.setRepeatCount(-1);
        animator.start();
        return animator;
    }

    /**
     * 取消动画
     * @param animator
     */
    public static void cancelAnimator(ObjectAnimator animator){
        if(null != animator && animator.isRunning()){
            animator.cancel();
        }
    }
}

