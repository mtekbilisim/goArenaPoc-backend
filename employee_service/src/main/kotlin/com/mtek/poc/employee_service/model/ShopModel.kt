package com.mtek.poc.employee_service.model

import javax.persistence.*

@Entity
@Table(name = "shops", schema = "users")
class ShopModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    @Column
    var name: String? = null
}