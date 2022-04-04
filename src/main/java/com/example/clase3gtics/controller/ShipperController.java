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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
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

    @PostMapping("/buscarTransportista")
    public String buscarTransportista(Model model,
                                      @RequestParam("searchText") String searchText) {

        List<Shipper> shippers = shipperRepository.buscarPorNombreParcial(searchText);
        model.addAttribute("shipperList",shippers);
        return "shipper/list";
    }

    @GetMapping("/new")
    public String nuevoTransportistaFrm() {
        return "shipper/newFrm";
    }

    @PostMapping("/save")
    public String guardarNuevoTransportista(Shipper shipper, RedirectAttributes attributes) {
        shipperRepository.save(shipper);
        attributes.addFlashAttribute("msg", "Transportista creado exitosamente");
        Shipper shipper3= new Shipper();
        shipper3.setCompanyname("nombre Prueba");
        shipper3.setPhone("999999999");
        attributes.addFlashAttribute("TransportistaPrueba",shipper1);
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
            /*ArrayList<Integer> listaBorra = new ArrayList<>();
            listaBorra.add(1);
            listaBorra.add(2);
            shipperRepository.deleteAllById(listaBorra);*/

        }
        return "redirect:/shipper/list";

    }


}

