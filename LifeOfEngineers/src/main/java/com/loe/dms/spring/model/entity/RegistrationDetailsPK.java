package com.loe.dms.spring.model.entity;

import java.io.Serializable;

public class RegistrationDetailsPK implements Serializable {

	private static final long serialVersionUID = 1L;

	private String user_id;

	private String sequence_num;

	public RegistrationDetailsPK() {
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof RegistrationDetailsPK)) {
			return false;
		}
		final RegistrationDetailsPK other = (RegistrationDetailsPK) obj;
		if (user_id != null ? !user_id.equals(other.user_id) : other.user_id != null)
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
		sb.append(sequence_num);
		return sb.hashCode();
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getSequence_num() {
		return sequence_num;
	}

	public void setSequence_num(String sequence_num) {
		this.sequence_num = sequence_num;
	}

}
