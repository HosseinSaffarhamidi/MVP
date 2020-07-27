package mvptutorial.ir.adromsh.mvp.cat;

import java.util.List;

import mvptutorial.ir.adromsh.mvp.base.BasePresenter;
import mvptutorial.ir.adromsh.mvp.base.BaseView;
import mvptutorial.ir.adromsh.mvp.data.Cat;

public interface CatContract {
    interface View extends BaseView{

        void showCatList(List<Cat> catList);
        void showError(String error);
    }
    interface Presenter extends BasePresenter<View>{

        void getCatsList();
    }
}
