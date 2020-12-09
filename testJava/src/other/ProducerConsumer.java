package other;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProducerConsumer {

    public static void main(String[] args) {
        Person person = new Person();
        new Thread(new Consumer("消费者一", person)).start();
//        new Thread(new Consumer("消费者二", person)).start();
//        new Thread(new Consumer("消费者三", person)).start();

//        new Thread(new Producer("生产者一", person)).start();
//        new Thread(new Producer("生产者一", person)).start();
        new Thread(new Producer("生产者一", person)).start();

    }
}

class Producer implements Runnable {
    private Person person;
    private String producerName;

    public Producer(String producerName, Person person) {
        this.producerName = producerName;
        this.person = person;
    }

    @Override
    public void run() {
        while (true) {
            try {
                person.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

class Consumer implements Runnable {

    private Person person;
    private String consumerName;

    public Consumer(String consumerName, Person person) {
        this.consumerName = consumerName;
        this.person = person;
    }

    @Override
    public void run() {
        try {
            person.consume();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}

class Person {
    private int foodNum = 0;
    private Object synObj = new Object();

    private final int MAX_NUM = 5;

    public void produce() throws InterruptedException {
        synchronized (synObj) {
            while (foodNum == 5) {
                System.out.println("box is full，size = " + foodNum);
                synObj.wait();
            }
            foodNum++;
            System.out.println("produce success foodNum = " + foodNum);
            synObj.notifyAll();
        }

    }

    public void consume() throws InterruptedException {
        synchronized (synObj) {
            while (foodNum == 0) {
                System.out.println("box is empty,size = " + foodNum);
                synObj.wait();
            }
            foodNum--;
            System.out.println("consume success foodNum = " + foodNum);
            synObj.notifyAll();
        }

    }

}

//class Person {
//    private BlockingQueue<String> foodQueue = new ArrayBlockingQueue(5);
//    public void produce() throws InterruptedException {
//        foodQueue.put("food");
//        System.out.println("produce success foodNum = " + foodQueue.size());
//    }
//
//    public void consume() throws InterruptedException {
//        String temp = foodQueue.take();
//        System.out.println("consume success foodNum = " + foodQueue.size());
//    }
//}

//class PersonModel {
//    private static final int FULL_NUM = 5;  //容器总容量
//    private int foodNum;  //容器中物品的数量
//    private Object object = new Object();
//
//    public void produce() throws InterruptedException {
//        synchronized (object) {
//            while (foodNum == FULL_NUM) {
//                object.wait();
//            }
//            foodNum++;
//            object.notifyAll();
//        }
//    }
//
//    public void consume() throws InterruptedException {
//        synchronized (object) {
//            while (foodNum == 0) {
//                object.wait();
//            }
//            foodNum--;
//            object.notifyAll();
//        }
//    }
//}
