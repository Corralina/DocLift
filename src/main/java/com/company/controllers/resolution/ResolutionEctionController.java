package com.company.controllers.resolution;


import com.company.dataService.DataToday;
import com.company.domian.*;
import com.company.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.util.Map;

@Controller
@RequestMapping("resolution")
public class ResolutionEctionController {

    @Autowired
    private ResolutionService resolutionService;

    @Autowired
    private ReversService reversService;

    @Autowired
    private DataToday dataToday;

    @Autowired
    private VisaService visaService;

    @Autowired
    private StaticService staticService;

    @Autowired
    private PerformerService performerService;

    @Autowired
    private UserService userService;

    @Autowired
    private StatusService statusService;


    @PostMapping("visa")
    public String visa(
            @RequestParam("resolution") Resolution resolution,
            @AuthenticationPrincipal User user,
            Model model
    ) throws ParseException {
        if (!resolution.getAgrees().getUsername().equals(user.getUsername()) && !user.isAdmin()){
            model.addAttribute("error","Не достатньо прав!");
            return "redirect:resolution/" + resolution.getId();
        }
        if(resolution.getStatus().getFinish()){
            model.addAttribute("error","Резолюцію було завізовано" + resolution.getDate());
            return "redirect:resolution/" + resolution.getId();

        }
        if (resolution.getStatus().getRevers()){
            model.addAttribute("error","Резолюція знаходиться на редагувані!");
            return "redirect:resolution/" + resolution.getId();

        }

        Visa visa = new Visa();
        visa.setAgrees(resolution.getAgrees().getInformation().getPerson().getInitials());
        visa.setData(dataToday.dateToday());
        visa.setPosition(resolution.getAgrees().getInformation().getPerson().getPost());
        visa = visaService.save(visa);

        Iterable<Performer> performers = performerService.byResolution(resolution);

        Visa finalVisa = visa;
        performers.forEach(performer ->{
                    Static stat = new Static();

                    stat.setDoer(performer.getComent());
                    stat.setInitials((performer.getUser().getInformation().getPerson().getInitials()));
                    stat.setVisa(finalVisa);
                    staticService.save(stat);
                }
        );

        resolution.setVisa(visa);
        resolution.getStatus().setFinish(true);
        resolutionService.save(resolution);


        return "redirect:resolution";
    }



    @PostMapping("revers")
    public String revers(
            @RequestParam("resolution") Resolution resolution,
            @AuthenticationPrincipal User user,
            @RequestParam Map<String, String> form,
            Model model
    ) throws ParseException {

        if(resolution.getStatus().getFinish()){
            model.addAttribute("error","Резолюцію було завізовано" + resolution.getDate());
            return "redirect:resolution/" + resolution.getId();

        }
        if (resolution.getStatus().getRevers()){
            model.addAttribute("error","Резолюція вже знаходиться на редагувані!");
            return "redirect:resolution/" + resolution.getId();

        }
        if (!resolution.getAgrees().getUsername().equals(user.getUsername()) && !user.isAdmin()){
            model.addAttribute("error","Не достатньо прав!");
            return "redirect:resolution/" + resolution.getId();

        }

        resolution.getStatus().setRevers(true);
        resolutionService.save(resolution);

        Revers revers = new Revers();
        revers.setComent(form.get("coment"));
        revers.setDate(dataToday.dateToday());
        revers.setRev(false);
        revers.setTelegram(false);
        revers.setResolution(resolution);
        reversService.save(revers);

        return "redirect:resolution/" + resolution.getId();

    }


    @PostMapping("edit")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('TABLIN')")
    public String edit(
            @AuthenticationPrincipal User user,
            @RequestParam Map<String, String> form,
            Resolution upate,
            @RequestParam("revers") Revers revers,
            Model model
    ){

        revers.setRev(true);
        reversService.save(revers);

        Status status = revers.getResolution().getStatus();
        status.setRevers(false);
        status.setTelegram(false);
        statusService.save(status);

        Resolution resolution = revers.getResolution();
        resolution.setComent(upate.getComent());
        resolution.setAgrees(upate.getAgrees());
        resolutionService.save(resolution);


        for (Performer performer : performerService.byResolution(revers.getResolution())){
            performerService.drop(performer);
        }
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

        return "redirect:resolution";

    }
}
