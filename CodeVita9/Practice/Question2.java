import java.io.*;

class Operations{

boolean checkPrime(int n){

boolean  prime=true;

for(int i = 2;i<n;i++){

if(n%i==0){

prime=false;
break;

}//end of if

}//end of for

return prime;

}//end of checkPrime

int createCombinationPrimeList(int x,int y){
int combined = 0 , result = 0;
combined = Integer.parseInt((Integer.toString(x))+(Integer.toString(y)));
if(checkPrime(combined)){
result = combined;
}//end of if
return result;
}//end of createCombinationPrimeList

void fibonacci(int a,int b,int n){

int c= 0,result =0;
for(int i = 0;i<n;i++ ){
c=a+b;
result = a;
a=b;
b=c;
}

System.out.println(result);
}//end of fibonacci

}//end of class


class Question2{

public static void main(String args[]){
String[] input=new String[2];
int first=0,last=0,flag=0,count=0,combined_prime=0,smallest=0,largest=0;
int[] firstPrimeList = new int[50];
int[] secondPrimeList  = new int[100];
Operations op = new Operations();

try{
BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
input = (br.readLine()).split(" ");

first = Integer.parseInt(input[0]);
last = Integer.parseInt(input[1]);

for(int i=first;i<last;i++){
if(op.checkPrime(i)){
firstPrimeList[count]=i;
count++;
}//end of if
}//end of for

count = 0;

//create combination
for(int i : firstPrimeList){
for(int j : firstPrimeList){

combined_prime =  op.createCombinationPrimeList(i,j);
if(combined_prime != 0 && i!=j && i!=0 && j!=0 ){
secondPrimeList[count]=combined_prime;
count++;
}//end of if


}//end of inner for
}//end of outer for

//System.out.println("Count : "+count+"Smallest : "+secondPrimeList[0]+"Largest : "+secondPrimeList[count-1]);

//Fibonacci Series
smallest=secondPrimeList[0];
largest=secondPrimeList[count-1];
op.fibonacci(smallest,largest,count);




}//end of try
catch(IOException ie){

}//end of catch


}//end of main

}//end of class
