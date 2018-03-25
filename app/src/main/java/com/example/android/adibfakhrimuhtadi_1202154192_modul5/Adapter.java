package com.example.android.adibfakhrimuhtadi_1202154192_modul5;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private Context context;        //membuat variable apa saja yang dibutuhkan
    private List<methodData> mList;
    int warna;


    public Adapter(Context context, List<methodData> mList, int warna){
        this.context=context;
        this.mList=mList;
        this.warna=warna;
    }


    class ViewHolder extends RecyclerView.ViewHolder{
        public TextView judul, deskripsi, priority;
        public CardView cardV;
        public ViewHolder(View itemView){
            super(itemView);


            judul = itemView.findViewById(R.id.judul); //identifikasi variable
            deskripsi = itemView.findViewById(R.id.deskripsi);
            priority= itemView.findViewById(R.id.priority);
            cardV = itemView.findViewById(R.id.cardV);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.cardv_list_to_do, parent, false);
        ViewHolder hold = new ViewHolder(v);
        return hold;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        methodData data = mList.get(position);
        holder.judul.setText(data.getJudul());
        holder.deskripsi.setText(data.getDeskripsi());
        holder.priority.setText(data.getPriority());
        holder.cardV.setCardBackgroundColor(context.getResources().getColor(this.warna));
    }


    @Override
    public int getItemCount() {
        return mList.size(); //mengambil jumlah item yang ada
    }


    public methodData getData(int position){
        return mList.get(position);
    }


    public void hapusDat(int i){ //method untuk menghapus data dari databse
        mList.remove(i);
        notifyItemRemoved(i); //notifikasi ketika data dihapus
        notifyItemRangeChanged(i, mList.size());
    }


}
