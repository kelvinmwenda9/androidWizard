package com.kelvin.dbsql;

public class Model {

    String surname, others, email, phone, nhif, pin;

    //auto generate getters and setters


    public Model(String surname, String others, String email, String phone, String nhif, String pin) {
        this.surname = surname;
        this.others = others;
        this.email = email;
        this.phone = phone;
        this.nhif = nhif;
        this.pin = pin;
    }

    //empty constructor incase we don't want to pass values
    public Model(){

    }


    //model for getters and setters
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getOthers() {
        return others;
    }

    public void setOthers(String others) {
        this.others = others;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNhif() {
        return nhif;
    }

    public void setNhif(String nhif) {
        this.nhif = nhif;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }
}
