package com.daovantam.quanlydaotao.controller.student;

import com.daovantam.quanlydaotao.controller.BaseController;
import com.daovantam.quanlydaotao.entity.Student;
import com.daovantam.quanlydaotao.mapper.student.StudentMapper;
import com.daovantam.quanlydaotao.model.request.student.StudentAuthRequest;
import com.daovantam.quanlydaotao.model.request.student.StudentRequest;
import com.daovantam.quanlydaotao.model.response.BaseResponse;
import com.daovantam.quanlydaotao.model.response.student.StudentResponse;
import com.daovantam.quanlydaotao.repository.specification.StudentSpecification;
import com.daovantam.quanlydaotao.security.jwt.TokenProducer;
import com.daovantam.quanlydaotao.security.jwt.model.JwtPayload;
import com.daovantam.quanlydaotao.service.StudentService;
import com.daovantam.quanlydaotao.utils.FileUploadUtil;
import com.daovantam.quanlydaotao.utils.PDFGenerator;
import com.daovantam.quanlydaotao.utils.PasswordHasher;
import lombok.Data;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000/admin/manager-student")
@RestController
@RequestMapping("/admin")
@Data
public class StudentController extends BaseController {
    private final StudentService studentService;
    private final StudentMapper studentMapper;
    private final TokenProducer tokenProducer;

    /*private JwtPayload createPayload(Student student){
        JwtPayload jwtPayload = new JwtPayload();
        jwtPayload.setCode(student.getCode());
        jwtPayload.setId(student.getId());
        jwtPayload.setFullName(student.getFullName());
        jwtPayload.setEmail(student.getEmail());
        String role = student.getRole().getName();

        jwtPayload.setRole(role);

        return jwtPayload;
    }*/

    /*@GetMapping("/students")
    public ResponseEntity<BaseResponse> findAll(@RequestParam int index, @RequestParam int size){
        PageRequest pageRequest = PageRequest.of(index, size);

        return success(studentService.getAll(pageRequest,studentMapper::mapToResponse));
    }*/

    @GetMapping("/students")
//    @PreAuthorize("hashAnyRole('ADMIN')")
    public ResponseEntity<List<StudentResponse>> findAll(){
        return ResponseEntity.ok(studentService.findAll(studentMapper::mapToResponse));
    }

    /*@GetMapping("/student/{id}")
    public ResponseEntity<BaseResponse> findById(@PathVariable long id){
        return success(studentService.findById(id, studentMapper::mapToResponse));
    }*/

    /*@PostMapping("/auth")
    public ResponseEntity<BaseResponse> auth(@RequestBody StudentAuthRequest authRequest){
        String password = PasswordHasher.hash(authRequest.getPassword());
        authRequest.setPassword(password);

        Student student = studentService.findOne(StudentSpecification.authFilter(authRequest));
        JwtPayload jwtPayload = createPayload(student);

        return success(tokenProducer.token(jwtPayload));
    }*/

    @GetMapping("/student/{id}")
    public ResponseEntity<StudentResponse> findById(@PathVariable long id){
        return ResponseEntity.ok(studentService.findById(id, studentMapper::mapToResponse));
    }

    @PostMapping("/student")
//    @PreAuthorize("hashAnyRole('ADMIN')")
    public ResponseEntity<Void> insert(@RequestBody StudentRequest studentRequest) {
        /*if (!multipartFile.isEmpty()){
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

            studentRequest.setPhotos(fileName);
            String password = PasswordHasher.hash(studentRequest.getPassword());
            studentRequest.setPassword(password);
            studentService.save(studentRequest, studentMapper::mapToEntity);

            String uploadDir = "user-photo/" + studentRequest.getId();
            FileUploadUtil.cleanDir(uploadDir);

            FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        }else {
            if (studentRequest.getPhotos().isEmpty()) studentRequest.setPhotos(null);
            String password = PasswordHasher.hash(studentRequest.getPassword());
            studentRequest.setPassword(password);
            studentService.save(studentRequest, studentMapper::mapToEntity);
        }*/

        String password = PasswordHasher.hash(studentRequest.getPassword());
        studentRequest.setPassword(password);
        studentService.save(studentRequest, studentMapper::mapToEntity);

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PutMapping("/student/{id}")
    public ResponseEntity<Void> update(@RequestBody StudentRequest studentRequest, @PathVariable long id){
        studentRequest.setId(id);
        studentService.save(studentRequest, studentMapper::mapToEntity);

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/student/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id){
        studentService.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/students-pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> exportToPDF(){
        ByteArrayInputStream stream = PDFGenerator.exportToPDF(studentService.findAllStudent());

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Disposition","filename=students.pdf");

        return ResponseEntity.ok().header(String.valueOf(httpHeaders)).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(stream));
    }
}
