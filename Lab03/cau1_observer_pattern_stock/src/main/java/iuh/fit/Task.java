package iuh.fit;

import java.util.ArrayList;
import java.util.List;

class Task {
    private String taskName;
    private String status;
    private List<Observer> observers = new ArrayList<>();

    public Task(String taskName) {
        this.taskName = taskName;
        this.status = "Todo";
    }

    public void subscribe(Observer observer) {
        observers.add(observer);
    }

    public void setStatus(String newStatus) {
        System.out.println("\n[Hệ thống] Task '" + taskName + "' chuyển trạng thái: " + status + " -> " + newStatus);
        this.status = newStatus;
        for (Observer o : observers) {
            o.update("Task '" + taskName + "' đã cập nhật trạng thái mới: " + status);
        }
    }
}