package com.aohui.btcorg.repo;

import com.aohui.btcorg.entity.IdentityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IdentityRepo extends JpaRepository<IdentityEntity,Long> {
}
