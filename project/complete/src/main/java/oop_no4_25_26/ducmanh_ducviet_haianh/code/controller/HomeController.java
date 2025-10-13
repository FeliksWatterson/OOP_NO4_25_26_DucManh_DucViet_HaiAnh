package oop_no4_25_26.ducmanh_ducviet_haianh.code.controller;

import jakarta.servlet.http.HttpSession;
import oop_no4_25_26.ducmanh_ducviet_haianh.code.services.CatalogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final CatalogService catalogService;

    public HomeController(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @GetMapping("/")
    public String home(Model model, HttpSession session) {
        model.addAttribute("products", catalogService.allProducts());
        model.addAttribute("session", session);
        return "index";
    }
}
