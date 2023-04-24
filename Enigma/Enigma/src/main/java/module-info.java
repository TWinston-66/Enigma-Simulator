module com.enigma.enigma {
    requires javafx.controls;
    requires javafx.fxml;
            
        requires org.controlsfx.controls;

    //opens com.enigma.enigma to javafx.fxml;
    //exports com.enigma.enigma;
    exports com.enigma.enigma.Simulator;
    opens com.enigma.enigma.Simulator to javafx.fxml;
    exports com.enigma.enigma.Simulator.Enigma;
    opens com.enigma.enigma.Simulator.Enigma to javafx.fxml;
}