package com.example.clase3gtics.controller;

import com.example.clase3gtics.entity.Region;
import com.example.clase3gtics.entity.Territory;
import com.example.clase3gtics.repository.RegionRepository;
import com.example.clase3gtics.repository.TerritoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/territory")
public class TerritoryController {

    @Autowired TerritoryRepository territoryRepository;
    @Autowired RegionRepository regionRepository;

    @GetMapping("/listar")
    public String listarTerritorios(Model model) {
        List<Territory> territoryList = territoryRepository.findAll();
        model.addAttribute("territoryList", territoryList);
        return "territory/list";
    }

    @GetMapping("/new")
    public String newform(Model model) {
        List<Region> regionList = regionRepository.findAll();
        model.addAttribute("regionList", regionList);
        return "territory/newfrm";
    }

    @PostMapping("/save")
    public String saveShip(Territory territory, RedirectAttributes attr) {
        if(territoryRepository.existsById(territory.getId())){
            attr.addFlashAttribute("msg", "Por favor, inserte un ID diferente");
            return "redirect:/territory/new";
        }else if(territory.getTerritoryDescription().equals("")) {
            attr.addFlashAttribute("msg", "Por favor, inserte una descripción");
            return "redirect:/territory/new";
        }else{
            territoryRepository.save(territory);
            return "redirect:/territory/listar";
        }
    }

    @PostMapping("/update")
    public String updateShip(Territory territoryForm, RedirectAttributes attr) {
        Optional<Territory> optionalTerritory = territoryRepository.findById(territoryForm.getId());
        if (optionalTerritory.isPresent()) {
            if(territoryForm.getTerritoryDescription().equals("")){
                attr.addFlashAttribute("msg", "Por favor, inserte una descripción");
                return "redirect:/territory/editar?id=" + territoryForm.getId();
            }else{
                Territory territoryFromDb = optionalTerritory.get();
                territoryFromDb.setTerritoryDescription(territoryForm.getTerritoryDescription());
                territoryFromDb.setRegionID(territoryForm.getRegionID());
                territoryRepository.save(territoryFromDb);
            }
        }
        return "redirect:/territory/listar";
    }

    @GetMapping("/editar")
    public String editarForm(@RequestParam("id") String id, Model model) {
        Optional<Territory> optionalTerritory = territoryRepository.findById(id);

        List<Region> regionList = regionRepository.findAll();
        model.addAttribute("regionList", regionList);

        if (optionalTerritory.isPresent()) {
            Territory territory = optionalTerritory.get();
            model.addAttribute("territory", territory);
            return "territory/editfrm";
        } else {
            return "redirect:/territory/listar";
        }
    }

}
