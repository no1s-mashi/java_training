package com.example.enkai.web.admin;

import com.example.enkai.common.DataNotFoundException;
import com.example.enkai.common.FlashData;
import com.example.enkai.entity.Category;
import com.example.enkai.entity.Event;
import com.example.enkai.service.CategoryService;
import com.example.enkai.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/admin/categories")
public class CategoriesController {
    @Autowired
    CategoryService categoryService;

    @Autowired
    EventService eventService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("categories", categoryService.findAll());
        return "admin/categories/list";
    }

    @GetMapping("create")
    public String add(@ModelAttribute Category category, Model model) {
        model.addAttribute("isNew", true);
        return "admin/categories/form";
    }

    @PostMapping("process")
    public String process(@Validated @ModelAttribute Category category, BindingResult result, Model model, RedirectAttributes ra) {
        FlashData flash;
        try {
            if (result.hasErrors()) {
                model.addAttribute("isNew", category.getId() == null);
                return "admin/categories/form";
            }
            String type = (category.getId() == null) ? "追加" : "編集";
            categoryService.save(category);
            flash = new FlashData().success("カテゴリの" + type + "が完了しました");
        } catch (Exception e) {
            flash = new FlashData().danger("処理中にエラーが発生しました");
        }
        ra.addFlashAttribute("flash", flash);
        return "redirect:/admin/categories";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("isNew", false);
        try {
            model.addAttribute("category", categoryService.findById(id));
        } catch (DataNotFoundException e) {
            return "redirect:/admin/categories";
        }
        return "admin/categories/form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, RedirectAttributes ra) {
        FlashData flash;
        try {
            List<Event> events = eventService.findByCategoryId(id);
            if (events.isEmpty()) {
                categoryService.findById(id);
                categoryService.deleteById(id);
                flash = new FlashData().success("カテゴリの削除が完了しました");
            } else {
                flash = new FlashData().danger("イベントに登録されているカテゴリは削除できません");
            }
        } catch (DataNotFoundException e) {
            flash = new FlashData().danger("該当データはありません");
        } catch (Exception e) {
            flash = new FlashData().danger("処理中にエラーが発生しました");
        }
        ra.addFlashAttribute("flash", flash);
        return "redirect:/admin/categories";
    }
}

