package mvptutorial.ir.adromsh.mvp.search;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import mvptutorial.ir.adromsh.mvp.data.News;
import mvptutorial.ir.adromsh.mvp.data.NewsDataSource;

public class SearchPresenter implements SearchContract.Presenter {
    private SearchContract.View view;
    private NewsDataSource newsDataSource;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private List<News> emptyList;

    public SearchPresenter(NewsDataSource newsDataSource) {
        this.newsDataSource = newsDataSource;
        emptyList = new ArrayList<>();
    }

    @Override
    public void attachView(SearchContract.View view) {

        this.view = view;
    }

    @Override
    public void detachView() {

        this.view = null;
        if (compositeDisposable != null && compositeDisposable.size() > 0) {
            compositeDisposable.clear();
        }
    }


    @Override
    public void getSearchedNews(CharSequence sequence) {

        if (sequence.length() > 0) {
            newsDataSource.getSearchedNews(sequence).subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new SingleObserver<List<News>>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            compositeDisposable.add(d);
                        }

                        @Override
                        public void onSuccess(List<News> news) {

                            view.ShowSearchedNews(news);
                        }

                        @Override
                        public void onError(Throwable e) {

                            view.showError(e.toString());

                        }
                    });
        } else {
            view.ShowSearchedNews(emptyList);
        }

    }
}
