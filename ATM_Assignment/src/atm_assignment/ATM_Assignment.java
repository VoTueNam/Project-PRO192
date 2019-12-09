/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atm_assignment;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Admin
 */
public class ATM_Assignment {

    static String ID = "";
    static String IDUser = "";
    public static Scanner ip = new Scanner(System.in);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException, Exception {
        // TODO code application logic here  
        TaoThuMuc("ATM");
        TaoTaiKhoan("Admin");
        menu();
    }
    //Nếu acc bị khóa return False
    static boolean Unlock(String ID){
    File f = new File("D:/ATM/KhoaACC");
    if(CheckName(ID+".txt", f)==true){
        String MoKhoa=LayLS("D:/ATM/KhoaACC/"+ID+".txt");
        if(MoKhoa!=null||MoKhoa.compareTo("0")!=0){
        Date date1=new Date();
        try {
                date1 = new SimpleDateFormat("dd/MM/yyyy").parse(MoKhoa);
            } 
            catch (Exception e) {
                return false;
            }
        Date dd= new Date();
        if(dd.compareTo(date1)<=0){
            return false;          
        }
        else{
            File ff= new File("D:/ATM/KhoaACC/"+ID+".txt");
            ff.delete();
            return true;
        } 
    } 
    }
    else{
        return true;
    }
    return false;
}
    static void menu() throws Exception {

        System.out.println("\n\n===CHÀO MỪNG ĐẾN VỚI ATM NGÂN HÀNG CHÚNG TÔI=== \n");
        do {
            System.out.println("Bạn là Admin (Quản trị viên) hay User (Người dùng) ? ");
            System.out.println("    1. ADMIN. ");
            System.out.println("    2. USER. ");
            System.out.println("    3.Kết thúc giao dịch");
            System.out.print("Nhập lựa chọn của bạn (1-2): ");
            String chon1;
            chon1 = ip.nextLine();
            switch (chon1) {
                case "1":
                    String Admin = "Admin";
                    Menu2(Admin);
                    MenuAdmin();
                    break;
                case "2":
                    String User = "User";
                    String gett = Menu2(User);
                    System.out.println("\n" + gett + "\n");
                    MenuUser(gett);
                    break;
                case "3":
                    System.out.println("\n Kết thúc giao dịch. Cám ơn bạn đã sử dụng ATM của chúng tôi !!! \n");
                    System.exit(0);
                default:
                    System.out.println("\n*** Kí tự bạn nhập không hợp lệ. Quay lại Menu ***\n");
                    ip.nextLine();
                    break;
            }
        } while (true);
    }

    static String Menu2(String Inmenu) throws Exception {
        do {
            System.out.println("\nBạn chọn " + Inmenu + ". Nhập lựa chọn của bạn: ");
            System.out.println("    1. Tiếp tục.");
            System.out.println("    2. Thoát.");
            System.out.print("Chọn: ");
            String chon2 = ip.nextLine();
            if (chon2.compareTo("1") == 0) {
                System.out.println("Tiến hành đăng nhập...");
                return TimKiemFile(Inmenu);
            } else if (chon2.compareTo("2") == 0) {
                menu();
            } else {
                System.out.println("Lựa chọn của bạn không hợp lệ !!!");
            }
            System.out.println("\n");
        } while (true);
    }

