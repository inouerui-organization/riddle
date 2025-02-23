package jp.mentor.app.domain.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

/**
 * サンプルのエンティティ.
 *
 * @author rui.inoue
 */
@Entity
data class Sample(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Int? = null,
    val name: String,
    val mail: String? = null,
    val age: Int? = null
)
