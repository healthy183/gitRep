package icac.opsis.model;

import org.apache.commons.lang.StringUtils;

public class FieldModel  implements java.io.Serializable{

	
	 private String fieldName;	
	
	 private boolean isEditable;
	
	 private boolean isMandatory;
	 
	 private String remarks;
	 
	 private String noWhiteieldName;
	 
	 

	public FieldModel() {
		super();
	}



	public FieldModel(String fieldName, boolean isEditable,
			boolean isMandatory, String remarks) {
		super();
		this.fieldName = fieldName;
		this.isEditable = isEditable;
		this.isMandatory = isMandatory;
		this.remarks = remarks;
		String noWhiteieldName = fieldName.replaceAll("(?i)[^a-zA-Z0-9\u4E00-\u9FA5]", ""); 
		this.noWhiteieldName =  StringUtils.uncapitalize(noWhiteieldName);
		
	}



	public String getFieldName() {
		return fieldName;
	}



	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}



	public boolean isEditable() {
		return isEditable;
	}



	public void setEditable(boolean isEditable) {
		this.isEditable = isEditable;
	}



	public boolean isMandatory() {
		return isMandatory;
	}



	public void setMandatory(boolean isMandatory) {
		this.isMandatory = isMandatory;
	}



	public String getRemarks() {
		return remarks;
	}



	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}



	public String getNoWhiteieldName() {
		return noWhiteieldName;
	}



	public void setNoWhiteieldName(String noWhiteieldName) {
		this.noWhiteieldName = noWhiteieldName;
	}
	 
	
	 
	 
	 
}

