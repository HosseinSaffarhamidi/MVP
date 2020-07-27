package mvptutorial.ir.adromsh.mvp.cat;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import mvptutorial.ir.adromsh.mvp.data.Cat;
import mvptutorial.ir.adromsh.mvp.data.NewsDataSource;

public class CatPresenter implements CatContract.Presenter {
    private CatContract.View view;
    private NewsDataSource newsDataSource;
    private CompositeDisposable compositeDisposable=new CompositeDisposable();

    public CatPresenter(NewsDataSource newsDataSource) {
        this.newsDataSource = newsDataSource;
    }

    @Override
    public void attachView(CatContract.View view) {

        this.view = view;
        getCatsList();
    }

    @Override
    public void detachView() {

        this.view = null;
        if(compositeDisposable!=null && compositeDisposable.size()>0){
            compositeDisposable.clear();
        }
    }

    @Override
    public void getCatsList() {
        newsDataSource.getCats().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<Cat>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(List<Cat> catList) {

                        view.showCatList(catList);
                    }

                    @Override
                    public void onError(Throwable e) {

                        view.showError(e.toString());
                    }

                });
    }
}
