package com.aohui.btcorg.repo;

import com.aohui.btcorg.entity.BackgroundOperationRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BackgroundOperationRecordRepo extends JpaRepository<BackgroundOperationRecordEntity,Long> {
}
