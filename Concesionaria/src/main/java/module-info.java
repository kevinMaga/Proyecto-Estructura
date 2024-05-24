module com.espol.concesionaria {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.espol.concesionaria to javafx.fxml;
    exports com.espol.concesionaria;
}
