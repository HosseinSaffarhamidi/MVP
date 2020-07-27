package mvptutorial.ir.adromsh.mvp.data;

import java.util.List;

import io.reactivex.Single;

public interface NewsDataSource {
    Single<List<News>> getNews();

    Single<List<Banner>> getBanners();

    Single<List<Cat>> getCats();

    Single<List<News>> getLastNews();

    //Single<List<News>> getSavedNews();

    Single<List<News>> getSearchedNews(CharSequence sequence);




}
