package com.company.controllers.document;

import com.company.dataService.DataToday;
import com.company.domian.Document;
import com.company.domian.User;
import com.company.fileService.SaveFileDocument;
import com.company.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.security.RolesAllowed;
import javax.jws.soap.SOAPBinding;
import javax.management.relation.Role;
import javax.validation.Valid;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Map;

@Controller
@RequestMapping("document")
public class DocumentController {

    @Autowired
    public DocumentService documentService;

    @Autowired
    private SaveFileDocument saveFileDocument;

    @Autowired
    private DataToday dataToday;

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
                    model.addAttribute("documents", documentService.filterOneDate(number, autor, LocalDate.parse(dataFinish)));
                } else {
                    model.addAttribute("documents", documentService.filterOneDate(number, autor, LocalDate.parse(dataStart)));
                }
            } else {
                model.addAttribute("documents", documentService.filterAll(number, autor, LocalDate.parse(dataStart), LocalDate.parse(dataFinish)));
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
                    model.addAttribute("documents", documentService.filterOneDateStatus(number, autor, LocalDate.parse(dataFinish), st));
                } else {
                    model.addAttribute("documents", documentService.filterOneDateStatus(number, autor, LocalDate.parse(dataStart), st));
                }
            } else {
                model.addAttribute("documents", documentService.filterAllStatus(number, autor, LocalDate.parse(dataStart), LocalDate.parse(dataFinish), st));
            }

        }
        return "document/list";

    }


    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('RECORTED')")
    @GetMapping("/new")
    public String newDocument(@AuthenticationPrincipal User user, Model model){

        model.addAttribute("roles", user.getRoles());

        return "document/new";

    }


    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('RECORTED')")
    public String create(
            @ModelAttribute("document") @Valid Document document,
            BindingResult bindingResult,
            @AuthenticationPrincipal User user,
            @RequestParam("file") MultipartFile file,
            Model model
            ) throws IOException, ParseException {

        if (!file.getOriginalFilename().matches(".*pdf")){
            model.addAttribute("error","Невiдповiдний формат файлу!!! Файл повинен бути у форматi pdf");
            return "document/new";
        }
        if (documentService.findByNumber(document.getNumber()) != null){
            model.addAttribute("error","Невiдповiдний ім'я файлу!!! Файл з таким іменем уже існує");
            return "document/new";
        }


        document.setName(saveFileDocument.uploadDocument(file));
        document.setDate(dataToday.dateToday());
        document.setTelegram(false);
        document.setResolution(false);
        document.setAuthor(user);

        if (bindingResult.hasErrors()){
            model.addAttribute("error",bindingResult.getFieldError().getDefaultMessage());
            return "document/new";
        }

        documentService.save(document);

        return "redirect:document";

    }

}
