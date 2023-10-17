package com.api.policeStation.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.api.policeStation.models.PoliceModel;

@Repository
public interface IUPoliceRepository extends JpaRepository<PoliceModel, Integer>{
	
	//list by range
    @Query("SELECT p FROM PoliceModel p WHERE p.privateOffice = :private_office")
    List<PoliceModel> listRangePolice(@Param("private_office") String private_office);
    
    @Query("SELECT p FROM PoliceModel p WHERE p.supervisor = :id_supervisor")
    List<PoliceModel> listSupervisorPolice(@Param("id_supervisor") PoliceModel id_supervisor);
    
    @Query("SELECT p FROM PoliceModel p WHERE p.privateOffice = :private_office")
    List<PoliceModel> filterPrivateOffice(@Param("private_office") String private_office);
}
