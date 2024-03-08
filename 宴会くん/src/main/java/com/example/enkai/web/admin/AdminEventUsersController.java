package com.example.enkai.web.admin;

import com.example.enkai.common.DataNotFoundException;
import com.example.enkai.common.FlashData;
import com.example.enkai.entity.Event;
import com.example.enkai.entity.EventUser;
import com.example.enkai.entity.User;
import com.example.enkai.service.EventService;
import com.example.enkai.service.EventUserService;
import com.example.enkai.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin/eventusers")
public class AdminEventUsersController {
    @Autowired
    EventUserService eventUserService;
    @Autowired
    UserService userService;
    @Autowired
    EventService eventService;

    @GetMapping("create/{id}")
    public String register(@PathVariable Integer id, @Validated @ModelAttribute EventUser eventUser, BindingResult result, Model model, RedirectAttributes ra) {
        FlashData flash;
        try {
            String email = SecurityContextHolder.getContext().getAuthentication().getName();
            User user = userService.findByEmail(email);

            Event event = eventService.findById(id);

            eventUser.setUser(user);
            eventUser.setEvent(event);
            eventUserService.save(eventUser);
            flash = new FlashData().success("イベントに参加しました");
        } catch (Exception e) {
            flash = new FlashData().danger("処理中にエラーが発生しました");
        }
        ra.addFlashAttribute("flash", flash);
        return "redirect:/admin/events/view/" + id;
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable Integer id, @Validated @ModelAttribute EventUser eventUser, BindingResult result, Model model, RedirectAttributes ra) {
        FlashData flash;
        try {
            String email = SecurityContextHolder.getContext().getAuthentication().getName();
            User user = userService.findByEmail(email);

            Event event = eventService.findById(id);

            flash = new FlashData().success("イベントを辞退しました");
        } catch (Exception e) {
            flash = new FlashData().danger("処理中にエラーが発生しました");
        }
        ra.addFlashAttribute("flash", flash);
        return "redirect:/admin/events/view/" + id;
    }
}
