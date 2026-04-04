package com.theelixirist.f112813.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class PixelPerfectImageButton extends androidx.appcompat.widget.AppCompatImageButton {
    public PixelPerfectImageButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            Drawable drawable = getDrawable();
            if (!(drawable instanceof BitmapDrawable)) {
                return super.onTouchEvent(event);
            }

            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();

            int x = (int) (event.getX() * bitmap.getWidth() / getWidth());
            int y = (int) (event.getY() * bitmap.getHeight() / getHeight());

            if (x >= 0 && x < bitmap.getWidth() && y >= 0 && y < bitmap.getHeight()) {
                int pixel = bitmap.getPixel(x, y);

                if (Color.alpha(pixel) > 0) {
                    performClick();
                    return true;
                }
            }
            return false;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean performClick() {
        return super.performClick();
    }
}
