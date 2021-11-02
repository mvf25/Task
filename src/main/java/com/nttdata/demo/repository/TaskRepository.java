package com.nttdata.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nttdata.demo.controller.Tasks;

public interface TaskRepository extends JpaRepository<Tasks, Integer> {

}
