package Search;

public abstract class InformedAlgorithm<T> extends Algorithm<T>{

    protected Valuable<Float,T> f;

    protected InformedAlgorithm(int refreshRate) {
        super(refreshRate);
    }
}
