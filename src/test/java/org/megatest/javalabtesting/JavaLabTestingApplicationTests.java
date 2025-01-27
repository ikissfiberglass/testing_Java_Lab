package org.megatest.javalabtesting;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.megatest.javalabtesting.Controller.Service.ValuesGenerator;
import org.megatest.javalabtesting.Model.Dyrektor;
import org.megatest.javalabtesting.Model.Handlowiec;
import org.megatest.javalabtesting.Model.Pracownik;
import org.megatest.javalabtesting.Model.PracownikRepository;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;
import java.util.logging.Logger;

@SpringBootTest
@Slf4j
class JavaLabTestingApplicationTests {

//private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

    Random random = new Random();
    ValuesGenerator iterator = new ValuesGenerator();

    private PracownikRepository repository = new PracownikRepository();
    private Pracownik handlowiec = new Handlowiec();
    private Pracownik dyrektor = new Dyrektor();


    @Test
    void contextLoads() {
    }

    @BeforeEach
    public void setup(){
        repository = new PracownikRepository();

        //h
        handlowiec.setPesel("11111111111");
        handlowiec.setFirstName("Axl");
        handlowiec.setSecondName("Rose");
        handlowiec.setWynagrodzenie(4999.9F);
        handlowiec.setPhoneNumber("450790267");

        //d
        dyrektor.setPesel("22222222222");
        dyrektor.setFirstName("Dave");
        dyrektor.setSecondName("Mustaine");
        dyrektor.setWynagrodzenie(7800.475F);
        dyrektor.setPhoneNumber("790489220");

    }

    @Test
    void addHandlowiecToEmptyContainerTest(){
        log.info( String.valueOf(repository.getPracownicySize()));
        repository.addPracownik(handlowiec);
        Assertions.assertTrue(repository.getPracownicySize() == 1);
    }

    @Test
    void addDyrektorToEmptyContainerTest(){
        log.info( String.valueOf(repository.getPracownicySize()));
        repository.addPracownik(dyrektor);
        Assertions.assertTrue(repository.getPracownicySize() == 1);
    }

    @Test
    void addHandlowiecToOtherPracownicyTest(){
        Pracownik handlowiec2 = new Handlowiec(
                "33333333333", "firstname1", "secondname1", 4500.5F, "512345678",
                new BigDecimal("0.03"), new BigDecimal("4000"));

        Pracownik handlowiec3 = new Handlowiec(
                "44444444444", "firstname2", "secondname2", 5600.75F, "600987654",
                new BigDecimal("0.04"), new BigDecimal("4500"));

        Pracownik dyrektor1 = new Dyrektor(
                "22222222222", "firstname2", "secondname2", 7800.475F, "790489220",
                new BigDecimal("1000"), "A123", 3000);

        Pracownik dyrektor2 = new Dyrektor(
                "55555555555", "firstname3", "secondname3", 9200.35F, "730567891",
                new BigDecimal("1200"), "B456", 3500);

        repository.addPracownik(handlowiec2);
        repository.addPracownik(handlowiec3);
        repository.addPracownik(dyrektor1);
        repository.addPracownik(dyrektor2);

        repository.addPracownik(handlowiec);
        Assertions.assertEquals("11111111111" , handlowiec.getPesel());

        String names = "Lista pracowników: \n";
        for(Pracownik p : repository.getAllPracownicy()){
//            log.info(p.getFirstName() + " " + p.getSecondName() + "\n");
            names += p.getFirstName() + " ";
            names += p.getSecondName() + "\n";
        }
        log.info(names);
    }

    @Test
    void addDyrektorToOtherPracownicyTest(){
        Pracownik handlowiec2 = new Handlowiec(
                "77777777777", "firstname1", "secondname1", 4500.5F, "512345678",
                new BigDecimal("0.03"), new BigDecimal("4000"));

        Pracownik handlowiec3 = new Handlowiec(
                "4444444444", "firstname2", "secondname2", 5600.75F, "600987654",
                new BigDecimal("0.04"), new BigDecimal("4500"));

        Pracownik dyrektor1 = new Dyrektor(
                "6666666666", "firstname2", "secondname2", 7800.475F, "790489220",
                new BigDecimal("1000"), "A123", 3000);

        Pracownik dyrektor2 = new Dyrektor(
                "5555555555", "firstname3", "secondname3", 9200.35F, "730567891",
                new BigDecimal("1200"), "B456", 3500);

        repository.addPracownik(handlowiec2);
        repository.addPracownik(handlowiec3);
        repository.addPracownik(dyrektor1);
        repository.addPracownik(dyrektor2);

        repository.addPracownik(dyrektor);
        Assertions.assertEquals("22222222222" , dyrektor.getPesel());

        String names = "Lista pracowników: \n";
        for(Pracownik p : repository.getAllPracownicy()){
//            log.info(p.getFirstName() + " " + p.getSecondName() + "\n");
            names += p.getFirstName() + " ";
            names += p.getSecondName() + "\n";
        }
        log.info(names);

    }


