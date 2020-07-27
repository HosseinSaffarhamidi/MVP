package mvptutorial.ir.adromsh.mvp.data;

import java.util.List;

import io.reactivex.Single;

public class NewsRepository implements NewsDataSource {
    private ServerDataSource serverDataSource=new ServerDataSource();
    private LocalDataSource localDataSource=new LocalDataSource();
    @Override
    public Single<List<News>> getNews() {
        return serverDataSource.getNews();
    }

    @Override
    public Single<List<Banner>> getBanners() {
        return serverDataSource.getBanners();
    }

    @Override
    public Single<List<Cat>> getCats() {
        return serverDataSource.getCats();
    }

    @Override
    public Single<List<News>> getLastNews() {
        return null;
    }


    @Override
    public Single<List<News>> getSearchedNews(CharSequence sequence) {
        return serverDataSource.getSearchedNews(sequence);
    }



}
