package upc.dsa.minimogithub;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.Constraints;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Recycler extends RecyclerView.Adapter<Recycler.ViewHolder> {

    List<Follower> data;
    Context context;

    public Recycler(Context context) {
        this.context = context;
        this.data = new ArrayList<>();
    }

    public void addFollowers(List<Follower> list){

        data.addAll(list);
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.follower_item,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Follower follower = data.get(i);

        viewHolder.nombreUserView.setText(follower.getLogin());

        Picasso.with(context).load(follower.getAvatarUrl()).into(viewHolder.fotoUserView);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView nombreUserView;
        private ConstraintLayout constraintLayout;
        private ImageView fotoUserView;


        public ViewHolder(View v){
            super(v);
            constraintLayout = v.findViewById(R.id.constraintLayout);
            nombreUserView = v.findViewById(R.id.nombreUser);
            fotoUserView = v.findViewById(R.id.imagenUser);



        }

    }
}
