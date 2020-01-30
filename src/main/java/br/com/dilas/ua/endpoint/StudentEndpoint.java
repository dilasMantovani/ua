package br.com.dilas.ua.endpoint;

import br.com.dilas.ua.model.Student;
import br.com.dilas.ua.util.DateTimeUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.Arrays.asList;

@RestController
@RequestMapping("students")
public class StudentEndpoint {

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getAll(){
        return new ResponseEntity(Student.studentList, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable("id") int id){
    Student student = new Student();
    student.setId(id);
    int index = Student.studentList.indexOf(student);
    if(index == -1){//não achou

    }
    }
}
