package com.cs.gmu.project321;

/**
 * Created by Jacob on 11/20/14.
 */
public class RegulateTemp {
    //need dummy classes for all of these objects and for Temperature
    private static AC myAC = new AC();
    private static PriHeat myHeater = new PriHeat();
    private static SecHeat mySecondHeater = new SecHeat();
    private static Fan myFan = new Fan();
    private static Thermostat therm = new Thermostat();

    String mode;
    double desired;
    double current;

    RegulateTemp(String mode){
        this.mode = mode;
        desired = therm.desiredTemp.get();
        current = therm.currentTemp.get();
        monitorTemp();
    }

    void setMode(String mode){
        this.mode = mode;
    }

    void monitorTemp(){
        myFan.on();

        if(mode.equalsIgnoreCase("AC")) {
            if (desired < current){
                while (desired < current) {
                    myAC.on();
                    current = therm.currentTemp.get();
                }
                myAC.off();
            }

        } else if(mode.equalsIgnoreCase("Heat")){
            if(desired > current){
                while (desired > current) {
                    myHeater.on();
                    if(desired - current >= 10){
                        mySecondHeater.on();
                    }
                    current = therm.currentTemp.get();
                }
                myHeater.off();
                mySecondHeater.off();

            }

        } else if(mode.equalsIgnoreCase("FanOnly")){

        } else {
            myFan.off();
            return;
        }
    }
}
