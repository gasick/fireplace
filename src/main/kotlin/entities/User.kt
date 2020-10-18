package dev.alpas.fireplace.entities

import dev.alpas.auth.BaseUser
import dev.alpas.auth.BaseUsersTable
import dev.alpas.ozone.OzoneEntity
import me.liuwj.ktorm.dsl.eq
import me.liuwj.ktorm.entity.findList

// https://alpas.dev/docs/ozone#dao
interface User : BaseUser<User> {
    // https://alpas.dev/docs/email-verification
    val projects get() = Projects.findList{it.ownerId eq id}
    val projectMembership get() = ProjectMemberships.findList {it.userId eq id}.map{it.project}

    override val mustVerifyEmail get() = false

    companion object : OzoneEntity.Of<User>()
}

// https://alpas.dev/docs/ozone#dsl
object Users : BaseUsersTable<User>()
