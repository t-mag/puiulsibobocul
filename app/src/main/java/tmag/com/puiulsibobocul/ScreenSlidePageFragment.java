/*
 * Copyright 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package tmag.com.puiulsibobocul;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * A fragment representing a single step in a wizard. The fragment shows a dummy title indicating
 * the page number, along with some dummy text.
// *
// * <p>This class is used by the {link CardFlipActivity} and {@link
// * ScreenSlideActivity} samples.</p>
 */
public class ScreenSlidePageFragment extends Fragment {
    /**
     * The argument key for the page number this fragment represents.
     */
    public static final String ARG_PAGE = "page";

    /**
     * The fragment's page number, which is set to the argument value for {@link #ARG_PAGE}.
     */
    private int mPageNumber;

    /**
     * Factory method for this fragment class. Constructs a new fragment for the given page number.
     */
    public static ScreenSlidePageFragment create(int pageNumber) {
        ScreenSlidePageFragment fragment = new ScreenSlidePageFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, pageNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public ScreenSlidePageFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPageNumber = getArguments().getInt(ARG_PAGE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout containing a title and body text.
        ViewGroup rootView = (ViewGroup) inflater
                .inflate(R.layout.bookpage, container, false);

        //ImageView imgPage = (ImageView)rootView.findViewById(R.id.imgPage);
        RelativeLayout imgPage = (RelativeLayout)rootView.findViewById(R.id.imgPage);
        imgPage.setOnClickListener(new View.OnClickListener() {
            @Override
        public void onClick(View v) {
                //show the dialog fragment RecordDialogBoxFragment
                FullScreenDialogBoxFragment newFragment = FullScreenDialogBoxFragment.newInstance(1);
                newFragment.show(getFragmentManager(), "dialog");
            }
        });
        switch (mPageNumber) {
                case 0: {
                    imgPage.setBackgroundResource(R.drawable.page_1);
                    //imgPage.setImageResource(R.drawable.page_1);
                    break;
                }
                case 1: {
                    imgPage.setBackgroundResource(R.drawable.page_2);
                    //imgPage.setImageResource(R.drawable.page_2);
                    break;
                }
                case 2: {
                    imgPage.setBackgroundResource(R.drawable.page_3);
                    //imgPage.setImageResource(R.drawable.page_3);
                    break;
                }
                case 3: {
                    imgPage.setBackgroundResource(R.drawable.page_4);
                    //imgPage.setImageResource(R.drawable.page_4);
                    break;
                }
                case 4: {
                    imgPage.setBackgroundResource(R.drawable.page_5);
                    //imgPage.setImageResource(R.drawable.page_5);
                    break;
                }
                case 5: {
                    imgPage.setBackgroundResource(R.drawable.page_6);
                    //imgPage.setImageResource(R.drawable.page_6);
                    break;
                }
                case 6: {
                    imgPage.setBackgroundResource(R.drawable.page_7);
                    //imgPage.setImageResource(R.drawable.page_7);
                    break;
                }
                case 7: {
                    imgPage.setBackgroundResource(R.drawable.page_8);
                    //imgPage.setImageResource(R.drawable.page_8);
                    break;
                }
                case 8: {
                    imgPage.setBackgroundResource(R.drawable.page_9);
                    //imgPage.setImageResource(R.drawable.page_9);
                    break;
                }
                case 9: {
                    imgPage.setBackgroundResource(R.drawable.page_10);
                    //imgPage.setImageResource(R.drawable.page_10);
                    break;
                }
                case 10: {
                    imgPage.setBackgroundResource(R.drawable.page_11);
                    //imgPage.setImageResource(R.drawable.page_11);
                    break;
                }
                case 11: {
                  //  imgPage.setImageResource(R.drawable.page_12);
                    break;
                }

        }
//        // Set the title view to show the page number.
//        (ImageView)rootView.findViewById(android.R.id.imgPage).setBackground(
//
//        );
//        ((TextView) rootView.findViewById(android.R.id.text1)).setText(
//                getString(R.string.title_template_step, mPageNumber + 1));

        return rootView;
    }

    /**
     * Returns the page number represented by this fragment object.
     */
    public int getPageNumber() {
        return mPageNumber;
    }


    public void imgPage_onClick(View v)
    {
        //show the dialog fragment RecordDialogBoxFragment
        FullScreenDialogBoxFragment newFragment = FullScreenDialogBoxFragment.newInstance(1);
        newFragment.show(getFragmentManager(), "dialog");

    }
}
