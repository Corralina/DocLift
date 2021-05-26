package com.company.controllers.resolution;


import com.company.dataService.DataToday;
import com.company.domian.*;
import com.company.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.*;

@Controller
@RequestMapping("resolution")
public class ResolutionController {

    @Autowired
    private UserService userService;

    @Autowired
    private DataToday dataToday;

    @Autowired
    private StatusService statusService;

    @Autowired
    private ResolutionService resolutionService;

    @Autowired
    private DocumentService documentService;

    @Autowired
    private PerformerService performerService;

    @Autowired
    private ReversService reversService;

    @Autowired
    private StaticService staticService;

    @GetMapping
    public String list(
            @RequestParam(value = "ds", required = false) String dataStart,
            @RequestParam(value = "df", required = false) String dataFinish,
            @RequestParam(value = "n", required = false) String number,
            @RequestParam(value = "a", required = false) String autor,
            @RequestParam(value = "c", required = false) String confirm,
            @AuthenticationPrincipal User user,
            Model model
    ){

        model.addAttribute("ds", dataStart);
        model.addAttribute("df", dataFinish);
        model.addAttribute("n", number);
        model.addAttribute("a", autor);
        model.addAttribute("c", confirm);

        if (autor == null) {
            autor = "";
        }
        if (number == null) {
            number = "";
        }
        if (confirm == null){
            confirm = "";
        }

        if (user.isAdmin() || user.isResolve() || user.isConfirm() || user.isTablin()){
            if (dataStart == null && dataFinish == null && number == "" && autor == "" && confirm == "") {
                model.addAttribute("resolutions", resolutionService.allStatusFinish(true));
            } else if (dataStart == null && dataFinish == null) {
                model.addAttribute("resolutions", resolutionService.filterNoDate(number, autor, confirm, true));
            } else if (dataStart == dataFinish || dataStart == null || dataFinish == null) {
                if (dataStart == null) {
                    model.addAttribute("resolutions", resolutionService.filterOneDate(number, autor, confirm, true, LocalDate.parse(dataFinish)));
                } else {
                    model.addAttribute("resolutions", resolutionService.filterOneDate(number, autor, confirm, true, LocalDate.parse(dataStart)));
                }
            } else {
                model.addAttribute("resolutions", resolutionService.filterAll(number, autor, confirm, true, LocalDate.parse(dataStart), LocalDate.parse(dataFinish).plusDays(2)));
            }
        }else{
            Iterable<Performer> performers;
            if (dataStart == null && dataFinish == null && number == "" && autor == "" && confirm == "") {
                performers = performerService.allStatusFinish(user,true);

            } else if (dataStart == null && dataFinish == null) {
                performers = performerService.filterNoDate(user, number, autor, confirm, true);
            } else if (dataStart == dataFinish || dataStart == null || dataFinish == null) {
                if (dataStart == null) {
                    performers = performerService.filterOneDate(user, number, autor, confirm, true, LocalDate.parse(dataFinish));
                } else {
                    performers = performerService.filterOneDate(user, number, autor, confirm, true, LocalDate.parse(dataStart));
                }
            } else {
                performers = performerService.filterAll(user, number, autor, confirm, true, LocalDate.parse(dataStart), LocalDate.parse(dataFinish).plusDays(2));
            }

            List<Resolution> res = new ArrayList<>();
            Iterator<Performer> p = performers.iterator();
            while( p.hasNext()){
                        Resolution resol = p.next().getResolution();
                        res.add(resol);
                    }

            model.addAttribute("resolutions", res);

        }




        return "resolution/list";
    }

