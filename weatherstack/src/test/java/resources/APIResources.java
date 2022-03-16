/**
 * @author Harjinder Singh Banga
 * @email hsbanga@yahoo.com
 * @create date 14/03/2022 
 * @modify By:  date:
 * 
 * @desc 
 */
package resources;

public enum APIResources {

	// SendInBlue API
	WEATHERSTACK("current");
	
	private String resource;

	APIResources(String resource) {
		this.resource = resource;
	}

	public String getResource() {
		return resource;
	}
}
