module com.example.nowarpolisfinal {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires algs4.master.fc511547db;


    opens com.example.nowarpolisfinal to javafx.fxml;
    exports com.example.nowarpolisfinal;
}