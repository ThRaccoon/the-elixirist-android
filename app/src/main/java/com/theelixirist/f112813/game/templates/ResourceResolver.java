package com.theelixirist.f112813.game.templates;

import android.util.Log;

import com.theelixirist.f112813.R;

public class ResourceResolver {
    public static int drawable(String name) {
        try {
            Integer resId = (Integer) R.drawable.class.getField(name).get(null);
            return resId != null ? resId : 0;
        } catch (NoSuchFieldException | IllegalAccessException e) {
            Log.e("ResourceResolver", "Drawable not found: " + name);
            return 0;
        }
    }

    public static int string(String name) {
        try {
            Integer resId = (Integer) R.string.class.getField(name).get(null);
            return resId != null ? resId : 0;
        } catch (NoSuchFieldException | IllegalAccessException e) {
            Log.e("ResourceResolver", "String not found: " + name);
            return 0;
        }
    }
}
