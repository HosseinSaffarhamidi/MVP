package mvptutorial.ir.adromsh.mvp.best;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import mvptutorial.ir.adromsh.mvp.data.MyDatabase;
import mvptutorial.ir.adromsh.mvp.data.News;
import mvptutorial.ir.adromsh.mvp.data.NewsDataSource;

public class BestPresenter implements BestContract.Presenter {
    private BestContract.View view;
    private NewsDataSource newsDataSource;
    private CompositeDisposable compositeDisposable=new CompositeDisposable();
    MyDatabase myDatabase;
    List<News>newsList;

    public BestPresenter(NewsDataSource newsDataSource){
        this.newsDataSource=newsDataSource;
        newsList=new ArrayList<>();
    }
    @Override
    public void attachView(BestContract.View view) {

        this.view=view;
        getSavedNews(view.getViewContext());
    }

    @Override
    public void detachView() {

    }


    @Override
    public void getSavedNews(Context context) {
        myDatabase = new MyDatabase(context);
        Cursor cursor=myDatabase.getInfos();
        for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()){
            int id=cursor.getInt(0);
            String title=cursor.getString(1);
            String desc=cursor.getString(2);
            String img=cursor.getString(3);
            String date=cursor.getString(4);
            News news=new News();
            news.setTitle(title);
            news.setDescription(desc);
            news.setDate(date);
            news.setImage(img);

            newsList.add(news);
            view.showSavedNews(newsList);

        }
    }

}
