package com.club.data;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by jibo on 2017/4/26.
 */
public interface ForumRepository extends CrudRepository<Forum, Long> {
    Forum findTopByRefer(String refer);
}
