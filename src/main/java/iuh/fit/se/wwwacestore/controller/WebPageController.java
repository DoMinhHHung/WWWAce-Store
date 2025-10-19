package iuh.fit.se.wwwacestore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebPageController {

    @GetMapping("/")
    public String home() {
        // Thymeleaf sẽ resolve tới classpath:webapp/META-INF/views/products/list.html
        return "products/list";
    }

    @GetMapping("/products/{id}/page")
    public String productDetail() {
        return "products/detail";
    }

    // Admin pages (no security here)
    @GetMapping("/admin/products")
    public String adminProducts() {
        return "admin/products/list";
    }

    @GetMapping("/admin/products/new")
    public String adminProductCreate() {
        return "admin/products/form";
    }

    @GetMapping("/admin/products/{id}/edit")
    public String adminProductEdit() {
        return "admin/products/form";
    }

    @GetMapping("/admin/promotions")
    public String adminPromotions() {
        return "admin/promotions/list";
    }

    @GetMapping("/admin/promotions/new")
    public String adminPromotionCreate() {
        return "admin/promotions/form";
    }

    @GetMapping("/admin/promotions/{id}/edit")
    public String adminPromotionEdit() {
        return "admin/promotions/form";
    }
}