package com.lnikkila.extendedtouchview.sample;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.lnikkila.extendedtouchview.ExtendedTouchView;

/**
 * Sample activity for the view that shows the outline of the touch target.
 */
public final class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new ButtonClickListener());

        final ExtendedTouchView touchView = (ExtendedTouchView) findViewById(R.id.touch_view);
        final View areaIndicator = findViewById(R.id.area_indicator);

        touchView.post(new Runnable() {
            @Override
            public void run() {
                ViewGroup.LayoutParams params = areaIndicator.getLayoutParams();

                params.width = touchView.getTouchWidth();
                params.height = touchView.getTouchHeight();

                areaIndicator.setLayoutParams(params);
            }
        });
    }

    private class ButtonClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            View rootView = findViewById(R.id.root);

            int red = (int) (Math.random() * 255);
            int green = (int) (Math.random() * 255);
            int blue = (int) (Math.random() * 255);

            int color = Color.rgb(red, green, blue);

            // Don't wanna show the same colour twice!
            if (rootView.getBackground() instanceof ColorDrawable) {
                ColorDrawable background = (ColorDrawable) rootView.getBackground();

                if (background.getColor() == color) {
                    onClick(view);
                    return;
                }
            }

            rootView.setBackgroundColor(color);
        }

    }

}
