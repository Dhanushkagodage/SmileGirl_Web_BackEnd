package org.smilegirl.smilegirl_web_backend.repository;

import org.smilegirl.smilegirl_web_backend.dto.ProductSummaryDTO;
import org.smilegirl.smilegirl_web_backend.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT new org.smilegirl.smilegirl_web_backend.dto.ProductSummaryDTO(p.id, p.productName, p.rentalPrice, " +
            "(SELECT pi.imageUrl FROM ProductImage pi WHERE pi.product.id = p.id ORDER BY pi.id ASC LIMIT 1)) " +
            "FROM Product p")
    List<ProductSummaryDTO> findAllProductsSummary();
}
