package pl.poblock.plan2fly.trips;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import pl.poblock.plan2fly.R;
import pl.poblock.plan2fly.data.model.Lot;
import pl.poblock.plan2fly.data.model.Podroz;

import java.util.List;

public class TripAdapter extends RecyclerView.Adapter<TripAdapter.ViewHolder> {

    private final List<Podroz> mValues;
    private PodrozItemListener mItemListener;

    public TripAdapter(List<Podroz> values, PodrozItemListener itemListener) {
        mValues = values;
        mItemListener = itemListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_trip, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mItem = mValues.get(position);
        holder.mCena.setText(String.valueOf(mValues.get(position).getCenaRazem())+" zł");
        Lot tam = mValues.get(position).getPodrozTam().getLoty().get(0);
        Lot powrot = mValues.get(position).getPodrozPowrot().getLoty().get(0);
        holder.mDataWylotu.setText(tam.getDataWylotu()+" "+tam.getGodzinaWylotu());
        holder.mDataPowrotu.setText(powrot.getDataWylotu()+" "+powrot.getGodzinaWylotu());

//        holder.mWylotPrzesiadek.setText(String.valueOf(mValues.get(position).getPodrozTam().getLoty().size()-1));
//        holder.mPowrotPrzesiadek.setText(String.valueOf(mValues.get(position).getPodrozPowrot().getLoty().size()-1));

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItemListener.OnPodrozClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mCena;
        public final TextView mDataWylotu;
        public final TextView mDataPowrotu;
//        public final TextView mWylotPrzesiadek;
//        public final TextView mPowrotPrzesiadek;

        public Podroz mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mCena = (TextView) view.findViewById(R.id.detailCenaRazem);
            mDataWylotu = (TextView) view.findViewById(R.id.dataWylotuValue);
            mDataPowrotu = (TextView) view.findViewById(R.id.dataPowrotuValue);
//            mWylotPrzesiadek = (TextView) view.findViewById(R.id.przesiadekWylotValue);
//            mPowrotPrzesiadek = (TextView) view.findViewById(R.id.przesiadekPowrotValue);
        }
    }
}
