package iuh.fit;

public class VendingMachine {
    private State nomoneyState;
    private State hasMoneyState;

    private State currentState;

    public VendingMachine() {
        this.nomoneyState = new NoMoneyState();
        this.hasMoneyState = new  HasMoneyState();
        this.currentState = nomoneyState;
    }

    public void setState (State state) {
        this.currentState = state;
    }

    public void insertMoney() {
        this.currentState.insertMoney();
    }

    public void rejectMoney() {
        this.currentState.ejectMoney();
    }

    public State getNomoneyState() {
        return this.nomoneyState;
    }

    public State getHasMoneyState() {
        return this.hasMoneyState;
    }
}
