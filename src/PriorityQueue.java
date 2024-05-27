import java.util.ArrayList;

public class PriorityQueue<E extends Comparable<E>> {
    
    private ArrayList<E> arr;
    private int itemCount = 0;

    public PriorityQueue()
    {
        arr = new ArrayList<>();
    }

    public E peek(){
    return arr.get(itemCount - 1);
    }

    public boolean isEmpty(){
    return itemCount == 0;
    }

    public int size(){
    return itemCount;
    }  

    public void insert(E data){
    int i = 0;

        // if queue is empty, insert the data 
        if(itemCount == 0)
        {
            arr.add(data);
            itemCount++;        
        }
        else{
            // start from the right end of the queue 
            arr.add(data);
            for(i = itemCount - 1; i >= 0; i-- )
            {
                if(data.compareTo(arr.get(i)) < 0)
                {
                    arr.set(i+1, arr.get(i));
                }else
                {
                    break;
                }            
            }  
                
            // insert the data 
            arr.set(i+1, data);
            itemCount++;
        }
    }

    public E removeData(){
        itemCount--;
        return arr.remove(itemCount); 
    }

}
