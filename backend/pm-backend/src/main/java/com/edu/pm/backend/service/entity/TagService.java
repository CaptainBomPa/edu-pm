package com.edu.pm.backend.service.entity;

import com.edu.pm.backend.commons.dto.TagDTO;
import com.edu.pm.backend.model.Tag;
import com.edu.pm.backend.model.UserStory;
import com.edu.pm.backend.repository.TagRepository;
import com.edu.pm.backend.repository.UserStoryRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@AllArgsConstructor
public class TagService {

    private final TagRepository tagRepository;
    private final UserStoryRepository userStoryRepository;

    public TagWithStats add(TagDTO tagDTO) {
        Tag tag = Tag.builder()
                .tagName(tagDTO.getTagName())
                .build();
        return mapToTagWithStats(tagRepository.save(tag));
    }

    public Tag removeById(Integer tagId) {
        Tag tag = tagRepository.findById(tagId).orElseThrow();

        Collection<UserStory> affectedStories = userStoryRepository.findAll().stream()
                .filter(userStory -> userStory.getTags().contains(tag)).toList();
        affectedStories.forEach(userStory -> userStory.getTags().remove(tag));
        userStoryRepository.saveAll(affectedStories);

        tagRepository.delete(tag);
        return tag;
    }

    public Collection<Tag> getAll() {
        return tagRepository.findAll();
    }

    public Collection<TagWithStats> getAllWithStats() {
        Collection<TagWithStats> tags = getAll()
                .stream()
                .map(this::mapToTagWithStats)
                .toList();
        Collection<UserStory> stories = userStoryRepository.findAll();

        for (TagWithStats tagWithStats : tags) {
            for (UserStory story : stories) {
                if (story.getTags().stream().anyMatch(tag -> tag.getId().equals(tagWithStats.getId()))) {
                    tagWithStats.setRelatedItemCount(tagWithStats.getRelatedItemCount() + 1);
                }
            }
        }

        return tags;
    }

    private TagWithStats mapToTagWithStats(Tag tag) {
        TagWithStats tagWithStats = new TagWithStats();
        tagWithStats.setId(tag.getId());
        tagWithStats.setTagName(tag.getTagName());
        tagWithStats.setRelatedItemCount(0);
        return tagWithStats;
    }


    @Data
    public static class TagWithStats {

        private Integer id;
        private String tagName;
        private Integer relatedItemCount;
    }
}
