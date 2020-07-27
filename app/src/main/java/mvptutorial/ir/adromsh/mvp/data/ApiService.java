package mvptutorial.ir.adromsh.mvp.data;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("news.php")
    Single<List<News>> getNews();
    @GET("banner.php")
    Single<List<Banner>> getBanner();
    @GET("cat.php")
    Single<List<Cat>> getCats();
    @GET("search.php")
    Single<List<News>> getSearchedNews(@Query("search") String search);
}
