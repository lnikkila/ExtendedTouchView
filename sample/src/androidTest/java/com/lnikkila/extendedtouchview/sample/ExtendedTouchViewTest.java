package com.lnikkila.extendedtouchview.sample;

import android.graphics.drawable.Drawable;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.action.CoordinatesProvider;
import android.support.test.espresso.action.GeneralClickAction;
import android.support.test.espresso.action.Press;
import android.support.test.espresso.action.Tap;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.*;
import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.assertion.ViewAssertions.*;
import static android.support.test.espresso.matcher.ViewMatchers.*;
import static org.hamcrest.Matchers.*;

@RunWith(AndroidJUnit4.class)
@LargeTest
public final class ExtendedTouchViewTest {

    @Rule
    public ActivityTestRule<MainActivity> rule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void buttonClickChangesBackground() throws Exception {
        actionChangesBackground(R.id.button, click());
    }

    @Test
    public void touchViewClickChangesBackground() throws Exception {
        actionChangesBackground(R.id.touch_view, click());
    }

    @Test
    public void buttonTouchTargetIsExtendedOutsideBounds() throws Exception {
        actionChangesBackground(R.id.button, clickXY(0, -20));
    }

    private void actionChangesBackground(int viewId, ViewAction action) throws Exception {
        Drawable background = findViewById(R.id.root).getBackground();

        onView(withId(viewId)).perform(action);
        onView(withId(R.id.root)).check(matches(not(hasBackground(background))));
    }

    private View findViewById(int id) {
        return rule.getActivity().findViewById(id);
    }

    private static Matcher<View> hasBackground(final Drawable background) {
        return new TypeSafeMatcher<View>() {
            @Override
            protected boolean matchesSafely(View item) {
                if (item.getBackground() == null) {
                    return (background == null);
                } else {
                    return item.getBackground().equals(background);
                }
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("has background " + background);
            }
        };
    }

    /**
     * Thanks to @haffax!
     * https://stackoverflow.com/a/22798043
     */
    private static ViewAction clickXY(final int x, final int y) {
        return new GeneralClickAction(Tap.SINGLE, new CoordinatesProvider() {
            @Override
            public float[] calculateCoordinates(View view) {
                int[] screenPos = new int[2];

                view.getLocationOnScreen(screenPos);

                float screenX = screenPos[0] + x;
                float screenY = screenPos[1] + y;

                return new float[] { screenX, screenY };
            }
        }, Press.FINGER);
    }

}
