package com.example.jsonreader;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.FileChooser;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;


public class Controller {
    private static ArrayList<City> cities = new ArrayList<>();

    public static ArrayList<City> getCities(){
        return cities;
    }

    public static File inputFile(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose File");
        FileChooser.ExtensionFilter json = new FileChooser.ExtensionFilter("JSON files", "*.json");
        fileChooser.getExtensionFilters().add(json);
        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            try {
                String jsonString = new String(Files.readAllBytes(Paths.get(selectedFile.getAbsolutePath())));
            } catch (IOException e) {
                e.getMessage();
            } catch (Exception e) {
                e.getMessage();
            }
        } else {
            System.out.println("Файл не выбран.");
        }

        return selectedFile;
    }

    public static void readJson(File selectedFile){
        try {
            String content = new String(Files.readAllBytes(Paths.get(selectedFile.getAbsolutePath())));

            JSONObject jsonObject = new JSONObject(content);
            JSONArray branches = jsonObject.getJSONArray("branches");

            for (int i = 0; i < branches.length(); i ++) {
                JSONObject branch = branches.getJSONObject(i);

                String cityName = branch.getString("title");

                JSONArray warehouses = branch.getJSONArray("warehouses");

                int pvzCount = warehouses.length();

                City city = new City(cityName, pvzCount);

                cities.add(city);
            }

        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Ошибка при разборе JSON: " + e.getMessage());
        }

    }






}
