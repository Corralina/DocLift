package com.company.controllers.document;

import com.company.domian.User;
import com.company.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
@RequestMapping("document")
public class DocumentController {

    @Autowired
    public DocumentService documentService;


    @GetMapping
    public String list(
            @AuthenticationPrincipal User user,
            @RequestParam(value = "ds", required = false) String dataStart,
            @RequestParam(value = "df", required = false) String dataFinish,
            @RequestParam(value = "n", required = false) String number,
            @RequestParam(value = "a", required = false) String autor,
            @RequestParam(value = "s", required = false) String status,
            Model model
    ){
        model.addAttribute("ds", dataStart);
        model.addAttribute("df", dataFinish);
        model.addAttribute("n", number);
        model.addAttribute("a", autor);
        model.addAttribute("s", status);
        model.addAttribute("user", user);
        if (autor == null) {
            autor = "";
        }
        if (number == null) {
            number = "";
        }
        if (status == null || status.equals("all")) {
            if (dataStart == null && dataFinish == null && number == "" && autor == "") {
                model.addAttribute("documents", documentService.all());
            } else if (dataStart == null && dataFinish == null) {
                model.addAttribute("documents", documentService.filterNoDate(number, autor));
            } else if (dataStart == dataFinish || dataStart == null || dataFinish == null) {
                if (dataStart == null) {
                    model.addAttribute("documents", documentService.filterOneDate(number, autor, LocalDate.parse(dataFinish).plusDays(1)));
                } else {
                    model.addAttribute("documents", documentService.filterOneDate(number, autor, LocalDate.parse(dataStart).plusDays(1)));
                }
            } else {
                model.addAttribute("documents", documentService.filterAll(number, autor, LocalDate.parse(dataStart), LocalDate.parse(dataFinish).plusDays(2)));
            }
        }else{
            boolean st = false;
            if (status.equals("wr")){
                st = true;
            }
            if (dataStart == null && dataFinish == null && number == "" && autor == "") {
                model.addAttribute("documents", documentService.allStatus(st));
            } else if (dataStart == null && dataFinish == null) {
                model.addAttribute("documents", documentService.filterNoDateStatus(number, autor, st));
            } else if (dataStart == dataFinish || dataStart == null || dataFinish == null) {
                if (dataStart == null) {
                    model.addAttribute("documents", documentService.filterOneDateStatus(number, autor, LocalDate.parse(dataFinish).plusDays(1), st));
                } else {
                    model.addAttribute("documents", documentService.filterOneDateStatus(number, autor, LocalDate.parse(dataStart).plusDays(1), st));
                }
            } else {
                model.addAttribute("documents", documentService.filterAllStatus(number, autor, LocalDate.parse(dataStart), LocalDate.parse(dataFinish).plusDays(2), st));
            }

        }
        return "document/list";

    }




}
