import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ListAndArray {

    @Test
    public void TestDemo1(){
        int[] arr = new int[10];
        System.out.println(arr.length);

        int[] arr2 = new int[20];
        int[] arr3 = {1,3,4,5};
        System.out.println(arr3.length);


        List<Integer> list = new ArrayList<>();
        System.out.println(list.size());
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        System.out.println(list.size());
        list.remove(1);
        System.out.println(list.size());
    }

    @Test
    public void testAdd(){
        System.out.println("这是一个测试类！");
    }
}
