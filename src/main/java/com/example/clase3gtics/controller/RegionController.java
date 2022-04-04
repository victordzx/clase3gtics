package com.example.clase3gtics.controller;

import com.example.clase3gtics.entity.Region;
import com.example.clase3gtics.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/region")
public class RegionController {

    @Autowired RegionRepository regionRepository;

    @GetMapping("/listar")
    public String listarRegion(Model model) {
        List<Region> regionList = regionRepository.findAll();
        model.addAttribute("regionList", regionList);
        return "region/list";
    }

    @GetMapping("/new")
    public String newform(Model model) {
        return "region/newfrm";
    }

    @PostMapping("/save")
    public String saveRegion(Region region, RedirectAttributes attr) {
        if((regionRepository.existsById(region.getId())) || region.getId() == 0){
            attr.addFlashAttribute("msg", "El ID no es válido o ya está en uso");
            return "redirect:/region/new";
        }else if(region.getRegionDescription().equals("")) {
            attr.addFlashAttribute("msg", "Por favor, inserte una descripción");
            return "redirect:/region/new";
        } else {
            regionRepository.save(region);
            return "redirect:/region/listar";
        }
    }

    @PostMapping("/update")
    public String updateRegion(Region regionForm, RedirectAttributes attr) {
        Optional<Region> optionalRegion = regionRepository.findById(regionForm.getId());
        if (optionalRegion.isPresent()) {
            if(regionForm.getRegionDescription().equals("")){
                attr.addFlashAttribute("msg", "Por favor, inserte una descripción");
                return "redirect:/region/editar?id=" + regionForm.getId();
            }else{
                Region regionFromDb = optionalRegion.get();
                regionFromDb.setRegionDescription(regionForm.getRegionDescription());
                regionRepository.save(regionFromDb);
            }
        }
        return "redirect:/region/listar";
    }

    @GetMapping("/editar")
    public String editarForm(@RequestParam("id") Integer id, Model model) {
        Optional<Region> optionalRegion = regionRepository.findById(id);
        if (optionalRegion.isPresent()) {
            Region region = optionalRegion.get();
            model.addAttribute("region", region);
            return "region/editfrm";
        } else {
            return "redirect:/region/listar";
        }
    }

    @GetMapping("/delete")
    public String borrarRegion(Model model, @RequestParam("id") Integer id) {
        Optional<Region> optionalRegion = regionRepository.findById(id);
        if (optionalRegion.isPresent()) {
            regionRepository.deleteById(id);
        }
        return "redirect:/region/listar";

    }

}
