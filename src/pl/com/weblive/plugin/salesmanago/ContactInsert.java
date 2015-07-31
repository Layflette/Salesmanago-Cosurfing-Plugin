package pl.com.weblive.plugin.salesmanago;
 
import java.util.Date;
 
import org.apache.http.HttpResponse;
 
import pl.benhauer.salesmanago.api.ContactInsertRequest;
import pl.benhauer.salesmanago.api.ContactInsertResponse;
import pl.benhauer.salesmanago.api.model.SmContact;
import pl.benhauer.salesmanago.api.samples.CryptoTools;
 
 
 
public class ContactInsert
{
	 private SmContact contact;
	 private IntegrationDetails loginDetails;
	 private boolean status = false;
	 private String contactId;
	 
	
	 public ContactInsert(IntegrationDetails login)
	 {
		 this.loginDetails = login;
	 }
	 
	 public void createSmContact(String reqString)
	 {
		 SmContact tmpContact = new SmContact();
         String[] tab = new String[6];
         int j = 0;
         while(j < 5)
         {
            for(int i = 0; i < reqString.length(); i++)
            {
                if (reqString.charAt(i) == ';')
                {
	                if(reqString.substring(0, i).compareTo("undefined") != 0)
	                        tab[j] = reqString.substring(0, i);
	                reqString = reqString.substring(i+1, reqString.length());
	                j++;
	                break;
                }
            }
          }
	      tmpContact.setName(tab[0]);
	      tmpContact.setEmail(tab[1]);
	      tmpContact.setFax(tab[2]);
	      tmpContact.setCompany(tab[3]);
	      tmpContact.setTags(tab[4]);
	      tmpContact.setState(tab[5]);
	   
	      this.contact = tmpContact;
       }
 
	 public void sendRequest()
	 {
	 
		  ContactInsertRequest contactInsReq = new ContactInsertRequest(loginDetails.getClientId(), loginDetails.getApiKey(), new Date(),
		  CryptoTools.sha1(loginDetails.getApiKey() + loginDetails.getClientId() + loginDetails.getApiSecret()));
		 
		  contactInsReq.setOwner(loginDetails.getOwnerMail());
		  contactInsReq.setContact(contact);
		 
		  HttpResponse response = HttpTools.httpPost(loginDetails.getEndPoint() + "/contact/insert", contactInsReq);
		 
		  ContactInsertResponse contactInsResp = null;
		  contactInsResp = HttpTools.getResponse(response, ContactInsertResponse.class);
		  if(contactInsResp != null && contactInsResp.isSuccess() == true)
		  {
			   this.contactId = contactInsResp.getContactId();
			   this.status = true;
		  }
		  else this.status = false;
	 }
 
 
	 public SmContact getContact()
	 {
		 return contact;
	 }
	 
	 public void setContact(SmContact contact)
	 {
		 this.contact = contact;
	 }
	 
	 public IntegrationDetails getLoginDetails()
	 {
		 return loginDetails;
	 }
	 
	 public void setLoginDetails(IntegrationDetails loginDetails)
	 {
		 this.loginDetails = loginDetails;
	 }
	 
	 public boolean getStatus()
	 {
		 return this.status;
	 }
	 
	 public String getContactId()
	 {
		 return this.contactId;
	 }
 
}