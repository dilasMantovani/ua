package br.com.dilas.ua.endpoint;

import br.com.dilas.ua.error.CustomErrorType;
import br.com.dilas.ua.model.Student;
import br.com.dilas.ua.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    Student student = dao.findById(id).get();
    if(student == null){//não achou
        return new ResponseEntity<>(new CustomErrorType("no student found"), HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Student student){
        dao.save(student);
        return new ResponseEntity(student, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestBody Student student){
        dao.delete(student);
        return new ResponseEntity(HttpStatus.OK);
    }
    @PutMapping
    public ResponseEntity<?> update(@RequestBody Student student){
        dao.save(student);
        return new ResponseEntity(HttpStatus.OK);
    }
}
