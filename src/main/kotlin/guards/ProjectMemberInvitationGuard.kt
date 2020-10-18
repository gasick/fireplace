package dev.alpas.fireplace.guards

import dev.alpas.fireplace.entities.ProjectMembership
import dev.alpas.fireplace.entities.ProjectMemberships
import dev.alpas.fireplace.entities.User
import dev.alpas.fireplace.entities.Users
import dev.alpas.fireplace.rules.ValidUserRule
import dev.alpas.fireplace.rules.validUserRule
import dev.alpas.orAbort
import dev.alpas.validation.ValidationGuard
import dev.alpas.validation.Rule
import dev.alpas.validation.email
import dev.alpas.validation.required
import me.liuwj.ktorm.dsl.eq
import me.liuwj.ktorm.dsl.insert
import me.liuwj.ktorm.entity.findOne

class ProjectMemberInvitationGuard : ValidationGuard() {
    private val validUserRule by lazy {ValidUserRule()}
    override fun rules(): Map<String, Iterable<Rule>> {
          return mapOf("email" to listOf(required(), email(), validUserRule))
    }

    fun commit(): User {
        val now = call.nowInCurrentTimezone().toInstant()
        val member = validUserRule.user.orAbort()
        ProjectMemberships.insert {
            it.projectId to call.longParam("project")
            it.userId to member.id
            it.createdAt to now
            it.updatedAt to now
        }

        return member
    }
}