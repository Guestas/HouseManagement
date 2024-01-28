package com.mc.HouseManagement.api.Controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Locale;

@Controller
public class ControllerLanguage {
    //TODO finish languages
    private final MessageSource messageSource;

    @Autowired
    public ControllerLanguage(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @GetMapping("/change-language")
    public String changeLanguage(@RequestParam String lang, HttpServletRequest request) {
        // Set the new locale based on the language parameter
        Locale newLocale = new Locale(lang);

        // Store the new locale in the session
        request.getSession().setAttribute("locale", newLocale);

        // Redirect back to the current page or home page
        String referer = request.getHeader("Referer");
        return "redirect:" + (referer != null ? referer : "/");
    }
}
