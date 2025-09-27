module org.example.escritura_rapida_jemm {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.desktop;


    opens org.example.escritura_rapida_jemm to javafx.fxml;
    exports org.example.escritura_rapida_jemm;
    opens org.example.escritura_rapida_jemm.controllers to javafx.fxml;
    opens org.example.escritura_rapida_jemm.views to javafx.fxml;
}