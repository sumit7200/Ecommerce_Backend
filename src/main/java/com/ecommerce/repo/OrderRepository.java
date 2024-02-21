package com.ecommerce.repo;

import com.ecommerce.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {



//    @Query("SELECT O FROM Order o WHERE O.user.id = :userId AND (o.Orderstatus = PLACED OR o.orderstatus = CONFIRMED OR o.orderstatus = SHIPPED OR o.orderstatus = DELIVERED) "
//            )
//    public List<Order> getUserOrders(@Param("userId") Long userId);

}
