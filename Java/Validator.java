/******************************************************************************

Welcome to GDB Online.
GDB online is an online compiler and debugger tool for C, C++, Python, Java, PHP, Ruby, Perl,
C#, VB, Swift, Pascal, Fortran, Haskell, Objective-C, Assembly, HTML, CSS, JS, SQLite, Prolog.
Code, Compile, Run and Debug online from anywhere in world.

*******************************************************************************/
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

class Validator{
    
    private String value;
    
    private boolean isValid;
    
    private ArrayList<String> errorMessages;
    
    private static String EMAIL_REGEX_PATTERN = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$" ;
    
    Validator(String value){
        this.value = value ;
        this.isValid = false;
        this.errorMessages = new ArrayList<String>();
    }
    
    private boolean minLength(String value,int length){
        return (value.length()>=length);
    }
    
    public Validator minLength(int length,String message){
        if(!this.minLength(this.value,length)){
            this.errorMessages.add(message);
        }
        
        return this;
    }
    
    public Validator minLength(int length){
        return this.minLength(length,"Must contain atleast "+length+" characters !");
    }
    
    private boolean maxLength(String value,int length){
        return (value.length()<=length);
    }
    
    public Validator maxLength(int length,String message){
        if(!this.maxLength(this.value,length)){
            this.errorMessages.add(message);
        }
        
        return this;
    }
    
    
    public Validator maxLength(int length){
        return this.maxLength(length,"Must not contain more than "+length+" characters !");
    }
    
    private boolean exactLength(String value,int length){
        return (value.length()==length);
    }
    
    public Validator exactLength(int length,String message){
        if(!this.exactLength(this.value,length)){
            this.errorMessages.add(message);
        }
        
        return this;
    }
    
    public Validator exactLength(int length){
        return this.exactLength(length,"Must contain exactly "+length+" characters !");
    }
    
    private static boolean isNotEmpty(String value){
        return (!( value==null || value.equals("")));
    }
    
    public Validator isPresent(String value,String message){
        if(!this.isNotEmpty(value)){
            this.errorMessages.add(message);
        }
        return this;
    }
    
    public Validator isPresent(String value){
        return this.isPresent(value,"Must not be empty !");
    }
    
    public static boolean isEmpty(String value){
        return !(isNotEmpty(value));
    }
    
    public static boolean isValidEmail(String value){
        return Pattern.compile(EMAIL_REGEX_PATTERN,Pattern.CASE_INSENSITIVE).matcher(value).matches();
    }
    
    public Validator isEmail(String message){
        if(!this.isValidEmail(this.value)){
            this.errorMessages.add(message);
        }
        return this;
    }
    
    public Validator isEmail(){
        return this.isEmail("Must be a valid email !");
    }  
    
    public static boolean isAnEmail(String value){
        return isValidEmail(value);
    }
    
    public boolean isValid(){
        return (this.errorMessages.isEmpty());
    }
    
    
    
    public ArrayList<String> getErrorMessages(){
        return this.errorMessages;
    }
    
    
}

public class Main
{
	public static void main(String[] args) {
		Validator v = new Validator("Hello");
		v.minLength(8).maxLength(4).exactLength(2);
		//String s = "";
		System.out.println(Validator.isEmpty(""));
		System.out.println(Validator.isAnEmail("vg@gam.com"));
		
		System.out.println(v.getErrorMessages());
	}
}
