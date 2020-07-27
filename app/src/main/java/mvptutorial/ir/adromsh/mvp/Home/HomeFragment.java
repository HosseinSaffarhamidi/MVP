package mvptutorial.ir.adromsh.mvp.Home;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import mvptutorial.ir.adromsh.mvp.R;
import mvptutorial.ir.adromsh.mvp.base.BaseFragment;
import mvptutorial.ir.adromsh.mvp.data.Banner;
import mvptutorial.ir.adromsh.mvp.data.News;
import mvptutorial.ir.adromsh.mvp.data.NewsRepository;

public class HomeFragment extends BaseFragment implements HomeContract.View {
    private HomeContract.Presenter presenter;
    private ImageView imgBanner;
    private RecyclerView newsRecycler;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new HomePresenter(new NewsRepository());
    }

    @Override
    public void showNews(List<News> newsList) {
        setupViews();
        newsRecycler.setAdapter(new NewsAdapter(getViewContext(),newsList));

    }

    @Override
    public void showBanners(List<Banner> banners) {
        Banner banner=banners.get(0);
        Picasso.get().load(banner.getUrl()).into(imgBanner);
    }


    @Override
    public void showError(String error) {

        Toast.makeText(getViewContext(),error,Toast.LENGTH_SHORT).show();
    }

    @Override
    public Context getViewContext() {
        return getContext();
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

    @Override
    public void setupViews() {

         imgBanner=(ImageView)rootView.findViewById(R.id.img_fragmentHome_slider);
         newsRecycler=(RecyclerView)rootView.findViewById(R.id.rv_fragmentHome_lastNews);
         newsRecycler.setLayoutManager(new LinearLayoutManager(getViewContext(),LinearLayoutManager.VERTICAL,false));

    }

    @Override
    public int getLayout() {
        return R.layout.fragment_home;
    }
}
