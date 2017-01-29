package pl.poblock.plan2fly.tripdetail;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pl.poblock.plan2fly.R;
import pl.poblock.plan2fly.data.model.Podroz;
import pl.poblock.plan2fly.data.repository.Repository;

public class EtapFragment extends Fragment {

    private static final String ARG = "TamLubPowrot";
    private int mTryb = 0;
    private int position;

    public EtapFragment() {}

    public static EtapFragment newInstance(int position, int tryb) {
        EtapFragment fragment = new EtapFragment();
        Bundle args = new Bundle();
        args.putInt("pos", position);
        args.putInt(ARG, tryb);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            position = getArguments().getInt("pos");
            mTryb = getArguments().getInt(ARG);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_etap_list, container, false);

        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            Podroz p = Repository.getInstance().getCachedResult(position);
            if(p!=null) {
                if(mTryb==0) {
                    recyclerView.setAdapter(new EtapAdapter(p.getPodrozTam().getLoty()));
                } else {
                    recyclerView.setAdapter(new EtapAdapter(p.getPodrozPowrot().getLoty()));
                }
            }
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
