import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class SimulationManager implements Runnable{

    public int timeLimit;
    public int minProcessingTime;
    public int maxProcessingTime;
    public int numberOfQueues;
    public int numberOfClients;
    public int minArrivalTime;
    public int maxArrivalTime;
    private Scheduler scheduler;
    private List<Client> generatedClients = new ArrayList<>();
    private View v;

    public SimulationManager(int timeLimit, int minProcessingTime, int maxProcessingTime, int numberOfQueues,
                             int numberOfClients, int minArrivalTime, int maxArrivalTime, View v) {
        this.timeLimit = timeLimit;
        this.minProcessingTime = minProcessingTime;
        this.maxProcessingTime = maxProcessingTime;
        this.numberOfQueues = numberOfQueues;
        this.numberOfClients = numberOfClients;
        this.minArrivalTime = minArrivalTime;
        this.maxArrivalTime = maxArrivalTime;
        this.generatedClients = new ArrayList<>();
        this.v = v;
        this.scheduler = new Scheduler(numberOfQueues, numberOfClients );

        generateRandomClients();
    }

    public int getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(int timeLimit) {
        this.timeLimit = timeLimit;
    }

    public int getMinProcessingTime() {
        return minProcessingTime;
    }

    public void setMinProcessingTime(int minProcessingTime) {
        this.minProcessingTime = minProcessingTime;
    }

    public int getMaxProcessingTime() {
        return maxProcessingTime;
    }

    public void setMaxProcessingTime(int maxProcessingTime) {
        this.maxProcessingTime = maxProcessingTime;
    }

    public int getNumberOfQueues() {
        return numberOfQueues;
    }

    public void setNumberOfQueues(int numberOfQueues) {
        this.numberOfQueues = numberOfQueues;
    }

    public int getNumberOfClients() {
        return numberOfClients;
    }

    public void setNumberOfClients(int numberOfClients) {
        this.numberOfClients = numberOfClients;
    }

    public int getMinArrivalTime() {
        return minArrivalTime;
    }

    public void setMinArrivalTime(int minArrivalTime) {
        this.minArrivalTime = minArrivalTime;
    }

    public int getMaxArrivalTime() {
        return maxArrivalTime;
    }

    public void setMaxArrivalTime(int maxArrivalTime) {
        this.maxArrivalTime = maxArrivalTime;
    }

    public void generateRandomClients() {
        Random rand = new Random();
        for(int i=1; i<=numberOfClients; i++) {
            int randArrivalTime = rand.nextInt(maxArrivalTime-minArrivalTime) + minArrivalTime;
            int randServiceTime = rand.nextInt(maxProcessingTime - minProcessingTime) + minProcessingTime;
            Client newClient = new Client(i, randArrivalTime, randServiceTime);
            generatedClients.add(newClient);
        }
        Collections.sort(generatedClients);
    }

    public float averageServiceTime() {
        int sum = 0;
        for(Client c : generatedClients) {
            sum = sum + c.getServiceTime();
        }
        return sum / numberOfClients;
    }


    @Override
    public void run() {
        int peakHour=0;
        int maxSize=0;
        float averageServiceTime = averageServiceTime();

        String msg="";
        int currentTime = 0;
        while (currentTime < timeLimit) {

            for(int i = 0; i < numberOfQueues && !generatedClients.isEmpty(); i++) {
                if(currentTime >= generatedClients.get(0).getArrivalTime()) {
                    scheduler.dispatchClient(generatedClients.get(0));
                    generatedClients.remove(0);

                }
            }

            msg=msg+afisareSimulare(currentTime, scheduler);
            v.setResult(afisareSimulare(currentTime, scheduler));

            if(maxSize < scheduler.returnMaxSize())
            {
                maxSize = scheduler.returnMaxSize();
                peakHour = currentTime;
            }

            currentTime++;
            try {

                Thread.sleep(1000);
            } catch (InterruptedException e) {

                e.printStackTrace();
            }

        }
        float averageWaitingTime = scheduler.calculateAverageWaitingTime();
        msg = msg + afisareSimulare(currentTime, scheduler);
        msg = msg + "Peak hour: " + peakHour + "\n";
        msg = msg + "Average service time: " + averageServiceTime + "\n";
        msg = msg + "Average waiting time: " + averageWaitingTime + "\n";
        v.setResult(afisareSimulare(currentTime, scheduler) + "\nSimulation finished!\n");
        CreateFile f = new CreateFile();
        f.writeInFile(msg);
        scheduler.opresteCozi();
    }


    public String afisareSimulare(int timp, Scheduler scheduler) {
        String msg = "";
        msg = msg + "Timp curent: " + timp + "\n\n";
        msg = msg + "Clienti: ";
        int i = 1;
        for (Client c : generatedClients) {
            if(i % 7 == 0) {
                msg = msg + "\n";
            }
            msg = msg + "Client" + i + "(" + c.getID() + ","+ c.getArrivalTime() + "," + c. getServiceTime() + "); ";
            i++;
        }
        msg = msg+ "\n\n";

        int j = 1;
        for(Queue q : scheduler.getQueues()) {
            msg = msg + "Queue" + j + ": " + q.returnClientList() + "\n";
            j++;
        }
        msg = msg + "\n";
        return msg;
    }

    public static void main(String[] args) {

        View v = new View();

    }
}
