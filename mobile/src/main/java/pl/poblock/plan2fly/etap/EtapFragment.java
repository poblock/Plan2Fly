package pl.poblock.plan2fly.etap;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

import pl.poblock.plan2fly.R;
import pl.poblock.plan2fly.common.MathUtils;
import pl.poblock.plan2fly.data.model.Lot;
import pl.poblock.plan2fly.data.model.Podroz;
import pl.poblock.plan2fly.data.repository.PodrozRepository;
import pl.poblock.plan2fly.data.repository.Ulubione;

public class EtapFragment extends Fragment implements EtapContract.View {

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
        CheckBox check = (CheckBox) view.findViewById(R.id.checkBox);
        TextView cenaEtap = (TextView) view.findViewById(R.id.etapCena);
        Context context = view.getContext();
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        Podroz p = PodrozRepository.getInstance(getContext()).getPodroz(position);
        if(p!=null) {
            List<Lot> loty = null;
            if(mTryb==0) {
                loty = p.getPodrozTam().getLoty();
            } else {
                loty = p.getPodrozPowrot().getLoty();
            }
            if(loty!=null) {
                recyclerView.setAdapter(new EtapAdapter(loty));
                final List<Lot> finalLoty = loty;
                double cena = 0.0;
                for(Lot l : loty) {
                    cena += Double.parseDouble(l.getCena());
                }
                check.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Ulubione.update(((CheckBox) view).isChecked(), mTryb, finalLoty);
                    }
                });

                cenaEtap.setText("Etap: "+String.valueOf(MathUtils.makeDouble(cena))+" zł");
            }
        }
        if(position == 999) {
            check.setVisibility(View.INVISIBLE);
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

    @Override
    public void setPresenter(EtapContract.Presenter presenter) {

    }
}
