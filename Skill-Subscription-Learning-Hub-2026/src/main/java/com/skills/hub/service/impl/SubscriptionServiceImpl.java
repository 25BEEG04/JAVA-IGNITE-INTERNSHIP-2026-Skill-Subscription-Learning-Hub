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
    private final SkillPackRepository packRepo;
    public SubscriptionServiceImpl(SubscriptionRepository subRepo,
                                   UserRepository userRepo,
                                   SkillPackRepository packRepo) {
        this.subRepo = subRepo;
        this.userRepo = userRepo;
        this.packRepo = packRepo;
    }
    @Override
    public Subscription subscribe(Long userId, Long packId) {
        // STEP 1: fetch user by id
        User user = userRepo.findById(userId).orElse(null);
        // STEP 2: fetch skill pack by id
        SkillPack pack = packRepo.findById(packId).orElse(null);
        // STEP 3: validate both exist
        if (user == null || pack == null) {
            return null;
        }
        else {
            // STEP 4: create new Subscription object
            Subscription subscription = new Subscription();
            // STEP 5: set user + skill pack
            subscription.setUser(user);
            subscription.setSkillPack(pack);
            // STEP 6: set start date = today
            subscription.setStartDate(LocalDate.now());
            // STEP 7: set end date = today + 30 days
            subscription.setEndDate(LocalDate.now().plusDays(30));
            // STEP 8: set status = ACTIVE
            subscription.setStatus("ACTIVE");
            // STEP 9: save and return subscription
            return subRepo.save(subscription);
        }
    }
    @Override
    public List<Subscription> getUserSubscriptions(Long userId) {
        // STEP 1: fetch user by id
        User user = userRepo.findById(userId).orElse(null);
        // STEP 2: fetch user subscriptions from DB
        // STEP 3: return list
        return subRepo.findByUser(user);
    }
    public SubscriptionRepository getSubRepo() {
        return subRepo;
    }
}
