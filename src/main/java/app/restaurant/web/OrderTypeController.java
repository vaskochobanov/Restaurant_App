package app.restaurant.web;

import app.restaurant.models.bindings.OrderTypeAddBindingModel;
import app.restaurant.services.OrderTypeService;
import app.restaurant.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/tables")
public class OrderTypeController {
    private final OrderTypeService orderTypeService;
    private final UserService userService;

    public OrderTypeController(OrderTypeService orderTypeService, UserService userService) {
        this.orderTypeService = orderTypeService;
        this.userService = userService;
    }

    @GetMapping("/add")
    public String getAddTable(Model model) {
        model.addAttribute("waiters", userService.getAllWaiters());
        if (!model.containsAttribute("orderTypeAddBindingModel")) {
            model.addAttribute("orderTypeAddBindingModel", new OrderTypeAddBindingModel());
            model.addAttribute("orderTypeExists", false);
        }
        return "admin-add-order-type";
    }
    @PostMapping("/add")
    public String postAddTable(@Valid OrderTypeAddBindingModel orderTypeAddBindingModel, BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("orderTypeAddBindingModel", orderTypeAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.orderTypeAddBindingModel",
                    bindingResult);
            redirectAttributes.addFlashAttribute("orderTypeExists", false);
            return "redirect:add";
        }
        boolean orderTypeExsists = orderTypeService.addOrderType(orderTypeAddBindingModel);
        if (orderTypeExsists) {
            redirectAttributes.addFlashAttribute("orderTypeAddBindingModel", orderTypeAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.orderTypeAddBindingModel",
                    bindingResult);
            redirectAttributes.addFlashAttribute("orderTypeExists", true);
            return "redirect:add";
        }
        return "redirect:/home";
    }
    @GetMapping("/edit")
    public String getEditTables(Model model) {
        model.addAttribute("tables", orderTypeService.getAllTables());
        return "admin-edit-tables";
    }
    @GetMapping("/delete/{id}")
    public String deleteTable(@PathVariable Long id) {
        orderTypeService.deleteTable(id);
        return "redirect:/tables/edit";
    }
    @GetMapping("/single-edit/{id}")
    public String getEditSingleTable(@PathVariable Long id) {
        return null;
    }
}
