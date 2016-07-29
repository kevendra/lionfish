package xyz.lionfish.edge.api.endpoint;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static xyz.lionfish.edge.api.util.Api.EMPLOYEE_INFO;
import static xyz.lionfish.edge.api.util.Api.PERSON;
import io.swagger.annotations.Api;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import me.parakh.core.api.endpoint.AbstractCommonUtilityController;
import me.parakh.core.api.response.ApiResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import xyz.lionfish.edge.dto.common.PersonDto;
import xyz.lionfish.edge.service.common.PersonService;

/**
 * 
 * @author Kevendra Patidar
 */
@RestController
@RequestMapping(PERSON)
@Api(value = "Person", description = "Person API related actions")
public class PersonController extends AbstractCommonUtilityController {

	/* ************************************ Static Fields ************************************ */
	private static final Logger LOG = LoggerFactory.getLogger(PersonController.class);

	/* ************************************ Instance Fields ************************************ */
	@Resource
	private PersonService personService;

	/* ************************************ Public Methods ************************************ */
	/**
	 * ../api/person/employee.json?personId=xxxxxx
	 * 
	 */
	@RequestMapping(value=EMPLOYEE_INFO, method= GET)
	public ApiResponse getEmployeeInfo(@RequestParam final Long personId){
		LOG.debug("in getEmployeeInfo");
		PersonDto  person = personService.getCustomer(personId);
		final Map<String,Object> data = new HashMap<String, Object>();
		data.put("person", person);
		return this.successResponse(data);
	}	
	
}
