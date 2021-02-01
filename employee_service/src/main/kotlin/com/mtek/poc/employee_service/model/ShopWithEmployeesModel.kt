package com.mtek.poc.employee_service.model

import javax.persistence.*

@Entity
@Table(name = "shops", schema = "users")
class ShopWithEmployeesModel(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long = 0,
    @Column var name: String?,
    @OneToMany(
        fetch = FetchType.LAZY,
        cascade = [CascadeType.ALL],
        orphanRemoval = true
    ) @JoinColumn(
        name = "shopId",
        referencedColumnName = "id"
    ) var employees: List<EmployeeModel>?
) {

}