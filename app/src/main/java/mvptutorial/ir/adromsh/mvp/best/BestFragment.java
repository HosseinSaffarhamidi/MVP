package mvptutorial.ir.adromsh.mvp.best;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import mvptutorial.ir.adromsh.mvp.Home.NewsAdapter;
import mvptutorial.ir.adromsh.mvp.R;
import mvptutorial.ir.adromsh.mvp.base.BaseFragment;
import mvptutorial.ir.adromsh.mvp.data.News;
import mvptutorial.ir.adromsh.mvp.data.NewsRepository;

public class BestFragment extends BaseFragment implements BestContract.View {
    private BestContract.Presenter presenter;
    RecyclerView recyclerView;
    NewsAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new BestPresenter(new NewsRepository());


    }
    @Override
    public void setupViews() {

        recyclerView=(RecyclerView)rootView.findViewById(R.id.rv_fragmentBest_savedNews);
        recyclerView.setLayoutManager(new LinearLayoutManager(getViewContext(),LinearLayoutManager.VERTICAL,false));
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_best;
    }

    @Override
    public Context getViewContext() {
        return getContext();
    }

    @Override
    public void showSavedNews(List<News> news) {

        adapter=new NewsAdapter(getViewContext(),news);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.attachView(this);
        setupViews();

    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.detachView();
    }
}
