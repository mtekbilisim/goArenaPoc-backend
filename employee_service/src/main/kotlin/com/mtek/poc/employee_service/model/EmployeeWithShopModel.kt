package com.mtek.poc.employee_service.model

import com.mtek.poc.employee_service.enums.EmployeeType
import javax.persistence.*


@Entity
@Table(name = "users", schema = "users")
class EmployeeWithShopModel(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long,
    @Column var last_name: String,
    @Column var first_name: String,
    @Column var email: String,
    @Column var avatar: String?,
    @Column var password: String,
    @Column var username: String,
    @Column @Enumerated(EnumType.STRING) var employee_type: EmployeeType = EmployeeType.EMPLOYEE,
    @ManyToOne(
        fetch = FetchType.LAZY,
        cascade = [CascadeType.ALL]
    ) @JoinColumn(
        name = "shopId",
        referencedColumnName = "id"
    ) var shop: ShopModel?
) {

}