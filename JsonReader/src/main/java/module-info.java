module com.example.jsonreader {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;


    opens com.example.jsonreader to javafx.fxml;
    exports com.example.jsonreader;
}