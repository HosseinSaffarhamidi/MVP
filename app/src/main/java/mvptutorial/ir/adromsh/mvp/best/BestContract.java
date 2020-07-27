package mvptutorial.ir.adromsh.mvp.best;

import android.content.Context;

import java.util.List;

import mvptutorial.ir.adromsh.mvp.base.BasePresenter;
import mvptutorial.ir.adromsh.mvp.base.BaseView;
import mvptutorial.ir.adromsh.mvp.data.News;

public interface BestContract {
    interface View extends BaseView {
        void showSavedNews(List<News> news);
    }

    interface Presenter extends BasePresenter<View> {
        void getSavedNews(Context context);
    }
}
