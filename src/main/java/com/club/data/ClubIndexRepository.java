package com.club.data;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by jibo on 2017/4/26.
 */
public interface ClubIndexRepository extends CrudRepository<ClubIndex, Long> {
    ClubIndex findTopByClub(String club);
}
