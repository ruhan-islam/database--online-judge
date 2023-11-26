package control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import utils.Encrypt;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class qList implements Initializable {

    @FXML
    private Button sub;

    @FXML
    private TextField handle;

    @FXML
    private PasswordField pass;

    @FXML
    private Button b1;

    @FXML
    private Button b2;

    @FXML
    private Button b4;

    @FXML
    private Button b5;

    @FXML
    private Button b3;

    @FXML
    private TextField q11;

    @FXML
    private TextField q12;

    @FXML
    private Label msg;

    @FXML
    private TextField q21;

    @FXML
    private TextField q31;

    @FXML
    private TextField q41;

    @FXML
    private TextField q51;

    @FXML
    private TextField suh;

    @FXML
    private TextField sun;

    @FXML
    private TextField sue;

    @FXML
    private TextField suct;

    @FXML
    private TextField suc;

    @FXML
    private PasswordField sup;

    @FXML
    private Button su;

    @FXML
    private Label sumsg;

    @FXML
    private TextField q61;

    @FXML
    private Button b6;

    @FXML
    private TextField q71;

    @FXML
    private Button b7;

    @FXML
    private TextField q81;

    @FXML
    private Button b8;

    @FXML
    private Button ap;

    @FXML
    private Button csb;

    @FXML
    private Button ucb;

    @FXML
    void b1pressed(ActionEvent event) {
        String _city, _country;

        _city = q11.getText().toLowerCase();
        _country = q12.getText().toLowerCase();

        control.table1.sql = "SELECT ROW_NUMBER() OVER (ORDER BY 1) AS serial, handle, name, number_of_participation, rating " +
                             "FROM public.\"user\" " +
                             "WHERE city = '" + _city + "' AND country = '" + _country +"'" +
                             "ORDER BY rating DESC;";
        design.table1.showPage();
    }

    @FXML
    void b2pressed(ActionEvent event) {
        String f2, in21;

        f2 = "search_by_type";

        in21 = q21.getText().toLowerCase();

        control.table1.sql = "SELECT ROW_NUMBER() OVER (ORDER BY 1) AS serial, * " +
                "FROM " + f2 + "('" + in21 + "')";
        design.table1.showPage();
    }

    @FXML
    void b3pressed(ActionEvent event) {
        String f3, in31;

        f3 = "get_src_codes";

        in31 = q31.getText().toLowerCase();

        control.table1.sql = "SELECT ROW_NUMBER() OVER (ORDER BY 1) AS serial, * " +
                "FROM " + f3 + "('" + in31 + "')";
        design.table1.showPage();
    }

    @FXML
    void b4pressed(ActionEvent event) {
        String f4, in41;

        f4 = "get_contest_info";

        in41 = q41.getText().toLowerCase();

        control.table1.sql = "SELECT ROW_NUMBER() OVER (ORDER BY 1) AS serial, * " +
                "FROM " + f4 + "('" + in41 + "')";
        design.table1.showPage();
    }

    @FXML
    void b5pressed(ActionEvent event) {
        String f5, in51;

        f5 = "get_contest_probs";

        in51 = q51.getText().toLowerCase();

        control.table1.sql = "SELECT ROW_NUMBER() OVER (ORDER BY 1) AS serial, * " +
                "FROM " + f5 + "('" + in51 + "')";
        design.table1.showPage();
    }

    @FXML
    void b6pressed(ActionEvent event) {
        String f6, in61;

        f6 = "search_by_language";

        in61 = q61.getText().toLowerCase();

        control.table1.sql = "SELECT ROW_NUMBER() OVER (ORDER BY 1) AS serial, * " +
                "FROM " + f6 + "('" + in61 + "')";
        design.table1.showPage();
    }

    @FXML
    void b7pressed(ActionEvent event) {
        String f7, in71;

        f7 = "search_by_setter";

        in71 = q71.getText().toLowerCase();

        control.table1.sql = "SELECT ROW_NUMBER() OVER (ORDER BY 1) AS serial, * " +
                "FROM " + f7 + "('" + in71 + "')";
        design.table1.showPage();
    }

    @FXML
    void b8pressed(ActionEvent event) {
        String f8, in81;

        f8 = "rating_change";

        in81 = q81.getText().toLowerCase();

        control.table1.sql = "SELECT ROW_NUMBER() OVER (ORDER BY 1) AS serial, * " +
                "FROM " + f8 + "('" + in81 + "')";
        design.table1.showPage();
    }

    @FXML
    void ucbpressed(ActionEvent event) {
        control.table1.sql = "SELECT * FROM upcoming_contests();";
        design.table1.showPage();
    }

    @FXML
    void appressed(ActionEvent event) {
        control.table1.sql = "SELECT ROW_NUMBER() OVER (ORDER BY 1) AS serial, id, name, type, description, time_limit, memory_limit, attempted, solved " +
                "FROM public.\"problem\"; " ;
        design.table1.showPage();
    }

    @FXML
    void csbpressed(ActionEvent event) {
        control.table1.sql = "SELECT handle, name, rating,city, country " +
                "FROM public.\"user\" ORDER BY rating DESC;" ;
        design.table1.showPage();
    }


    @FXML
    void spressed(ActionEvent event) {
        Connection c = sample.Main.con;

        if(sub.getText().equals("logout")) {
            hide_facilities();
            show_signup();
            msg.setText("");
            handle.setVisible(true);
            pass.setVisible(true);
            sub.setText("login");
            handle.setText("");
            pass.setText("");
            return;
        }

        String han = handle.getText();
        String password = pass.getText();

        if(han.equals("") || password.equals("")) {
            msg.setText("invalid info");
            return;
        }

        //SQL FOR SELECTING ALL OF CUSTOMER
        String SQL = "SELECT * FROM public.\"user\" WHERE handle = '" + han +"';";
        //ResultSet
        ResultSet rs = null;
        try {
            rs = c.createStatement().executeQuery(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(rs == null) {
            msg.setText("invalid info");
            return;
        }

        String passs = null;
        while (true) {
            try {
                if (!rs.next()) {
                    msg.setText("invalid info");
                    return;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                passs = rs.getString("password");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            break;
        }

        if (passs.equals(Encrypt.encrypt(password))) {
            hide_signup();
            show_facilities();
            handle.setVisible(false);
            pass.setVisible(false);
            msg.setText(han + ", you are logged in");
            sub.setText("logout");
        }
        else {
            msg.setText("invalid info");
        }

    }

    @FXML
    void supressed(ActionEvent event) {
        String hh, nn, pp, ee, ct, cc;
        hh = suh.getText();
        nn = sun.getText();
        pp = sup.getText();
        ee = sue.getText();
        ct = suct.getText();
        cc = suc.getText();

        if(hh.equals("") || nn.equals("") || pp.equals("") || ee.equals("") || ct.equals("") || cc.equals("")) {
            sumsg.setText("invalid input");
            return;
        }

        pp = utils.Encrypt.encrypt(pp);

        Connection c = sample.Main.con;

        String str = "'" + hh + "', '" + pp + "', '" + nn + "', '" + ee + "', '" + ct + "', '" + cc + "'";

        System.out.println(str);

        String SQL = "SELECT * FROM public.\"sign_up\"(" + str +");";
        //ResultSet
        ResultSet rs = null;
        try {
            rs = c.createStatement().executeQuery(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(rs == null) {
            msg.setText("invalid info");
            return;
        }

        String res = null;
        while (true) {
            try {
                if (!rs.next()) {
                    msg.setText("invalid info");
                    return;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                res = rs.getString(1);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            break;
        }

        System.out.println(res);

        if(res.equals("t")) {
            show_signup();
            sumsg.setText("registration successful");
        }
        else {
            sumsg.setText("invalid handle or email");
            return;
        }
    }

    private void show_facilities() {

    }

    private void hide_facilities() {

    }

    private void show_signup() {
        suh.setVisible(true);
        sun.setVisible(true);
        sup.setVisible(true);
        sue.setVisible(true);
        suct.setVisible(true);
        suc.setVisible(true);
        sumsg.setVisible(true);
        su.setVisible(true);

        suh.setText("");
        sun.setText("");
        sup.setText("");
        sue.setText("");
        suct.setText("");
        suc.setText("");
        sumsg.setText("");
    }

    private void hide_signup() {
        suh.setVisible(false);
        sun.setVisible(false);
        sup.setVisible(false);
        sue.setVisible(false);
        suct.setVisible(false);
        suc.setVisible(false);
        sumsg.setVisible(false);
        su.setVisible(false);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Connection c = sample.Main.con;
    }

}

