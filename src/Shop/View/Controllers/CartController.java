package Shop.View.Controllers;

import Shop.DAL.Models.Cart;
import Shop.DAL.Models.ProductAmount;
import Shop.Infrastructure.Client.Client;
import Shop.Infrastructure.Client.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;

import java.io.IOException;

public class CartController {

    public TableColumn<ProductAmount, String> Product;
    public TableColumn<ProductAmount, Integer> Amount;
    public TableColumn<ProductAmount, Double> Price;
    public TableView<ProductAmount> table;
    public Label totalPriceLabel;

    public TextField changeAmount;
    private ProductAmount selected;

    @FXML
    public void initialize()
    {
        Product.setCellValueFactory(new PropertyValueFactory<>("Name"));
        Amount.setCellValueFactory(new PropertyValueFactory<>("Amount"));
        Price.setCellValueFactory(new PropertyValueFactory<>("Price"));
        UpdateTableData();

        table.setRowFactory(tv ->
        {
            TableRow<ProductAmount> row = new TableRow<>();
            row.setOnMouseClicked(event ->
            {
                if(event.getButton().equals(MouseButton.PRIMARY) && !row.isEmpty())
                {
                    selected = row.getItem();
                    changeAmount.setText(String.valueOf(selected.getAmount()));
                }
            });
            return row;
        });
    }

    public void back() {
        Main.OpenScene("../../View/GUI/MainMenu.fxml", "Главная", null);
    }

    private void UpdateTableData() {
        Cart cart = null;
        try {
            cart = Client.getInstance().getCart();
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert cart != null;
        ObservableList<ProductAmount> observableList = FXCollections.observableList(cart.getProducts());
        table.setItems(observableList);
        totalPriceLabel.setText(String.valueOf(cart.getTotalPrice()));
    }

    public void accept() {
        int amount = 0;
        try
        {
            amount = Integer.parseInt(changeAmount.getText());
        }
        catch (NumberFormatException ignore)
        {
        }
        if(amount > 0 && selected != null)
        {
            selected.setAmount(amount);
            UpdateTableData();
        }
    }

    public void delete() {
        try {
            Client.getInstance().getCart().getProducts().remove(selected);
        } catch (IOException e) {
            e.printStackTrace();
        }
        UpdateTableData();
    }
}
