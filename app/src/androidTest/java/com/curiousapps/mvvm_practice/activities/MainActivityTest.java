package com.curiousapps.mvvm_practice.activities;

import android.view.View;

import androidx.lifecycle.ViewModel;
import androidx.test.rule.ActivityTestRule;

import com.curiousapps.mvvm_practice.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityActivityTestRule =
            new ActivityTestRule<>(MainActivity.class);
    private MainActivity mainActivity = null;

    @Before
    public void setUp() throws Exception {
        mainActivity = mActivityActivityTestRule.getActivity();
    }

    @Test
    public void testIfTextViewIsNullInActivity(){
        View view = mainActivity.findViewById(R.id.title_text_view);

        assertNotNull(view);
    }
    @Test
    public void testIfToolbarIsNullInActivity(){
        View view = mainActivity.findViewById(R.id.tool_bar);

        assertNotNull(view);
    }
    @Test
    public void testIfRecyclerViewIsNull(){
        View view = mainActivity.findViewById(R.id.school_list_recycler_view);
       if (mainActivity.findViewById(R.id.school_list_recycler_view) == null){
            System.out.println("RecyclerView is NULL...");
        }else {
            System.out.println("RecyclerView Exists!");
        }
        assertNotNull(view);
    }
    @After
    public void tearDown() throws Exception {
        mainActivity = null;
    }
}