//Chengkao Ma
package sample;
import java.util.TreeSet;

public class Simulator {

    private static SimulatorEngine instance = null;

    public static SimulatorEngine getSim() {
        if(instance == null) {
            instance = new SimulatorEngine();
        }
        return instance;
    }

    public static void stopAt(double time) {
        Event stopEvent = new SimulatorEvent(time, getSim(), SimulatorEvent.STOP_EVENT);
        schedule(stopEvent);
    }

    public static void run() {
        getSim().run();
    }

    public static double getCurrentTime() {
        return getSim().getCurrentTime();
    }

    public static void schedule(Event event) {
        event.setTime(event.getTime() + getSim().getCurrentTime());
        getSim().schedule(event);
    }

    public static void restart() {instance = new SimulatorEngine();}
}
