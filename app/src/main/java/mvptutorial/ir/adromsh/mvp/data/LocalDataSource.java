package mvptutorial.ir.adromsh.mvp.data;

import java.util.List;

import io.reactivex.Single;

public class LocalDataSource implements NewsDataSource {
    @Override
    public Single<List<News>> getNews() {
        return null;
    }

    @Override
    public Single<List<Banner>> getBanners() {
        return null;
    }

    @Override
    public Single<List<Cat>> getCats() {
        return null;
    }

    @Override
    public Single<List<News>> getLastNews() {
        return null;
    }


    @Override
    public Single<List<News>> getSearchedNews(CharSequence sequence) {
        return null;
    }

}
