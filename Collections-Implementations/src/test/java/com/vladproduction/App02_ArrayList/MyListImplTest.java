package com.vladproduction.App02_ArrayList;



import org.junit.Assert;
import org.junit.Test;
import com.vladproduction.arrayList.MyList;
import com.vladproduction.arrayList.MyListImpl;

public class MyListImplTest {

    @Test
    public void apiTest() {
        MyList list = new MyListImpl();

        //isEmpty, size, add----------
        Assert.assertTrue(list.isEmpty());
        Assert.assertTrue(list.size() == 0);
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");
        Assert.assertFalse(list.isEmpty());
        Assert.assertFalse(list.size() == 0);
        Assert.assertTrue(list.size() == 5);

        //get--------------------------
        Assert.assertTrue("a".equals(list.get(0)));
        Assert.assertTrue("c".equals(list.get(2)));
        Assert.assertTrue("e".equals(list.get(4)));


        //set--------------------------
        Object oldValue = list.set(0, "AAA");
        Assert.assertTrue("AAA".equals(list.get(0)));
        Assert.assertEquals("a", oldValue);
        Assert.assertTrue(list.size() == 5);


        //remove--------------------------
        list.remove(0);
        Assert.assertTrue(list.size() == 4);
        Assert.assertTrue("b".equals(list.get(0)));


        //clear--------------------------
        list.clear();
        Assert.assertTrue(list.size() == 0);
        Assert.assertTrue(list.isEmpty());


    }

    @Test
    public void hash_equalsTest() {
        MyList list = new MyListImpl();
        list.add("a");
        list.add("b");
        list.add("c");

        MyList list2 = new MyListImpl();
        list2.add("a");
        list2.add("b");
        list2.add("c");

                //as == hashCode
        int hashList = list.hashCode();
        int hashList2 = list2.hashCode();
        Assert.assertTrue(hashList==hashList2);
        Assert.assertTrue(list.hashCode()==list2.hashCode());



                //as equals lists
        Assert.assertTrue(list.equals(list2));
        Assert.assertTrue(list2.equals(list));
        Assert.assertEquals(list,list2);//тоже самое что и предыдущий


                //what if we change inside elements
        list2.set(2,"CCC");
        Assert.assertFalse(list.hashCode()==list2.hashCode());
        Assert.assertFalse(list.equals(list2));
        //after that hashCode not same, hence not equals

    }
}
