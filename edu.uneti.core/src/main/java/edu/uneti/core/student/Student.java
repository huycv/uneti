package edu.uneti.core.student;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Student {

	private String id;
	private int maSV;
	private String name;
	private LocalDate ngaySinh;
	private Sex sex;
	private String diaChi;
	private String sdt;
	private String email;
	private String chuyenNganhId;
	private String lopId;
	private LocalDate ngayVaoTruong;
	
	public void update(String name, LocalDate ngaySinh, Sex sex, String diaChi, String sdt, String email, String chuyenNganhId, String lopId, LocalDate ngayVaoTruong) {
		this.name = name;
		this.ngaySinh = ngaySinh;
		this.sex = sex;
		this.diaChi = diaChi;
		this.sdt = sdt;
		this.email = email;
		this.chuyenNganhId = chuyenNganhId;
		this.lopId = lopId;
		this.ngayVaoTruong = ngayVaoTruong;
	}
}
