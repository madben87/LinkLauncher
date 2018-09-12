package ben.com.linklauncher.core;

public interface Presenter<V extends MVPView> {
    void attachPresenter(V v);
    void detachPresenter();
}
