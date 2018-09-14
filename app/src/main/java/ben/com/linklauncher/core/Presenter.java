package ben.com.linklauncher.core;

public interface Presenter<V extends MVPView> {
    void attachView(V v);
    void detachView();
}
