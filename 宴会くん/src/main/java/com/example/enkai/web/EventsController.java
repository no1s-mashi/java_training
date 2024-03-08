package com.example.enkai.web;

import com.example.enkai.common.FlashData;
import com.example.enkai.entity.Category;
import com.example.enkai.entity.Event;
import com.example.enkai.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/")
public class EventsController {
    @Autowired
    BaseService<Event> eventService;

    /*
     * 一覧表示
     */
    @GetMapping(path = {"", "/"})
    public String list(Model model) {
        // 全件取得
        List<Event> events = eventService.findAll();
        model.addAttribute("events", events);
        return "events/list";
    }


    /*
     * 受注表示
     */
    @GetMapping(value = "events/view/{id}")
    public String view(@PathVariable Integer id, Model model, RedirectAttributes ra) {
        try {
            // 存在確認
            Event event = eventService.findById(id);
            model.addAttribute("event", event);
        } catch (Exception e) {
            FlashData flash = new FlashData().danger("該当データがありません");
            ra.addFlashAttribute("flash", flash);
            return "redirect:/";
        }
        return "events/view";
    }
}
