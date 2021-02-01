package com.mtek.poc.dashboard_service.model

import javax.persistence.*

@Entity
@Table(name = "shops", schema = "users")
class ShopModel(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long,
                @Column var name: String?
) {

}