    static void MenuAdmin() throws IOException {
        System.out.println("\n*** BẠN ĐANG LÀ ADMIN **** \n");
        do {
            System.out.println("MENU");
            System.out.println("    1. Tạo tài khoản user mới. ");
            System.out.println("    2. Xem lịch sửa của User. ");
            System.out.println("    3. Xóa tài khoản User. ");
            System.out.println("    4. Thoát.");
            System.out.print("Chọn: ");
            String chon = ip.nextLine();
            switch (chon) {
                case "1":
                    System.out.println("\n === Admin tiến hành tạo tài khoản cho User ===");
                    TaoTaiKhoan("Not");
                    break;
                case "2":
                    System.out.println("\n === Admin tiền hành xem Lịch sử giao dịch của User ===\n");
                        System.out.print("Nhập ID User cần tra cứu: ");
                        String TimID=ip.nextLine();
                        File dir= new File("D:/ATM");
                        if(CheckName(TimID, dir)==true){
                            DocFile("D:/ATM/LichSu/"+TimID+"/LichSu.txt");
                        }
                        else{
                            System.out.println("\n      ID này Không tồn tại !!!");                           
                        }
                        System.out.println("\n=== Kết thúc chức năng. Enter để quay lại Menu ===\n");
                            ip.nextLine();
                    break;
                case "3":
                    System.out.println("\n === Admin tiến hành xóa tài khoản User ===");
                    System.out.println("Nhập ID của User mà bạn muốn xóa : ");
                    do {
                        String XoaID = ip.nextLine();
                        File dir1 = new File("D:/ATM/");
                        if (CheckName(XoaID, dir1) == true) {
                            System.out.println("\n **** Đã xóa **** \n");
                            File dir2 = new File("D:/ATM/" + XoaID);
                            delete(dir2);
                            System.out.println("    Enter để quay lại Menu");
                            ip.nextLine();
                            break;
                        } else {
                            System.out.println("ID này không tồn tại");
                            System.out.print("Nhập Lại: ");
                            XoaID = ip.nextLine();
                        }
                    } while (true);
                    break;
                case "4":
                    System.exit(0);
                    break;
                default:
                    System.out.println("\n*** Lựa chọn của bạn không tồn tại ***\n");
                    break;
            }
        } while (true);

    }

    static void TaoTaiKhoan(String Admin) throws FileNotFoundException, IOException {
        File file = new File("D:/ATM/" + Admin);
        if (file.exists() == false) {
            String link = null;
            if (Admin.compareTo("Admin") == 0) {
                System.out.println("Để bắt đầu chương trình ta cần có 1 tài khoản Quản trị viên.\nTiền hành tạo tài khoản Admin:");
                System.out.println("Bước 1: Tạo ID\n ID được cấp là : \"Admin\"  ");
                TaoThuMuc("ATM/" + Admin);
            } else {
                System.out.println("Bước 1: Tạo ID  ");
                Admin = ip.nextLine();
                int thoat = 1;
                File dir1 = new File("D:/ATM/");
                while (thoat != 0) {
                    if (CheckName(Admin, dir1) == true) {
                        System.out.println("\nID này đã tồn tại");
                        System.out.print("Nhập lại ID khác: ");
                        Admin = ip.nextLine();
                    } else {
                        thoat = 0;
                    }
                }
                TaoThuMuc("ATM/" + Admin);
            }
            //String TraLink ="D:/ATM"+Admin
            System.out.println("\nBước 2: Tạo mật khẩu (mật khẩu là 1 chuỗi có 4 số)");
            String passAdmin;
            do {
                passAdmin = ip.nextLine();
                String checkpass = "\\d\\d\\d\\d";
                if (Pattern.matches(checkpass, passAdmin) == false) {
                    System.out.println("Mật khẩu bạn nhập không hợp lệ. Vui lòng nhập lại: ");
                } else {
                    TaoThuMuc("ATM/" + Admin + "/pass." + passAdmin);
                    link = "D:/ATM/" + Admin + "/pass." + passAdmin;
                    break;
                }
            } while (true);
            System.out.println("*** Chúc mừng bạn đã tạo tài khoản \"" + Admin + "\" thành công. ***");
            int thoat = 1;
            do {
                System.out.println("\n Bạn có muốn cập nhật thông tin cá nhân ?");
                System.out.println("    1. Có.");
                System.out.println("    2. Để sau. ");
                System.out.print("Nhập: ");
                String chon = ip.nextLine();
                switch (chon) {
                    case "1":
                        System.out.println("Tiến hành cập nhập thông tin cá nhân");
                        CapNhatTT(link);
                        GhiFile("0", link + "Tien");
                        thoat = 0;
                        break;
                    case "2":
                        System.out.println("\nQuay lại Menu. Enter để tiếp tục");
                        GhiFile("0", link + "Tien");
                        ip.nextLine();
                        thoat = 0;
                        break;
                    default:
                        System.out.print("\n Vui lòng chỉ nhập 1 hoặc 2 !!!\n ");
                        break;
                }
            } while (thoat != 0);
        } else if (Admin.compareTo("Admin") != 0) {
            System.out.println("\n*** ID này đã tồn tại ***\n");
        }
    }
//    static void TaoFile(String link) throws FileNotFoundException{
//        FileOutputStream file = null;//Ghi vao file co dinh dang như link bên dưới !!!
//        file = new FileOutputStream(link+"/Tien.txt");
//        String chuoi="0";
//        byte[] ghivaofile = chuoi.getBytes();
//    }

