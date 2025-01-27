package org.megatest.javalabtesting.Model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor
public class Handlowiec extends Pracownik {

    private BigDecimal stawkaProwizji;
    private BigDecimal limitProwizjiMiesiac;

    private Roles stanowisko = Roles.HANDLOWIEC;


    public Handlowiec(String pesel, String firstName, String secondName, float wynagrodzenie, String phoneNumber, BigDecimal stawkaProwizji, BigDecimal limitProwizjiMiesiac) {
        super(pesel, firstName, secondName, wynagrodzenie, phoneNumber);
        this.stawkaProwizji = stawkaProwizji;
        this.limitProwizjiMiesiac = limitProwizjiMiesiac;
//        this.stanowisko = stanowisko;
    }

    @Override
    public String toString() {
        return "Identyfikator PESEL:             " + pesel + "\n" +
                "Imię:                            " + firstName + "\n" +
                "Nazwisko:                        " + secondName + "\n" +
                "Stanowisko:                      " + stanowisko + "\n" +
                "Wynagrodzenie:                   " + wynagrodzenie + "\n" +
                "Numer telefonu:                  " + phoneNumber + "\n" +
                "Stawka prowizji:                 " + stawkaProwizji.setScale(2, RoundingMode.HALF_UP) + "\n" +
                "Limit prowizji miesięczny:       " + limitProwizjiMiesiac.setScale(2, RoundingMode.HALF_UP);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Handlowiec that)) return false;
        return Objects.equals(stawkaProwizji, that.stawkaProwizji) && Objects.equals(limitProwizjiMiesiac, that.limitProwizjiMiesiac) && stanowisko == that.stanowisko;
    }

    @Override
    public int hashCode() {
        return Objects.hash(stawkaProwizji, limitProwizjiMiesiac, stanowisko);
    }
}