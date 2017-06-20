package com.club.data;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by jibo on 2017/4/26.
 */
public interface ClubListRepository extends CrudRepository<ClubList, Long> {
    ClubList findTopByReferAndTitle(String refer, String title);
}
