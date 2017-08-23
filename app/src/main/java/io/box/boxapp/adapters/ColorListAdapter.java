package io.box.boxapp.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.box.boxapp.R;


public class ColorListAdapter extends RecyclerView.Adapter<ColorListAdapter.ViewHolder>{

    private Context context;
    private List<String> colors;

    public ColorListAdapter(Context context, List<String> colors){
        this.context = context;
        this.colors = colors;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.color_item, parent, false);
        return new ColorListAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.colorItem.setBackgroundColor(Color.parseColor(colors.get(position)));
    }

    @Override
    public int getItemCount() {
        return colors.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.color_item) LinearLayout colorItem;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
