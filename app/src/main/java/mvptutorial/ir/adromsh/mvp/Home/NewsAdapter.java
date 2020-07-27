package mvptutorial.ir.adromsh.mvp.Home;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import mvptutorial.ir.adromsh.mvp.R;
import mvptutorial.ir.adromsh.mvp.data.MyDatabase;
import mvptutorial.ir.adromsh.mvp.data.News;
import mvptutorial.ir.adromsh.mvp.detail.DetailActivity;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    List<News> newsList;
    Context context;
    MyDatabase myDatabase;

    public NewsAdapter(Context context,List<News> news){
        this.newsList =news;
        this.context=context;
        myDatabase=new MyDatabase(context);
    }
    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.news_row,parent,false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final NewsViewHolder holder, int position) {

        final News news=newsList.get(position);
        holder.txtTitle.setText(news.getTitle());
        holder.txtDate.setText(news.getDate());
        Picasso.get().load(news.getImage()).into(holder.imgIcon);

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,DetailActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("news_image",news.getImage());
                intent.putExtra("news_title",news.getTitle());
                intent.putExtra("news_date",news.getDate());
                intent.putExtra("news_desc",news.getDescription());

                context.startActivity(intent);
            }
        });

        holder.imgSavedNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long id=myDatabase.addInfo(holder.txtTitle.getText().toString(),"desc",news.getImage(),holder.txtDate.getText().toString());
                Log.i("LOG", "onClick: "+holder.txtTitle.getText().toString());
                Log.i("LOG", "onClick: "+holder.txtDate.getText().toString());
                Toast.makeText(context, id+"", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder{

        CardView parent;
        ImageView imgIcon;
        TextView txtDate;
        TextView txtTitle;
        ImageView imgSavedNews;
        public NewsViewHolder(final View itemView) {
            super(itemView);
            txtTitle=(TextView)itemView.findViewById(R.id.txt_newRow_title);
            txtDate=(TextView)itemView.findViewById(R.id.txt_newsRow_date);
            imgIcon=(ImageView)itemView.findViewById(R.id.img_newsRow_icon);
            parent=(CardView) itemView.findViewById(R.id.card_newsRow_parent);
            imgSavedNews=(ImageView)itemView.findViewById(R.id.img_newsRow_bookmark);

        }
    }
}
