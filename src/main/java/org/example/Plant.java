package org.example;

import java.time.LocalDate;

public class Plant implements Comparable<Plant> {


    private String name;
    private String notes;
    private LocalDate planted;
    private LocalDate watering;
    private int frekvencyOfWatering;

    private static final String DELIMITER = "\t";

    public Plant(String name, String notes, LocalDate planted, LocalDate watering, int frekvencyOfWatering) throws PlantException {

        this.name = name;
        this.notes = notes;
        this.planted = planted;
        if(watering.isBefore(planted)) throw new PlantException("Datum posledn� z�livky nesm� b�t star?�" +
                " ne? datum zasazen� rostliny.");
        this.watering = watering;
        if(frekvencyOfWatering <= 0) throw new PlantException("Frekvence z�livky mus� b�t v?t?� ne? nula!");
        this.frekvencyOfWatering = frekvencyOfWatering;
    }
    public Plant(String name, LocalDate planted, LocalDate watering, int frekvencyOfWatering) throws PlantException {

        this(name,"",planted,watering,frekvencyOfWatering);
    }

    public Plant(String name, LocalDate planted, int frekvencyOfWatering) throws PlantException {

        this(name,"",planted,LocalDate.now(),frekvencyOfWatering);
    }

    public Plant(String name) throws PlantException {

        this(name,LocalDate.now(),7);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public LocalDate getPlanted() {
        return planted;
    }

    public void setPlanted(LocalDate planted) {
        this.planted = planted;
    }

    public LocalDate getWatering() {
        return watering;
    }

    public void setWatering(LocalDate watering) throws PlantException {
        if(watering.isBefore(planted)) throw new PlantException("Datum posledn� z�livky nesm� b�t star?�" +
                " ne? datum zasazen� rostliny.");
        this.watering = watering;
    }

    public int getFrekvencyOfWatering() {
        return frekvencyOfWatering;
    }

    public void setFrekvencyOfWatering(int frekvencyOfWatering) throws PlantException {
        if(frekvencyOfWatering <= 0) throw new PlantException("Frekvence z�livky mus� b�t v?t?� ne? nula!");
        this.frekvencyOfWatering = frekvencyOfWatering;
    }

    public String getWateringInfo() {

     String info = "N�zev kv?tiny:\n"+this.name+"\nDatum posledn� z�livky: "+this.watering.toString()+
             "\nDatum doporu?en� dal?� z�livky: "+this.watering.plusDays(getFrekvencyOfWatering()).toString()+"\n";

        return info;
    }

    @Override
    public String toString() {
        String str = getName()+DELIMITER+getNotes()+DELIMITER+getFrekvencyOfWatering()+DELIMITER+getWatering()+
                DELIMITER+getPlanted();
        return str;
    }

    // sorts plants in list by name
    @Override
    public int compareTo(Plant plant) {
        return getName().compareTo(plant.getName());
    }
}
