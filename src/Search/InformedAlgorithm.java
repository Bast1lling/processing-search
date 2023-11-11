package Search;

public abstract class InformedAlgorithm<T> extends Algorithm<T>{

    protected Valuable<Float,T> f;

    protected InformedAlgorithm(float interval_in_sec) {
        super(interval_in_sec);
    }
}
