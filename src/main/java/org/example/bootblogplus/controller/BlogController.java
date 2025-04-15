package org.example.bootblogplus.controller;

import lombok.RequiredArgsConstructor;
import org.example.bootblogplus.model.form.ArticleForm;
import org.example.bootblogplus.model.service.BlogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/blog") // Get? Post?
@RequiredArgsConstructor
public class BlogController {
    private final BlogService blogService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("title", "블로그 글 목록");
        model.addAttribute("list", blogService.getAllArticles());
        return "/blog/list";
    }

    @GetMapping("/new")
    public String newArticle(Model model) {
        model.addAttribute("title", "블로그 글 작성");
        model.addAttribute("form", ArticleForm.empty());
        return "/blog/form";
    }

    @PostMapping("/new")
    public String newArticle(ArticleForm form, RedirectAttributes redirectAttributes) {
//        Article article = new Article();
//        article.setTitle(form.title());
//        article.setContent(form.content());
        try {
            blogService.createArticle(form.formToEntity());
            return "redirect:/blog";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/blog/new";
    }
}