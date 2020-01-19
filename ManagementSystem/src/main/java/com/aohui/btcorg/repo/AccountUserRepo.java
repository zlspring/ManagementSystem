package com.aohui.btcorg.repo;

import com.aohui.btcorg.entity.AccountUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface AccountUserRepo extends JpaRepository<AccountUserEntity, Long> {

    AccountUserEntity findByUsername(@Param("username") String username);


    @Transactional
    @Modifying
    @Query(value ="UPDATE account SET password = ?1 WHERE username = ?2", nativeQuery = true)
    int updatePassword(@Param("username") String username, @Param("newPassword") String newPassword);

    List<AccountUserEntity> findAllBy();

    @Transactional
    @Modifying
    @Query(value ="UPDATE account SET ban = 1 WHERE username = ?2", nativeQuery = true)
    int updateBan(@Param("username")String username);
}
