package sample;

import JDBC.Employee;
import JDBC.Inspection;
import JDBC.Transaction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

/*
TabPane ma wyświetlać tabele dla danych basenów:
po kliknięciu w dany basen z listy po lewej,
aplikacja usuwa zawartość tabel i wczytuje nowe elo
 */
public class ManagerController implements Initializable {

    //TODO pensje transakcje zyski

    @FXML
    private TableView<ManagerEmployee> employeeTable;
    @FXML
    private TableColumn<ManagerEmployee, String> employeeID;
    @FXML
    private TableColumn<ManagerEmployee, String> employeeFName;
    @FXML
    private TableColumn<ManagerEmployee, String> employeeLName;
    @FXML
    private TableColumn<ManagerEmployee, String> employeePosition;

    @FXML
    private TableView<ManagerTransaction> transactionTable;
    @FXML
    private TableColumn<ManagerTransaction, String> transactionNumber;
    @FXML
    private TableColumn<ManagerTransaction, String> transactionDate;
    @FXML
    private TableColumn<ManagerTransaction, String> transactionPrice;

    @FXML
    private TableView<ManagerIncome> incomeTable;
    @FXML
    private TableColumn<ManagerIncome, String> incomeMonth;
    @FXML
    private TableColumn<ManagerIncome, String> income;

    @FXML
    private TableView<ManagerSalary> salaryTable;
    @FXML
    private TableColumn<ManagerSalary, String> salaryMonth;
    @FXML
    private TableColumn<ManagerSalary, String> salary;

    @FXML
    private TableView<ManagerInspection>  inspectionsTable;
    @FXML
    private TableColumn<ManagerInspection, String> inspectionNumber;
    @FXML
    private TableColumn<ManagerInspection, String> inspectionDate;
    @FXML
    private TableColumn<ManagerInspection, String> inspectionComment;

    public ManagerController() throws SQLException {
    }

    private ObservableList<ManagerEmployee> getEmployees(Connection conn) throws SQLException {
        int noEmployees;
        PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) FROM Pracownicy");
        ResultSet rSet = stmt.executeQuery();

        if(rSet.next()) noEmployees = rSet.getInt(1);
        else noEmployees = 0;

        rSet.close();
        stmt.close();

        ObservableList<ManagerEmployee> list = FXCollections.observableArrayList();
        for(int i = 0; i < noEmployees; i++){
            list.add(Employee.getManagerEmployee(conn, i+1));
        }
        return list;
    }

    private ObservableList<ManagerTransaction> getTransactions(Connection conn) throws SQLException {
        int noTransactions;
        PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) FROM Transakcje");
        ResultSet rSet = stmt.executeQuery();

        if(rSet.next()) noTransactions = rSet.getInt(1);
        else noTransactions = 0;

        rSet.close();
        stmt.close();

        ObservableList<ManagerTransaction> list = FXCollections.observableArrayList();
        for(int i = 0; i < noTransactions; i++){
            list.add(Transaction.getManagerTransaction(conn, i+1));
        }
        return list;
    }

    /*private ObservableList<ManagerIncome> getIncomes(Connection conn) throws SQLException {
        int noIncomes;
        PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) FROM Pracownicy");
        ResultSet rSet = stmt.executeQuery();

        if(rSet.next()) noIncomes = rSet.getInt(1);
        else noIncomes = 0;

        rSet.close();
        stmt.close();

        ObservableList<ManagerIncome> list = FXCollections.observableArrayList();
        for(int i = 0; i < noIncomes; i++){
            list.add(Transaction.getManagerIncome(conn, i+1));
        }
        return list;
    }*/

    /*private ObservableList<ManagerSalary> getSalaries(Connection conn) throws SQLException {
        int noEmployees;
        PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) FROM Pracownicy");
        ResultSet rSet = stmt.executeQuery();

        if(rSet.next()) noEmployees = rSet.getInt(1);
        else noEmployees = 0;

        rSet.close();
        stmt.close();

        ObservableList<ManagerSalary> list = FXCollections.observableArrayList();
        for(int i = 0; i < noEmployees; i++){
            list.add(Employee.getManagerEmployee(conn, i+1));
        }
        return list;
    }*/

    private ObservableList<ManagerInspection> getInspections(Connection conn) throws SQLException {
        int noInspections;
        PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) FROM Przeglady");
        ResultSet rSet = stmt.executeQuery();

        if(rSet.next()) noInspections = rSet.getInt(1);
        else noInspections = 0;

        rSet.close();
        stmt.close();

        ObservableList<ManagerInspection> list = FXCollections.observableArrayList();
        for(int i = 0; i < noInspections; i++){
            list.add(Inspection.getManagerInspection(conn, i+1));
        }
        return list;
    }

    private final ObservableList<ManagerEmployee> employees = getEmployees(Main.jdbc.getConn());

    private final ObservableList<ManagerTransaction> transactions = getTransactions(Main.jdbc.getConn());

    //private final ObservableList<ManagerIncome> incomes = getEmployees(Main.jdbc.getConn());

    //private final ObservableList<ManagerSalary> salaries = getEmployees(Main.jdbc.getConn());

    private final ObservableList<ManagerInspection> inspections = getInspections(Main.jdbc.getConn());

    private void initializeEmployee()
    {
        employeeID.setCellValueFactory(new PropertyValueFactory<>("id"));
        employeeFName.setCellValueFactory(new PropertyValueFactory<>("name"));
        employeeLName.setCellValueFactory(new PropertyValueFactory<>("surname"));
        employeePosition.setCellValueFactory(new PropertyValueFactory<>("post"));

        employeeTable.getItems().addAll(employees);
    }

    private void initializeTransaction()
    {
        transactionNumber.setCellValueFactory(new PropertyValueFactory<>("number"));
        transactionDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        transactionPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        transactionTable.getItems().addAll(transactions);
    }

    /*private void initializeIncome()
    {
        incomeMonth.setCellValueFactory(new PropertyValueFactory<>("month"));
        income.setCellValueFactory(new PropertyValueFactory<>("income"));

        incomeTable.getItems().addAll(incomes);
    }

    private void initializeSalary()
    {
        salaryMonth.setCellValueFactory(new PropertyValueFactory<>("month"));
        salary.setCellValueFactory(new PropertyValueFactory<>("salary"));

        salaryTable.getItems().addAll(salaries);
    }*/

    private void initializeInspection()
    {
        inspectionNumber.setCellValueFactory(new PropertyValueFactory<>("number"));
        inspectionDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        inspectionComment.setCellValueFactory(new PropertyValueFactory<>("comment"));

        inspectionsTable.getItems().addAll(inspections);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initializeEmployee();
        initializeTransaction();
        //initializeIncome();
        //initializeSalary();
        initializeInspection();
    }

    // powrót do okna logowania
    public void logOutButtonPushed(ActionEvent event) throws IOException {

        Parent tableViewParent = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }
}
