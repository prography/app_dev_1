package com.prograpy.app1.appdev1.lib.mainlist;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.prograpy.app1.appdev1.R;

import java.util.ArrayList;

public class CarouselAdapter extends FragmentPagerAdapter implements ViewPager.OnPageChangeListener {
    private float scale;
    private CarouselViewPager carousel;

    private Context context;
    private FragmentManager fragmentManager;
    private ArrayList<MainDramaData> entities = new ArrayList<>();
    private ScaledFrameLayout currentFrameLayout = null;
    private ScaledFrameLayout nextFrameLayout = null;

    public final static float BIG_SCALE = 1.0f;
    public final static float SMALL_SCALE = 0.90f;
    public final static float DIFF_SCALE = BIG_SCALE - SMALL_SCALE;

    public CarouselAdapter(Context context, CarouselViewPager carousel, FragmentManager fragmentManager, ArrayList<MainDramaData> mData) {
        super(fragmentManager);
        this.fragmentManager = fragmentManager;
        this.context = context;
        this.carousel = carousel;
        this.entities = mData;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            scale = BIG_SCALE;
        } else {
            scale = SMALL_SCALE;
        }
        Fragment fragment = CarouselFragment.newInstance(context, entities.get(position), position, scale);
        return fragment;
    }

    @Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }

    @Override
    public int getCount() {
        return entities.size();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (positionOffset >= 0f && positionOffset <= 1f) {
            currentFrameLayout = getRootView(position);
            currentFrameLayout.setScaleBoth(BIG_SCALE - DIFF_SCALE * positionOffset);

            if (position < entities.size() - 1) {
                nextFrameLayout = getRootView(position + 1);
                nextFrameLayout.setScaleBoth(SMALL_SCALE + DIFF_SCALE * positionOffset);
            }
        }
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public void onPageSelected(int position) {
        Log.w("onPageSelected", "position : " + position);
    }

    @Override
    public void onPageScrollStateChanged(int state) { }

    private ScaledFrameLayout getRootView(int position) {
        return (ScaledFrameLayout) fragmentManager.findFragmentByTag(this.getFragmentTag(position)).getView().findViewById(R.id.rootItem);
    }

    private String getFragmentTag(int position) {
        return "android:switcher:" + carousel.getId() + ":" + position;
    }
}