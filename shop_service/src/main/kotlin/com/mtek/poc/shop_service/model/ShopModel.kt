package com.mtek.poc.shop_service.model

import javax.persistence.*

@Entity
@Table(name="shops",schema="shops")
class ShopModel(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,
    @Column
    var name: String?,
    var employees:List<UserModel>
) {
}