package com.api.policeStation.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.api.policeStation.models.CriminalModel;
import com.api.policeStation.models.PoliceModel;

@Repository
public interface IUCriminalRepository extends JpaRepository<CriminalModel, Integer>{
	@Query("SELECT c FROM CriminalModel c WHERE c.police = :id_police")
    List<CriminalModel> getArrestedCriminalForPolice(@Param("id_police") PoliceModel id_police);
	
	@Query("SELECT c FROM CriminalModel c WHERE c.arrests >= :min AND c.arrests <= :max")
    List<CriminalModel> filterArrests(@Param("min") Integer min,@Param("max") Integer mix);
}
