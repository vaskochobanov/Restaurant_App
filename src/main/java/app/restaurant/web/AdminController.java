package app.restaurant.web;

import app.restaurant.models.bindings.UserAdminRegisterBindingModel;
import app.restaurant.services.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    public AdminController(PasswordEncoder passwordEncoder, UserService userService) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    @GetMapping("/add-user")
    public String getAdminAddUser(Model model) {
        if (!model.containsAttribute("userAdminRegisterBindingModel")) {
            model.addAttribute("userAdminRegisterBindingModel", new UserAdminRegisterBindingModel());
            model.addAttribute("usernameTaken", false);
        }
        return "admin-add-user";
    }

    @PostMapping("/add-user")
    public String postAdminAddUser(@Valid UserAdminRegisterBindingModel userAdminRegisterBindingModel, BindingResult bindingResult,
                                   RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors() ||
                !userAdminRegisterBindingModel.getPassword().equals(userAdminRegisterBindingModel.getConfirmPassword())) {
            redirectAttributes.addFlashAttribute("userAdminRegisterBindingModel", userAdminRegisterBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userAdminRegisterBindingModel",
                    bindingResult);
            redirectAttributes.addFlashAttribute("usernameTaken", false);
            return "redirect:add-user";
        }
        userAdminRegisterBindingModel.setPassword(passwordEncoder.encode(userAdminRegisterBindingModel.getPassword()));
        boolean usernameTaken = userService.adminRegisterUser(userAdminRegisterBindingModel);
        if (usernameTaken) {
            redirectAttributes.addFlashAttribute("userAdminRegisterBindingModel", userAdminRegisterBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userAdminRegisterBindingModel",
                    bindingResult);
            redirectAttributes.addFlashAttribute("usernameTaken", true);
            return "redirect:add-user";
        }
        return "redirect:/home";
    }
    @GetMapping("/edit-user")
    public String getAdminUserEdit() {
        return "admin-edit-users";
    }
}