    @Test
    void adding10PracownicyTest(){

        int iterationsNumber = 10;
        BigDecimal stawka = new BigDecimal(499.000003);
        BigDecimal limitProwizji = new BigDecimal(909.0001625);

        for(int i = 0; i< iterationsNumber; i++) {

            if (random.nextInt() % 2 == 0 ) {
                Pracownik p = new Handlowiec(String.valueOf(iterator.generatedId()),
                        "firstname" + String.valueOf(iterator.generatedId()),
                        "secondname" + String.valueOf(iterator.generatedId()),
                        iterator.incrementedFloat(),
                        String.valueOf(iterator.generatedId()),
                        stawka,
                        limitProwizji );
                repository.addPracownik(p);

            } else {

                BigDecimal dodatekSluzbowy = new BigDecimal("1500.25");
                String kartaSluzbowa = "CARD-" + iterator.generatedId();
                float limitKosztowMiesiac = iterator.incrementedFloat() * 1.25F;

                Pracownik d = new Dyrektor(
                        String.valueOf(iterator.generatedId()),
                        "firstname" + iterator.generatedId(),
                        "secondname" + iterator.generatedId(),
                        iterator.incrementedFloat(),
                        String.valueOf(iterator.generatedId()),
                        dodatekSluzbowy,
                        kartaSluzbowa,
                        limitKosztowMiesiac );

                repository.addPracownik(d);
            }
            stawka = stawka.add(BigDecimal.valueOf(399.000003));
            limitProwizji = limitProwizji.divide(BigDecimal.valueOf(0.75), RoundingMode.HALF_UP);

        }
//        log.info(String.valueOf(repository.getPracownicySize()));
        Assertions.assertEquals(iterationsNumber , repository.getPracownicySize() );

    }

    @Test
    void removeHandlowiecFromContainerTest(){

        Pracownik handlowiec2 = new Handlowiec(
                "777777777", "firstname1", "secondname1", 4500.5F, "512345678",
                new BigDecimal("0.03"), new BigDecimal("4000"));

        Pracownik handlowiec3 = new Handlowiec(
                "444444444", "firstname2", "secondname2", 5600.75F, "600987654",
                new BigDecimal("0.04"), new BigDecimal("4500"));

        Pracownik dyrektor1 = new Dyrektor(
                "666666666", "firstname2", "secondname2", 7800.475F, "790489220",
                new BigDecimal("1000"), "A123", 3000);

        Pracownik dyrektor2 = new Dyrektor(
                "555555555", "firstname3", "secondname3", 9200.35F, "730567891",
                new BigDecimal("1200"), "B456", 3500);

        repository.addPracownik(handlowiec2);
        repository.addPracownik(handlowiec3);
        repository.addPracownik(dyrektor1);
        repository.addPracownik(dyrektor2);

        repository.addPracownik(handlowiec);

        int repoSizeBeforeRemoval = repository.getPracownicySize();
        log.info( "Before: " + String.valueOf(repository.getPracownicySize()));


//        repository.removePracownik(handlowiec);
        repository.removePracownikByPesel(handlowiec.getPesel());
        int repoSizeAfterRemoval = repository.getPracownicySize();
        log.info("After: " + String.valueOf(repository.getPracownicySize()));

        Assertions.assertTrue(repoSizeAfterRemoval < repoSizeBeforeRemoval);

    }

    // zad 1.7
    @Test
    void removeDyrektorFromContainerTest(){

        log.info("Pracownicy: " + String.valueOf(repository.getAllPracownicy()));

        Pracownik handlowiec1 = new Handlowiec(
                "11111", "firstname1", "secondname1", 4500.5F, "512345678",
                new BigDecimal("0.03"), new BigDecimal("4000"));

        Pracownik handlowiec2 = new Handlowiec(
                "22222", "firstname2", "secondname2", 5600.75F, "600987654",
                new BigDecimal("0.04"), new BigDecimal("4500"));

        Pracownik dyrektor1 = new Dyrektor(
                "33333", "firstname2", "secondname2", 7800.475F, "790489220",
                new BigDecimal("1000"), "A123", 3000);

        Pracownik dyrektor2 = new Dyrektor(
                "44444", "firstname3", "secondname3", 9200.35F, "730567891",
                new BigDecimal("1200"), "B456", 3500);

        repository.addPracownik(handlowiec1);
        repository.addPracownik(handlowiec2);
        repository.addPracownik(dyrektor1);
        repository.addPracownik(dyrektor2);

        log.info("Pracownicy: " + String.valueOf(repository.getAllPracownicy()));

        repository.addPracownik(dyrektor);

        int repoSizeBeforeRemoval = repository.getPracownicySize();
        log.info( "Before: " + String.valueOf(repository.getPracownicySize()));

        repository.removePracownik(dyrektor);
//        repository.removePracownikByPesel(dyrektor.getPesel());
        int repoSizeAfterRemoval = repository.getPracownicySize();
        log.info("After: " + String.valueOf(repository.getPracownicySize()));

        Assertions.assertTrue(repoSizeAfterRemoval < repoSizeBeforeRemoval);
    }




}
