package iuh.fit;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        VendingMachine machine = new VendingMachine();

        machine.insertMoney();
        machine.setState(new HasMoneyState());
        machine.insertMoney();
        machine.rejectMoney();
    }
}