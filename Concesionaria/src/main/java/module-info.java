module com.espol.concesionaria {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires javafx.base;
    requires javafx.graphics;

    opens com.espol.concesionaria to javafx.fxml;
    exports com.espol.concesionaria;
}
