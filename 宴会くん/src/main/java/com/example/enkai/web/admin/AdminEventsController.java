package com.example.enkai.web.admin;

import com.example.enkai.common.DataNotFoundException;
import com.example.enkai.common.FlashData;
import com.example.enkai.common.UserDetailsServiceImpl;
import com.example.enkai.entity.Event;
import com.example.enkai.entity.User;
import com.example.enkai.service.BaseService;
import com.example.enkai.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin/events")
public class AdminEventsController {
    @Autowired
    EventService eventService;

    /*
     * 一覧表示
     */
    @GetMapping(path = {"", "/"})
    public String list(Model model) {
        // 全件取得
        List<Event> events = eventService.findAll();
        model.addAttribute("events", events);
        return "admin/events/list";
    }

    /*
     * マイイベント一覧表示
     */
    @GetMapping(path = {"/mylist"})
    public String mylist(Model model) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        List<Event> events = eventService.findByUserEmail(email);
        model.addAttribute("events", events);
        return "admin/events/mylist";
    }


    /*
     * 受注表示
     */
    @GetMapping(value = "/view/{id}")
    public String view(@PathVariable Integer id, Model model, RedirectAttributes ra) {
        try {
            // 存在確認
            Event event = eventService.findById(id);
            model.addAttribute("event", event);
        } catch (Exception e) {
            FlashData flash = new FlashData().danger("該当データがありません");
            ra.addFlashAttribute("flash", flash);
            return "redirect:/admin/events";
        }
        return "admin/events/view";
    }
    @GetMapping("create")
    public String add(@ModelAttribute Event event, Model model) {
        model.addAttribute("isNew", true);
        return "admin/events/form";
    }

    @PostMapping("process")
    public String process(@Validated @ModelAttribute Event event, BindingResult result, Model model, RedirectAttributes ra) {
        FlashData flash;
        try {
            if (result.hasErrors()) {
                model.addAttribute("isNew", event.getId() == null);
                return "admin/events/form";
            }
            String type = (event.getId() == null) ? "追加" : "編集";
            eventService.save(event);
            flash = new FlashData().success("イベントの" + type + "が完了しました");
        } catch (Exception e) {
            flash = new FlashData().danger("処理中にエラーが発生しました");
        }
        ra.addFlashAttribute("flash", flash);
        return "redirect:/admin/events";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("isNew", false);
        try {
            model.addAttribute("event", eventService.findById(id));
        } catch (DataNotFoundException e) {
            return "redirect:/admin/events";
        }
        return "admin/events/form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, RedirectAttributes ra) {
        FlashData flash;
        try {
            eventService.findById(id);
            eventService.deleteById(id);
            flash = new FlashData().success("イベントの削除が完了しました");
        } catch (DataNotFoundException e) {
            flash = new FlashData().danger("該当データはありません");
        } catch (Exception e) {
            flash = new FlashData().danger("処理中にエラーが発生しました");
        }
        ra.addFlashAttribute("flash", flash);
        return "redirect:/admin/events/mylist";
    }

}
