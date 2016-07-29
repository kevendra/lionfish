package xyz.lionfish.edge.api.endpoint;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static xyz.lionfish.edge.api.util.Api.CLEAN_ALL;
import static xyz.lionfish.edge.api.util.Api.DATA_CLEAN;
import io.swagger.annotations.Api;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.annotation.Resource;

import me.parakh.core.api.endpoint.AbstractCommonUtilityController;
import me.parakh.core.api.response.ApiResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import xyz.lionfish.edge.service.common.DataCleanUpService;

/**
 * DataCleanUpController
 * 
 * @author Kevendra Patidar
 */
@RestController
@RequestMapping(DATA_CLEAN)
@Api(value = "DataClean", description = "Data cleanup API related actions")
public class DataCleanUpController extends AbstractCommonUtilityController {

	/* ************************************ Static Fields ************************************ */
	private static final Logger LOG = LoggerFactory.getLogger(DataCleanUpController.class);

	/* ************************************ Instance Fields ************************************ */
	@Resource
	private DataCleanUpService dataCleanUpService;

	/* ************************************ Public Methods ************************************ */
	/**
	 * ../api/data-clean/clean-all.json?deviceId=XXXXX&key=
	 */
	@RequestMapping(value=CLEAN_ALL, method= GET)
	public ApiResponse cleanAll(@RequestParam final String deviceId, @RequestParam final Long key){
		if(key == 232425l){
			try {
				dataCleanUpService.cleanAll(deviceId);
			} catch (Exception e) {
				LOG.error("error in cleanAll", e);
			}
		}
		return successResponse(null);
	}
	/**
	 * ../api/data-clean/add-make.json 
	 */
	@RequestMapping(value="add-make.json", method= GET)
	public ApiResponse addMake(){
		File file = new File("vehicle/car-make-v1.json");
		if(null != file && file.isFile()){
			try {
				FileInputStream inputStream = new FileInputStream(file);
				//JsonUtil.toJava(json, valueType)
				System.out.println("file : " + inputStream.read());
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		return successResponse(null);
	}
		
}
