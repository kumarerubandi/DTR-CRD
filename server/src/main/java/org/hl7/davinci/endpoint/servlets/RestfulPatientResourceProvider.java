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
	
	/**
	 * The "@Read" annotation indicates that this method supports the
	 * read operation. Read operations should return a single resource
	 * instance. 
	 * 
	 * @param theId
	 *    The read operation takes one parameter, which must be of type
	 *    IdDt and must be annotated with the "@Read.IdParam" annotation.
	 * @return 
	 *    Returns a resource matching this identifier, or null if none exists.
	 */
	@Read()
	public Practitioner getResourceById(@IdParam IdType theId) {
		Practitioner patient = new Practitioner();
		patient.addIdentifier()
		   .setSystem("http://acme.org/mrns")
		   .setValue("12345");
//		patient.addName()
//		   .setFamily("Jameson")
//		   .addGiven("J")
//		   .addGiven("Jonah");
		patient.setGender(AdministrativeGender.MALE);
		// Give the patient a temporary UUID so that other resources in
		// the transaction can refer to it
		patient.setId(theId.getIdPart());
		return patient;
	}

	/**
	 * The "@Search" annotation indicates that this method supports the 
	 * search operation. You may have many different method annotated with 
	 * this annotation, to support many different search criteria. This
	 * example searches by family name.
	 * 
	 * @param theFamilyName
	 *    This operation takes one parameter which is the search criteria. It is
	 *    annotated with the "@Required" annotation. This annotation takes one argument,
	 *    a string containing the name of the search criteria. The datatype here
	 *    is StringParam, but there are other possible parameter types depending on the
	 *    specific search criteria.
	 * @return
	 *    This method returns a list of Patients. This list may contain multiple
	 *    matching resources, or it may also be empty.
	 */
	@Search()
	public List<Patient> getPatient(@RequiredParam(name = Patient.SP_FAMILY) StringParam theFamilyName) {
		Patient patient = new Patient();
		patient.addIdentifier();
		patient.getIdentifier().get(0).setSystem(("urn:hapitest:mrns"));
		patient.getIdentifier().get(0).setValue("00001");
		patient.addName();
		patient.getName().get(0).setFamily(theFamilyName.getValue());
		patient.getName().get(0).addGiven("PatientOne");
		return Collections.singletonList(patient);
	}
	
	
	@Create
	public MethodOutcome customMethod (@ResourceParam Patient patient,@ResourceParam String theRawBody) {
	  System.out.println("IN Custom function ");
	  System.out.println("IN Custom function "+theRawBody);
	  
	  String abc = "111";
	  MethodOutcome retVal = new MethodOutcome();
	  return retVal; // populate this
	}
	

}
//END SNIPPET: provider


