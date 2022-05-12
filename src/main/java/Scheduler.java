import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Scheduler {

    private List<Queue> queues;
    private List<Thread> threads;
    private int maxNoQueues;
    private int maxClientsPerQueue;
    private Strategy strategy;

    public Scheduler(int maxNoQueues, int maxClientsPerQueue) {
        strategy = new Strategy();
        queues = new ArrayList<>();
        threads = new ArrayList<>();
        this.maxNoQueues=maxNoQueues;
        this.maxClientsPerQueue=maxClientsPerQueue;

        for(int i = 0; i < maxNoQueues; i++){
            Queue coada = new Queue(i);
            queues.add(coada);
            threads.add(new Thread(queues.get(i)));
            threads.get(i).start();
        }
    }

    public List<Queue> getQueues() {
        return queues;
    }

    public void setQueues(List<Queue> queues) {
        this.queues = queues;
    }

    public List<Thread> getThreads() {
        return threads;
    }

    public void setThreads(List<Thread> threads) {
        this.threads = threads;
    }

    public int getMaxNoQueues() {
        return maxNoQueues;
    }

    public void setMaxNoQueues(int maxNoQueues) {
        this.maxNoQueues = maxNoQueues;
    }

    public int getMaxClientsPerQueue() {
        return maxClientsPerQueue;
    }

    public void setMaxClientsPerQueue(int maxClientsPerQueue) {
        this.maxClientsPerQueue = maxClientsPerQueue;
    }

    public Strategy getStrategy() {
        return strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public void dispatchClient(Client c) {
        strategy.addClient(queues, c);
    }

    public void opresteCozi() {
        for (Queue coada:  queues) {
            coada.setOprire();
        }
    }

    public int returnMaxSize() {
        int max = 0;
        for(Queue coada : queues) {
            if(coada.returnSize() > max) {
                max = coada.returnSize();
            }
        }
        return max;
    }

    public float calculateAverageWaitingTime() {
        float sum = 0;
        for (Queue q : queues) {

            sum = sum + q.getOverallWaitingTime().intValue();
        }
        return sum / maxNoQueues;
    }


}
