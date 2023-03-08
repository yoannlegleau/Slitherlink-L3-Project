module fr.slitherlink {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.xml.bind;

    opens fr.slitherlink.save to java.xml.bind;
    opens fr.slitherlink.app to javafx.fxml;
    exports fr.slitherlink.app;
    exports fr.slitherlink.app.fx_controlleur;
    opens fr.slitherlink.app.fx_controlleur to javafx.fxml;
    exports fr.slitherlink.app.component;
    opens fr.slitherlink.app.component to javafx.fxml;
}