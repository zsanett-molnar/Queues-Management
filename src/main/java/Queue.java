import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;

public class Queue implements Runnable {

    private int ID;
    private BlockingQueue<Client> clients;
    private AtomicInteger actualWaitingTime = new AtomicInteger(0);
    private AtomicInteger overallWaitingTime = new AtomicInteger(0);

    private boolean oprire = false;

    public Queue(int ID) {
        clients = new LinkedBlockingDeque<>();
        this.ID = ID;
    }

    public void addClient(Client c) {
        clients.add(c);
        overallWaitingTime.addAndGet(c.getServiceTime());
        actualWaitingTime.addAndGet(c.getServiceTime());
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public AtomicInteger getActualWaitingTime() {
        return actualWaitingTime;
    }

    public void setActualWaitingTime(AtomicInteger actualWaitingTime) {
        this.actualWaitingTime = actualWaitingTime;
    }

    public AtomicInteger getOverallWaitingTime() {
        return overallWaitingTime;
    }

    public void setOprire() {
        this.oprire = true;
    }

    public String returnClientList() {
        String str = "";
        int i = 1;
        if (clients.size() == 0) {
            str = "closed.";
        } else {
            for (Client c : clients) {
                str = str + "Client" + i + "(" + c.getID() + " , " + c.getArrivalTime() + " , " + c.getServiceTime() + "); ";
                i++;
            }

        }
        return str;
    }


    public int returnSize() {
        int nr = 0;
        for(Client c : clients) {
            nr++;
        }
        return nr;
    }

    @Override
    public void run() {
        while (oprire!=true) {
            try {

                if (!clients.isEmpty()) {

                    Client c = clients.peek();
                    int time = c.getServiceTime();
                    Thread.sleep(time * 1000);
                    int currentWaitPeriod = this.actualWaitingTime.get();
                    this.actualWaitingTime.set(currentWaitPeriod - time);
                    clients.take();

                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
