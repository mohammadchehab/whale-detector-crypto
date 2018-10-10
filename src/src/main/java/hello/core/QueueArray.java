package hello.core;

import java.util.Queue;

public class QueueArray<T> implements IQueue<T> {

    private T[] arr;

    private int total, first, next;

    @SuppressWarnings("unchecked")
    public QueueArray(int size)
    {
        arr = (T[]) new Object[size];
    }

    @SuppressWarnings("unchecked")
    private void resize(int capacity)
    {
        T[] tmp = (T[]) new Object[capacity];

        for (int i = 0; i < total; i++)
            tmp[i] = arr[(first + i) % arr.length];

        arr = tmp;
        first = 0;
        next = total;
    }

    public QueueArray<T> enqueue(T ele)
    {
        if (arr.length == total) resize(arr.length * 2);
        arr[next++] = ele;
        if (next == arr.length) next = 0;
        total++;
        return this;
    }

    public T dequeue()
    {
        if (total == 0) throw new java.util.NoSuchElementException();
        T ele = arr[first];
        arr[first] = null;
        if (++first == arr.length) first = 0;
        if (--total > 0 && total == arr.length / 4) resize(arr.length / 2);
        return ele;
    }

	@Override
	public int size(){
		return this.total;
	}

	@Override
	public T peekAt(int index) {
		if (total == 0) throw new java.util.NoSuchElementException();
		
		return this.arr[index];
    }
    
    @Override
    public T []  toArray (){
      return this.arr;
    }
}