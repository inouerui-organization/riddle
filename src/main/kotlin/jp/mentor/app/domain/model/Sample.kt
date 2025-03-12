package jp.mentor.app.domain.model

import jakarta.persistence.*

/**
 * サンプルのエンティティ.
 *
 * @author rui.inoue
 */
@Entity
@Table(name = "samples")
data class Sample(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,
    @Column(name = "name", nullable = false)
    val name: String,
    @Column(name = "mail")
    val mail: String? = null,
    @Column(name = "age")
    val age: Int? = null
)