    @GetMapping("/process")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('TABLIN') or hasAuthority('CONFIRM')")
    public String listProcess(
            @RequestParam(value = "ds", required = false) String dataStart,
            @RequestParam(value = "df", required = false) String dataFinish,
            @RequestParam(value = "n", required = false) String number,
            @RequestParam(value = "a", required = false) String autor,
            @RequestParam(value = "c", required = false) String confirm,
            Model model
    ){
        model.addAttribute("ds", dataStart);
        model.addAttribute("df", dataFinish);
        model.addAttribute("n", number);
        model.addAttribute("a", autor);
        model.addAttribute("c", confirm);

        if (autor == null) {
            autor = "";
        }
        if (number == null) {
            number = "";
        }
        if (confirm == null){
            confirm = "";
        }

        if (dataStart == null && dataFinish == null && number == "" && autor == "" && confirm == "") {
            model.addAttribute("resolutions", resolutionService.allStatusFinish(false));
        } else if (dataStart == null && dataFinish == null) {
            model.addAttribute("resolutions", resolutionService.filterNoDate(number, autor, confirm, false));
        } else if (dataStart == dataFinish || dataStart == null || dataFinish == null) {
            if (dataStart == null) {
                model.addAttribute("resolutions", resolutionService.filterOneDate(number, autor, confirm, false, LocalDate.parse(dataFinish)));
            } else {
                model.addAttribute("resolutions", resolutionService.filterOneDate(number, autor, confirm, false, LocalDate.parse(dataStart)));
            }
        } else {
            model.addAttribute("resolutions", resolutionService.filterAll(number, autor, confirm, false, LocalDate.parse(dataStart), LocalDate.parse(dataFinish)));
        }

        return "resolution/process";

    }

    @GetMapping("{resolution}")
    public String show(
            @AuthenticationPrincipal User user,
            @PathVariable Resolution resolution,
            Model model
    ){

        model.addAttribute("resolution", resolution);
        model.addAttribute("user", user);

        if (resolution.getStatus().getFinish()){
            model.addAttribute("revers", reversService.byResolution(resolution));
            model.addAttribute("statics", staticService.byVisa(resolution.getVisa()));
            return "resolution/resolution";
        }
        model.addAttribute("performers", performerService.byResolution(resolution));
        if (resolution.getStatus().getRevers() && user.getUsername().equals(resolution.getFilling().getUsername())){
            model.addAttribute("revers", reversService.byResolutionActiv(resolution));
            model.addAttribute("users",userService.allUser());
            model.addAttribute("confirms",userService.userConfirms(Collections.singleton(Role.CONFIRM)));
            return "resolution/edit";
        }
        return "resolution/resolutionProcess";
    }



    @GetMapping("new/{document}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('TABLIN')")
    public String newResolution(
            @PathVariable Document document,
            Model model
    ){

        if (document.getResolution()){
            model.addAttribute("error", "Даний документ уже розписано");
            return "redirect:/document";
        }

        model.addAttribute("document", document);
        model.addAttribute("users", userService.allUser());
        model.addAttribute("confirms", userService.userConfirms(Collections.singleton(Role.CONFIRM)));

        return "resolution/new";


    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('TABLIN')")
    public String save(
            @AuthenticationPrincipal User user,
            @ModelAttribute("document") Document document,
            @ModelAttribute("resolution") @Valid Resolution resolution,
            @RequestParam Map<String, String> form,
            Model model
            ) throws ParseException {

        if (document.getResolution()){
            return "redirect:/document";
        }

        Status status = new Status();
        status.setFinish(false);
        status.setRevers(false);
        status.setTelegram(false);
        status.setDone(false);
        status = statusService.save(status);

        resolution.setDate(dataToday.dateToday());
        resolution.setStatus(status);
        resolution.setFilling(user);
        resolutionService.save(resolution);

        document.setResolution(true);
        documentService.save(document);

        int i = 1;
        for(String key : form.keySet()){
            if (key.matches("performs[0-9]")){
                Performer performer = new Performer();
                performer.setResolution(resolution);
                performer.setUser(userService.findByUsername(form.get(key)));
                performer.setComent(form.get(key.replace("performs", "note")));
                performerService.save(performer);
                i++;
            }
        }

        return "redirect:/resolution";
    }






}

