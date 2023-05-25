package com.example.bookapp.apies;

import com.example.bookapp.entities.Student;
import com.example.bookapp.services.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/students/{groupId}")
@RequiredArgsConstructor
public class StudentApi {

    private final StudentService service;


    @GetMapping
    public String findAll(Model model,
                          @PathVariable("groupId") Long groupId) {
        model.addAttribute("students", service.getAllStudentByGroupId(groupId));
        return "studentMainPage";
    }

    @GetMapping("/new")
    public String createStudent(Model model, @PathVariable("groupId") Long groupId) {
        model.addAttribute("newStudent", new Student());
        model.addAttribute("groupId", groupId);
        return "/newStudent";
    }


    @PostMapping("/save")
    public String saveStudent(@PathVariable("groupId") Long groupId,
                              @ModelAttribute("newStudent") Student student) {
        service.save(groupId, student);
        return "redirect:/api/students/{groupId}";
    }

    @GetMapping("/edit-student/{studentId}")
    private String edit(@PathVariable Long studentId,
                        Model model) {
//        Student student = service.getById(studentId);
        model.addAttribute("studentId", service.getById(studentId));
        model.addAttribute("editStudent", new Student());
        return "editStudent";
    }

    @PatchMapping("/updateStudent/{studentId}")
    public String update(@PathVariable("studentId") Long studentId,
                         @ModelAttribute("student") Student student) {
        service.updateStudent(studentId, student);
        return "redirect:/api/students/{groupId} ";
    }

//    @DeleteMapping("/{studentId}/{id}/deleteStudent")
//    public String deleteStudent(@PathVariable("id")Long id,
//                                @PathVariable("studentId")Long studentId){
//        service.deleteStudentById(studentId);
//        return "redirect:/allStudents/ "+id;
//    }
}
