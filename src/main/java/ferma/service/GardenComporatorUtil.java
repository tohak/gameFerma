package ferma.service;

import ferma.domain.Garden;

import java.util.Comparator;

public class GardenComporatorUtil implements Comparator<Garden> {
    @Override
    public int compare(Garden o1, Garden o2) {
        return o1.getPole().compareTo(o2.getPole());
    }


}
