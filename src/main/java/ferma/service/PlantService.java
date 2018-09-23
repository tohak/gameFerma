package ferma.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import ferma.domain.Plant;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PlantService {
    private static ObjectMapper mapper = new ObjectMapper();
    private static final String FILEPATCH = "plans.json";

    public static void saveJakson() {
        List<Plant> plant = new ArrayList<>();
        plant.add(new Plant("Помидор", 300L, 500L, 20L));
        plant.add(new Plant("Картошка", 100L, 400L, 90L));
        plant.add(new Plant("БыстрыйТест", 100L, 200L, 3L));

        try {
            mapper.writeValue(new File(FILEPATCH), plant);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Plant> loadJakson() {
        List<Plant> plants = new ArrayList<>();
        try {
            plants = mapper.readValue(new FileInputStream(FILEPATCH),
                    mapper.getTypeFactory().constructCollectionType(List.class, Plant.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return plants;
    }
}
