package vn.com.pos.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.com.pos.rest.model.Tutorial;

import java.util.List;

public interface TutorialRepository extends JpaRepository<Tutorial, Long> {
    List<Tutorial> findByPublished(boolean published);

    List<Tutorial> findByTitleContaining(String title);
}