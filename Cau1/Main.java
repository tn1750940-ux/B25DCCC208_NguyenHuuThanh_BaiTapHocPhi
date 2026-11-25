import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class SinhVien {
    private String maSV;
    private String hoTen;
    private String ngaySinh;

    public SinhVien(String maSV, String hoTen, String ngaySinh) {
        this.maSV = maSV.trim();
        this.hoTen = chuanHoaTen(hoTen);
        this.ngaySinh = chuanHoaNgay(ngaySinh);
    }

    private static String chuanHoaTen(String s) {
        String[] words = s.trim().toLowerCase().split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (String w : words) {
            if (w.isEmpty()) continue;
            if (sb.length() > 0) sb.append(' ');
            sb.append(Character.toUpperCase(w.charAt(0))).append(w.substring(1));
        }
        return sb.toString();
    }

    private static String chuanHoaNgay(String s) {
        String[] p = s.trim().split("/");
        String d = p[0].trim();
        String m = p[1].trim();
        String y = p[2].trim();
        if (d.length() < 2) d = "0" + d;
        if (m.length() < 2) m = "0" + m;
        return d + "/" + m + "/" + y;
    }

    public String getMaSV() { return maSV; }
    public String getHoTen() { return hoTen; }
    public String getNgaySinh() { return ngaySinh; }

    @Override
    public String toString() {
        return maSV + " " + hoTen + " " + ngaySinh;
    }
}

public class Main {
    private static String nextNonEmpty(BufferedReader br) throws IOException {
        String line;
        while ((line = br.readLine()) != null) {
            if (!line.trim().isEmpty()) return line;
        }
        return null;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in, "UTF-8"));
        int n = Integer.parseInt(nextNonEmpty(br).trim());
        List<SinhVien> ds = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String ma = nextNonEmpty(br);
            String ten = nextNonEmpty(br);
            String ngay = nextNonEmpty(br);
            ds.add(new SinhVien(ma, ten, ngay));
        }
        StringBuilder out = new StringBuilder();
        for (SinhVien sv : ds) out.append(sv).append('\n');
        System.out.print(out);
    }
}
