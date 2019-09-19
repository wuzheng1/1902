package com.example.liguixiao.day00_1;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.liguixiao.day00_1.RootBeans.BodyBean.ResultBean;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by liguixiao on 2019/9/18.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>{

    private Context context;
    private List<ResultBean> result;

    public RecyclerAdapter(Context context, List<ResultBean> result) {
        this.context = context;
        this.result = result;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context,R.layout.recyclerlayout,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Glide.with(context).load(result.get(position).getTeacherPic()).into(holder.img);
        holder.name.setText(result.get(position).getTeacherName());
        holder.title.setText(result.get(position).getTitle());

        try {
            Gson gson = new Gson();
            String s = gson.toJson(result);
            JSONObject jsonObject = new JSONObject(s);
            JSONObject body = jsonObject.getJSONObject("body");
            JSONArray res = body.getJSONArray("result");
            JSONArray teacherType = res.getJSONObject(position).getJSONArray("TeacherType");
            for (int i = 0; i < teacherType.length(); i++) {
                if (i==0){
                    JSONObject jsonObject1 = teacherType.getJSONObject(i);
                    String typename = jsonObject1.optString("typename");
                    holder.type_a.setText(typename);
                }else{
                    JSONObject jsonObject1 = teacherType.getJSONObject(i);
                    String typename = jsonObject1.optString("typename");
                    holder.type_b.setText(typename);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView img;
        TextView name;
        TextView title;
        TextView type_a;
        TextView type_b;

        public ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            name = itemView.findViewById(R.id.name);
            title = itemView.findViewById(R.id.title);
            type_a = itemView.findViewById(R.id.type_a);
            type_b = itemView.findViewById(R.id.type_b);
        }
    }
}
