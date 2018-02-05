package com.loe.dms.spring.dao;

import com.loe.dms.spring.model.entity.GalleryDetails;

public interface GalleriesDAO {

	public void saveFile(GalleryDetails galleryDetails);

	public GalleryDetails getFileById(String user_id, String file_id);

	public void deleteFileById(String user_id, String file_id);

}
