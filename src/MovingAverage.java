

public interface MovingAverage<T>{
        Iterable<T> getItems();
        void add(T val);
        boolean isEmpty();
        T getAverage();
}