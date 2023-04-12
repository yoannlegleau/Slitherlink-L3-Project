module fr.slitherlink {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.xml.bind;
    requires com.google.api.client.extensions.java6.auth;
    requires com.google.api.client.extensions.jetty.auth;
    requires google.api.client;
    requires com.google.api.client;
    requires google.api.services.oauth2.v2.rev50;
    requires com.google.api.client.json.jackson2;

    opens fr.slitherlink.save to java.xml.bind;
    opens fr.slitherlink.app to javafx.fxml;
    exports fr.slitherlink.app;
    exports fr.slitherlink.app.gui.component;
    opens fr.slitherlink.app.gui.component to javafx.fxml;
    exports fr.slitherlink.game;
    opens fr.slitherlink.game to javafx.fxml;
    opens fr.slitherlink.save.puzzle to java.xml.bind;
    opens fr.slitherlink.save.gamesave to java.xml.bind;
    exports fr.slitherlink.app.gui;
    opens fr.slitherlink.app.gui to javafx.fxml;
}