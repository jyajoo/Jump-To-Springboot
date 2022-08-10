package com.ll.exam.sbb.controller;

import com.ll.exam.sbb.domain.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PersonController {
    List<Person> personList = new ArrayList<>();

    @GetMapping("/addPerson")
    @ResponseBody
    public String addPerson(@ModelAttribute Person person) {
        personList.add(person);
        return "%d번 사람이 등록되었습니다".formatted(person.getId());
    }
}