    static void CapNhatTT(String link) throws FileNotFoundException, IOException {
        System.out.print("Họ và tên: ");
        String Ten = ip.nextLine();
        System.out.print("\nTuổi: ");
        int tuoi = 0;
        tuoi = CheckNumber(tuoi);
        ip.nextLine();
        System.out.print("\nĐịa chỉ: ");
        String Diachi = ip.nextLine();
        link = link + "/Ad.ThongTinCaNhan";
        System.out.println(link);
        String ghivofile = " Họ và tên: " + Ten + "\n Tuổi: " + tuoi + "\n Địa chỉ: " + Diachi;
        GhiFile(ghivofile, link);
    }

    static void GhiFile(String chuoi, String duongdan) throws FileNotFoundException, IOException {
        FileOutputStream file = null;//Ghi vao file co dinh dang như link bên dưới !!!
        file = new FileOutputStream(duongdan + ".txt");
        byte[] ghivaofile = chuoi.getBytes();
        try {
            file.write(ghivaofile);
        } catch (Exception e) {
            System.out.println("**** Lỗi và thoát ****");
        } finally {
            file.close();//Nếu chương trình lỗi thì đóng file lại
        }
    }

    static void TaoThuMuc(String TenThuMuc) {
        File dir1 = new File("D:/" + TenThuMuc);
        boolean created = dir1.mkdir();
    }

    static int CheckNumber(int n) {
        String nhap = null;
        while (true) {
            try {
                nhap = ip.nextLine();
                n = Integer.parseInt(nhap);
                if (n <= 0) {
                    System.out.println("\nSố bạn nhập không hợp lệ !!!");
                    System.out.print(" Vui lòng nhập lại: ");
                } else {
                    break;
                }
            } catch (Exception ex) {
                System.out.println("Số bạn nhập không hợp lệ !!!");
                System.out.print(" Vui lòng nhập lại: ");
            }
        }
        return n;
    }

    static int DemFile(String link) {
        int dem = 0;
        File dir = new File(link);
        File[] children = dir.listFiles();
        String[] paths = dir.list();
        for (String path : paths) {
            dem++;
        }
        return dem;
    }
    static String PassCuUser = null;

