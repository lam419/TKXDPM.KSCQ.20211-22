module EcoBikeRental {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.logging;
	requires java.sql;
	
	opens application to javafx.graphics, javafx.fxml;
	opens views.screen to javafx.fxml;
	opens views.screen.home to javafx.fxml;
	opens views.screen.rentbike to javafx.fxml;
	opens views.screen.payment to javafx.fxml;
	opens views.screen.popup to javafx.fxml;
}
