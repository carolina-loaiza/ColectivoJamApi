package com.cenfotec.ColectivoJamApi.service;

import java.util.List;
import java.util.concurrent.ExecutionException;
import com.cenfotec.ColectivoJamApi.repository.BandsRepository;
import com.cenfotec.ColectivoJamApi.domain.Bands;

public class BandsService {
	
	BandsRepository bandsRepository = new BandsRepository();
	
	public List<Bands> getAllPatents() {
		return bandsRepository.findAll();
	}

	public String savePatent(Bands newBand) {
		return bandsRepository.save(newBand);
	}
	
	public Bands getBandByName(String checkName) throws InterruptedException, ExecutionException, Exception {
		return bandsRepository.findByName(checkName);
	}
	
	public String deleteBandByName(String name) throws InterruptedException, ExecutionException, Exception {
		return bandsRepository.delete(name);
	}

}
