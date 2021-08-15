/******************************************************************************

Welcome to GDB Online.
GDB online is an online compiler and debugger tool for C, C++, Python, Java, PHP, Ruby, Perl,
C#, VB, Swift, Pascal, Fortran, Haskell, Objective-C, Assembly, HTML, CSS, JS, SQLite, Prolog.
Code, Compile, Run and Debug online from anywhere in world.

*******************************************************************************/
import java.util.ArrayList;
class Validator{
    
    private String value;
    
    private boolean isValid;
    
    private ArrayList<String> errorMessages;
    
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
		System.out.println(v.getErrorMessages());
	}
}
