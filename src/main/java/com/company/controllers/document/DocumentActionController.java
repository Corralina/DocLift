package com.company.controllers.document;


import com.company.dataService.DataToday;
import com.company.domian.Document;
import com.company.domian.User;
import com.company.fileService.SaveFileDocument;
import com.company.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.text.ParseException;

@Controller
@RequestMapping("document")
public class DocumentActionController {
    @Autowired
    public DocumentService documentService;

    @Autowired
    private SaveFileDocument saveFileDocument;

    @Autowired
    private DataToday dataToday;



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
