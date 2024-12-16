package com.POS.CRUD_project.repositroy;

import com.POS.CRUD_project.entity.productEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface productRepo extends JpaRepository<productEntity,Long> {
    // Query method to find by barcode
    Optional<productEntity> findByProductBarcode(String ProductBarcode);

    Page<productEntity> findAll(Pageable pageable);



}
