

public interface MovingAverage<T>{ //interface
        Iterable<T> getItems();
        void add(T val);
        boolean isEmpty();
        T getAverage();
}