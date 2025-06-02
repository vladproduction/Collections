## Complexity of our Bag:

Adding an element to our Bag has a time complexity of O(1), because we simply insert the element into the i-th cell of our array. 
This is a very fast operation that takes constant time, O(1).
---
However, there is a case when the capacity of our Bag is full. In order to add one more element in such a situation, we need to perform a few additional steps:
1. Create a new array with a length greater than the current one — for example, we double the size.
2. Copy all elements from the old array into the new one.
3. Redirect the reference from the old array to the new one.
4. The old array will then be removed by the garbage collector in the future.
5. In this case, we perform O(n) steps — this is the critical scenario.
---
We want to be able to specify the capacity when creating a Bag. By default, we will assume the capacity is 10.