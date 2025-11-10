package producerconsumer;

import java.util.LinkedList;
import java.util.Queue;

public class Data {

    Queue<String> queue;
    int capacity;

    Data(int capacity) {
        this.queue = new LinkedList<>();
        this.capacity = capacity;
    }

    public synchronized void publish(String msg) {
        String name = Thread.currentThread().getName();
        try {
            if (queue.size() == capacity) {
                System.out.println("Queue is Full." + name + " waiting for messages to be consumed");
                //return; // you can not retun. you need to wait
                wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        queue.add(msg);
        System.out.println("Message published "+msg);
        System.out.println("Queue "+queue);
        System.out.println();
        notifyAll();
    }

    public synchronized void consume() {
        String name = Thread.currentThread().getName();
        try {
            if(queue.size() == 0) {
                System.out.println("Queue is Empty."+ name+" waiting for messages to be published");
                //return;
                wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String msg = queue.poll();
        System.out.println("Message consumed "+msg);
        System.out.println("Queue "+queue);
        System.out.println();
        notifyAll();
    }
}