    static String TimKiemFile(String Menu) throws Exception {
        String linkUser = null;
        if (Menu.compareTo("Admin") != 0 && DemFile("D:/ATM") == 1) {
            System.out.println("\nKhông có bất kì User nào trong hệ thống !!!");
        } else {
            int dem = 5, thoat = 1;
            while (thoat != 0) {
                String abc;
                System.out.print("=== Nhập ID của bạn: ");
                do {
                    abc = ip.nextLine();
                    if (Menu.compareTo("Admin") == 0 && abc.compareTo("Admin") != 0) {
                        System.out.print("\n Sai ID vui lòng nhập lại: ");
                    } else {
                        break;
                    }
                } while (true);
                if (Menu.compareTo("Admin") != 0 && abc.compareTo("Admin") == 0) {
                    System.out.println("\nID bạn nhập không tồn tại !!!");
                } else {
                    IDUser = abc;

                    if (Unlock(abc) == false) {
                        System.out.println("\nTài khoản này đang bị khóa. Vui lòng quay lại vào ngày hôm sau !!! \n");
                        System.exit(0);
                    } else {
                        File dir = new File("D:/ATM");
                        if (CheckName(abc, dir) == true) {
                            File dir1 = new File("D:/ATM/" + abc);
                            while (thoat != 0) {
                                System.out.print("=== Nhập pass của bạn: ");
                                String xyz = "pass." + ip.nextLine();
                                if (CheckName(xyz, dir1) == true) {
                                    linkUser = "D:/ATM/" + abc + "/" + xyz;
                                    PassCuUser = xyz;
                                    thoat = 0;
                                    return linkUser;
                                } else {
                                    dem--;
                                    if (dem == 0) {
                                        Date de = new Date();
                                        String Date = de.toString();
                                        System.out.println("\n### Khóa acc rồi nha ###\n");
                                        Date dc = new Date();
                                        SimpleDateFormat sml = new SimpleDateFormat("dd/MM/yyyy");
                                        dc.setDate(dc.getDate() + 1);
                                        String dcc = sml.format(dc);
                                        KhoaAcc(abc, dcc);
                                        thoat = 0;
                                        break;
                                    }
                                    System.out.println("\nSai pass. Bạn còn " + dem + " lần nhập lại");

                                }
                            }
                        } else {
                            System.out.println("\nID bạn nhập không tồn tại !!!");
                        }
                    }
                }
            }
            if(dem==0) System.exit(0);
        }
        ip.nextLine();
        return "0";
    }
    
     static   void KhoaAcc(String ID,String Date) throws IOException{
        TaoThuMuc("ATM/KhoaACC");
        File dir=new File("D:/ATM/KhoaACC");
        GhiFile(Date,"D:/ATM/KhoaACC/"+ID);      
    }

    static boolean CheckName(String abc, File dir) {
        int c = 1;
        String[] children = dir.list();
        if (children != null) {
            for (int i = 0; i < children.length; i++) {
                String filename = children[i];
                if (children[i].compareTo(abc) == 0) {
                    c = 1;
                    return true;
                } else {
                    c = 0;
                }
            }
        }
        return false;
    }

    public static void delete(File file) throws IOException {
        // kiểm tra có phải là thư mục hay không
        if (file.isDirectory()) {
            // thư mục rỗng thì xóa ngay và luôn bằng phương thức delete() của lớp File
            if (file.list().length == 0) {
                file.delete();
//				System.out.println("Thư mục đã xóa : " + file.getAbsolutePath());
            } else {
                // tạo mảng String chứa tên các file, thư mục chứa trong thư mục cần kiểm tra
                String files[] = file.list();
                for (String temp : files) {
                    // tạo đối tượng file với tên từ mảng đã tạo chuẩn bị cho việc đệ quy xóa
                    File fileDelete = new File(file, temp);

                    // đệ quy: dùng lại phương thức xóa
                    delete(fileDelete);
                }

                // kiểm tra thư mục lại một lần nữa, nếu rỗng thì xóa
                if (file.list().length == 0) {
                    file.delete();
//					System.out.println("Thư mục đã xóa: " + file.getAbsolutePath());
                }
            }
        } else {
            // nếu là file thì xóa ngay bằng phương thức delete() của lớp File
            file.delete();
//			System.out.println("File đã được xóa: " + file.getAbsolutePath());
        }
    }

    public static void DocFile(String link) {
        try {
            //Bước 1: Tạo đối tượng luồng và liên kết nguồn dữ liệu
            File f = new File(link);
            FileReader fr = new FileReader(f);
            //Bước 2: Đọc dữ liệu
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            //Bước 3: Đóng luồng
            fr.close();
            br.close();
        } catch (Exception ex) {
            System.out.println("\n ### Thông tin chưa được cập nhật ###\n ");
        }
    }

