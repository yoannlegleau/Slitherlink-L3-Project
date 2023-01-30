module fr.slitherlink.demo {
    requires javafx.controls;
    requires javafx.fxml;


    opens fr.slitherlink.app to javafx.fxml;
    exports fr.slitherlink.app;
}