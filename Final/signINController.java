import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class signINController implements Initializable {

    @FXML
    private JFXTextField username;

    @FXML
    private JFXPasswordField pass;



    private PreparedStatement preparedStatement;


    public void getStarted(ActionEvent acc)
    {


        try{
            Connection con = ConnectionConfig.getConnection();

             String sql = "SELECT *FROM users WHERE " +
                "id='"+username.getText()+"' AND password = '"+pass.getText()+"'";

            ResultSet resultSet =  con.prepareStatement(sql).executeQuery();

            if(resultSet.next())
            {
                idGetter.setID(username.getText());
                showMessenger.userName = resultSet.getString("username");
                showMessenger.usernumber = resultSet.getInt("usernumber");
                showMenu.show();
                showSignIN.stage.close();
                System.out.println("Success!");

                DataOutputStream open = new DataOutputStream(new FileOutputStream("AllFriends.dat"));
            }
            else
            {
                errorPass.showBox();
            }

        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    public void close(ActionEvent acc)
    {

        showSignIN.stage.close();

    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }
}
