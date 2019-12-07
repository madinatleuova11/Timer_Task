package com.sample.countdowntimer;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class CountFragment extends Fragment
  {
  TextView textView;
  int count = 0;
  CountDownTimer timer;

  @Override
  public void onCreate (@Nullable Bundle savedInstanceState)
    {
    super.onCreate (savedInstanceState);
    }

  @Override
  public View onCreateView (LayoutInflater inflater, ViewGroup container,
                            Bundle savedInstanceState)
    {
    View view = inflater.inflate (R.layout.fragment_main, container, false);
    textView = view.findViewById (R.id.textView);
    textView.setText (String.valueOf (count));
    return view;
    }

  public void setCount (int count)
    {
    this.count = count;
    timer = new CountDownTimer (count * 1000, 1000)
      {
      public void onTick (long millisUntilFinished)
        {
        if (textView != null)
          textView.setText (String.valueOf (millisUntilFinished / 1000));
        }

      public void onFinish ()
        {
        if (textView != null)
          textView.setText ("Done");
        if (getActivity () != null)
          ((MainActivity) getActivity ()).progressBar.setProgress (0);
        }
      };
    }

  public void animate ()
    {
    if (isVisible () && textView != null)
      {
      timer.start ();
      }
    }

  public int getCount ()
    {
    return count;
    }

  public void stop ()
    {
    timer.cancel ();
    }

  public void setText (String text)
    {
    textView.setText (text);
    }
  }
