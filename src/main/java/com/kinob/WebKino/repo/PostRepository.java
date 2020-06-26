package com.kinob.WebKino.repo;

import com.kinob.WebKino.models.KinoPost;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<KinoPost, Long> {
}
