# Special collections

## Description
Implement special collections by given specifications

## [Pair String List](src\main\java\com\efimchick\ifmo\collections\PairStringList.java)
1. Implement a list of Strings that adds/removes elements by pairs.
2. Each time you add a string to the list, you should add it twice,
so the list would contain a pair of entries of that string.
3. Every time you remove a string, you should remove its pair as well.
4. The list should keep pairs together: if you add a string by index, ensure it will not break a pair of the elements.
Put pair of new element after it.

5.The following aspects should be covered in your implementation:
- adding
- adding by index
- removing of object
- removing by index
- getting by index
- setting by index
- adding of a collection
- adding of a collection by index
- iterator (removing via iterator is not required)

## [Sorted By Absolute Value Integer Set](src\main\java\com\efimchick\ifmo\collections\SortedByAbsoluteValueIntegerSet.java)
1. Implement a class of an Integer sorted set. The values in set should be sorted by their absolute values in ascending order.

2. The following aspects should be covered in your implementation:
- adding element
- removing element
- searching element
- adding a collection
- iterator (removing using iterator is not required)

## [Median Queue](src\main\java\com\efimchick\ifmo\collections\MedianQueue.java)
Implement the Integer queue that returns its median element.\
Median here is an element than is less than 50% of the items in the queue and more than 50% of the items in the queue. Median represents middle value of the collection.    
For instance, if you put `1, 2, 3, 4, 5` to the queue and then pull an element of it, queue will return `3`.\
If there is even amount of elements, there are two possible values to return. Return a lower one.\
More examples:
- `1, 10, 100` &rightarrow; `10`
- `100, 10, 1` &rightarrow; `10`
- `100, 1, 10` &rightarrow; `10`
- `1, 987, 2` &rightarrow; `2`
- `1, 987, 2, 3` &rightarrow; `2`
- `1, 987, 4, 2, 3` &rightarrow; `3`
- `1, 2, 3, 3, 3` &rightarrow; `3`

You need to implement following methods:
- offer - pushes an element to a queue
- poll - pulls an element out of the queue 
- peek - get the first element on the top of the queue (just gets, without pulling it out)
- iterator - iterates over elements of a queue (no need to keep order)
- size - just presents the number of current queue elements
