package iuh.fit;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
// 1. Khởi tạo các "Người quan sát" (Nhà đầu tư / Thành viên dự án)
        User tai = new User("Thành Tài");
        User partner = new User("Bạn đồng hành");

        // 2. Test trường hợp Cổ phiếu
        System.out.println("--- TEST: THEO DÕI CỔ PHIẾU ---");
        Stock vcb = new Stock("VCB", 90.5);
        vcb.addObserver(tai);      // Tài theo dõi mã VCB
        vcb.addObserver(partner);  // Bạn đồng hành cũng theo dõi

        vcb.setPrice(92.0); // Cả hai cùng nhận thông báo

        // 3. Test trường hợp Quản lý công việc (Task)
        System.out.println("\n--- TEST: QUẢN LÝ CÔNG VIỆC ---");
        Task devTask = new Task("Triển khai Microservices cho Cab Booking");
        devTask.subscribe(tai); // Chỉ có Tài theo dõi task này

        devTask.setStatus("In Progress");
        devTask.setStatus("Done");
    }
}