package org.example;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;

public class ListOfPlants {

    private List<Plant> listOfPlants = new ArrayList<>();

    private Set<LocalDate> setOfWaterings = new HashSet<>();

    // reads items from text file and stores them to list
    public static ListOfPlants importFromFile(String filename) throws PlantException {

        ListOfPlants list = new ListOfPlants();
        String line;
        Plant plant;
        String[] items;

    try(Scanner sc = new Scanner(new File(filename))){
        while(sc.hasNextLine()){
            line = sc.nextLine();
            items = line.split("\t");
            String name = items[0];
            String notes = items[1];
            int frekvencyOfWatering = Integer.parseInt(items[2]);
            LocalDate watering = LocalDate.parse(items[3]);
            LocalDate planted = LocalDate.parse(items[4]);

            plant = new Plant(name,notes,planted,watering,frekvencyOfWatering);
            list.addPlant(plant);
            list.setOfWaterings.add(watering);
        }
    }
    catch (FileNotFoundException e){
        throw new PlantException("Soubor "+filename+" nebyl nalezen."+e.getLocalizedMessage());
    }
    catch (DateTimeParseException e){
        throw new PlantException("Chybn? zapsan? datum v souboru "+filename+" "+e.getLocalizedMessage());
    }
    catch (NumberFormatException e){
        throw new PlantException("Chybn? zapsan? frekvence zal?v?n? kv?tiny v souboru "+filename+" "+
                e.getLocalizedMessage());
    }
    return list;
    }

    //loads items from list and prints them to text file
    public void exportToFile(String filename) throws PlantException {
    try(PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(filename)))) {

        for (Plant plant:listOfPlants) {
            writer.println(plant.toString());
        }
    }
      catch (IOException e) {
        throw new PlantException("Nepodařil se zápis do souboru "+filename+" "+e.getLocalizedMessage());
    }
    }

    public void addPlant(Plant plant){

        listOfPlants.add(plant);
    }

    public void removePlant(Plant plant){

        listOfPlants.remove(plant);
    }

    public Plant getPlantAtIndex(int index){

        return listOfPlants.get(index);
    }

   public void removePlantByName(String name) {

       for (Plant plant:listOfPlants) {
           if(plant.getName().equals(name)) removePlant(plant);
       }
   }

    public StringBuilder getWateringInfoForAllPlants(){

        StringBuilder info = new StringBuilder();

        for (Plant plant:this.listOfPlants) {
            info.append(plant.getWateringInfo());
        }
        return info;
    }

    public List<Plant> getListOfPlants() {

        return listOfPlants;
    }

    // returns set of days when happened a last watering of any plant
    public Set<LocalDate> getSetOfWaterings() {

        return new HashSet<>(setOfWaterings);
    }

    // sorts plants in list by name
    public void sortByName() {

        Collections.sort(getListOfPlants());
    }

    // sorts plants in list by date of last watering
    public void sortByWatering() {

        Collections.sort(getListOfPlants(),new PlantWateringComparator());
    }
}
