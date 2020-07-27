package mvptutorial.ir.adromsh.mvp.cat;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.List;

import mvptutorial.ir.adromsh.mvp.R;
import mvptutorial.ir.adromsh.mvp.base.BaseFragment;
import mvptutorial.ir.adromsh.mvp.data.Cat;
import mvptutorial.ir.adromsh.mvp.data.NewsRepository;

public class CatFragment extends BaseFragment implements CatContract.View {
    CatContract.Presenter presenter;
    RecyclerView recyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new CatPresenter(new NewsRepository());
    }

    @Override
    public void setupViews() {

        recyclerView=(RecyclerView)rootView.findViewById(R.id.rv_fragmentCat_catList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getViewContext(),LinearLayoutManager.VERTICAL,false));

    }

    @Override
    public int getLayout() {
        return R.layout.fragment_cat;
    }

    @Override
    public Context getViewContext() {
        return getContext();
    }

    @Override
    public void showCatList(List<Cat> catList) {
        setupViews();
        recyclerView.setAdapter(new CatAdapter(catList));


    }

    @Override
    public void showError(String error) {

        Toast.makeText(getViewContext(), error + "", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.attachView(this);

    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.detachView();
    }
}
