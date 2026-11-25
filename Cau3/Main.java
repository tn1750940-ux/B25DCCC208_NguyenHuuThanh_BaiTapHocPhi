import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

class DuLieuKhongHopLeException extends Exception {
    private final String maSV;

    public DuLieuKhongHopLeException(String maSV, String message) {
        super(message);
        this.maSV = maSV;
    }

    public String getMaSV() { return maSV; }
}

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

class QuanLyHocPhi {
    private final List<SinhVien> danhSach = new ArrayList<>();
    private final Set<String> maDaCo = new HashSet<>();

    /** Tao sinh vien tu 3 truong du lieu, nem ngoai le neu khong hop le. */
    public void them(String maHe, String maSV, String soTinChiStr) throws DuLieuKhongHopLeException {
        if (!maHe.equals("CQ") && !maHe.equals("CLC") && !maHe.equals("LT")) {
            throw new DuLieuKhongHopLeException(maSV, "Ma he khong hop le");
        }
        int tc;
        try {
            tc = Integer.parseInt(soTinChiStr);
        } catch (NumberFormatException e) {
            throw new DuLieuKhongHopLeException(maSV, "So tin chi khong phai so nguyen");
        }
        if (tc < 1 || tc > 30) {
            throw new DuLieuKhongHopLeException(maSV, "So tin chi ngoai doan [1, 30]");
        }
        if (maDaCo.contains(maSV)) {
            throw new DuLieuKhongHopLeException(maSV, "Trung ma sinh vien");
        }
        SinhVien sv;
        switch (maHe) {
            case "CQ": sv = new SinhVienChinhQuy(maSV, tc); break;
            case "CLC": sv = new SinhVienChatLuongCao(maSV, tc); break;
            default: sv = new SinhVienLienThong(maSV, tc); break;
        }
        danhSach.add(sv);
        maDaCo.add(maSV);
    }

    public List<SinhVien> sapXep() {
        List<SinhVien> kq = new ArrayList<>(danhSach);
        kq.sort((a, b) -> {
            int c = Long.compare(b.hocPhi(), a.hocPhi());
            if (c != 0) return c;
            return a.getMaSV().compareTo(b.getMaSV());
        });
        return kq;
    }

    public long tongHocPhi() {
        long tong = 0;
        for (SinhVien sv : danhSach) tong += sv.hocPhi();
        return tong;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.next().trim());
        QuanLyHocPhi ql = new QuanLyHocPhi();
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < n; i++) {
            String maHe = sc.next();
            String maSV = sc.next();
            String tc = sc.next();
            try {
                ql.them(maHe, maSV, tc);
            } catch (DuLieuKhongHopLeException e) {
                out.append("INVALID ").append(e.getMaSV()).append('\n');
            }
        }
        for (SinhVien sv : ql.sapXep()) out.append(sv).append('\n');
        out.append("TONG ").append(ql.tongHocPhi()).append('\n');
        System.out.print(out);
    }
}
