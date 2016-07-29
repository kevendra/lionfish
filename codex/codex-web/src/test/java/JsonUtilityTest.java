

import static org.junit.Assert.assertEquals;
import me.parakh.core.dto.common.UserDto;
import me.parakh.core.model.util.JsonUtility;

import org.junit.Ignore;
import org.junit.Test;

/**
 * 
 * @author Kevendra Patidar
 */
public class JsonUtilityTest {
	
	/* ************************************ Public Methods ************************************ */
	/**
	 * run and copy the output to env-service.js
	 */
	public static void main(final String[] arg){
	}

	/**
	 * if it will break, sync/adjust UI accordingly
	 */
	@Test
	@Ignore
	public void testEmptyBean() {
		String expected;

		expected = "{username:null,password:null,enabled:false,accountNonExpired:false,accountNonLocked:false,credentialsNonExpired:false,displayName:null,orgId:null,email:null,role:null,version:0,modified:null,created:null,details:null,oldRole:null,repassword:null,oldpassword:null}";
		assertEquals(expected, getEmptyModel(new UserDto()));
//		expected = "{bmi:null,vision:null}";
//		assertEquals(expected, getEmptyModel(new HealthBean()));
	}

	/* ************************************ Private Methods ************************************ */
	private static String getEmptyModel(final Object object){
		return JsonUtility.toJson(object).replaceAll("\"", "");
	}
}
