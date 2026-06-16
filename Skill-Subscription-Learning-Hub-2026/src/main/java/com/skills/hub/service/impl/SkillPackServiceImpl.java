package com.skills.hub.service.impl;
import com.skills.hub.model.SkillPack;
import com.skills.hub.repository.SkillPackRepository;
import com.skills.hub.service.SkillPackService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SkillPackServiceImpl implements SkillPackService {
    private final SkillPackRepository packRepo;
    public SkillPackServiceImpl(SkillPackRepository packRepo) {
        this.packRepo = packRepo;
    }

    @Override
    public SkillPack addSkillPack(SkillPack pack) {
        if (pack == null || pack.getName() == null || pack.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("SkillPack or its name must not be null or empty");
        }
        return packRepo.save(pack);
    }

    @Override
    public List<SkillPack> getAllPacks() {
        return packRepo.findAll();
    }

    @Override
    public SkillPack updateSkillPack(SkillPack pack) {
        if (pack == null || pack.getId() == null) {
            return null;
        }
        SkillPack existing = packRepo.findById(pack.getId()).orElse(null);
        if (existing == null) {
            return null;
        }
        existing.setName(pack.getName());
        existing.setDescription(pack.getDescription());
        existing.setPrice(pack.getPrice());
        return packRepo.save(existing);
    }

    @Override
    public void deleteSkillPack(Long id) {
        packRepo.deleteById(id);
    }

    public SkillPackRepository getPackRepo() {
        return packRepo;
    }
}
