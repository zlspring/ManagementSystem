package com.aohui.btcorg.entity

import javax.persistence.*

/**
 * 用户账号
 *
 * @property uid
 * @property email
 * @property password
 * @property phone
 * @property enabled
 * @property emailVerified
 * @property phoneVerified
 */
@Entity
@Table(indexes = [
    Index(name = "username_idx", columnList = "username", unique = true),
    Index(name = "uid_idx", columnList = "uid", unique = true)
])
data class AccountEntity(

        @Id
        @Column(nullable = false, unique = true, updatable = false)
        var uid: Long = 0,

        @Column(nullable = false, unique = true)
        var username: String = "",

        @Column(nullable = false)
        var password: String = "",

        var phone: String = "",

        @Column(nullable = false)
        var enabled: Boolean = true,

        @Column(nullable = false)
        var emailVerified:Boolean = false,

        @Column(nullable = false)
        var phoneVerified:Boolean = false
)