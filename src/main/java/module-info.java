module fr.slitherlink.demo {
    requires javafx.controls;
    requires javafx.fxml;


    opens fr.slitherlink.demo to javafx.fxml;
    exports fr.slitherlink.demo;
}