package Search;
import java.util.function.Function;

public class Action<T,R>{
    private Function<T,R> function;

    public Action(Function<T,R> function) {
        this.function = function;
    }

    public R act(T input){
        return function.apply(input);
    }

}
