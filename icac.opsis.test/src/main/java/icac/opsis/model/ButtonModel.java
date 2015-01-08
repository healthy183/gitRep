package icac.opsis.model;

import org.apache.commons.lang.StringUtils;

public class ButtonModel  implements java.io.Serializable {

	
	private String buttonName;
	private String buttonLabel;
	private boolean isSaveAndCancel;
	private String buttonVar;
	private String urlNamespace;
	private String actionName;
	private boolean isBack;
	private String showType;
	
	
	public ButtonModel() {
		super();
	}

	
	

	public ButtonModel(String buttonLabel,
			boolean isSaveAndCancel, String urlNamespace,
			String actionName, boolean isBack, String showType) {
		
		
		super();

		if(!isSaveAndCancel){
			
			String noWhileButtonLabel = buttonLabel.replaceAll("(?i)[^a-zA-Z0-9\u4E00-\u9FA5]", "");
			this.buttonName =  StringUtils.uncapitalize(noWhileButtonLabel)+"Btn";
			//StringUtils.capitalise(noWhileButtonLabel);
			this.buttonLabel = noWhileButtonLabel;
			this.isSaveAndCancel = isSaveAndCancel;
			this.buttonVar = buttonLabel+"URL";
			this.urlNamespace = urlNamespace;
			this.actionName = actionName;
			this.showType = showType;
		}
		
		this.isBack = isBack;
		
		
	}

	
	
	
	public boolean isBack() {
		return isBack;
	}




	public void setBack(boolean isBack) {
		this.isBack = isBack;
	}




	public String getShowType() {
		return showType;
	}




	public void setShowType(String showType) {
		this.showType = showType;
	}




	public String getButtonName() {
		return buttonName;
	}


	public void setButtonName(String buttonName) {
		this.buttonName = buttonName;
	}


	public String getButtonLabel() {
		return buttonLabel;
	}


	public void setButtonLabel(String buttonLabel) {
		this.buttonLabel = buttonLabel;
	}


	public boolean isSaveAndCancel() {
		return isSaveAndCancel;
	}


	public void setSaveAndCancel(boolean isSaveAndCancel) {
		this.isSaveAndCancel = isSaveAndCancel;
	}


	public String getButtonVar() {
		return buttonVar;
	}


	public void setButtonVar(String buttonVar) {
		this.buttonVar = buttonVar;
	}


	public String getUrlNamespace() {
		return urlNamespace;
	}


	public void setUrlNamespace(String urlNamespace) {
		this.urlNamespace = urlNamespace;
	}


	public String getActionName() {
		return actionName;
	}


	public void setActionName(String actionName) {
		this.actionName = actionName;
	}




	
	
	
	
	
	
}
