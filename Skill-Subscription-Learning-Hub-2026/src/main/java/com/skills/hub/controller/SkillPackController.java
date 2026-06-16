package com.skills.hub.controller;
import com.skills.hub.model.SkillPack;
import com.skills.hub.service.SkillPackService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class SkillPackController {
    private final SkillPackService packService;
    public SkillPackController(SkillPackService packService) {
        this.packService = packService;
    }

    @GetMapping("/packs")
    public String viewPacks(Model model) {
        model.addAttribute("packs", packService.getAllPacks());
        return "packs";
    }

    @GetMapping("/add-pack")
    public String showAddPackPage() {
        return "add-pack";
    }

    @PostMapping("/add-pack")
    public String addPack(@ModelAttribute SkillPack pack) {
        packService.addSkillPack(pack);
        return "redirect:/packs";
    }

    @GetMapping("/delete-pack/{id}")
    public String deletePack(@PathVariable Long id) {
        packService.deleteSkillPack(id);
        return "redirect:/packs";
    }

    public SkillPackService getPackService() {
        return packService;
    }
}
