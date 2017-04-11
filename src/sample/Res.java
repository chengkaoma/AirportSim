/**
 * Created by liusilin on 4/8/17.
 */
package sample;

import java.time.LocalDate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Model class for a Person.
 *
 * @author Marco Jakob
 */
public class Res {

    private final StringProperty airportName;
    private final StringProperty circlingTime;
    private final StringProperty refuelDelay;
    private final StringProperty weatherDelay;
    private final StringProperty passengerFlow;
    private final StringProperty street;
    private final IntegerProperty postalCode;
    private final StringProperty city;
    private final ObjectProperty<LocalDate> birthday;

    /**
     * Default constructor.
     */
    public Res() {
        this(null, null, null, null, null);
    }

    /**
     * Constructor with some initial data.
     *
     * @param airportName
     * @param circlingTime
     * @param refuelDelay
     * @param weatherDelay
     * @param passengerFlow
     *
     */
    public Res(String airportName, String circlingTime, String refuelDelay, String weatherDelay, String passengerFlow) {
        this.airportName = new SimpleStringProperty(airportName);
        this.circlingTime = new SimpleStringProperty(circlingTime);
        this.refuelDelay = new SimpleStringProperty(refuelDelay);
        this.weatherDelay = new SimpleStringProperty(weatherDelay);
        this.passengerFlow = new SimpleStringProperty(passengerFlow);


        // Some initial dummy data, just for convenient testing.
        this.street = new SimpleStringProperty("some street");
        this.postalCode = new SimpleIntegerProperty(1234);
        this.city = new SimpleStringProperty("some city");
        this.birthday = new SimpleObjectProperty<LocalDate>(LocalDate.of(1999, 2, 21));
    }

    public String getAirportName() {
        return airportName.get();
    }

    public void setAirportName(String airportName) {
        this.airportName.set(airportName);
    }

    public StringProperty airportNameProperty() {
        return airportName;
    }

    public String getCirclingTime() {
        return circlingTime.get();
    }

    public void setCirclingTime(String circlingTime) {
        this.circlingTime.set(circlingTime);
    }

    public StringProperty circlingTimeProperty() {
        return circlingTime;
    }

    public String getRefuelDelay() {
        return refuelDelay.get();
    }

    public void setRefuelDelay(String refuelDelay) {
        this.refuelDelay.set(refuelDelay);
    }

    public StringProperty refuelDelayProperty() {
        return refuelDelay;
    }

    public String getWeatherDelay() {
        return weatherDelay.get();
    }

    public void setWeatherDelay(String weatherDelay) {
        this.weatherDelay.set(weatherDelay);
    }

    public StringProperty weatherDelayProperty() {
        return weatherDelay;
    }

    public String getPassengerFlow() {
        return passengerFlow.get();
    }

    public void setPassengerFlow(String passengerFlow) {
        this.passengerFlow.set(passengerFlow);
    }

    public StringProperty passengerFlowProperty() {
        return passengerFlow;
    }

    public String getStreet() {
        return street.get();
    }

    public void setStreet(String street) {
        this.street.set(street);
    }

    public StringProperty streetProperty() {
        return street;
    }

    public int getPostalCode() {
        return postalCode.get();
    }

    public void setPostalCode(int postalCode) {
        this.postalCode.set(postalCode);
    }

    public IntegerProperty postalCodeProperty() {
        return postalCode;
    }

    public String getCity() {
        return city.get();
    }

    public void setCity(String city) {
        this.city.set(city);
    }

    public StringProperty cityProperty() {
        return city;
    }

    public LocalDate getBirthday() {
        return birthday.get();
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday.set(birthday);
    }

    public ObjectProperty<LocalDate> birthdayProperty() {
        return birthday;
    }
}

