package org.example;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Set;

public class Main {

    public static final String FILENAMEIN = "kvetiny.txt";
    public static final String FILENAMEOUT = "nove kvetiny.txt";

    // returns set of days for last seven days when happened a last watering of any plant
    public static Set<LocalDate> getsetofwateringssevendays(Set<LocalDate> setWaterings){

        Set<LocalDate> setWateringsSevenDays = new HashSet<>();

        for (LocalDate date:setWaterings) {
            if(ChronoUnit.DAYS.between(date, LocalDate.now()) <= 7) setWateringsSevenDays.add(date);
        }
        return setWateringsSevenDays;
    }
    public static void main(String[] args) throws PlantException {

        ListOfPlants list;

        // reads items from text file and stores them to list
        try {
            list = ListOfPlants.importFromFile(FILENAMEIN);
        } catch (PlantException e) {
            throw new PlantException(e.getLocalizedMessage());
        }

        System.out.println(list.getWateringInfoForAllPlants());

        try {
            Plant plant1 = new Plant("Bazalka v kuchyni", "", LocalDate.of(2021,9,4),
                    LocalDate.of(2021,9,4), 3);
            list.addPlant(plant1);
        } catch (PlantException e) {
            throw new PlantException(e.getLocalizedMessage());
        }

        list.removePlantByName("Sukulent v koupelně");

        //loads items from list and prints them to text file
        try {
            list.exportToFile(FILENAMEOUT);
        } catch (PlantException e) {
            throw new PlantException(e.getLocalizedMessage());
        }

        System.out.println("seznam rostlin:\n"+list.getListOfPlants());

        list.sortByName();

        System.out.println("\nseznam rostlin se?azen� podle jm�na:\n"+list.getListOfPlants());

        list.sortByWatering();

        System.out.println("\nseznam rostlin se?azen� podle data z�livky:\n"+list.getListOfPlants());

        System.out.println("\ndny,kdy prob�hala posledn� z�livka:\n"+list.getSetOfWaterings());

        System.out.println("\ndny,kdy prob�hala posledn� z�livka za posledn�ch sedm dn�:\n"+ getsetofwateringssevendays(list.getSetOfWaterings()));
    }
}