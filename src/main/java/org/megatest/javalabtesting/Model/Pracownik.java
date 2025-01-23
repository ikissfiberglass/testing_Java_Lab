package org.megatest.javalabtesting.Model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public abstract class  Pracownik<T> implements Serializable {
    private static Set<String> uniquePesels = new HashSet<>();

    protected String pesel;
    protected String firstName;
    protected String secondName;
    protected float wynagrodzenie;
    protected String phoneNumber;
    protected T custom;
    protected Roles stanowisko = Roles.PRACOWNIK;

    public Pracownik() {}

    public Pracownik(String pesel, String firstName, String secondName, float wynagrodzenie, String phoneNumber){
        if(!uniquePesels.add(pesel)){
            throw new IllegalArgumentException("PESEL [" + pesel + "] musi być wyjątkowy");
        }
        this.pesel = pesel;
        this.firstName = firstName;
        this.secondName = secondName;
        this.wynagrodzenie = wynagrodzenie;
        this.phoneNumber = phoneNumber;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public void setCustom(T custom) {
        this.custom = custom;
    }

    public String getPesel() {
        return pesel;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setPesel(String pesel){
        this.pesel = pesel;
    }



}