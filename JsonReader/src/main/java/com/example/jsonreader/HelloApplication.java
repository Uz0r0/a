package com.example.jsonreader;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class HelloApplication extends Application {
    private static ObservableList<City> observableList = FXCollections.observableArrayList();
    private static ArrayList<City> sortCities;

    @Override
    public void start(Stage stage) throws IOException {
        Button chooseFile = new Button("Choose File");

        chooseFile.setOnAction(e -> {
            File selectedFile = Controller.inputFile();
            if (selectedFile != null) {
                observableList.clear();
                read(selectedFile);
                sortCities = qSort(Controller.getCities());
            }
        });

        TableView<City> table = new TableView<>();
        table.setItems(observableList);

        TableColumn<City, String> columnName = new TableColumn<>("City");
        columnName.setCellValueFactory(new PropertyValueFactory<>("title"));

        TableColumn<City, Integer> count = new TableColumn<>("Number of PVZ");
        count.setCellValueFactory(new PropertyValueFactory<>("pvzCount"));

        table.getColumns().addAll(columnName, count);

        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setCategories(FXCollections.observableArrayList(Arrays.asList(
                "январь", "февраль", "март", "апрель", "май", "июнь", "июль", "август", "сентябрь", "октябрь", "ноябрь", "декабрь"
        )));
        xAxis.setLabel("Cities");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Number of PVZ");

        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("Top 10 cities with PVZ");

        barChart.setLegendVisible(false);

        VBox root = new VBox(20, chooseFile, table, barChart);
        Scene scene = new Scene(root, 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public static void read(File selectedFile) {
        Controller.readJson(selectedFile);
        observableList.setAll(Controller.getCities());
    }

    public static ArrayList<City> qSort(ArrayList<City> arrayList) {
        if(arrayList.size() <= 1){
            return arrayList;
        }

        ArrayList<City> left = new ArrayList<>();
        ArrayList<City> mid = new ArrayList<>();
        ArrayList<City> right = new ArrayList<>();

        int p = arrayList.get(0).getPvzCount();

        for(int i = 0; i < arrayList.size(); i++){
            if (arrayList.get(i).getPvzCount() < p) {
                left.add(arrayList.get(i));
            }
            if (arrayList.get(i).getPvzCount() > p) {
                right.add(arrayList.get(i));
            }
            if (arrayList.get(i).getPvzCount() == p) {
                mid.add(arrayList.get(i));
            }
        }

        ArrayList<City> ans1 = new ArrayList<>(qSort(right));
        ArrayList<City> ans2 = new ArrayList<>(qSort(left));

        ArrayList<City> result = new ArrayList<>();

        for(int i = 0; i < ans1.size(); i++){
            result.add(ans1.get(i));
        }
        for(int i = 0; i < mid.size(); i++){
            result.add(mid.get(i));
        }
        for(int i = 0; i < ans2.size(); i++){
            result.add(ans2.get(i));
        }

        return result;
    }
}