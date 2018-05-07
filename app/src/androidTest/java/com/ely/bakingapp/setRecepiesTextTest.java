package com.ely.bakingapp;



import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.IdlingResource;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.ely.bakingapp.displayRecepies.RecepieActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.withChild;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anything;

/**
 * Created by Lior on 5/7/2018.
 */


@RunWith(AndroidJUnit4.class)
public class setRecepiesTextTest {

    private IdlingResource idlingResource;
    @Rule
    public ActivityTestRule<RecepieActivity> mActivityTestRule =
            new ActivityTestRule<>(RecepieActivity.class);



    @Before
    public void registerIdilingResource(){
      idlingResource = mActivityTestRule.getActivity().getIdlingResource();
        IdlingRegistry.getInstance().register(idlingResource);
    }


    @Test
    public void idlingResourceTest() {
        onView(withId(R.id.recepie_rv))
                .check(matches(hasDescendant(withText("Nutella Pie"))));
    }


    @After
    public void unregisterIdlingResource() {
        if (idlingResource != null) {
            IdlingRegistry.getInstance().unregister(idlingResource);
        }
    }


}
