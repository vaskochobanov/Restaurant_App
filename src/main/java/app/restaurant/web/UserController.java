package app.restaurant.web;

import app.restaurant.models.bindings.UserLoginBindingModel;
import app.restaurant.models.bindings.UserRegisterBindingModel;
import app.restaurant.services.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/register")
    public String getRegister(Model model) {
        if (!model.containsAttribute("userRegisterBindingModel")) {
            model.addAttribute("userRegisterBindingModel", new UserRegisterBindingModel());
            model.addAttribute("usernameTaken", false);
        }
        return "register";
    }

    @PostMapping("/register")
    public String postRegisterUser(@Valid UserRegisterBindingModel userRegisterBindingModel, BindingResult bindingResult,
                                   RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors() ||
                !userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())) {
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel",
                    bindingResult);
            return "redirect:register";
        }
        userRegisterBindingModel.setPassword(passwordEncoder.encode(userRegisterBindingModel.getPassword()));
        boolean usernameTaken = userService.registerCustomerUser(userRegisterBindingModel);
        if (usernameTaken) {
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel",
                    bindingResult);
            redirectAttributes.addFlashAttribute("usernameTaken", true);
            return "redirect:register";
        }
        return "redirect:login";
    }

    @GetMapping("login")
    public String login(Model model) {
        model.addAttribute("notFound", false);
        return "login";
    }
    @PostMapping("/users/login")
    public String postLoginUser(@Valid UserLoginBindingModel userLoginBindingModel, BindingResult bindingResult,
                                RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userLoginBindingModel", userLoginBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userLoginBindingModel",
                    bindingResult);
            return "redirect:login";
        }
        if (userService.checkUserExistsInDB(userLoginBindingModel.getUsername())) {
            redirectAttributes.addFlashAttribute("userLoginBindingModel", userLoginBindingModel);
            redirectAttributes.addFlashAttribute("notFound", true);
        }
        userLoginBindingModel.setPassword(passwordEncoder.encode(userLoginBindingModel.getPassword()));
        userService.loginUser(userLoginBindingModel);
        return "redirect:home";
    }
    @PostMapping("/users/login-error")
    public String failedLogin(@ModelAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY) String username,
                              RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("notFound", true);
        redirectAttributes.addFlashAttribute("username", username);
        return "redirect:login";
    }
}
