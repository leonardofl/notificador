package sp.alvaro.model;

import java.util.ArrayList;
import java.util.List;

public class ProfFile {

    private String professor;
    private String fileName;
    private List<ProfSheet> sheets;

    public ProfFile() {
        this.sheets = new ArrayList<ProfSheet>();
    }
   
    public ProfFile(String professor, String fileName) {
    	this();
		this.professor = professor;
		this.fileName = fileName;
	}

	public String getProfessor() {
        return professor;
    }

    public String getFileName() {
        return fileName;
    }

    public List<ProfSheet> getSheets() {
        return sheets;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setSheets(List<ProfSheet> sheets) {
        this.sheets = sheets;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((fileName == null) ? 0 : fileName.hashCode());
		result = prime * result
				+ ((professor == null) ? 0 : professor.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProfFile other = (ProfFile) obj;
		if (fileName == null) {
			if (other.fileName != null)
				return false;
		} else if (!fileName.equals(other.fileName))
			return false;
		if (professor == null) {
			if (other.professor != null)
				return false;
		} else if (!professor.equals(other.professor))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ProfFile [professor=" + professor + ", fileName=" + fileName
				+ ", sheets=" + sheets + "]";
	}
}