package hello.core;

interface IQueue<T> {
    IQueue<T> enqueue(T ele);
    T dequeue();
  	int size();
    T peekAt(int index);
    T [] toArray();
}