package com.aohui.btcorg.repo;

import com.aohui.btcorg.entity.AccountLoginEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginMassageRepo extends JpaRepository<AccountLoginEntity,Long> {
}
