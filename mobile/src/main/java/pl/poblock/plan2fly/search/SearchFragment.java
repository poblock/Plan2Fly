package pl.poblock.plan2fly.search;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import pl.poblock.plan2fly.R;
import pl.poblock.plan2fly.common.ActivityUtils;
import pl.poblock.plan2fly.trips.TripsActivity;

public class SearchFragment extends Fragment implements SearchContract.View, TextView.OnEditorActionListener, View.OnFocusChangeListener {
    private AutoCompleteTextView mSkadView;
    private AutoCompleteTextView mDokadView;
    private SeekBar mMonthBar;
    private TextView mDateView;

    private View mProgressView;
    private View mLoginFormView;
    private SearchContract.Presenter presenter;

    public SearchFragment() {}
    public static SearchFragment newInstance() {
        SearchFragment fragment = new SearchFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        mSkadView = (AutoCompleteTextView) view.findViewById(R.id.from);
        mDokadView = (AutoCompleteTextView) view.findViewById(R.id.to);
        mDateView = (TextView) view.findViewById(R.id.monthText);
        mMonthBar = (SeekBar) view.findViewById(R.id.monthBar);
        mLoginFormView = view.findViewById(R.id.login_form);
        mProgressView = view.findViewById(R.id.login_progress);

        Button monthNext = (Button) view.findViewById(R.id.monthNext);
        Button monthPrev = (Button) view.findViewById(R.id.monthPrev);
        Button mSearchButton = (Button) view.findViewById(R.id.email_sign_in_button);

        mSkadView.setThreshold(3);
        mDokadView.setThreshold(3);
        mDokadView.setOnEditorActionListener(this);
        mSkadView.setOnFocusChangeListener(this);
        mDokadView.setOnFocusChangeListener(this);
        mMonthBar.setOnFocusChangeListener(this);
        mMonthBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean fromUser) {
                if(presenter!=null) {
                    presenter.changeMonthFromSeekBar(i, fromUser);
                }
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
        mSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performSearchUI();
            }
        });
        monthNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.changeMonth(1, Integer.MIN_VALUE);
            }
        });
        monthPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.changeMonth(-1, Integer.MIN_VALUE);
            }
        });
        return view;
    }

    private void performSearchUI() {
        if(presenter!=null) {
            presenter.performSearch();
        }
    }

    @Override
    public void showLoadingError() {
        Snackbar.make(getView(),getString(R.string.blad),Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showProgressOnUI(final boolean show) {
        ActivityUtils.showProgress(show, getContext(), mLoginFormView, mProgressView);
    }

    @Override
    public void setPresenter(SearchContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onResume() {
        super.onResume();
        if(presenter!=null) {
            presenter.start();
        }
    }

    @Override
    public void prepareQuery(int month, int year) {
        String skad = mSkadView.getText().toString();
        String dokad = mDokadView.getText().toString();
        if(skad!=null && dokad!=null) {
            if(!skad.equals("") && !dokad.equals("")) {
                Intent intent = new Intent(getContext(), TripsActivity.class);
                intent.putExtra("skad",skad);
                intent.putExtra("dokad",dokad);
                intent.putExtra("miesiac",month);
                intent.putExtra("rok",year);
                startActivity(intent);
            }
        }
    }

    @Override
    public void monthChanged(int monthValue, int stringID, String yearTxt, int seekBarProgress) {
        mDateView.setText(getString(stringID)+" "+yearTxt);
        mMonthBar.setProgress(seekBarProgress);
    }

    @Override
    public void setDane(List<String> collection) {
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(getActivity(),
                        android.R.layout.simple_dropdown_item_1line, collection);

        mSkadView.setAdapter(adapter);
        mDokadView.setAdapter(adapter);
    }

    @Override
    public void onFocusChange(View view, boolean b) {

    }

    @Override
    public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
        if (textView == mDokadView && actionId == EditorInfo.IME_ACTION_SEND) {
            performSearchUI();
            return true;
        }
        return false;
    }
}
