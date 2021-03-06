package com.mtek.poc.dashboard_service.model

import javax.persistence.*

@Entity
@Table(name = "expectations", schema = "dashboard")
class ExpectationModel(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long,
    @Column var product: String?,
    @Column var product_group: String?,
    @Column var quantity: Long = 0,
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(
        name = "shopId",
        referencedColumnName = "id"
    ) var shop: ShopModel?,
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(
        name = "userId",
        referencedColumnName = "id"
    ) var user: EmployeeModel?
) {

}