package pl.com.weblive.plugin.salesmanago;
import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

 
public class Run extends HttpServlet
{
		public static String requestType = "UNKNOWN";
		public static Logger log = Logger.getLogger(Run.class.getName());
		public IntegrationDetails details = null;
		public String requestContent = null;
		private static final long serialVersionUID = 1L;
		
		
		@Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp)
                        throws ServletException, IOException
        {
			if((requestContent = req.getReader().readLine().toString()) != null)
			{
				log.info("ODEBRALEM: " + requestContent);
				for(int i = 0; i < requestContent.length(); i++)
				{
					if(requestContent.charAt(i) == ';')
					{
						requestType = requestContent.substring(0, i);
						requestContent = requestContent.substring(i+1, requestContent.length());
						break;
					}
				}		
			}
			
			if(requestType.equals("USER"))
			{
				if(details == null)
				{
					log.warning("Brak autoryzacji...");
					resp.getOutputStream().println("Brak autoryzacji. Uzupelnij dane w panelu administratora CoSurfing");
					return;
				}
				ContactInsert contactInsert = new ContactInsert(details);
				contactInsert.createSmContact(requestContent);
				contactInsert.sendRequest();
				
				if(contactInsert.getStatus() == false)
				{
					resp.getOutputStream().println("Nie udalo sie dodac kontatku. Sprawdz dane logowania do SALESmanago");
				}
				else resp.getOutputStream().println("Dodano nowy kontakt " + contactInsert.getContactId() + " do email " + details.getOwnerMail());
			}
			else if(requestType.equals("ADMIN"))
			{
				details = new IntegrationDetails(requestContent);
				log.info("Odebrano autoryzacje");
				log.info("Owner Email = " + details.getOwnerMail());
				log.info("ClientId = " + details.getClientId());
				log.info("ApiSecret = " + details.getApiSecret());
				log.info("EndPoint = " + details.getEndPoint());
				resp.getOutputStream().println("Otrzymano dane potrzebene do autoryzacji.");
				
			}
			else
			{
				log.warning("Blad w zapytaniu: " + requestType);
				resp.getOutputStream().println("Blad w prefixie zapytania");
			}
			
        }
}