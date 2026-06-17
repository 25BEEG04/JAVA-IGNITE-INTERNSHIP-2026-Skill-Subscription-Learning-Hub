package com.skills.hub.controller;

import com.skills.hub.model.Subscription;
import com.skills.hub.service.SubscriptionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
=========================================================
WHAT IS THIS FILE?
Handles subscription between user and skill pack
=========================================================
*/
@Controller
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @GetMapping("/subscribe")
    public String subscribe(@RequestParam Long userId,
                            @RequestParam Long packId) {
        // STEP 1: Call subscriptionService.subscribe(userId, packId)
        subscriptionService.subscribe(userId, packId);

        // STEP 2: Redirect to subscriptions page
        return "redirect:/subscriptions/" + userId;
    }

    @GetMapping("/subscriptions/{userId}")
    public String viewSubscriptions(@PathVariable Long userId, Model model) {
        // STEP 1: Get list of subscriptions for the user
        List<Subscription> list = subscriptionService.getUserSubscriptions(userId);

        // STEP 2: Add list to model
        model.addAttribute("subs", list);

        // STEP 3: Return subscriptions view
        return "subscriptions";
    }

    public SubscriptionService getSubscriptionService() {
        return subscriptionService;
    }
}
