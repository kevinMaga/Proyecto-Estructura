module com.espol.concesionaria {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.espol.concesionaria to javafx.fxml;
    exports com.espol.concesionaria;
}
