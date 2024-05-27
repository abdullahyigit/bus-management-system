import java.util.*;

/**
 * An implementation of a set using a skip list.
 *
 * @author Jim Glenn
 * @version 0.1 2015-10-05
 */

public class SkipList<E extends Comparable<? super E>> implements Iterable<E> 
{
    /*
     * Records how many times links are traversed at a given level.
    List<Integer> counts;
    */

    /**
     * The dummy head node in this skip list.
     */
    private Node<E> head;

    /**
     * The count of non-dummy nodes at each level.
     */
    private List<Integer> nodeCounts;

    public Iterator<E> iterator() 
    {
        return new ListIterator();
    }

    public class ListIterator implements Iterator<E> {

        private Node<E> N = head;

        public boolean hasNext() {
            return (N.next != null);
        }

        public E next() {
            if (!hasNext())
                throw new NoSuchElementException();
            else {
                N = N.next.get(0);
                return N.data;
            }
        }

        public void remove() {

        }

    }

    /**
     * Creates an empty set.
     */
    public SkipList()
	{
	    head = new Node<E>(null, 1);
	    nodeCounts = new ArrayList<Integer>();
	    nodeCounts.add(0);

	    /*
	    counts = new ArrayList<Integer>();
	    counts.add(0);
	    */
	}

    /**
     * Returns the size of this skip list.
     *
     * @return the size of this skip list
     */
    public int size()
    {
	// from an external point of view, size does not include the dummy head
	return nodeCounts.get(0);
    }

    

    /**
     * Finds the node with the given value in this skip list.  The returned
     * list is a list of nodes, one per level, such that the next references
     * at the nodes from the corresponding levels is either the node containing
     * the data to search for or the 1st node beyond it.  The value to search
     * for is in this skip list if and only if
     * <code>last.get(0).next.get(0).equals(key)</code>
     * 
     * @param key the non-null value to search for
     * @return a list of nodes before the key at each level
     */
    private List<Node<E>> find(E key)
    {
	List<Node<E>> last = new ArrayList<Node<E>>(head.next.size());
	for (int i = 0; i < head.next.size(); i++)
	    {
		last.add(head);
	    }

	Node<E> curr = head;
	int level = head.next.size() - 1;
	while (level >= 0)
	    {
		while (curr.next.get(level) != null && curr.next.get(level).data.compareTo(key) < 0)
		    {
			//counts.set(level, counts.get(level) + 1);
			curr = curr.next.get(level);
		    }
		last.set(level, curr);
		level--;
	    }

	return last;
    }

    /**
     * Determines if the given value is present in this skip list.
     *
     * @param value the non-null value to test
     * @return true if that value is in this skip list and false otherwise
     */
    public boolean contains(E value)
    {
	Node<E> last = find(value).get(0);

	return last.next.get(0) != null && last.next.get(0).data.compareTo(value) == 0;
    }

    /**
     * Adds the given value to this skip list if it is not already present.
     *
     * @param value the non-null value to add
     * @return true if the value was added, false otherwise
     */
    public boolean add(E value)
    {
	List<Node<E>> last = find(value);

	if (last.get(0).next.get(0) == null || last.get(0).next.get(0).data.compareTo(value) > 0)
	    {
		// not found -- add
		int newHeight = chooseHeight();
		
		// ensure that the head node and the last references are
		// at least newHeight high
		while (head.next.size() < newHeight)
		    {
			head.addLevel();
			last.add(head);
			nodeCounts.add(0);
			//counts.add(0);
		    }

		// make the new node
		Node<E> newNode = new Node<E>(value, newHeight);

		for (int h = 0; h < newHeight; h++)
		    {
			// count the new node
			nodeCounts.set(h, nodeCounts.get(h) + 1);
			// link up the new node
			Node<E> oldNext = last.get(h).next.get(h);
			last.get(h).next.set(h, newNode);
			newNode.next.set(h, oldNext);
		    }

		return true;
	    }
	else
	    {
		// already there
		return false;
	    }
    }

    public boolean remove(E value)
    {
	// find the previous value at each level
	List<Node<E>> last = find(value);
	Node<E> toDelete = last.get(0).next.get(0);
	
	if (toDelete != null && toDelete.data.compareTo(value) == 0)
	    {
		// found -- delete

		// link around deleted node
		int height = toDelete.next.size();
		for (int h = 0; h < height; h++)
		    {
			last.get(h).next.set(h, toDelete.next.get(h));
			nodeCounts.set(h, nodeCounts.get(h) - 1);
		    }

		// remove extra levels in dummy head node
		while (head.next.size() > 1 && nodeCounts.get(head.next.size() - 1) == 0)
		    {
			head.next.remove(head.next.size() - 1);
			nodeCounts.remove(nodeCounts.size() - 1);
			//counts.remove(counts.size() - 1);
		    }

		return true;
	    }
	else
	    {
		// not found
		return false;
	    }
    }

    /**
     * Validates the class invariants for this skip list.
     */
    private void validate()
    {
	// head's height should match amount of bookkeeping
	assert head.next.size() == nodeCounts.size() : "mismatched head node height and node counts";

	// validate each level
	for (int h = 0; h < head.next.size(); h++)
	    {
		validate(h);
	    }
    }

    /**
     * Validates the structure of the given level of this skip list.
     */
    private void validate(int h)
    {
	int count = 0;
	Node<E> curr = head;

	// nodes should be in order
	while (curr.next.get(h) != null && (curr == head || curr.data.compareTo(curr.next.get(h).data) < 0))
	    {
		curr = curr.next.get(h);
		count++;
	    }
	assert curr.next.get(h) == null : "out of order at level " + h + ": " + curr.data + " >= " + curr.next.get(h).data;

	// number of nodes should match bookkepping
	assert nodeCounts.get(h) == count : "bad node count at level " + h + ": counted " + count + "; recorded " + nodeCounts.get(h);
    }

    /*
    private void clearCounts()
    {
	for (int i = 0; i < counts.size(); i++)
	    {
		counts.set(i, 0);
	    }
    }
    */

    /**
     * Returns a printable representation of this skip list.
     *
     * @return a printable representation of this skip list
     */
    public String toString()
    {
	return asList().toString();
    }

    /**
     * Returns the items in this skip list as a sorted list.
     *
     * @return the items in this skip list as a sorted list
     */
    public List<E> asList()
    {
	List<E> list = new ArrayList<E>();
	Node<E> curr = head.next.get(0);
	while (curr != null)
	    {
		list.add(curr.data);
		curr = curr.next.get(0);
	    }

	return list;
    }

    /**
     * Randonly returns a height.  Height 1 is returned with probability 1/2;
     * 2 with probability 1/4, ...
     *
     * @return a randomly chosen height
     */
    private int chooseHeight()
    {
	int height = 1;
	while (Math.random() < 0.5)
	    {
		height++;
	    }
	return height;
    }
    
    /**
     * A node in a skip list.
     */    
    private static class Node<E>
    {
	private E data;
	private List<Node<E>> next;

	/**
	 * Creates a new node of the given level holding the given data.
	 *
	 * @param d the data in the new node
	 * @param h a nonnegative integer for the height of the new node
	 */
	public Node(E d, int h)
	    {
		data = d;
		next = new ArrayList<Node<E>>(h);
		for (int i = 0; i < h; i++)
		    {
			next.add(null);
		    }
	    }

	/**
	 * Adds a level to this node.
	 */
	public void addLevel()
	{
	    next.add(null);
	}

	public String toString()
	{
	    return "<" + data + "; " + next.size() + ">";
	}
    }
}