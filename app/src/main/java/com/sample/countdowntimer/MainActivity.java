package com.sample.countdowntimer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ProgressBar;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
  {
  ProgressBar progressBar;
  Button button;
  TabLayout tabLayout;
  ViewPager viewPager;
  ObjectAnimator animation;
  ArrayList<CountFragment> fragments = new ArrayList<> ();

  @Override
  protected void onCreate (Bundle savedInstanceState)
    {
    super.onCreate (savedInstanceState);
    setContentView (R.layout.activity_main);

    progressBar = findViewById (R.id.vertical_progressbar);
    button = findViewById (R.id.start);
    tabLayout = findViewById (R.id.tabs);
    viewPager = findViewById (R.id.container);

    button.setOnClickListener (new View.OnClickListener ()
      {
      @Override
      public void onClick (View v)
        {
        if (button.getText ().equals ("Start."))
          {
          button.setText ("Stop.");
          fragments.get (viewPager.getCurrentItem ()).animate ();
          animation = ObjectAnimator.ofInt (progressBar, "progress", 100, 0);
          animation.setDuration (fragments.get (viewPager.getCurrentItem ()).getCount () * 1000);
          animation.setInterpolator (new DecelerateInterpolator ());
          animation.start ();
          }
        else
          {
          button.setText ("Start.");
          }
        }
      });

    CountFragment fragment1 = new CountFragment ();
    fragment1.setCount (10);

    CountFragment fragment2 = new CountFragment ();
    fragment2.setCount (20);

    CountFragment fragment3 = new CountFragment ();
    fragment3.setCount (30);

    CountFragment fragment4 = new CountFragment ();
    fragment4.setCount (40);

    CountFragment fragment5 = new CountFragment ();
    fragment5.setCount (50);

    CountFragment fragment6 = new CountFragment ();
    fragment6.setCount (60);

    fragments.add (fragment1);
    fragments.add (fragment2);
    fragments.add (fragment3);
    fragments.add (fragment4);
    fragments.add (fragment5);
    fragments.add (fragment6);

    PagerAdapter pagerAdapter = new PagerAdapter (getSupportFragmentManager ());

    viewPager.setAdapter (pagerAdapter);
    tabLayout.setupWithViewPager (viewPager);

    viewPager.addOnPageChangeListener (new ViewPager.OnPageChangeListener ()
      {
      public void onPageScrollStateChanged (int state)
        {
        }

      public void onPageScrolled (int position, float positionOffset, int positionOffsetPixels)
        {
        }

      public void onPageSelected (int position)
        {
        progressBar.setProgress (0);
        if (animation != null && animation.isRunning ())
          animation.cancel ();
        for (CountFragment fragment : fragments)
          {
          fragment.stop ();
          }
        }
      });

    }


  public class PagerAdapter extends FragmentPagerAdapter
    {

    public PagerAdapter (FragmentManager fm)
      {
      super (fm);
      }


    @Override
    public Fragment getItem (int position)
      {
      return fragments.get (position);
      }


    @Override
    public int getCount ()
      {
      return fragments.size ();
      }

    @Override
    public CharSequence getPageTitle (int position)
      {
      switch (position)
        {
        case 0:
          return "00:10";
        case 1:
          return "00:20";
        case 2:
          return "00:30";
        case 3:
          return "00:40";
        case 4:
          return "00:50";
        case 5:
          return "01:00";
        }
      return null;
      }
    }
  }
