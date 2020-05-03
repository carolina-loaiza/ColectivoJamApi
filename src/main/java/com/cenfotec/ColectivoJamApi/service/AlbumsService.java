package com.cenfotec.ColectivoJamApi.service;

import java.util.List;
import java.util.concurrent.ExecutionException;
import com.cenfotec.ColectivoJamApi.repository.AlbumsRepository;
import com.cenfotec.ColectivoJamApi.domain.Albums;

public class AlbumsService {
	
	AlbumsRepository albumsRepository = new AlbumsRepository();
	
	public List<Albums> getAllAlbums() {
		return albumsRepository.findAll();
	}

	public String saveAlbum(Albums newAlbum) {
		return albumsRepository.save(newAlbum);
	}
	
	public Albums getAlbumByName(String checkName) throws InterruptedException, ExecutionException, Exception {
		return albumsRepository.findByName(checkName);
	}
	
	public String deleteAlbumByName(String name) throws InterruptedException, ExecutionException, Exception {
		return albumsRepository.delete(name);
	}

}
