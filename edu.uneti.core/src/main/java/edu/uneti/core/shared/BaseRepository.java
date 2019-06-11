package edu.uneti.core.shared;

import java.util.List;
import java.util.Optional;

public interface BaseRepository<T> {
	
	public List<T> findAll();
	
	public Optional<T> findById(String id);
	
	public T save(T domain);
	
	public List<T> saveAll(List<T> domain);
	
	public void delete(String id);

}
