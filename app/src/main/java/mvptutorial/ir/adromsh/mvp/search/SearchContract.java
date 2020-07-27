package mvptutorial.ir.adromsh.mvp.search;

import java.util.List;

import mvptutorial.ir.adromsh.mvp.base.BasePresenter;
import mvptutorial.ir.adromsh.mvp.base.BaseView;
import mvptutorial.ir.adromsh.mvp.data.News;

public interface SearchContract {
    interface View extends BaseView{
        void ShowSearchedNews(List<News> news);
        void showError(String error);

    }
    interface Presenter extends BasePresenter<View>{
        void getSearchedNews(CharSequence sequence);
    }
}
