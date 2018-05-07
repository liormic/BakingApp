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

import static org.hamcrest.CoreMatchers.anything;

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
      onData(anything()).inAdapterView(withId(R.id.recepie_rv)).atPosition(0).perform(click());
        onView(withId(R.id.recepie_name)).check(matches(withText("Cheesecake")));


        onView(withId(R.id.recycler_view))
                .check(matches(atPosition(0, hasDescendant(withText("First Element")))));
    }

    @After
    public void unregisterIdlingResource() {
        if (idlingResource != null) {
            IdlingRegistry.getInstance().unregister(idlingResource);
        }
    }


}
