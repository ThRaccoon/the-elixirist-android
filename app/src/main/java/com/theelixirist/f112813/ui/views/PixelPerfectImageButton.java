package com.theelixirist.f112813.ui.views;

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

            float scale = Math.min(
                    (float) getWidth() / bitmap.getWidth(),
                    (float) getHeight() / bitmap.getHeight()
            );

            float scaledWidth = bitmap.getWidth() * scale;
            float scaledHeight = bitmap.getHeight() * scale;

            float offsetX = (getWidth() - scaledWidth) / 2f;
            float offsetY = (getHeight() - scaledHeight) / 2f;

            float bitmapX = (event.getX() - offsetX) / scale;
            float bitmapY = (event.getY() - offsetY) / scale;

            int x = (int) bitmapX;
            int y = (int) bitmapY;

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
