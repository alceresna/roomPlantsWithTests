package org.example;

import java.util.Comparator;

public class PlantWateringComparator implements Comparator<Plant> {

    // sorts plants in list by date of last watering
    @Override
    public int compare(Plant plant1, Plant plant2) {
        return plant1.getWatering().compareTo(plant2.getWatering());
    }
}
