import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class MovingAverageImpl implements MovingAverage<Double>{

    private int size;
    private Queue<Double> queue;
    private double sum;
    private List<Double> list;
    MovingAverageImpl(int size){
        this.size = size;
        queue = new LinkedList<>();
        list = new ArrayList<>();
        sum = 0;
    }

    @Override
    public Iterable<Double> getItems() {
        return list;
    }

    @Override
    public void add(Double val) {
        queue.offer(val);
        list.add(val);
        sum+=val;
        if(queue.size()>size){ // size is more than n, queue will pop a value, sum will reduce this value
            double num = queue.poll();
            sum-=num;
        }
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public Double getAverage() {
        return sum/queue.size();
    }


}