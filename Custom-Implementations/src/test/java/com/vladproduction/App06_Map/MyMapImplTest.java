package com.vladproduction.App06_Map;



import org.junit.Assert;
import org.junit.Test;
import com.vladproduction.map.MyMap;
import com.vladproduction.map.MyMapImpl;

import java.util.Arrays;

public class MyMapImplTest {

    @Test
    public void apiTest() {
        MyMap map = new MyMapImpl();

        //size, isEmpty
        Assert.assertTrue(map.isEmpty());
        Assert.assertTrue(map.size() == 0);

        //put
        map.put("zidane", 5);
        map.put("costa", 10);
        map.put("figo", 8);
        Assert.assertFalse(map.isEmpty());
        Assert.assertTrue(map.size() == 3);
        System.out.println(map.isEmpty());
        System.out.println(map.size());
        System.out.println(Arrays.toString(new MyMap[]{map}));
        System.out.println(map);
        Object oldValue = map.put("figo", 10);
        System.out.println(oldValue);
        System.out.println(map);
        Assert.assertTrue(map.size() == 3);
        System.out.println(map.size());

        //get
        Object zidane = map.get("zidane");
        System.out.println(zidane);
        Assert.assertEquals(zidane, 5);
        Assert.assertTrue(map.get("zidane").equals(zidane));
        Assert.assertEquals(map.get("figo"), 10);

        //remove
        map.put("player4", 4);
        Assert.assertTrue(map.size() == 4);
        System.out.println(map);
        Object removedValue = map.remove("player4");
        Assert.assertSame(removedValue, 4);
        Assert.assertFalse(map.size() == 4);
        System.out.println(map.size());
        System.out.println(map);

        //clear
        map.clear();
        Assert.assertTrue(map.isEmpty());
        Assert.assertTrue(map.size() == 0);

    }

    @Test
    public void hasCode_equals_Test() {
        MyMap map = new MyMapImpl();
        map.put("zidane", 5);
        map.put("costa", 10);
        map.put("figo", 8);

        MyMap map1 = new MyMapImpl();
        map1.put("zidane", 5);
        map1.put("costa", 10);
        map1.put("figo", 8);

        //hashCode == ; equals.
        Assert.assertFalse(map == map1);
        Assert.assertTrue(map.hashCode() == map1.hashCode());

        Assert.assertEquals(map,map1);
        Assert.assertTrue(map.equals(map1));
        Assert.assertTrue(map1.equals(map));



    }
}
