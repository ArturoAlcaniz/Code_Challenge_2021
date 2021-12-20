package challenge_six;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import org.apache.commons.lang.time.DateUtils;

public class Challenge6 {

	private void loadValues() throws IOException {
	
		HashMap<String, Locale> formatCountries = new HashMap<String, Locale>();

        

        String[] possibleDateFormats =
            {
                  "yyyy-MM-dd",
                  "dd-MM-yyyy"
            };
        
        formatCountries.put("ES", new Locale("es","ES"));
        formatCountries.put("HU", new Locale("hu", "HU"));
        formatCountries.put("RO", new Locale("ro", "RO"));
        formatCountries.put("CZ", new Locale("cs", "CZ"));
        formatCountries.put("FI", new Locale("fi", "FI"));
        formatCountries.put("IT", new Locale("it", "IT"));
        formatCountries.put("RU", new Locale("ru"));
        formatCountries.put("DE", new Locale("de", "DE"));
        formatCountries.put("FR", Locale.FRENCH);
        formatCountries.put("NL", new Locale("nl", "NL"));
        formatCountries.put("SE", Locale.forLanguageTag("sv-SE"));
        formatCountries.put("DK", new Locale("da", "DK"));
        formatCountries.put("IS", new Locale("is", "IS"));
        formatCountries.put("VI", new Locale("vi", "VN"));
        formatCountries.put("SI", new Locale("sl", "SI"));
        formatCountries.put("EN", new Locale("en", "US"));
        formatCountries.put("GR", new Locale("el", "GR"));
        formatCountries.put("PL", new Locale("pl", "PL"));
        formatCountries.put("SK", new Locale("sk", "SK"));
        formatCountries.put("CA", new Locale("ca","ES"));


        File input = new File("./src/challenge_six/submitInput.txt");
		File output = new File("./src/challenge_six/submitOutput.txt");
		output.createNewFile(); // if exists will do nothing 
	    PrintWriter writer = new PrintWriter("./src/challenge_six/submitOutput.txt", "UTF-8");
        
        try (FileReader fr1 = new FileReader(input);
             BufferedReader bf1 = new BufferedReader(fr1)) {
        	String line1;
        	
        	int numberCases = Integer.parseInt(bf1.readLine());
        	
        	for(int i=1; i<=numberCases; i++) {
        		line1 = bf1.readLine();
        		String date = line1.split(":")[0];
        		String country = line1.split(":")[1];
        		String res = "Case #"+i+": ";
        		if(!formatCountries.containsKey(country)) {
        			res = res+"INVALID_LANGUAGE";
        			writer.print(res+"\n");
        			continue;
        		}	
        		try {
        			Date dateForm = DateUtils.parseDateStrictly(date, possibleDateFormats);
        			Calendar cal = Calendar.getInstance();
        			cal.setTime(dateForm);
        			int mesActual = cal.get(Calendar.MONTH)+1;
        			int year = cal.get(Calendar.YEAR);
        			int dayinteger = cal.get(Calendar.DAY_OF_MONTH);
        			LocalDate localdate = LocalDate.of(year, mesActual, dayinteger);
        			String day = "";
        			Locale locale = formatCountries.get(country);
        			switch(localdate.getDayOfWeek().toString()) {
        				case "TUESDAY":
        					day = DayOfWeek.TUESDAY.getDisplayName(TextStyle.FULL, locale);
        					res = res+day.toLowerCase();
        					break;
        				case "MONDAY":
        					day = DayOfWeek.MONDAY.getDisplayName(TextStyle.FULL, locale);
        					res = res+day.toLowerCase();
        					break;
        				case "SATURDAY":
        					day = DayOfWeek.SATURDAY.getDisplayName(TextStyle.FULL, locale);
        					res = res+day.toLowerCase();
        					break;
        				case "SUNDAY":
        					day = DayOfWeek.SUNDAY.getDisplayName(TextStyle.FULL, locale);
        					res = res+day.toLowerCase();
        					break;
        				case "THURSDAY":
        					day = DayOfWeek.THURSDAY.getDisplayName(TextStyle.FULL, locale);
        					res = res+day.toLowerCase();
        					break;
        				case "WEDNESDAY":
        					day = DayOfWeek.WEDNESDAY.getDisplayName(TextStyle.FULL, locale);
        					res = res+day.toLowerCase();
        					break;
        				case "FRIDAY":
        					day = DayOfWeek.FRIDAY.getDisplayName(TextStyle.FULL, locale);
        					res = res+day.toLowerCase();
        					break;
        			}
        			writer.print(res+"\n");
        		} catch (Exception e) {
        			res = res+"INVALID_DATE";
        			writer.print(res+"\n");
        		}
        	}
    		
        } catch (Exception e) {
        	System.out.println("Error with files:");
        	System.out.println(e.getMessage());
        }
        writer.close();
    }
	
	public static void main (String [] args) {
		Challenge6 challenge = new Challenge6();
		try {
			challenge.loadValues();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
