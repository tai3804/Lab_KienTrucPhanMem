package iuh.fit;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        UIComponent btnSubmit = new Button("Gửi đơn");
        UIComponent btnCancel = new Button("Hủy");
        UIComponent inputName = new TextField("Nhập tên của bạn...");

        // Tạo Hộp thoại (Composite)
        UIContainer loginDialog = new UIContainer("Hộp thoại Đăng nhập");
        loginDialog.add(inputName);
        loginDialog.add(btnSubmit);
        loginDialog.add(btnCancel);

        // Tạo Thanh điều hướng (Composite)
        UIContainer navBar = new UIContainer("Thanh điều hướng chính");
        navBar.add(new Button("Trang chủ"));
        navBar.add(new Button("Liên hệ"));

        // Tạo Giao diện chính (Root Composite)
        UIContainer mainWindow = new UIContainer("Cửa sổ chính");
        mainWindow.add(navBar);
        mainWindow.add(loginDialog);

        // Render toàn bộ UI chỉ bằng một lệnh duy nhất
        System.out.println("--- ĐANG VẼ GIAO DIỆN NGƯỜI DÙNG ---");
        mainWindow.draw("");
    }
}