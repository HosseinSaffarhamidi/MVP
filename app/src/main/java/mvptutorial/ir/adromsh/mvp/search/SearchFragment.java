package mvptutorial.ir.adromsh.mvp.search;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import mvptutorial.ir.adromsh.mvp.Home.NewsAdapter;
import mvptutorial.ir.adromsh.mvp.R;
import mvptutorial.ir.adromsh.mvp.base.BaseFragment;
import mvptutorial.ir.adromsh.mvp.data.News;
import mvptutorial.ir.adromsh.mvp.data.NewsRepository;

public class SearchFragment extends BaseFragment implements SearchContract.View {
    private SearchContract.Presenter presenter;
    private RecyclerView searchRecycler;
    NewsAdapter adapter;
    EditText edtSearch;
    TextView txtNotFound;
    List<News> searchedNewsList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new SearchPresenter(new NewsRepository());
        searchedNewsList=new ArrayList<>();
    }

    @Override
    public void setupViews() {
        txtNotFound=(TextView)rootView.findViewById(R.id.txt_fragmentSearch_notFound);
        edtSearch = (EditText) rootView.findViewById(R.id.edt_fragmentSearch_search);
        searchRecycler = (RecyclerView) rootView.findViewById(R.id.rv_fragmentSearch_searchedNews);
        searchRecycler.setLayoutManager(new LinearLayoutManager(getViewContext(), LinearLayoutManager.VERTICAL, false));

        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                presenter.getSearchedNews(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    @Override
    public int getLayout() {
        return R.layout.fragment_search;
    }

    @Override
    public Context getViewContext() {
        return getContext();
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


    @Override
    public void ShowSearchedNews(List<News> news) {
        searchedNewsList.clear();
        searchedNewsList=news;
        if(news.isEmpty()){
            txtNotFound.setVisibility(View.VISIBLE);
        }else{
            txtNotFound.setVisibility(View.GONE);
            adapter=new NewsAdapter(getViewContext(),searchedNewsList);
            searchRecycler.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }


    }

    @Override
    public void showError(String error) {

    }
}
