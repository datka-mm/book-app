package com.example.bookapp.apies;

import com.example.bookapp.entities.Group;
import com.example.bookapp.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/groups")
public class GroupApi {

    private final GroupService service;

    @Autowired
    public GroupApi(GroupService service) {
        this.service = service;
    }

    // get all
    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("groups", service.findAll());
        return "getAllGroups";
    }

    // get by id
//    @GetMapping("/find/{id}")
//    public String findByID(@PathVariable Long id,
//                           Model model) {
//        model.addAttribute(service.findByID(id));
//        return "/coursePage";
//    }


    // save
    @GetMapping("/new")
    public String createGroup(Model model) {
        model.addAttribute("newGroup", new Group());
        return "newGroup";
    }

    @PostMapping("/save")
    public String saveGroup(@ModelAttribute("newGroup") Group group) {
        service.save(group);
        return "redirect:/api/groups";
    }

    // update
    @GetMapping("/edit/{id}")
    public String editGroup(@PathVariable Long id,
                            Model model) {
        model.addAttribute("id", service.findByID(id));
        model.addAttribute("editGroup", new Group());
        return "editGroup";
    }

    @PatchMapping("/update/{id}")
    public String updateGroup(@PathVariable Long id,
                              @ModelAttribute("editGroup") Group group) {
        service.updateGroup(id, group);
        return "redirect:/api/groups";
    }

    // delete
    @DeleteMapping("/delete/{id}")
    public String deleteGroup(@PathVariable Long id) {
        service.deleteById(id);
        return "redirect:/api/groups";
    }


}
