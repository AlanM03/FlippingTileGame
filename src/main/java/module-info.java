module org.fliptile {
    // Requires directives for JavaFX modules
    requires javafx.controls;
    requires javafx.fxml;

    // Exports your packages to be accessible by JavaFX
    exports org.fliptile.view;
    exports org.fliptile.controller;

    // Opens packages to javafx.fxml which is necessary for FXML to function
    opens org.fliptile.view to javafx.fxml;
    opens org.fliptile.controller to javafx.fxml;


}
