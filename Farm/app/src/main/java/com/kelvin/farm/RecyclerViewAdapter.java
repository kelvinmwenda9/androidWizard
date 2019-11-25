package com.kelvin.farm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.toolbox.ImageLoader;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Juned on 2/8/2017.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    Context context;

    List<DataAdapter> dataAdapters;

    ImageLoader imageLoader;

    public RecyclerViewAdapter(List<DataAdapter> getDataAdapter, Context context){

        super();
        this.dataAdapters = getDataAdapter;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder Viewholder, int position) {

        DataAdapter dataAdapterOBJ =  dataAdapters.get(position);


        Glide.with(context).load("https://modkenya.com/kelvin/"+dataAdapterOBJ.getImageUrl()).into(Viewholder.VollyImageView);



//        imageLoader = ImageAdapter.getInstance(context).getImageLoader();
//
//        imageLoader.get(dataAdapterOBJ.getImageUrl(),
//                ImageLoader.getImageListener(
//                        Viewholder.VollyImageView,//Server Image
//                        R.mipmap.ic_launcher,//Before loading server image the default showing image.
//                        android.R.drawable.ic_dialog_alert //Error image if requested image dose not found on server.
//                )
//        );

        //Viewholder.VollyImageView.setImageUrl("https://modkenya.com/stray/"+dataAdapterOBJ.getImageUrl(), imageLoader);

        Viewholder.ImageTitleTextView.setText(dataAdapterOBJ.getImageTitle());

        Viewholder.SeenTextView.setText(dataAdapterOBJ.getSeen());

        Viewholder.DescTheAnimalTextView.setText(dataAdapterOBJ.getDescTheAnimal());

    }

    @Override
    public int getItemCount() {

        return dataAdapters.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public TextView ImageTitleTextView;
        public ImageView VollyImageView ;
        public TextView SeenTextView;
        public TextView DescTheAnimalTextView;

        public ViewHolder(View itemView) {

            super(itemView);

            ImageTitleTextView = (TextView) itemView.findViewById(R.id.ImageNameTextView) ;
            VollyImageView = (ImageView) itemView.findViewById(R.id.VolleyImageView) ;
            SeenTextView = (TextView) itemView.findViewById(R.id.SeenTextView) ;
            DescTheAnimalTextView = (TextView) itemView.findViewById(R.id.DescTheAnimalTextView) ;

        }
    }
}