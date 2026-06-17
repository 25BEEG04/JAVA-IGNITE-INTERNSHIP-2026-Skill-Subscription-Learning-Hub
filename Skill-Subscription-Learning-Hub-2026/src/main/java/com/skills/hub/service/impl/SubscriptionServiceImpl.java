package com.skills.hub.service.impl;

import com.skills.hub.model.Subscription;
import com.skills.hub.model.User;
import com.skills.hub.model.SkillPack;
import com.skills.hub.repository.SubscriptionRepository;
import com.skills.hub.repository.UserRepository;
import com.skills.hub.repository.SkillPackRepository;
import com.skills.hub.service.SubscriptionService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository subRepo;
    private final UserRepository userRepo;
    private final SkillPackRepository skillPackRepo;

    public SubscriptionServiceImpl(SubscriptionRepository subRepo,
                                   UserRepository userRepo,
                                   SkillPackRepository skillPackRepo) {
        this.subRepo = subRepo;
        this.userRepo = userRepo;
        this.skillPackRepo = skillPackRepo;
    }

    @Override
    public Subscription subscribe(Long userId, Long packId) {
        // STEP 1: Fetch user by id
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        // STEP 2: Fetch skill pack by id
        SkillPack skillPack = skillPackRepo.findById(packId)
                .orElseThrow(() -> new RuntimeException("SkillPack not found with id: " + packId));

        // STEP 3: Create new Subscription object
        Subscription subscription = new Subscription();

        // STEP 4: Set user + skill pack
        subscription.setUser(user);
        subscription.setSkillPack(skillPack);

        // STEP 5: Set start date = today
        subscription.setStartDate(LocalDate.now());

        // STEP 6: Set end date = today + 30 days
        subscription.setEndDate(LocalDate.now().plusDays(30));

        // STEP 7: Set status = ACTIVE
        subscription.setStatus("ACTIVE");

        // STEP 8: Save subscription
        Subscription saved = subRepo.save(subscription);

        // STEP 9: Return subscription
        return saved;
    }

    @Override
    public List<Subscription> getUserSubscriptions(Long userId) {
        // STEP 1: Fetch user subscriptions from DB
        // STEP 2: Return list
        return subRepo.findByUserId(userId);
    }

    public SubscriptionRepository getSubRepo() {
        return subRepo;
    }
}
