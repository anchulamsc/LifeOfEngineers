package com.loe.dms.spring.model.entity;

import java.io.Serializable;

public class GalleryDetailsPK implements Serializable {

	private static final long serialVersionUID = 1L;

	private String user_id;

	private String gallery_id;

	private String file_id;

	private String sequence_num;

	public GalleryDetailsPK() {
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof GalleryDetailsPK)) {
			return false;
		}
		final GalleryDetailsPK other = (GalleryDetailsPK) obj;
		if (user_id != null ? !user_id.equals(other.user_id) : other.user_id != null)
			return false;
		if (gallery_id != null ? !gallery_id.equals(other.gallery_id) : other.gallery_id != null)
			return false;
		if (file_id != null ? !file_id.equals(other.file_id) : other.file_id != null)
			return false;
		if (sequence_num != null ? !sequence_num.equals(other.sequence_num) : other.sequence_num != null)
			return false;
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		StringBuilder sb = new StringBuilder();
		sb.append(user_id);
		sb.append(gallery_id);
		sb.append(file_id);
		sb.append(sequence_num);
		return sb.hashCode();
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getGallery_id() {
		return gallery_id;
	}

	public void setGallery_id(String gallery_id) {
		this.gallery_id = gallery_id;
	}

	public String getFile_id() {
		return file_id;
	}

	public void setFile_id(String file_id) {
		this.file_id = file_id;
	}

	public String getSequence_num() {
		return sequence_num;
	}

	public void setSequence_num(String sequence_num) {
		this.sequence_num = sequence_num;
	}

}
