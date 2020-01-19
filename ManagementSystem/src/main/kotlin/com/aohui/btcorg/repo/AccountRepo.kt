package com.aohui.btcorg.repo

import com.aohui.btcorg.entity.AccountEntity
import org.springframework.data.jpa.repository.JpaRepository

interface AccountRepo : JpaRepository<AccountEntity, String> {
    fun findByUid(uid: String): AccountEntity?
    fun findByUsername(username:String): AccountEntity?
}