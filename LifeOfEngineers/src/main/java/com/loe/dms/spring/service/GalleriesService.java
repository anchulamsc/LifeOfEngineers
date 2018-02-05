package com.loe.dms.spring.service;

import com.loe.dms.spring.model.entity.GalleryDetails;

public interface GalleriesService {

	public void saveFile(GalleryDetails galleryDetails);

	public GalleryDetails getFileById(String user_id, String file_id);

	public void deleteFileById(String user_id, String file_id);

}
