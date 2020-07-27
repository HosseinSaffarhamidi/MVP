package mvptutorial.ir.adromsh.mvp.base;

public interface BasePresenter<T extends BaseView> {
    void attachView(T view);
    void detachView();
}
