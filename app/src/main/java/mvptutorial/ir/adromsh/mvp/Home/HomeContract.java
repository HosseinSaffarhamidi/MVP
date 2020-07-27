package mvptutorial.ir.adromsh.mvp.Home;

import java.util.List;

import mvptutorial.ir.adromsh.mvp.base.BasePresenter;
import mvptutorial.ir.adromsh.mvp.base.BaseView;
import mvptutorial.ir.adromsh.mvp.data.Banner;
import mvptutorial.ir.adromsh.mvp.data.News;

public interface HomeContract {

    interface View extends BaseView {
        void showNews(List<News> newsList);
        void showBanners(List<Banner> banners);
        void showError(String error);

    }

    interface Presenter extends BasePresenter<View> {
        void getNewsList();
        void getBanners();
    }
}
