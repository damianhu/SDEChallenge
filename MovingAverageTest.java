import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class MovingAverageTest {
    private MovingAverageImpl movingAverageImpl;

//    @Before
//    public void init(){
//        movingAverageImpl = new MovingAverageImpl(3);
//
//    }

    @Test
    public void getElementTest(){
        double[] nums = new double[]{1d,2d,3d,4d,5d,6d,7d,1d,10d,11d};
        for(double num : nums){
            movingAverageImpl.add(num);
        }
        List<Double> list = (List<Double>) movingAverageImpl.getItems();

        for(int i=0; i<nums.length; i++){
            assertEquals(nums[i], list.get(i), 0);
        }
    }

    @Test
    public void getEverageTest1(){
        int n = 3;
        movingAverageImpl = new MovingAverageImpl(n);
        double[] nums = new double[]{1d,2d,3d,4d,5d,6d,7d,1d,10d,11d,23d,32d,78d,11d,23d};
        double sum = 0d;
        for(double num : nums){
            movingAverageImpl.add(num);
        }

        for(int i=nums.length-1; i>=nums.length-n; i--){
            sum+=nums[i];
        }
        double avg = sum/n;

        double res = movingAverageImpl.getAverage();
        assertEquals(avg, res, 0);

    }

    @Test
    public void getEverageTest2(){
        int n = 3;
        movingAverageImpl = new MovingAverageImpl(n);
        double[] nums = new double[]{1d,2d};
        double sum = 0d;
        for(double num : nums){
            movingAverageImpl.add(num);
        }

        for(int i=nums.length-1; i>=0 && i>=nums.length-n; i--){
            sum+=nums[i];
        }
        double avg = sum/nums.length;

        double res = movingAverageImpl.getAverage();
        assertEquals(avg, res, 0);

    }

    @Test
    public void getgetEverageTest3(){
        int n = 10;
        movingAverageImpl = new MovingAverageImpl(n);
        double[] nums = new double[]{1d,2d,3d,4d,5d,6d};
        double sum = 0d;
        for(double num : nums){
            movingAverageImpl.add(num);
        }

        for(int i=nums.length-1; i>=0 && i>=nums.length-n; i--){
            sum+=nums[i];
        }
        double avg = sum/nums.length;

        double res = movingAverageImpl.getAverage();
        assertEquals(avg, res, 0);
    }

    @Test
    public void getgetEverageTest4(){
        int n = 10;
        movingAverageImpl = new MovingAverageImpl(n);
        double[] nums = new double[]{0d,0d,0d,0d,0d,0d,0d,0d,0d,0d,0d};
        double sum = 0d;
        for(double num : nums){
            movingAverageImpl.add(num);
        }

        for(int i=nums.length-1; i>=0 && i>=nums.length-n; i--){
            sum+=nums[i];
        }
        double avg = sum/n;

        double res = movingAverageImpl.getAverage();
        assertEquals(avg, res, 0);
    }





}
