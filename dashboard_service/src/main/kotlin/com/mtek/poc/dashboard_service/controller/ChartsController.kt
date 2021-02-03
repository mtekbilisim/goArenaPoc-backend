package com.mtek.poc.dashboard_service.controller

import com.mtek.poc.dashboard_service.configs.ResponseWrap
import com.mtek.poc.dashboard_service.model.ChartModel
import com.mtek.poc.dashboard_service.model.ExpectationModel
//import com.mtek.poc.dashboard_service.repository.ChartRepository
import com.mtek.poc.dashboard_service.repository.ExpectationRepository
import com.mtek.poc.dashboard_service.repository.SalesRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.rest.webmvc.ResourceNotFoundException
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping(value = ["/charts"])
class ChartsController {
    @Autowired
    private lateinit var jt : JdbcTemplate
    //private lateinit var chartRepository: ChartRepository

    /*@GetMapping("/shop/{id}")
    fun getShop(@PathVariable(name="id", required = true) shopId:Long): ResponseWrap<List<ChartModel>>? {
        return ResponseWrap(expectationRepository.getShopChart(shopId))
    }*/

    @GetMapping("/user/{id}")
    fun getUser(@PathVariable(name="id", required = true) userId:Long): ResponseWrap<List<ChartModel>>? {
        var list : List<ChartModel> = jt.queryForList("select sum(e.quantity) as expectation, sum(s.quantity) as sales, e.product_group\n" +
                "from dashboard.expectations as e\n" +
                "inner join dashboard.sales as s\n" +
                "on e.product_group = s.product_group\n" +
                "where e.user_id=$userId group by e.product_group"
        ) as List<ChartModel>;
        return ResponseWrap(list)
    }

    @GetMapping("/shop/{id}")
    fun getShop(@PathVariable(name="id", required = true) shopId:Long): ResponseWrap<List<ChartModel>>? {
        var list : List<ChartModel> = jt.queryForList("select sum(e.quantity) as expectation, sum(s.quantity) as sales, e.product_group\n" +
                "from dashboard.expectations as e\n" +
                "inner join dashboard.sales as s\n" +
                "on e.product_group = s.product_group\n" +
                "where e.shop_id=$shopId group by e.product_group"
        ) as List<ChartModel>;
        return ResponseWrap(list)
    }
}