package com.example.mileagecalculator;

public class Records {

    // public int id;
    public float Distance;
    public float Petrol;
    public float Milage;

    public Records(float distance, float petrol, float milage) {
        //this.id = id;
        Distance = distance;
        Petrol = petrol;
        Milage = milage;
    }

//    public int getId() {
//        return id;
//    }

   /* public void setId(int id) {
        this.id = id;
    }*/

    public float getDistance() {
        return Distance;
    }

    public void setDistance(float distance) {
        Distance = distance;
    }

    public float getPetrol() {
        return Petrol;
    }

    public void setPetrol(float petrol) {
        Petrol = petrol;
    }

    public float getMilage() {
        return Milage;
    }

    public void setMilage(float milage) {
        Milage = milage;
    }
}