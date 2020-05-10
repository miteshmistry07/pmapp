package org.mistry.entity;

import java.util.Arrays;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity()
@Table(name="files")
public class DbFiles {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@ManyToOne
	@JoinColumn(name="premiseId", referencedColumnName="premiseId")
	private Premise premise;
	
	@Lob //large object
    @Column(name = "data", columnDefinition="LONGBLOB") //storing images
	private byte[] data;
	
	@Column(name="description")
	private String description;
	
	@Column(name="category")
	private String category;
	
	@Column(name="fileType")
	private String fileType;
	
	@Column(name="fileName")
	private String fileName;
	
	public DbFiles() {
		
	}
	
	public DbFiles(Premise premise, byte[] data, String description, String category, String fileType,
			String fileName) {
		this.premise = premise;
		this.data = data;
		this.description = description;
		this.category = category;
		this.fileType = fileType;
		this.fileName = fileName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Premise getPremise() {
		return premise;
	}

	public void setPremise(Premise premise) {
		this.premise = premise;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public String toString() {
		return "Files [id=" + id + ", premiseId=" + premise + ", data=" + Arrays.toString(data) + ", description="
				+ description + ", category=" + category + ", fileType=" + fileType + ", fileName="
				+ fileName + "]";
	}
	
	
}
