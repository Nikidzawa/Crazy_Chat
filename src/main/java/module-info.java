module com.example.demo1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.desktop;
    requires javafx.swing;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.demo1 to javafx.fxml;
    exports com.example.demo1;
    exports com.example.demo1.server;
    opens com.example.demo1.server to javafx.fxml;
    exports com.example.demo1.chat;
    opens com.example.demo1.chat to javafx.fxml;
}