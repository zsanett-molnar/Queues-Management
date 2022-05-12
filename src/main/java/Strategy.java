import java.util.List;

public class Strategy {


    public void addClient(List<Queue> queues, Client c) {

        if(!queues.isEmpty()) {
            int minWait = queues.get(0).getActualWaitingTime().get();
            int k=0;
            for(int i=0; i< queues.size(); i++) {
                int queueWait = queues.get(i).getActualWaitingTime().get();
                if(queueWait < minWait) {
                    minWait = queueWait;
                    k=i;
                }
            }

            queues.get(k).addClient(c);
        }

    }
}
