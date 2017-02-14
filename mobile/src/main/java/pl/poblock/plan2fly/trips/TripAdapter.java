package pl.poblock.plan2fly.trips;

import android.graphics.Color;
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
        holder.mCena.setText(String.valueOf(holder.mItem.getCenaRazem())+" zł");
        Lot tam = holder.mItem.getPodrozTam().getLoty().get(0);
        Lot powrot = holder.mItem.getPodrozPowrot().getLoty().get(0);
        holder.mDataWylotu.setText(tam.getDataWylotu());
        holder.mDataPowrotu.setText(powrot.getDataWylotu());

        holder.mWylotCena.setText(String.valueOf(holder.mItem.getPodrozTam().getSuma())+" zł");
        holder.mPowrotCena.setText(String.valueOf(holder.mItem.getPodrozPowrot().getSuma())+" zł");

        if(holder.mItem.isNajtanszyWylot()) {
            holder.mWylotCena.setTextColor(Color.parseColor("#77EE77"));
        }
        if(holder.mItem.isNajtanszyPowrot()) {
            holder.mPowrotCena.setTextColor(Color.parseColor("#77EE77"));
        }

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItemListener.OnPodrozClick(position);
            }
        });
    }

    public static int getColorFromGradient(int[] colors, float[] positions, float v ){

        if( colors.length == 0 || colors.length != positions.length ){
            throw new IllegalArgumentException();
        }

        if( colors.length == 1 ){
            return colors[0];
        }

        if( v <= positions[0]) {
            return colors[0];
        }

        if( v >= positions[positions.length-1]) {
            return colors[positions.length-1];
        }

        for( int i = 1; i < positions.length; ++i ){
            if( v <= positions[i] ){
                float t = (v - positions[i-1]) / (positions[i] - positions[i-1]);
                return lerpColor(colors[i-1], colors[i], t);
            }
        }

        //should never make it here
        throw new RuntimeException();
    }

    public static int lerpColor( int colorA, int colorB, float t){
        int alpha = (int)Math.floor(Color.alpha(colorA) * ( 1 - t ) + Color.alpha(colorB) * t);
        int red   = (int)Math.floor(Color.red(colorA)   * ( 1 - t ) + Color.red(colorB)   * t);
        int green = (int)Math.floor(Color.green(colorA) * ( 1 - t ) + Color.green(colorB) * t);
        int blue  = (int)Math.floor(Color.blue(colorA)  * ( 1 - t ) + Color.blue(colorB)  * t);

        return Color.argb(alpha, red, green, blue);
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
        public final TextView mWylotCena;
        public final TextView mPowrotCena;

        public Podroz mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mCena = (TextView) view.findViewById(R.id.detailCenaRazem);
            mDataWylotu = (TextView) view.findViewById(R.id.dataWylotuValue);
            mDataPowrotu = (TextView) view.findViewById(R.id.dataPowrotuValue);
            mWylotCena = (TextView) view.findViewById(R.id.wylotCena);
            mPowrotCena = (TextView) view.findViewById(R.id.powrotCena);
        }
    }
}
