public class bia {

    // Bien thanh vien

    public String nhanbia;
    public String lapbia;
    public String chatlong;

    // Phương thuc hoat dong: Methods: operation of object

    public String layNhanBia() {
        return nhanbia;
    }

    public String ganNhanBia(String nhan) {
        nhanbia = nhan;

        return nhanbia;
    }

    // khởi tạo
    // constructor

    bia() {
    }

    bia(String nhan, String lap, String chatLong) {
        nhanbia = nhan;
        lapbia = lap;
        chatlong = chatLong;

    }

    bia(String lap) {
        lapbia = lap;
    }

}