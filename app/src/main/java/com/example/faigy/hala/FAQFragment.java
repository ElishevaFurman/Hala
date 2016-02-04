package com.example.faigy.hala;

import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;



public class FAQFragment extends Fragment {
    // Declare Controls
    RecyclerView recyclerView;
    FAQExpandableAdapter mAdapter;
    FloatingActionButton fab;
    ArrayList<String> questionList, answerList;
    MainActivity mainActivity;
    int prev = -1;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_faq, container, false);
        // Initialize the views for this fragment
        initializeViews(rootView);
        return rootView;
    }

    public void initializeViews(final View rootView) {

        answerList = new ArrayList<>();
        questionList = new ArrayList<>();

        // and items to question/answer list
        questionList.add("What is a mammogram?");
        answerList.add("Mammogram is a photograph (imaging, picture) of a breast, taken using" +
                " low-dose X-RAYS. The mammogram is used for early detection of breast cancer, " +
                "typically through detection of characteristic masses and/or micro-calcifications.");
        questionList.add(" At what age should women begin to go for breast cancer screening?");
        answerList.add("Israel’s Ministry of Health recommends that women begin going for " +
                "mammograms at the age of 50. For women who are at higher risk due to breast " +
                "cancer history in the family, dense breast tissue, HRT, IVF and prior cancer " +
                "elsewhere, the recommendation is to begin screening at the age of 40, and to " +
                "receive a yearly mammogram thereafter.");
        questionList.add(" Are there any risks from the examination?");
        answerList.add("The theoretical radiation risk from screening mammography is extremely " +
                "small, compared with the established benefit from this life-saving procedure " +
                "and should not unduly distract women. A similar level of risk we get by: " +
                "400 miles travel by air, 60 miles travel by car, smoking ¾ of one cigarette, " +
                "90 seconds of mountain climbing, 20 minutes of being a man aged 60.");
        questionList.add("Why should I undergo a mammography examination?");
        answerList.add("Screening mammography offers the potential for significant benefits in " +
                "addition to mortality reduction, including early diagnosis, less aggressive " +
                "therapy and improved cosmetic results.");
        questionList.add("Why must the breast be squeezed?");
        answerList.add("-- In order to reduce radiation dose\n" +
                "-- In order to increase image quality and accuracy of mammography. " +
                "The bigger compression the smaller cancer can be identified.");
        questionList.add("question 6");
        answerList.add("answer 6");
        questionList.add("question 7");
        answerList.add("answer 7");
        questionList.add("question 8");
        answerList.add("answer 8");
        questionList.add("question 9");
        answerList.add("answer 9");

        fab = (FloatingActionButton) rootView.findViewById(R.id.askFab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().addToBackStack("ASK").replace(R.id.container,
                        mainActivity.askFragment).commit();
            }
        });



        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        mAdapter = new FAQExpandableAdapter(questionList, answerList);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new ClickListenerInterface() {
            @Override
            public void onClick(View view, int position) {
                // Check for an expanded view, collapse if you find one
                if (mAdapter.expandedPosition >= 0) {
                    // set pre to expandedPosition
                    prev = mAdapter.expandedPosition;
                    // notify adapter on item changed
                    mAdapter.notifyItemChanged(prev);
                }
                // if position is expanded
                if (position == mAdapter.expandedPosition) {
                    // Set the current position to "collapse"
                    mAdapter.expandedPosition = -1;
                    // notify adapter on item changed
                    mAdapter.notifyItemChanged(mAdapter.expandedPosition);
                } else {
                    // Set the current position to "expanded"
                    mAdapter.expandedPosition = position;
                    // notify adapter on item changed
                    mAdapter.notifyItemChanged(mAdapter.expandedPosition);
                }
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }


    public void setMainActivity(MainActivity mainActivity){
        this.mainActivity = mainActivity;
    }

}



