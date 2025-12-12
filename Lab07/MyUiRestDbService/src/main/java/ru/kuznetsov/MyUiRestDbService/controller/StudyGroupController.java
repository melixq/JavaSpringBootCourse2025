package ru.kuznetsov.MyUiRestDbService.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import ru.kuznetsov.MyUiRestDbService.dao.StudyGroupRepository;
import ru.kuznetsov.MyUiRestDbService.entity.StudyGroup;

import java.util.Optional;

@Slf4j
@RestController
public class StudyGroupController {
    private final StudyGroupRepository studyGroupRepository;

    @Autowired
    public StudyGroupController(StudyGroupRepository studyGroupRepository) {
        this.studyGroupRepository = studyGroupRepository;
    }

    @GetMapping("/listStudyGroups")
    public ModelAndView getAllStudyGroups(){
        var mav = new ModelAndView("list-study-groups");
        mav.addObject("studyGroups", studyGroupRepository.findAll());
        return mav;
    }

    @GetMapping("/addStudyGroupForm")
    public ModelAndView addStudyGroupForm(){
        var mav = new ModelAndView("add-study-group-form");
        var studyGroup = new StudyGroup();
        mav.addObject("studyGroup", studyGroup);
        return mav;
    }

    @PostMapping("/saveStudyGroup")
    public RedirectView saveStudyGroup(@ModelAttribute StudyGroup studyGroup){
        studyGroupRepository.save(studyGroup);
        return new RedirectView("listStudyGroups");
    }

    @GetMapping("/showUpdateStudyGroupForm")
    public ModelAndView showUpdateForm(@RequestParam Long studyGroupId) {
        var mav = new ModelAndView("add-study-group-form");
        Optional<StudyGroup> optionalStudyGroup = studyGroupRepository.findById(studyGroupId);
        var studyGroup = new StudyGroup();

        if (optionalStudyGroup.isPresent())
            studyGroup = optionalStudyGroup.get();

        mav.addObject("studyGroup", studyGroup);
        return mav;
    }

    @GetMapping("/deleteStudyGroup")
    public RedirectView deleteStudyGroup(@RequestParam Long studyGroupId, ModelAndView model) {
        studyGroupRepository.deleteById(studyGroupId);
        return new RedirectView("listStudyGroups");
    }
}
