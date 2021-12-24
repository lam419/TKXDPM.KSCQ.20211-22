module EcoBikeRental {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.logging;
	requires java.sql;
	
	opens application to javafx.graphics, javafx.fxml;
}
