package sg.nus.iss.project69.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sg.nus.iss.project69.models.ProductMatches;
import sg.nus.iss.project69.services.PairingService;

@Controller
@RequestMapping("/productMatches")
public class PairingsController {

    @Autowired
    private PairingService pairSvc;

    @GetMapping
    public String getProductMatches(Model model, @RequestParam String food){
        List<ProductMatches>productMatches = pairSvc.getProductMatches(food);
        model.addAttribute("food", food.toLowerCase());
        model.addAttribute("productMatches", productMatches);
        return "productMatches";
    }
    
}
