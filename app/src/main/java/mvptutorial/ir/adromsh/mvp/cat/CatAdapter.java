package mvptutorial.ir.adromsh.mvp.cat;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import mvptutorial.ir.adromsh.mvp.R;
import mvptutorial.ir.adromsh.mvp.data.Cat;

public class CatAdapter extends RecyclerView.Adapter<CatAdapter.CatViewHolder> {
    List<Cat> catList;
    public CatAdapter(List<Cat> catList){
        this.catList=catList;
    }
    @NonNull
    @Override
    public CatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cat_row,parent,false);
        return new CatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CatViewHolder holder, int position) {

        Cat cat=catList.get(position);
        holder.txtTitle.setText(cat.getTitle());

    }

    @Override
    public int getItemCount() {
        return catList.size();
    }

    public class CatViewHolder extends RecyclerView.ViewHolder{

        TextView txtTitle;
        public CatViewHolder(View itemView) {
            super(itemView);
            txtTitle=(TextView)itemView.findViewById(R.id.txt_catRow_title);
        }
    }
}
