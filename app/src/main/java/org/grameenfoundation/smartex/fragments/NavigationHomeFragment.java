package org.grameenfoundation.smartex.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.TabHost;

import org.grameenfoundation.smartex.R;
import org.grameenfoundation.smartex.adapters.HomeFragmentPagerAdapter;
import org.grameenfoundation.smartex.common.Utils;

import java.util.List;
import java.util.Vector;


public class NavigationHomeFragment extends Fragment implements TabHost.OnTabChangeListener, ViewPager.OnPageChangeListener  {
    public static final String TAG = "home";

    private View mFragmentContainerView;

    private TabHost tabHost;
    private ViewPager viewPager;
    private HomeFragmentPagerAdapter myViewPagerAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_navigation_home, container, false);

        mFragmentContainerView = view;

        tabHost = (TabHost) view.findViewById(android.R.id.tabhost);
        tabHost.setup();

        TabHost.TabSpec tabSpec = tabHost.newTabSpec("Home");
        tabSpec.setIndicator("Home", Utils.getDrawable(getActivity().getApplicationContext(), R.drawable.ic_home_black_24dp));
        tabSpec.setContent(new FakeContent());

        TabHost.TabSpec tabSpec1 = tabHost.newTabSpec("Meetings");
        tabSpec1.setIndicator("Meetings", Utils.getDrawable(getActivity().getApplicationContext(), R.drawable.ic_forum_black_24dp));
        tabSpec1.setContent(new FakeContent());

        TabHost.TabSpec tabSpec2 = tabHost.newTabSpec("Farmers");
        tabSpec2.setIndicator("Farmers", Utils.getDrawable(getActivity().getApplicationContext(), R.drawable.ic_person_black_24dp));
        tabSpec2.setContent(new FakeContent());

        TabHost.TabSpec tabSpec3 = tabHost.newTabSpec("Tools");
        tabSpec3.setIndicator("Tools", Utils.getDrawable(getActivity().getApplicationContext(), R.drawable.ic_phone_android_black_24dp));
        tabSpec3.setContent(new FakeContent());

        tabHost.addTab(tabSpec);
        tabHost.addTab(tabSpec1);
        tabHost.addTab(tabSpec2);
        tabHost.addTab(tabSpec3);
        tabHost.setOnTabChangedListener(this);

        List<android.support.v4.app.Fragment> fragments = new Vector<android.support.v4.app.Fragment>();

        fragments.add(new TabHomeFragment());
        fragments.add(new TabMeetingsFragment());
        fragments.add(new TabFarmersFragment());
        fragments.add(new TabToolsFragment());

        this.myViewPagerAdapter = new HomeFragmentPagerAdapter(((AppCompatActivity)getActivity()).getSupportFragmentManager(), fragments);
        this.viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        this.viewPager.setAdapter(this.myViewPagerAdapter);
        this.viewPager.addOnPageChangeListener(this);

        return view;
    }

    class FakeContent implements TabHost.TabContentFactory {
        public FakeContent() {}

        @Override
        public View createTabContent(String tag) {
            View v = new View(getActivity().getApplicationContext());
            v.setMinimumHeight(0);
            v.setMinimumWidth(0);
            return v;
        }
    }

    @Override
    public void onTabChanged(String tabId) {
        int pos = this.tabHost.getCurrentTab();
        this.viewPager.setCurrentItem(pos);

        HorizontalScrollView hScrollView = (HorizontalScrollView) mFragmentContainerView.findViewById(R.id.hScrollView);
        View tabView = tabHost.getCurrentTabView();
        int scrollPos = tabView.getLeft() - (hScrollView.getWidth() - tabView.getWidth()) / 2;
        hScrollView.smoothScrollTo(scrollPos, 0);
    }

    @Override
    public void onPageScrollStateChanged(int arg0) {
    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {
    }

    @Override
    public void onPageSelected(int position) {
        this.tabHost.setCurrentTab(position);
    }
}