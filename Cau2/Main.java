import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

abstract class SinhVien {
    public static final long DON_GIA = 450000L;

    protected String maSV;
    protected int soTinChi;

    public SinhVien(String maSV, int soTinChi) {
        this.maSV = maSV;
        this.soTinChi = soTinChi;
    }

    public String getMaSV() { return maSV; }
    public int getSoTinChi() { return soTinChi; }

    public abstract String getMaHe();

    public abstract long hocPhi();

    @Override
    public String toString() {
        return maSV + " " + getMaHe() + " " + hocPhi();
    }
}

class SinhVienChinhQuy extends SinhVien {
    public SinhVienChinhQuy(String maSV, int soTinChi) { super(maSV, soTinChi); }

    @Override
    public String getMaHe() { return "CQ"; }

    @Override
    public long hocPhi() { return soTinChi * DON_GIA; }
}

class SinhVienChatLuongCao extends SinhVien {
    public SinhVienChatLuongCao(String maSV, int soTinChi) { super(maSV, soTinChi); }

    @Override
    public String getMaHe() { return "CLC"; }

    @Override
    public long hocPhi() { return soTinChi * DON_GIA * 3 / 2; } // x 1,5
}

class SinhVienLienThong extends SinhVien {
    public static final long PHI_QUAN_LY = 2000000L;

    public SinhVienLienThong(String maSV, int soTinChi) { super(maSV, soTinChi); }

    @Override
    public String getMaHe() { return "LT"; }

    @Override
    public long hocPhi() { return soTinChi * DON_GIA + PHI_QUAN_LY; }
}

class SinhVienFactory {
    public static SinhVien tao(String maHe, String maSV, int soTinChi) {
        switch (maHe) {
            case "CQ": return new SinhVienChinhQuy(maSV, soTinChi);
            case "CLC": return new SinhVienChatLuongCao(maSV, soTinChi);
            case "LT": return new SinhVienLienThong(maSV, soTinChi);
            default: return null;
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.next().trim());
        List<SinhVien> ds = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String maHe = sc.next();
            String maSV = sc.next();
            int tc = Integer.parseInt(sc.next());
            ds.add(SinhVienFactory.tao(maHe, maSV, tc));
        }
        StringBuilder out = new StringBuilder();
        for (SinhVien sv : ds) out.append(sv).append('\n');
        System.out.print(out);
    }
}
