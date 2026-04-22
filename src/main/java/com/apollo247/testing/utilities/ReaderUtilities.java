package com.apollo247.testing.utilities;

import java.io.FileInputStream;
<<<<<<< HEAD
=======
import java.io.FileNotFoundException;
>>>>>>> feature/insurance
import java.io.IOException;
import java.util.Properties;

public class ReaderUtilities {

	// file utlitility

	// read

	public String getPropertyKeyValue(String key) throws IOException {
		FileInputStream fs = new FileInputStream("./src/test/resources/Reader/common.properties");
		Properties prop = new Properties();
		prop.load(fs);
		String value = prop.getProperty(key);
		return value;
	}


}
