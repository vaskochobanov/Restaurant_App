package app.restaurant.web;

import app.restaurant.models.bindings.OrderTypeAddBindingModel;
import app.restaurant.models.bindings.OrderTypeEditBindingModel;
import app.restaurant.services.OrderTypeService;
import app.restaurant.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public String getEditSingleTable(@PathVariable Long id, Model model) {
        model.addAttribute("tableToEdit", orderTypeService.getTableById(id));
        model.addAttribute("waiters", userService.getAllWaiters());
        if (!model.containsAttribute("hasErrors")) {
            model.addAttribute("hasErrors", false);
        }
        return "edit-single-table";
    }
    @PostMapping("/single-edit/{id}")
    public String postEditSingleTable(@PathVariable Long id, OrderTypeEditBindingModel orderTypeEditBindingModel,
                                      RedirectAttributes redirectAttributes) {
        if (orderTypeEditBindingModel.getWaiterName().isBlank()) {
            redirectAttributes.addFlashAttribute("hasErrors", true);
            return String.format("redirect:/tables/single-edit/%s", id);
        }
        orderTypeEditBindingModel.setId(id);
        orderTypeService.editTable(orderTypeEditBindingModel);
        return "redirect:/tables/edit";
    }
    @GetMapping("/edit-order/{id}")
    public String editOrder(@PathVariable Long id, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("waiterId", userService.getIdByUsername(authentication.getName()));
        model.addAttribute("tableId", id);
        return "edit-order";
    }
}