    static int DemDong(String link) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(link)); // dem dong
            int lines = 0;
            while (reader.readLine() != null) {
                lines++;
            }
            reader.close();
            return lines;

        } catch (Exception e) {
        }
        return 0;
    }
    static int TienGui = 0;
    static int TienRut = 0;
    static int TienChuyen = 0;
    static String PassMoi = null;

    static void MenuUser(String link) throws IOException {
        int tien = 0;
        String LichSu = "";
        System.out.println("\n*** BẠN ĐANG TRUY CẬP GIAO DIỆN MENU NGƯỜI DÙNG **** \n");
        File dir = new File(link);
        File dir1 = new File("D:/ATM/LichSu/" + IDUser);
        if (CheckName("Tien.txt", dir) == true) {
            tien = LayTien(link + "/Tien.txt");
        } else {
            GhiFile("0", link + "/Tien");//Tạo file Tien.txt và ghi 0 đồng vào nó               
            tien = 0;
        }
        if (CheckName("LichSu.txt", dir1) == true) {
            LichSu = LayLS("D:/ATM/LichSu/" + IDUser + "/LichSu.txt");
        } else {
            TaoThuMuc("ATM/LichSu");
            TaoThuMuc("ATM/LichSu/" + IDUser);
            String IDuser = IDUser;
            GhiFile("   Day la lich su Giao Dich cua \"" + IDuser + "\"    ", "D:/ATM/LichSu/" + IDUser + "/LichSu");//Tạo file LS.txt và ghi khoảng trắng vào nó               
            LichSu = LayLS("D:/ATM/LichSu/" + IDUser + "/LichSu.txt");
        }
        int temp = 0;
        String chon;
        do {
            temp = 0;
            System.out.println("Menu.");
            System.out.println("    1. Gửi tiền");
            System.out.println("    2. Rút tiền");
            System.out.println("    3. Xem số dư hiện tại");
            System.out.println("    4. Chuyển tiền");
            System.out.println("    5. Xem thông tin cá nhân");
            System.out.println("    6. Thay đổi mật khẩu");
            System.out.println("    7. Thoát");
            System.out.print("Nhập lựa chọn của bạn: ");
            chon = ip.nextLine();
            switch (chon) {
                case "1":
                    tien = guiTien(tien);
                    LichSu = LichSu + "\nGui tien: " + TienGui + " dong, Ngay: " + Ngay();
                    break;
                case "2":
                    if (tien == 0) {
                        System.out.println("\n### Tài khoàn bạn không đủ tiền để thực hiện chức năng này !!! ###\n");
                    } else {
                        tien = rutTien(tien);
                        LichSu = LichSu + "\nRut tien: " + TienRut + " dong, Ngay: " + Ngay();
                    }
                    break;
                case "3":
                    System.out.println(tien);
                    break;
                case "4":
                    if (tien == 0) {
                        System.out.println("\n### Tài khoàn bạn không đủ tiền để thực hiện chức năng này !!! ###\n");
                    } else {
                        tien = chuyenTien(tien, link);
                        LichSu = LichSu + "\nChuyen tien: " + TienChuyen + " dong qua ID \"" + ID + "\", Ngay: " + Ngay();
                    }
                    break;
                case "5":
                    System.out.println("\n=== Tiến hành xem thông tin cá nhân ===\n");
                    System.out.println(link);
                    DocFile(link + "/Ad.ThongTinCaNhan.txt");
                    break;
                case "6":
                    System.out.println("\n=== Tiến hành thay đổi pass của bạn ===\n");
                    System.out.print("Nhập mật khẩu hiện tại cùa bạn: ");
                    String oldPass = ip.nextLine();
                    String oldPassFake = "pass." + oldPass;
                    File Clink = new File("D:/ATM/" + IDUser);
                    if (CheckName(oldPassFake, Clink) == true) {
                        String newPass;
                        do {
                            System.out.print("Nhập mật khẩu mới: ");
                            do {
                                newPass = ip.nextLine();
                                String checkpass = "\\d\\d\\d\\d";
                                if (Pattern.matches(checkpass, newPass) == false) {
                                    System.out.println("Mật khẩu bạn nhập không hợp lệ. Vui lòng nhập lại: ");
                                } else {
                                    break;
                                }
                            } while (true);
                            if (newPass.compareTo(oldPass) == 0) {
                                System.out.println("\n ### Không được nhập trùng với mật khẩu cũ ###\n");
                            } else {
                                System.out.print("Xác nhận lại mật khẩu mới:");
                                String newPass2 = ip.nextLine();
                                if (newPass2.compareTo(newPass) == 0) {
                                    newPass = "pass." + newPass;
                                    File cu = new File("D:/ATM/" + IDUser + "/" + oldPassFake);
                                    File moi = new File("D:/ATM/" + IDUser + "/" + newPass);
                                    System.out.println(cu.renameTo(moi));
                                    PassMoi = newPass;
                                    System.out.println("\n=== Đổi mật khẩu thành công !!! Bấm Enter để quay lại Menu ===\n");
                                    ip.nextLine();
                                    break;
                                } else {
                                    System.out.println("Mật khẩu không khớp !!! Nhập lại Mật khẩu mới \n");
                                }
                            }
                        } while (true);
                    } else {
                        System.out.println("Sai mật khẩu !!!");
                        System.out.println("\n=== Kết thúc phiên đổi mật khẩu. Enter để quay lại Menu ===\n");
                        ip.nextLine();
                    }
                    break;
                case "7":
                    String tienGhi = tien + "";
                    System.out.println(tienGhi);
                    if (PassMoi == null) {
                        PassMoi = "";
                    } else if (PassMoi.compareTo(PassCuUser) == 0) {
                        GhiFile(tienGhi, "D:/ATM/" + IDUser + "/" + PassCuUser + "/Tien");
                    } else {
                        GhiFile(tienGhi, "D:/ATM/" + IDUser + "/" + PassMoi + "/Tien");
                    }
                    String LSGhi = "D:/ATM/LichSu/" + IDUser + "/LichSu";
                    System.out.println(LichSu);
                    GhiFile(LichSu, LSGhi);
                    temp = 1;
                    ID = "";
                    IDUser = "";
                    PassMoi = null;
                    PassCuUser = null;
                    break;
                default:
                    temp = 0;
            }
        } while (temp != 1);
    }

    static String Ngay() {
        SimpleDateFormat ac = new SimpleDateFormat("dd/MM/yyyy");
        Date d = new Date();
        String Date = ac.format(d);
        return Date;
    }

    static int LayTien(String link) {

        int i = 0;
        try {
            //Bước 1: Tạo đối tượng luồng và liên kết nguồn dữ liệu
            FileInputStream fis = new FileInputStream(link);
            DataInputStream dis = new DataInputStream(fis);
            //Bước 2: Đọc dữ liệu
            String n = dis.readLine();
            i = Integer.parseInt(n);
            //Bước 3: Đóng luồng
            fis.close();
            dis.close();
            //Hiển thị nội dung đọc từ file
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return i;
    }

    static String LayLS(String link) {
        String LichSu = null;
        int cc = DemDong(link) - 1;
        try {
            //Bước 1: Tạo đối tượng luồng và liên kết nguồn dữ liệu
            FileInputStream fis = new FileInputStream(link);
            DataInputStream dis = new DataInputStream(fis);
            //Bước 2: Đọc dữ liệu
            LichSu = dis.readLine();
            for (int i = 0; i < cc; i++) {
                LichSu = LichSu + "\n" + dis.readLine();
                cc--;
            }
            //Bước 3: Đóng luồng
            fis.close();
            dis.close();
            //Hiển thị nội dung đọc từ file
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return LichSu;
    }

    static int chuyenTien(int tien, String link) throws IOException {
        TienChuyen = 0;
        System.out.println("\n=== Tiến hành chuyển tiền ===\n");
        System.out.println("Số tiền hiện tại của bạn là: " + tien);
        while (true) {
            System.out.println("\n Nhập ID người thụ hưởng: ");
            ID = ip.nextLine();
            File dir = new File("D:/ATM");
            if (CheckName(ID, dir) == true) {
                if (ID.compareTo("Admin") == 0) {
                    System.out.println("Bạn không thể chuyển tiền đến tài khoản này !!!");
                } else if (ID.compareTo(IDUser) == 0) {
                    System.out.println("Bạn không thể chuyển tiền cho chính mình !!!");
                } else {
                    break;
                }
            } else {
                System.out.println("Tài khoản này không tồn tại !!!");
            }
        }
        System.out.print("Nhập số tiền bạn muốn chuyển: ");
        do {
            TienChuyen = CheckNumber(TienChuyen);
            if (TienChuyen <= 0) {
                System.out.println("Số bạn nhập không hợp lệ. Nhập lại: ");
            } else if (TienChuyen > tien) {
                System.out.println("Số bạn nhập vượt quá số tiền hiện tại. Nhập lại: ");
            } else {
                break;
            }
        } while (true);
        tien = tien - TienChuyen;
        int tienID = 0;
        File dir = new File("D:/ATM/" + ID);
        String TromPass = CuopPass();
        if (CheckName("Tien.txt", dir) == true) {
            tienID = LayTien("D:/ATM/" + ID + "/" + TromPass + "/Tien.txt");
        } else {
            GhiFile("0", "D:/ATM/" + ID + "/" + TromPass + "/Tien");//Tạo file Tien.txt và ghi 0 đồng vào nó               
            tienID = 0;
        }
        tienID = tienID + TienChuyen;
        String ChuyenTienID = tienID + "";
        GhiFile(ChuyenTienID, "D:/ATM/" + ID + "/" + TromPass + "/Tien");
        //Tien này ghi vô file
        System.out.println("Số tiền còn lại trong tài khoảng là: " + tien);
        System.out.println("\nGiao dịch thành công !!! Enter để quay lại Menu\n");
        ip.nextLine();
        return tien;
    }

    static String CuopPass() {
        File dir = new File("D:/ATM/" + ID);
        String[] paths = dir.list();
        for (String path : paths) {
            return path;
        }
        return "";
    }

    static int rutTien(int tien) {
        TienRut = 0;
        System.out.println("\n=== Tiến hành rút tiền ===\n");
        System.out.println("Số tiền hiện tại của bạn là: " + tien);
        System.out.print("Nhập số tiền bạn muốn rút: ");
        do {
            TienRut = CheckNumber(TienRut);
            if (TienRut <= 0) {
                System.out.println("Số bạn nhập không hợp lệ. Nhập lại: ");
            } else if (TienRut > tien) {
                System.out.println("Số bạn nhập vượt quá số tiền hiện tại. Nhập lại: ");
            } else {
                break;
            }
        } while (true);
        tien = tien - TienRut;
        System.out.println("Số tiền còn lại trong tài khoảng là: " + tien);
        System.out.println("\nGiao dịch thành công !!! Enter để quay lại Menu\n");
        ip.nextLine();
        return tien;
    }

    static int guiTien(int tien) {
        TienGui = 0;
        System.out.println("\n=== Tiến hành gửi tiền ===\n");
        System.out.println("Số tiền hiện tại của bạn là: " + tien);
        System.out.print("Nhập số tiền bạn muốn gửi: ");
        do {
            TienGui = CheckNumber(TienGui);
            if (TienGui <= 0) {
                System.out.println("Số bạn nhập không hợp lệ. Nhập lại: ");
            } else {
                break;
            }
        } while (true);
        tien = tien + TienGui;
        System.out.println("Số tiền hiện có trong tài khoảng là: " + tien);
        System.out.println("\nGiao dịch thành công !!! Enter để quay lại Menu\n");
        ip.nextLine();
        return tien;
    }
}
