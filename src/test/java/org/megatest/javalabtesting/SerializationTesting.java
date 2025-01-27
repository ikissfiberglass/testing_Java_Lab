package org.megatest.javalabtesting;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.megatest.javalabtesting.Model.Pracownik;
import org.megatest.javalabtesting.Model.PracownikRepository;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Slf4j
public class SerializationTesting {

    private final PracownikRepository repository = new PracownikRepository();

    @Test
    void serializationToZipTest(){
        String archiveName = "pracownicyTest.zip";
        File zipFile = new File("megafolder", archiveName);

        try{
            repository.serializeToZip(archiveName);
            Assertions.assertTrue(zipFile.exists());

//        }catch (Exception  e){
//            log.info("plik nie istnieje");
        }finally {
            if(zipFile.exists()){
                zipFile.delete();
            }
        }
    }


    @Test
    void deserializationFromZipTest(){
        String zipFileName = "pracownicyTest.zip";
        try {
            repository.serializeToZip(zipFileName);

            ArrayList<Pracownik> deserializedList = repository.deserializeFromZip(zipFileName);

            List<Pracownik> originalList = repository.getAllPracownicy();
            assertEquals(originalList.size(), deserializedList.size());

        } finally {
            new File("megafolder", zipFileName).delete();
        }
    }

    void deserializeCorruptedZipTest(){

        String corruptedZip = "corruptedTest.zip" ;

        try {
            File corruptedFile = new File("megafolder");
            if (!corruptedFile.getParentFile().exists()) {
                corruptedFile.getParentFile().mkdirs();
            }
            try (FileOutputStream fos = new FileOutputStream(corruptedFile)) {
                fos.write("This is not a valid ZIP file.".getBytes());
            } catch (IOException e) {

            }

            Assertions.assertThrows(IOException.class, () -> repository.deserializeFromZip(corruptedZip));
        } finally {
            new File("megafolder", corruptedZip).delete();
        }

    }

    @Test
    void deserializeFromNullZip( ){

        String nullZip = "nullZip.zip";
        Assertions.assertThrows(IOException.class,()-> repository.deserializeFromZip(nullZip) );
    }

}
