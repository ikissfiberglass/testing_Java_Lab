package org.megatest.javalabtesting.Model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
public abstract class  Pracownik<T> implements Serializable {
    private static Set<String> uniquePesels = new HashSet<>();

    protected String pesel;
    protected String firstName;
    protected String secondName;
    protected float wynagrodzenie;
    protected String phoneNumber;
    protected T custom;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pracownik<?> pracownik = (Pracownik<?>) o;
        return Float.compare(wynagrodzenie, pracownik.wynagrodzenie) == 0 && Objects.equals(pesel, pracownik.pesel) && Objects.equals(firstName, pracownik.firstName) && Objects.equals(secondName, pracownik.secondName) && Objects.equals(phoneNumber, pracownik.phoneNumber) && stanowisko == pracownik.stanowisko;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pesel, firstName, secondName, wynagrodzenie, phoneNumber, stanowisko);
    }
}