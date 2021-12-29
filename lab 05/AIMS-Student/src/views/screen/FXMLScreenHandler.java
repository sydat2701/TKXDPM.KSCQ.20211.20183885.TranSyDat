package views.screen;

import java.io.File;
import java.io.IOException;

import entity.cart.Cart;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class FXMLScreenHandler {

	protected FXMLLoader loader;
	protected AnchorPane content;
	protected Cart cart;

	public FXMLScreenHandler(String screenPath) throws IOException {
		this.loader = new FXMLLoader(getClass().getResource(screenPath));
		// Set this class as the controller
		this.loader.setController(this);
		try {
			this.content = (AnchorPane) loader.load();
		}catch (Exception e){
			System.out.println("loi 1");
			System.out.println(e);
		}
	}

	public AnchorPane getContent() {
		return this.content;
	}

	public FXMLLoader getLoader() {
		return this.loader;
	}

	public void setImage(ImageView imv, String path){
		File file = new File(path);
		Image img = new Image(file.toURI().toString());
		imv.setImage(img);
	}
}
