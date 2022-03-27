package com.example.clase3gtics.controller;

import com.example.clase3gtics.entity.Shipper;
import com.example.clase3gtics.repository.ShipperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/shipper")
public class ShipperController {

    @Autowired
    ShipperRepository shipperRepository;

    @GetMapping(value = {"/list", ""})
    public String listarTransportistas(Model model) {

        List<Shipper> lista = shipperRepository.findAll();
        model.addAttribute("shipperList", lista);

        return "shipper/list";
    }

    @GetMapping("/new")
    public String nuevoTransportistaFrm() {
        return "shipper/newFrm";
    }

    @PostMapping("/save")
    public String guardarNuevoTransportista(Shipper shipper) {
        shipperRepository.save(shipper);
        return "redirect:/shipper/list";
    }

    @GetMapping("/edit")
    public String editarTransportista(Model model,
                                      @RequestParam("id") int id) {

        Optional<Shipper> optShipper = shipperRepository.findById(id);

        if (optShipper.isPresent()) {
            Shipper shipper = optShipper.get();
            model.addAttribute("shipper", shipper);
            return "shipper/editFrm";
        } else {
            return "redirect:/shipper/list";
        }
    }

    @GetMapping("/delete")
    public String borrarTransportista(Model model,
                                      @RequestParam("id") int id) {

        Optional<Shipper> optShipper = shipperRepository.findById(id);

        if (optShipper.isPresent()) {
            shipperRepository.deleteById(id);
        }
        return "redirect:/shipper/list";

    }


}

