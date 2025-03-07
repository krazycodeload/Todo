package com.k2infosoft.Todo.entity

import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import jakarta.validation.constraints.NotEmpty

@Entity
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @NotEmpty(message = "Name must not be blank")
    val name: String,
    @NotEmpty(message = "UserName must not be blank")
    val username: String,
    @NotEmpty(message = "UserName must not be blank")
    val email: String,
    @NotEmpty(message = "Password must not be blank")
    val password: String,
    @ManyToMany(
        fetch = FetchType.LAZY,
        cascade = [CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH]
    )
    @JoinTable(
        name = "user_role",
        joinColumns = [JoinColumn(name = "user_id", referencedColumnName="id")],
        inverseJoinColumns = [JoinColumn(name = "role_id", referencedColumnName="id")]
    )
    val roles : Set<Role>
)
