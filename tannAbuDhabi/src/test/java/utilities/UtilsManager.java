/**
 * @author Harjinder Singh Banga
 * @email hsbanga@yahoo.com
 * @create date: 16/03/2022
 * @modify By:  date:
 *
 * @desc
 */

package utilities;

public class UtilsManager {
	
	public SeleniumUtils seleniumUtils = null;
	public JavaUtils javaUtils = null;
	
	
	public UtilsManager(SeleniumUtils seleniumUtils,JavaUtils javaUtils) {
		this.seleniumUtils = seleniumUtils;
		this.javaUtils = javaUtils;
	
	}
	
}
