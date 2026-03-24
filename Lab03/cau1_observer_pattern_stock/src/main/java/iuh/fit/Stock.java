package iuh.fit;

import java.util.ArrayList;
import java.util.List;

class Stock {
    private String name;
    private double price;
    private List<Observer> observers = new ArrayList<>();

    public Stock(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void setPrice(double newPrice) {
        System.out.println("\n[Hệ thống] Giá cổ phiếu " + name + " thay đổi: " + price + " -> " + newPrice);
        this.price = newPrice;
        notifyObservers();
    }

    private void notifyObservers() {
        for (Observer o : observers) {
            o.update("Cổ phiếu " + name + " hiện có giá mới là: " + price);
        }
    }
}
