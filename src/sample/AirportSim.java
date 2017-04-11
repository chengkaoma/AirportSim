//Chengkao Ma
package sample;
import java.io.*;
import java.util.*;

public class AirportSim {

    public static int SimulationTime;
    public static StringBuilder builder = new StringBuilder();
    public static HashMap<String, List<String>> result = new HashMap<>();
    public static boolean multipath;


    public static String simulation(int NumsPlane, int st, int speed, int maxcap, boolean multi,HashMap<String, List<String>>res) {
        SimulationTime=st;
        multipath=multi;

        builder = new StringBuilder();
        Simulator.restart();
        Event.m_nextId = 0;


        for(int i=0;i<Airport.AIRPORT.length;i++){
            List<String> l = new ArrayList<>();
            for(int j = 0; j < 60; j++) l.add(" ");
            AirportSim.result.put(Airport.AIRPORT[i],l);
        }


            for (int i = 0; i < NumsPlane; i++) {
                Airplane airplane = new Airplane("DL" + String.valueOf(i + 1), Airport.airpotarray[i % 60],speed,maxcap);
                airplane.set_passenger();
                AirportEvent arrivingEvent = new AirportEvent(0, Airport.airpotarray[i % 60], AirportEvent.PLANE_DEPARTS, airplane);
                Simulator.schedule(arrivingEvent);
            }
            Simulator.stopAt(SimulationTime);
            Simulator.run();

        for(String s : result.keySet()) {
            res.put(s,result.get(s));
        }
        return builder.toString();

    }
}
