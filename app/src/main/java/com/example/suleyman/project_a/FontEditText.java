package com.example.suleyman.project_a;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

/**
 * Created by Art Servis on 11/22/2017.
 */


public class FontEditText extends android.support.v7.widget.AppCompatEditText {
    public FontEditText(Context context) {
        super(context);
        Typeface face=Typeface.createFromAsset(context.getAssets(), "fonts/Cera PRO.ttf");
        this.setTypeface(face, Typeface.BOLD);
    }

    public FontEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        Typeface face=Typeface.createFromAsset(context.getAssets(), "fonts/Cera PRO.ttf");
        this.setTypeface(face, Typeface.BOLD);
    }

    public FontEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Typeface face=Typeface.createFromAsset(context.getAssets(), "fonts/Cera PRO.ttf");
        this.setTypeface(face, Typeface.BOLD);
    }

}
