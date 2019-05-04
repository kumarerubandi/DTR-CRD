package org.hl7.davinci.endpoint.servlets;

import java.util.Collections;
import java.util.List;
import org.hl7.fhir.dstu3.model.Patient;
import org.hl7.fhir.dstu3.model.Practitioner;
import org.hl7.fhir.dstu3.model.Enumerations.AdministrativeGender;
import ca.uhn.fhir.model.primitive.IdDt;
import ca.uhn.fhir.model.primitive.UriDt;
import ca.uhn.fhir.rest.annotation.IdParam;
import ca.uhn.fhir.rest.annotation.Read;
import ca.uhn.fhir.rest.annotation.RequiredParam;
import ca.uhn.fhir.rest.annotation.Search;
import ca.uhn.fhir.rest.annotation.Create;
import ca.uhn.fhir.rest.annotation.ResourceParam;
import ca.uhn.fhir.rest.param.StringParam;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.server.IResourceProvider;
import org.hl7.fhir.dstu3.model.IdType;
import org.json.JSONObject;
import org.json.JSONException;

//START SNIPPET: provider
/**
 * All resource providers must implement IResourceProvider
 */
public class RestfulPatientResourceProvider implements IResourceProvider {

	/**
	 * The getResourceType method comes from IResourceProvider, and must
	 * be overridden to indicate what type of resource this provider
	 * supplies.
	 */
	@Override
	public Class<Patient> getResourceType() {
		return Patient.class;
	}
	
	
	@Create
	public MethodOutcome customMethod (@ResourceParam Patient patient,@ResourceParam String theRawBody) {
	  System.out.println("IN Custom function "+theRawBody);
	  try {
		  JSONObject reqJson = new JSONObject(theRawBody);
		  if(reqJson.has("req_contents")){
			  /*****
			  STORING ACTUAL CREATED RESOURCE'S DATA  INTO "resourceObj" 
			  **** */
			  JSONObject resourceObj = new JSONObject(reqJson.get("req_contents").toString()); 
//			  System.out.println(resourceObj);			
			  
			  
		  }
		  
	  }
	  catch(JSONException json_ex) {
	    	 json_ex.printStackTrace();
	      } 
		catch(Exception ex) {
			ex.printStackTrace();
		}
	  String abc = "111";
	  MethodOutcome retVal = new MethodOutcome();
	  return retVal; // populate this
	}
	

}
//END SNIPPET: provider


