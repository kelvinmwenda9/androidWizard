package com.kelvin.dbsql;
import android.content.Context;
import android.graphics.Movie;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private Context context;
    private List<Model> list;
    public Adapter(Context context, List<Model> list) {
        this.context = context;
        this.list = list;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.single_item, parent,
                false);
        return new ViewHolder(v);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Model model = list.get(position);
        holder.surname.setText(model.getSurname());
        holder.others.setText(String.valueOf(model.getOthers()));
        holder.email.setText(String.valueOf(model.getEmail()));
        holder.phone.setText(String.valueOf(model.getPhone()));
        holder.nhif.setText(String.valueOf(model.getNhif()));
        holder.pin.setText(String.valueOf(model.getPin()));
    }
    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView surname, others, email, phone, nhif, pin;
        public ViewHolder(View itemView) {
            super(itemView);

            //find textviews in single_items.xml
            surname = itemView.findViewById(R.id.singlesurname);
            others = itemView.findViewById(R.id.singleothers);
            email = itemView.findViewById(R.id.singleemail);
            phone = itemView.findViewById(R.id.singlephone);
            nhif = itemView.findViewById(R.id.singlenhif);
            pin = itemView.findViewById(R.id.singlepin);
        }
    }
}