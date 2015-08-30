package com.lnikkila.extendedtouchview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.TouchDelegate;
import android.view.View;
import android.view.ViewParent;
import android.widget.FrameLayout;

/**
 * A {@link FrameLayout} that manipulates the size of its touch target.
 * <p>
 * Accepts two attributes {@code app:touchWidth} and {@code app:touchHeight} that set the touch
 * target's dimensions.
 * <p>
 * If a dimension is not set, that touch target's dimension is left unmodified.
 */
public class ExtendedTouchView extends FrameLayout {

    /**
     * Marks dimension values that should be left to their initial state.
     */
    public static final int INITIAL_VALUE = -1;

    private static final String TAG = ExtendedTouchView.class.getSimpleName();

    private int touchWidth;
    private int touchHeight;

    private Rect hitRect = new Rect();

    public ExtendedTouchView(Context context) {
        super(context);
        init();
    }

    public ExtendedTouchView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
        readAttributes(attrs);
    }

    public ExtendedTouchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
        readAttributes(attrs);
    }

    private void init() {
        touchWidth = INITIAL_VALUE;
        touchHeight = INITIAL_VALUE;
    }

    private void readAttributes(AttributeSet attrs) {
        TypedArray styledAttrs = getContext().getTheme()
                .obtainStyledAttributes(attrs, R.styleable.ExtendedTouchView, 0, 0);

        touchWidth = styledAttrs
                .getDimensionPixelSize(R.styleable.ExtendedTouchView_touchWidth, touchWidth);

        touchHeight = styledAttrs
                .getDimensionPixelSize(R.styleable.ExtendedTouchView_touchHeight, touchHeight);

        styledAttrs.recycle();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        createTouchDelegate();
    }

    /**
     * @return The touch area width in pixels.
     */
    public int getTouchWidth() {
        return touchWidth;
    }

    /**
     * Sets the touch area width.
     * @param touchWidth Touch area width in pixels.
     */
    public void setTouchWidth(int touchWidth) {
        this.touchWidth = touchWidth;
        createTouchDelegate();
    }

    /**
     * @return The touch area height in pixels.
     */
    public int getTouchHeight() {
        return touchHeight;
    }

    /**
     * Sets the touch area height.
     * @param touchHeight Touch area height in pixels.
     */
    public void setTouchHeight(int touchHeight) {
        this.touchHeight = touchHeight;
        createTouchDelegate();
    }

    private void createTouchDelegate() {
        final ViewParent parent = getParent();

        if (!(parent instanceof View)) {
            Log.w(TAG, "Parent %s is not a view, doing nothing.");
            return;
        }

        final View parentView = (View) parent;

        // Post into the message queue so that the hit rect is established first.
        parentView.post(new Runnable() {
            @Override
            public void run() {
                getHitRect(hitRect);

                if (touchWidth != INITIAL_VALUE) {
                    float deltaLeftRight = ((float) touchWidth - hitRect.width()) / 2;

                    hitRect.right += deltaLeftRight;
                    hitRect.left -= deltaLeftRight;
                }

                if (touchHeight != INITIAL_VALUE) {
                    float deltaTopBottom = ((float) touchHeight - hitRect.height()) / 2;

                    hitRect.bottom += deltaTopBottom;
                    hitRect.top -= deltaTopBottom;
                }

                touchWidth = hitRect.width();
                touchHeight = hitRect.height();

                parentView.setTouchDelegate(new TouchDelegate(hitRect, ExtendedTouchView.this));
            }
        });
    }

}
