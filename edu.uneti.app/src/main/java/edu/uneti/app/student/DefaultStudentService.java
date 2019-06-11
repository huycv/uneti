package edu.uneti.app.student;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.uneti.core.shared.BusinessException;
import edu.uneti.core.student.Student;
import edu.uneti.core.student.StudentRepository;
import edu.uneti.core.student.StudentService;

@Service
public class DefaultStudentService implements StudentService {

	//** The domain repo. */
	@Autowired
	private StudentRepository domainRepo;
	
	/** Tạo mới Sinh viên */
	@Override
	@Transactional
	public void register(Student student) throws BusinessException {
		this.domainRepo.save(student);
	}

	/** Cập nhật thông tin Sinh Viên */
	@Override
	@Transactional
	public void update(Student student) throws BusinessException {
		Student oldStudent = this.domainRepo.findById(student.getId())
				.orElseThrow(() -> new BusinessException("business_exception.student.not_found"));
		
		oldStudent.setName(student.getName());
		oldStudent.setNgaySinh(student.getNgaySinh());
		oldStudent.setSex(student.getSex());
		oldStudent.setDiaChi(student.getDiaChi());
		oldStudent.setSdt(student.getSdt());
		oldStudent.setEmail(student.getEmail());
		oldStudent.setChuyenNganhId(student.getChuyenNganhId());
		oldStudent.setLopId(student.getLopId());
		oldStudent.setNgayVaoTruong(student.getNgayVaoTruong());
		this.domainRepo.save(oldStudent);
		
	}

	@Override
	@Transactional
	public Optional<Student> findByMaSV(String maSV) {
		// TODO Auto-generated method stub
		return null;
	}

}
