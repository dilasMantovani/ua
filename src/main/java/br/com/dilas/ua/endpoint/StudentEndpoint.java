package br.com.dilas.ua.endpoint;

import br.com.dilas.ua.error.CustomErrorType;
import br.com.dilas.ua.error.ResourceNotFoundException;
import br.com.dilas.ua.model.Student;
import br.com.dilas.ua.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("students")
public class StudentEndpoint {

    private final StudentRepository dao;

    @Autowired
    public StudentEndpoint(StudentRepository dao) {
        this.dao = dao;
    }

    //com essa paginação da pra acessar cada parte do request da seguinte forma:
    //http://localhost:8080/students?page=0 | http://localhost:8080/students?page=1 | etc
    //é possível setar a quantidade de itens que vc quer assim:
    // http://localhost:8080/students?page=0&size=5

    //para ordenar:
    // http://localhost:8080/students?sort=name,asc | http://localhost:8080/students?sort=name,desc
    @GetMapping
    public ResponseEntity<?> getAll(Pageable pageable){
        return new ResponseEntity(dao.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable("id") Long id){
        verifyIfStudentExists(id);
        return new ResponseEntity<>(dao.findById(id), HttpStatus.OK);
    }

    @GetMapping(path = "/findByName/{name}")
    public ResponseEntity<?> getStudentByName(@PathVariable("name") String name){
        return new ResponseEntity<>(dao.findByNameIgnoreCaseContaining(name), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody Student student){
        dao.save(student);
        return new ResponseEntity(student, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestBody Student student){
        verifyIfStudentExists(student.getId());
        dao.delete(student);
        return new ResponseEntity(HttpStatus.OK);
    }
    @PutMapping
    public ResponseEntity<?> update(@RequestBody Student student){
        verifyIfStudentExists(student.getId());
        dao.save(student);
        return new ResponseEntity(HttpStatus.OK);
    }

    private void verifyIfStudentExists(Long id){
        if(!dao.findById(id).isPresent()){
            throw new ResourceNotFoundException("Student not found for id: "+id);
        }
    }
}
