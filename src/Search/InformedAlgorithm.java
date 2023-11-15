package Search;

public abstract class InformedAlgorithm<T,V,U> extends Algorithm<T,V,U>{

    protected Valuable<Float,T> f;

    protected InformedAlgorithm(int refreshRate) {
        super(refreshRate);
    }
}
