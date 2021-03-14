package cn.nuc.vacationTask.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Map;

import cn.nuc.vacationTask.R;
import cn.nuc.vacationTask.activity.DetailActivity;

public class StaggerdGridAdapter extends RecyclerView.Adapter<StaggerdGridAdapter.LinearViewHolder> {

    private Context mContext;
    private List<Map<String, Object>> mData;


    public StaggerdGridAdapter(Context context, List<Map<String, Object>> data) {
        this.mContext = context;
        this.mData = data;
    }

    @NonNull
    @Override
    public StaggerdGridAdapter.LinearViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LinearViewHolder(LayoutInflater.from(mContext).inflate(R.layout.layout_staggred_recyclerview_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull StaggerdGridAdapter.LinearViewHolder holder, final int position) {
        holder.titleText.setText(mData.get(position).get("title").toString());
        holder.contentText.setText(mData.get(position).get("content").toString());

        /*holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onClick(position);
            }
        });*/

        final Intent intent = new Intent(mContext, DetailActivity.class);
        intent.putExtra("title", (String) mData.get(position).get("title"));
        intent.putExtra("content", (String) mData.get(position).get("content"));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return 30;
    }

    class LinearViewHolder extends RecyclerView.ViewHolder {

        private TextView titleText;
        private TextView contentText;

        public LinearViewHolder(@NonNull View itemView) {
            super(itemView);
            titleText = itemView.findViewById(R.id.tv_title);
            contentText = itemView.findViewById(R.id.tv_content);
        }
    }

    public interface OnItemClickListener{
        void onClick(int pos);
    }
}
