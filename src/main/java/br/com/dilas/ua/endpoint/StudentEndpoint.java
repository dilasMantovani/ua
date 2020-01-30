package br.com.dilas.ua.endpoint;

import br.com.dilas.ua.error.CustomErrorType;
import br.com.dilas.ua.error.ResourceNotFoundException;
import br.com.dilas.ua.model.Student;
import br.com.dilas.ua.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping
    public ResponseEntity<?> getAll(){
        return new ResponseEntity(dao.findAll(), HttpStatus.OK);
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
