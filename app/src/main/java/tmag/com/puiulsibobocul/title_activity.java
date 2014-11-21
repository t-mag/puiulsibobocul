package tmag.com.puiulsibobocul;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.zip.Inflater;


/**
 * Created by Sam on 26/09/2014.
 */
public class title_activity extends FragmentActivity{

//    public static RecordDialogBoxFragment newInstance() {
//        RecordDialogBoxFragment f = new RecordDialogBoxFragment();
//        return f;
//    }

    /**
     * The number of pages (wizard steps) to show in this demo.
     */
    private static final int NUM_PAGES = 12;

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private ViewPager mPager;

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private PagerAdapter mPagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_pager);

        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getFragmentManager());
        mPager.setAdapter(mPagerAdapter);
        MyActivity.globalVars.setBook_Curr_Page(mPager.getCurrentItem());
        mPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                // When changing pages, reset the action bar actions since they are dependent
                // on which page is currently active. An alternative approach is to have each
                // fragment expose actions itself (rather than the activity exposing actions),
                // but for simplicity, the activity provides the actions in this sample.
                invalidateOptionsMenu();

            }
        });





    }


    /**
     * A simple pager adapter that represents 5 {@link ScreenSlidePageFragment} objects, in
     * sequence.
     */
    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return ScreenSlidePageFragment.create(position);
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }

    public void imgbtnHome_onClick(View v)
    {
        this.finish();

    }

    public void imgbtnRec_onClick(View v)
    {
        //show the dialog fragment RecordDialogBoxFragment
        RecordDialogBoxFragment newFragment = RecordDialogBoxFragment.newInstance(1);
        // USE BUNDLE to pass the number of current page to the RecordDialogBoxFragment
        Bundle passArgs = new Bundle();
        passArgs.putInt("currPage",mPager.getCurrentItem());
        newFragment.setArguments(passArgs);
        newFragment.show(getFragmentManager(), "dialog");
    }




//    class ScreenSlidePagerAdapter extends FragmentPagerAdapter {
//        MyAdapter(android.support.v4.app.FragmentManager fm) {
//
//            super(fm);
//        }
//
//
//        @Override
//        public android.support.v4.app.Fragment getItem(int i) {
//            android.support.v4.app.Fragment fragment = null;
//            switch (i) {
//                case 0: {
//                    fragment = new page1_activity();
//                    break;
//                }
//                case 1: {
//                    fragment=new page2_activity();
//                    break;
//                }
//                case 2: {
//                    fragment=new page3_activity();
//                    break;
//                }
//                case 3: {
//                    fragment=new page4_activity();
//                    break;
//                }
//                case 4: {
//                    fragment=new page5_activity();
//                    break;
//                }
//                case 5: {
//                    fragment=new page6_activity();
//                    break;
//                }
//                case 6: {
//                    fragment=new page7_activity();
//                    break;
//                }
//                case 7: {
//                    fragment=new page8_activity();
//                    break;
//                }
//                case 8: {
//                    fragment=new page9_activity();
//                    break;
//                }
//                case 9: {
//                    fragment=new page10_activity();
//                    break;
//                }
//                case 10: {
//                    fragment=new page11_activity();
//                    break;
//                }
//                case 11: {
//                    fragment=new page12_activity();
//                    break;
//                }
//            }
//            return fragment;
//        }
//
//        @Override
//        public int getCount() {
//            return NUM_PAGES;
//        }
//    }

}




