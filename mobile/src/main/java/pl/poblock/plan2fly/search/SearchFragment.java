package pl.poblock.plan2fly.search;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

import pl.poblock.plan2fly.R;
import pl.poblock.plan2fly.data.model.Miasto;
import pl.poblock.plan2fly.data.repository.Query;
import pl.poblock.plan2fly.data.repository.Repository;
import pl.poblock.plan2fly.trips.TripsActivity;

public class SearchFragment extends Fragment implements SearchContract.View {
    private AutoCompleteTextView mSkadView;
    private AutoCompleteTextView mDokadView;
    private EditText mMonthView;
    private EditText mYearView;
    private SeekBar mMonthBar;

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

        mDokadView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    if(presenter!=null) {
                        presenter.performSearch();
                    }
                    return true;
                }
                return false;
            }
        });

        mMonthView = (EditText) view.findViewById(R.id.month);
        mMonthBar = (SeekBar) view.findViewById(R.id.monthBar);
        mMonthBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if(presenter!=null) {
                    presenter.changeMonth(i);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
        mYearView = (EditText) view.findViewById(R.id.year);
        mLoginFormView = view.findViewById(R.id.login_form);
        mProgressView = view.findViewById(R.id.login_progress);
        mSkadView.setThreshold(3);
        mDokadView.setThreshold(3);
        Button mEmailSignInButton = (Button) view.findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(presenter!=null) {
                    presenter.performSearch();
                }
            }
        });

        return view;
    }

    @Override
    public void showProgressOnUI(final boolean show) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
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
    public Query prepareQuery() {
        String skad = mSkadView.getText().toString();
        String dokad = mDokadView.getText().toString();
        int miesiac = Integer.parseInt(mMonthView.getText().toString());
        int rok = Integer.parseInt(mYearView.getText().toString());
        return new Query(skad, dokad, miesiac, rok);
    }

    @Override
    public void showTripList(String skad, String dokad) {
        Intent intent = new Intent(getContext(), TripsActivity.class);
        intent.putExtra("skad",skad);
        intent.putExtra("dokad",dokad);
        startActivity(intent);
    }

    @Override
    public void monthChanged(int monthValue) {
        mMonthView.setText(String.valueOf(monthValue));
    }

    @Override
    public void setMiastoAdapter(List<String> collection) {
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(getActivity(),
                        android.R.layout.simple_dropdown_item_1line, collection);

        mSkadView.setAdapter(adapter);
        mDokadView.setAdapter(adapter);
    }
}
