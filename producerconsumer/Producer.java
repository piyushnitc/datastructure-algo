package producerconsumer;

public class Producer implements Runnable {
    Data data;
    final String[] messages={"Hi!!", "How are you!!", "I love you!", "What's going on?!!", "That's really funny!!"};

    public Producer(Data data) {
        this.data = data;
    }
    @Override
    public void run() {
        int i=0;
        try {
            while (true) {
                Thread.sleep(100);
                data.publish(messages[i]);
                i = (i + 1) % messages.length;
                if(i ==0)  {
                    break;
                }
            }
        } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
