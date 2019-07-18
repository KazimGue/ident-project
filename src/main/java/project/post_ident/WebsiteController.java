package project.post_ident;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebsiteController {

    @GetMapping(value = "/vergleich")
    public String personenDatenAbgleichen(Model model) {


        return "vergleich";
    }


}