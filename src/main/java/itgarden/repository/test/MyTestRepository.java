/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.repository.test;

import itgarden.model.test.MyTest;
import org.hibernate.boot.model.source.spi.Sortable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author User
 */
public interface MyTestRepository extends CrudRepository<MyTest, Long> {

    Page  findAll(Pageable pageable);

    
}
