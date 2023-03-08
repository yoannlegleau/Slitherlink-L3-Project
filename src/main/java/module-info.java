module fr.slitherlink {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.xml.bind;

    opens fr.slitherlink.save to java.xml.bind;
    opens fr.slitherlink.app to javafx.fxml;
    exports fr.slitherlink.app;
}