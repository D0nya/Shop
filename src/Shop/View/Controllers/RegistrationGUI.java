package Shop.View.Controllers;

import Shop.DAL.Models.Role;
import Shop.DAL.Models.User;
import Shop.Infrastructure.Client.Client;
import Shop.Infrastructure.Client.Main;
import Shop.Infrastructure.Models.Message;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistrationGUI
{
    public TextField login;
    public TextField password;
    public TextField email;
    public TextField phoneNum;
    public TextField name;
    public Label ErrorField;
    private Pattern emailPattern = Pattern.compile("^((?!\\.)[\\w-_.]*[^.])(@\\w+)(\\.\\w+(\\.\\w+)?[^.\\W])$");
    private Pattern phonePattern = Pattern.compile("\\d{9}|(?:\\d{3}-){2}\\d{4}|\\(\\d{2}\\)\\d{3}-?\\d{4}$");
    private ObjectMapper mapper = new ObjectMapper();


    public void Register() throws IOException
    {
        if(login.getLength() < 3)
        {
            ErrorField.setText("Логин должен быть длиннее 3 символов");
            return;
        }
        if(password.getLength() < 3)
        {
            ErrorField.setText("Пароль должен быть длиннее 3 символов");
            return;
        }
        if(name.getLength() == 0)
        {
            ErrorField.setText("Введите имя");
            return;
        }
        if(Validate(emailPattern, email.getText()))
        {
            ErrorField.setText("Неверно введена электронная почта");
            return;
        }
        if(Validate(phonePattern, phoneNum.getText()))
        {
            ErrorField.setText("Неверно введен номер телеофна");
            return;
        }
        ErrorField.setText("");

        User newUser;
        try
        {
            newUser = new User(0, login.getText(), password.getText(), email.getText(), new Role(2, "user"));
        } catch (Exception e)
        {
            e.printStackTrace();
            ErrorField.setText("Ошибка шифрования");
            return;
        }

        String userJson = mapper.writeValueAsString(newUser);
        Message msg = new Message<>("REGISTER", User.class, userJson);
        msg = Client.getInstance().Send(msg);
        if(msg.getHead().equals("SUCCESS"))
        {
            Alert a = new Alert(Alert.AlertType.CONFIRMATION, msg.getObject(), ButtonType.APPLY);
            a.show();
            Main.OpenScene("../../View/GUI/AuthorisationGUI.fxml", "Авторизация", null);
        }
    }

    public void Back()
    {
        Main.OpenScene("../../View/GUI/AuthorisationGUI.fxml", "Авторизация", null);
    }

    private boolean Validate(Pattern pattern, String str)
    {
        Matcher matcher = pattern.matcher(str);
        return !matcher.find();
    }
}
