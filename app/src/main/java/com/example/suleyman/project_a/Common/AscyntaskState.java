package com.example.suleyman.project_a.Common;

import android.app.Dialog;
import android.content.Context;
import android.widget.EditText;

import com.example.suleyman.project_a.InSearch2;
import com.example.suleyman.project_a.R;

/**
 * Created by Art Servis on 3/23/2018.
 */

public class AscyntaskState {


  public  static  int pointState = 0;
  public  static Dialog dialog2 = null;
  public  static EditText dialogEdtText = (EditText) AscyntaskState.dialog2 .findViewById(R.id.dialogEdtText);
}
