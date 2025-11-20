package com.anas.blogapi.respositories;

import com.anas.blogapi.entities.role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface roleRepo extends JpaRepository<role,Integer> {
}
