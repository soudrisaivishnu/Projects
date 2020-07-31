package com.example.mileagecheck;

public class Records {

    // public int id;
    public float Distance;
    public float Petrol;
    public float Milage;
    public float Amount;

    public Records(float distance, float petrol, float amount, float milage) {
        //this.id = id;
        Amount = amount;
        Distance = distance;
        Petrol = petrol;
        Milage = milage;
    }

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

    public float getAmount() {
        return Amount;
    }

    public void setAmount(float amount) {
        Amount = amount;
    }
}