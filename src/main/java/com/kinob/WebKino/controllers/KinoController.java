package com.kinob.WebKino.controllers;

import com.kinob.WebKino.models.Role;
import com.kinob.WebKino.models.User;
import com.kinob.WebKino.models.KinoPost;
import com.kinob.WebKino.repo.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class KinoController {

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/kino")
    public String kinoMain(Model model) {
        Iterable<KinoPost> posts = postRepository.findAll();
        model.addAttribute("posts", posts);

        String template = "kino-main-user";
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        boolean isAdmin = auth.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("ADMIN"));

        if(isAdmin) {
            template = "kino-main";
        }

        return template;
    }
    @GetMapping("/kino/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String kinoAdd(Model model) {
        return "kino-add";
    }

    @PostMapping("/kino/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String kinoPostAdd(@RequestParam String title, @RequestParam String genre, @RequestParam String director, @RequestParam int yearKino, @RequestParam String full_text, @RequestParam String shareLink, @RequestParam String startTime, Model model){
        KinoPost post = new KinoPost(title, genre, director, full_text, startTime, shareLink, yearKino );
        postRepository.save(post);
        return "redirect:/kino";
    }

    @GetMapping("/kino/{id}")
    public String kinoDetails(@PathVariable(value = "id") long id, Model model) {
        if (!postRepository.existsById(id)){
            return "redirect:/kino";
        }
        String template = "kino-details-user";
        Optional<KinoPost> post = postRepository.findById(id);
        ArrayList<KinoPost> res = new ArrayList<>();
        post.ifPresent(res::add);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        boolean isAdmin = auth.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("ADMIN"));

        if(isAdmin) {
            template = "kino-details";
        }

        model.addAttribute("post", res);
        return template;
    }

    @GetMapping("/kino/{id}/edit")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String kinoEdit(@PathVariable(value = "id") long id, Model model) {
        if (!postRepository.existsById(id)){
            return "redirect:/kino";
        }
        Optional<KinoPost> post = postRepository.findById(id);
        ArrayList<KinoPost> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("post", res);
        return "kino-edit";
    }

    @PostMapping("/kino/{id}/edit")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String kinoPostUpdate(@PathVariable(value = "id") long id,@RequestParam String title, @RequestParam String genre, @RequestParam String director, @RequestParam int yearKino, @RequestParam String full_text, @RequestParam String shareLink, @RequestParam String startTime, Model model){
        KinoPost post = postRepository.findById(id).orElseThrow();
        post.setTitle(title);
        post.setGenre(genre);
        post.setDirector(director);
        post.setYearKino(yearKino);
        post.setFull_text(full_text);
        post.setShareLink(shareLink);
        post.setStartTime(startTime);
        postRepository.save(post);
        return "redirect:/kino";
    }

    @PostMapping("/kino/{id}/remove")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String kinoPostDelete(@PathVariable(value = "id") long id, Model model){
        KinoPost post = postRepository.findById(id).orElseThrow();

        postRepository.delete(post);
        return "redirect:/kino";
    }

    @GetMapping("/cabinet/{id}")
    public String cabinet(@PathVariable(value = "id") long id, Model model) {
        KinoPost post = postRepository.findById(id).orElseThrow();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        model.addAttribute("shareLink", post.getShareLink());
        model.addAttribute("name", name);
        return "cabinet";
    }
}
