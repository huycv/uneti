package edu.uneti.core.student;

import java.util.Optional;

import edu.uneti.core.shared.BusinessException;

public interface StudentService {

	public void register(Student student) throws BusinessException;
	
	public void update(Student student) throws BusinessException;
	
	public Optional<Student> findByMaSV(String maSV);
}
