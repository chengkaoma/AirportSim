//Chengkao Ma
package sample;

public class Airplane {

    public static int maxcap=100 ;
    public static int speed=100 ;


    private String m_name;
    private int m_passenger;
    private Airport m_destination;
    private double arrivalTime;
    // set fuel and weather
    public static double fuelTank = 1000;
    public static double fuelLimit = 1000*2/3;
    private double m_usedFuel = 0;
    private double m_limit;

    public Airplane(String name, Airport destination,int spe, int max) {
        m_name = name;
        m_destination = destination;
        maxcap=max;
        speed=spe;
    }
    public int get_passenger(){
        return m_passenger;
    }
    public void set_ArrivalTime(double time){
        arrivalTime = time;
    }
    public void set_destination(Airport destination){
        m_destination = destination;
    }
    public Airport getDestination(){
        return m_destination;
    }
    public double get_ArrivalTime(){
        return arrivalTime;
    }
    public String get_Name() {
        return m_name;
    }
    public void set_passenger(){
        m_passenger = (int)(Math.random()*(maxcap+1));
    }
    // add fuel and bad weather
    public void set_fuel(int a1, int a2){
        if(m_usedFuel < fuelLimit){
            m_usedFuel += Airport.DISTANCE[a1][a2]*0.1;
        }else{
            m_usedFuel = Airport.DISTANCE[a1][a2]*0.1;
        }
    }
    public double get_fuel(){
        return m_usedFuel;
    }

}
