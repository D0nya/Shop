package Shop.View.Controllers;

import Shop.DAL.Models.Cart;
import Shop.Infrastructure.Client.Client;
import Shop.Infrastructure.Client.Main;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.FileInputStream;
import java.io.IOException;

public class MainMenu
{
    public Button Management;

    @FXML
    public void initialize() {
        try {
            if (Client.getInstance().getCustomer().getUser().getRole().getName().toUpperCase().equals("ADMIN"))
                Management.setVisible(true);
            else
                Management.setVisible(false);
            ObjectMapper mapper = new ObjectMapper();
            Cart cart = mapper.readValue(new FileInputStream("cart.json"), Cart.class);
            if(cart != null)
                Client.getInstance().setCart(cart);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void cart()
    {
        Main.OpenScene("../../View/GUI/CartGUI.fxml", "Корзина", null);
    }

    public void orders()
    {
        Main.OpenScene("../../View/GUI/OrdersGUI.fxml", "Заказы", null);
    }

    public void adminMenu()
    {
        Main.OpenScene("../../View/GUI/AdminGUI.fxml", "Управление", null);
    }

    public void products()
    {
        Main.OpenScene("../../View/GUI/ProductsList.fxml", "Продукты", null);
    }
}
