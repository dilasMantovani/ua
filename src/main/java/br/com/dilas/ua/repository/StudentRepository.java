package br.com.dilas.ua.repository;

import br.com.dilas.ua.model.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

//public interface StudentRepository extends CrudRepository<Student, Long> {
public interface StudentRepository extends PagingAndSortingRepository<Student, Long> {
    List<Student> findByNameIgnoreCaseContaining(String name);
}
