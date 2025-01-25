package org.megatest.javalabtesting;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.megatest.javalabtesting.Model.Handlowiec;
import org.megatest.javalabtesting.Model.Pracownik;
import org.megatest.javalabtesting.Model.PracownikRepository;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JavaLabTestingApplicationTests {

    private final PracownikRepository repository = new PracownikRepository();

    @Test
    void contextLoads() {
    }

    @BeforeEach
    public void setup(){


    }

    @Test
    public void addHandlowiecToEmptyContainer(){
        PracownikRepository localRepo = new PracownikRepository();

        Pracownik handlowiec = new Handlowiec();
        handlowiec.setPesel("123456789");
        handlowiec.setPesel("Axl");
        handlowiec.setSecondName("Rose");
        handlowiec.setWynagrodzenie(4999.9F);
        handlowiec.setPhoneNumber("450790267");

        Pracownik actualHandlowiec = handlowiec;

        repository.addPracownik(handlowiec);

//        Pracownik actualHandlowiec = new Handlowiec();

        Assertions.assertTrue(actualHandlowiec.equals(localRepo.findPracownikByPesel(handlowiec.getPesel())));

        Assertions.assertTrue();

    }



}
