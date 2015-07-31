package pl.com.weblive.plugin.salesmanago;


public class IntegrationDetails 
{
	
	private String clientId;
	private String apiSecret;
	final private String apiKey = "asdf123";
	private String endPoint;
	private String ownerMail;
	
	public String getOwnerMail() {
		return ownerMail;
	}

	public void setOwnerMail(String ownerMail) {
		this.ownerMail = ownerMail;
	}

	public IntegrationDetails(String clientId, String apiSecret, String endPoint, String ownerMail)
	{
		this.clientId = clientId;
		this.apiSecret = apiSecret;
		this.endPoint = endPoint;
		this.ownerMail = ownerMail;
	}
	
	public IntegrationDetails(String parseFromRequest)
	{
		String[] params = new String[4];
		
		for(int i = 0; i < 4; i++)
		{
			for(int j = 0; j < parseFromRequest.length(); j++)
			{
				if(parseFromRequest.charAt(j) == ';')
				{
					params[i] = parseFromRequest.substring(0, j);
					parseFromRequest = parseFromRequest.substring(j+1, parseFromRequest.length());
					break;
				}
			}
		}
		this.ownerMail = params[0];
		this.clientId = params[1];
		this.apiSecret = params[2];
		this.endPoint = params[3];
	}
	
	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getApiSecret() {
		return apiSecret;
	}

	public void setApiSecret(String apiSecret) {
		this.apiSecret = apiSecret;
	}

	public String getEndPoint() {
		return endPoint;
	}

	public void setEndPoint(String endPoint) {
		this.endPoint = endPoint;
	}

	public String getApiKey() {
		return apiKey;
	}
}
