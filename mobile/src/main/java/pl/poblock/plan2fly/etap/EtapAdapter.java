package pl.poblock.plan2fly.etap;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import pl.poblock.plan2fly.R;
import pl.poblock.plan2fly.data.model.Lot;

import java.util.List;

public class EtapAdapter extends RecyclerView.Adapter<EtapAdapter.ViewHolder> {

    private final List<Lot> loty;

    public EtapAdapter(List<Lot> items) {
        loty = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_etap, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = loty.get(position);
        Lot lot = loty.get(position);
        holder.mSkad.setText(lot.getSkad());
        holder.mDokad.setText(lot.getDokad());
        holder.mContentView.setText(lot.getDataWylotu()+" "+lot.getGodzinaWylotu());
        holder.mPrzylot.setText(lot.getDataPrzylotu()+" "+lot.getGodzinaPrzylotu());
        holder.mCena.setText(lot.getCena()+" zł");

        if(lot.getLinia().equals("Wizzair")) {
            holder.mLiniaImg.setImageResource(R.drawable.wizzair);
            holder.mLinia.setImageResource(R.drawable.linewizzair);
        } else if(lot.getLinia().equals("Ryanair")) {
            holder.mLiniaImg.setImageResource(R.drawable.ryanair);
            holder.mLinia.setImageResource(R.drawable.lineryanair);
        }

//        if(position == 0) {
//            holder.mView.setBackgroundResource(R.color.colorAccent);
//        }
    }

    @Override
    public int getItemCount() {
        return loty.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mSkad;
        public final TextView mDokad;
        public final TextView mContentView;
        public final TextView mPrzylot;
        public final TextView mCena;
        public final ImageView mLiniaImg;
        public final ImageView mLinia;
        public Lot mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mSkad = (TextView) view.findViewById(R.id.detalSkad);
            mDokad = (TextView) view.findViewById(R.id.detalDokad);
            mContentView = (TextView) view.findViewById(R.id.detalCzasWylotu);
            mPrzylot = (TextView) view.findViewById(R.id.detalCzasPrzylotu);
            mCena = (TextView) view.findViewById(R.id.detalCena);
            mLiniaImg = (ImageView) view.findViewById(R.id.detalLiniaImg);
            mLinia = (ImageView) view.findViewById(R.id.linia);
        }
    }
}
