package mvptutorial.ir.adromsh.mvp.data;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServerDataSource implements NewsDataSource {
    private ApiService apiService;

    public ServerDataSource(){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("http://192.168.1.117/MVP_News/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService=retrofit.create(ApiService.class);
    }
    @Override
    public Single<List<News>> getNews() {
        return apiService.getNews();
    }

    @Override
    public Single<List<Banner>> getBanners() {
        return apiService.getBanner();
    }

    @Override
    public Single<List<Cat>> getCats() {
        return apiService.getCats();
    }

    @Override
    public Single<List<News>> getLastNews() {
        return null;
    }



    @Override
    public Single<List<News>> getSearchedNews(CharSequence sequence) {
        return apiService.getSearchedNews(sequence.toString());
    }




}
