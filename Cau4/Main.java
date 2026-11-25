import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

interface MienGiam {
    long tinhGiam(long hocPhi);

    String getMa();
}

class ConChinhSach implements MienGiam {
    @Override
    public long tinhGiam(long hocPhi) { return hocPhi * 50 / 100; }

    @Override
    public String getMa() { return "CS"; }
}

class HoNgheo implements MienGiam {
    @Override
    public long tinhGiam(long hocPhi) { return hocPhi * 70 / 100; }

    @Override
    public String getMa() { return "HN"; }
}

class HocBong implements MienGiam {
    public static final long MUC_GIAM = 3000000L;

    @Override
    public long tinhGiam(long hocPhi) { return Math.min(MUC_GIAM, hocPhi); }

    @Override
    public String getMa() { return "HB"; }
}

abstract class SinhVien {
    public static final long DON_GIA = 450000L;

    protected String maSV;
    protected int soTinChi;
    protected MienGiam mienGiam; // co the null (khong co chinh sach)

    public SinhVien(String maSV, int soTinChi) {
        this.maSV = maSV;
        this.soTinChi = soTinChi;
    }

    public String getMaSV() { return maSV; }
    public int getSoTinChi() { return soTinChi; }
    public MienGiam getMienGiam() { return mienGiam; }
    public void setMienGiam(MienGiam mienGiam) { this.mienGiam = mienGiam; }

    public abstract String getMaHe();

    public abstract long hocPhi();

    public long soTienGiam() {
        return mienGiam == null ? 0 : mienGiam.tinhGiam(hocPhi());
    }

    public long phaiNop() {
        return hocPhi() - soTienGiam();
    }

    @Override
    public String toString() {
        return maSV + " " + getMaHe() + " " + hocPhi() + " " + soTienGiam() + " " + phaiNop();
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

class Factory {
    public static SinhVien taoSinhVien(String maHe, String maSV, int soTinChi) {
        switch (maHe) {
            case "CQ": return new SinhVienChinhQuy(maSV, soTinChi);
            case "CLC": return new SinhVienChatLuongCao(maSV, soTinChi);
            case "LT": return new SinhVienLienThong(maSV, soTinChi);
            default: return null;
        }
    }

    public static MienGiam taoMienGiam(String ma) {
        switch (ma) {
            case "CS": return new ConChinhSach();
            case "HN": return new HoNgheo();
            case "HB": return new HocBong();
            default: return null;
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder out = new StringBuilder();

        int n = Integer.parseInt(sc.next().trim());
        List<SinhVien> ds = new ArrayList<>();
        Map<String, SinhVien> theoMa = new LinkedHashMap<>();
        for (int i = 0; i < n; i++) {
            String maHe = sc.next();
            String maSV = sc.next();
            int tc = Integer.parseInt(sc.next());
            SinhVien sv = Factory.taoSinhVien(maHe, maSV, tc);
            ds.add(sv);
            theoMa.put(maSV, sv);
        }

        int m = sc.hasNext() ? Integer.parseInt(sc.next().trim()) : 0;
        for (int i = 0; i < m; i++) {
            String maSV = sc.next();
            String maCS = sc.next();
            SinhVien sv = theoMa.get(maSV);
            if (sv == null) {
                out.append("NOT FOUND ").append(maSV).append('\n');
            } else {
                sv.setMienGiam(Factory.taoMienGiam(maCS));
            }
        }

        for (SinhVien sv : ds) out.append(sv).append('\n');

        String[] cacHe = {"CQ", "CLC", "LT"};
        for (String he : cacHe) {
            int dem = 0;
            long tong = 0;
            for (SinhVien sv : ds) {
                if (sv.getMaHe().equals(he)) {
                    dem++;
                    tong += sv.phaiNop();
                }
            }
            if (dem > 0) out.append(he).append(' ').append(dem).append(' ').append(tong).append('\n');
        }
        System.out.print(out);
    }
}
