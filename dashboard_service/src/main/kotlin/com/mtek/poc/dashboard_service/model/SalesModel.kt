package com.mtek.poc.dashboard_service.model

import com.mtek.poc.dashboard_service.enums.SalesType
import java.math.BigDecimal
import java.time.LocalDateTime
import javax.persistence.*


@Entity
@Table(name = "sales", schema = "dashboard")
class SalesModel(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long,
    @Column var product: String?,
    @Column var product_group: String?,
    @Column var quantity: Long = 0,
    @Column var date_time: LocalDateTime? = LocalDateTime.now(),
    @Column(columnDefinition = "NUMERIC") var amount: BigDecimal = BigDecimal(0),
    @Column @Enumerated(EnumType.STRING) var type: SalesType = SalesType.SALE,
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(
        name = "shopId",
        referencedColumnName = "id"
    ) var shop: ShopModel,
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(
        name = "userId",
        referencedColumnName = "id"
    ) var user: EmployeeModel
) {

}