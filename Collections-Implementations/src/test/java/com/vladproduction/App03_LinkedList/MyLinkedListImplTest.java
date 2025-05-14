package com.vladproduction.App03_LinkedList;


import org.junit.Assert;
import org.junit.Test;
import com.vladproduction.linkedList.MyLinkedListImpl;
import com.vladproduction.linkedList.MyList;

public class MyLinkedListImplTest {

    @Test
    public void apiTest(){
        MyList list = new MyLinkedListImpl();

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
    public void equalsHashCodeTest() {
        MyList list1 = new MyLinkedListImpl();
        list1.add("A");
        list1.add("B");
        list1.add("C");
        list1.add("D");
        list1.add("E");

        MyList list2 = new MyLinkedListImpl();
        list2.add("A");
        list2.add("B");
        list2.add("C");
        list2.add("D");
        list2.add("E");

            //as == hashCode
        int hash1 = list1.hashCode();
        int hash2 = list2.hashCode();
        Assert.assertTrue(hash1 == hash2);
        Assert.assertTrue(list1.hashCode()==list2.hashCode());


            //as equals lists
        Assert.assertTrue(list1.equals(list2));
        Assert.assertTrue(list2.equals(list1));
        Assert.assertEquals(list1,list2);//тоже самое что и предыдущий
        //Assert.assertSame(list1,list2); //here == //error
        //Assert.assertTrue(list1==list2);//same as: Assert.assertSame(list1,list2); //here ==


            //what if we change inside elements
        list2.add("EE");
        hash1 = list1.hashCode();
        hash2 = list2.hashCode();
        Assert.assertFalse(hash1 == hash2);
        Assert.assertFalse(list1.hashCode() == list2.hashCode()); //тоже но одна строка
        Assert.assertFalse(list1.equals(list2));
        //after that hashCode not same, hence not equals

    }
}
