package org.vo;

import java.io.Serializable;

public class TreeNodeDto implements Serializable {
	private static final long serialVersionUID = 5005993466301611710L;
	private String id;
	private String pId;
	private String code;
	private String name;

	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TreeNodeDto [id=").append(this.id).append(", pId=")
				.append(this.pId).append(", code=").append(this.code)
				.append(", name=").append(this.name).append(", checked=")
				.append(this.checked).append("]");

		return builder.toString();
	}

	public TreeNodeDto() {
	}

	public TreeNodeDto(String id, String pId, String code, String name,
			boolean checked) {
		this.id = id;
		this.pId = pId;
		this.code = code;
		this.name = name;
		this.checked = Boolean.valueOf(checked);
	}

	public TreeNodeDto(String id, String pId, String code, String name) {
		this(id, pId, code, name, false);
	}

	public TreeNodeDto(String id, String pId, String name, boolean checked) {
		this(id, pId, "", name, checked);
	}

	public TreeNodeDto(String id, String pId, String name) {
		this(id, pId, "", name, false);
	}

	private Boolean checked = Boolean.valueOf(false);

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getpId() {
		return this.pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Boolean getChecked() {
		return this.checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = prime * result + (this.id == null ? 0 : this.id.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TreeNodeDto other = (TreeNodeDto) obj;
		if (this.id == null) {
			if (other.id != null)
				return false;
		} else if (!this.id.equals(other.id))
			return false;
		return true;
	}
}
