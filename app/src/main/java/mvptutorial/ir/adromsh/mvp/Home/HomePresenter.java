package mvptutorial.ir.adromsh.mvp.Home;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import mvptutorial.ir.adromsh.mvp.data.Banner;
import mvptutorial.ir.adromsh.mvp.data.News;
import mvptutorial.ir.adromsh.mvp.data.NewsDataSource;

public class HomePresenter implements HomeContract.Presenter {
    private HomeContract.View view;
    private NewsDataSource newsDataSource;
    private CompositeDisposable compositeDisposable=new CompositeDisposable();

    public HomePresenter(NewsDataSource newsDataSource){
        this.newsDataSource=newsDataSource;
    }
    @Override
    public void getNewsList() {

        newsDataSource.getNews().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<News>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(List<News> news) {

                        view.showNews(news);
                    }

                    @Override
                    public void onError(Throwable e) {

                        view.showError(e.toString());

                    }
                });
    }

    @Override
    public void getBanners() {
        newsDataSource.getBanners().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<Banner>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(List<Banner> banners) {

                        view.showBanners(banners);
                    }


                    @Override
                    public void onError(Throwable e) {

                        view.showError(e.toString());

                    }
                });
    }

    @Override
    public void attachView(HomeContract.View view) {
        this.view=view;
            getNewsList();
            getBanners();


    }

    @Override
    public void detachView() {
        this.view=null;
        if(compositeDisposable!=null && compositeDisposable.size()>0){
            compositeDisposable.clear();
        }

    }
}
