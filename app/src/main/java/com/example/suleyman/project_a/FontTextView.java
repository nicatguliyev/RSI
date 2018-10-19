package com.example.suleyman.project_a;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

/**
 * Created by Art Servis on 11/22/2017.
 */

public class FontTextView extends android.support.v7.widget.AppCompatTextView {
    public FontTextView(Context context) {
        super(context);
        if (!isInEditMode()) {
            Typeface face = Typeface.createFromAsset(context.getAssets(), "fonts/Cera PRO.ttf");
            this.setTypeface(face, Typeface.BOLD);
        }
    }

    public FontTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!isInEditMode()) {
            Typeface face = Typeface.createFromAsset(context.getAssets(), "fonts/Cera PRO.ttf");
            this.setTypeface(face, Typeface.BOLD);
        }
    }

    public FontTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (!isInEditMode()) {
            Typeface face = Typeface.createFromAsset(context.getAssets(), "fonts/Cera PRO.ttf");
            this.setTypeface(face, Typeface.BOLD);
        }
    }
}