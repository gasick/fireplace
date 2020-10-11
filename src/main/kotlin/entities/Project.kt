package dev.alpas.fireplace.entities

import dev.alpas.ozone.*
import me.liuwj.ktorm.schema.long
import me.liuwj.ktorm.schema.text
import me.liuwj.ktorm.schema.timestamp
import java.time.Instant

interface Project : OzoneEntity<Project> {
    var idi: Long
    var title: String?
    val description: String
    val notes: String?
    val owner: User
    var createdAt: Instant?
    var updatedAt: Instant?

    companion object : OzoneEntity.Of<Project>()
}

object Projects : OzoneTable<Project>("projects") {
    val idi by bigIncrements("idi").bindTo { it.idi }
    val title by mediumText("title").bindTo { it.title}
    val description by text("description").bindTo { it.description}
    val notes by text("notes").nullable().bindTo { it.notes}
    val ownerId by long("owner_id").belongsTo(Users) { it.owner}

    val createdAt by timestamp("created_at").useCurrent().bindTo { it.createdAt }
    val updatedAt by timestamp("updated_at").useCurrent().bindTo { it.updatedAt }
}