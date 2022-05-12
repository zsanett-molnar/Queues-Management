import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener {
    private View v;


    public Controller (View v) {
        this.v = v;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source == v.start){

            int timeL = v.getTimeLimit();
            int nrClienti = v.getNrClients();
            int nrCozi = v.getNrQueues();
            int minArr = v.getMinArrTime();
            int maxArr = v.getMaxArrTime();
            int minServ = v.getMinServTime();
            int maxServ = v.getMaxServiceTime();

            SimulationManager simulationmanager = new SimulationManager(timeL,minServ, maxServ, nrCozi, nrClienti, minArr, maxArr, v);
            //simulationmanager.afisareClienti();

            Thread t = new Thread(simulationmanager);
            t.start();


        }

        if(source == v.exit) {
            v.getFrame().dispose();
        }
        if(source == v.reset) {
            v.clear();
        }
    }
}
