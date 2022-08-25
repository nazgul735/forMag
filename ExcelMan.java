import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ExcelMan {
    private String filename;
	private ArrayList<List<String>> optionChain=new ArrayList<List<String>>();	
    private ArrayList<List<String>> newoptionChain = new ArrayList<List<String>>();	

    public ExcelMan(String filename) throws IOException{
        {
            this.filename=filename;
            Scanner optionFinder=null;
            optionFinder = new Scanner(new File(filename));
            while (optionFinder.hasNextLine())
            {
                List<String> temp= Arrays.asList(optionFinder.nextLine().split(","));
                optionChain.add(temp);
            }
            optionFinder.close();
        }
            
    }

	public ArrayList<List<String>> getOptionChain()
	{
		
		return optionChain;
	}

    public void checkStock(){

        for (List<String> option : optionChain.subList(0, getOptionChain().size()))  {

            if (option.get(2).toUpperCase().equals("STOCK")){
                List<String> temp = new ArrayList<String>();
                for (int i=0; i<option.size(); i++){
                    temp.add(option.get(i));
                }
                temp.add(option.get(11));
                newoptionChain.add(temp);
                
            }
            else{
            newoptionChain.add(option);}
        }

        
    }

    public void writeOptions(String newFilename) throws IOException  
	
	{
		try
		{
			FileWriter writer = new FileWriter(newFilename);
            BufferedWriter bw = new BufferedWriter(writer);
			for(List<String> i : newoptionChain.subList(0, newoptionChain.size()))
			{
                for(int j=0;j<i.size();j++)
                {
                    writer.write(i.get(j)+",");
                }
                writer.append('\n'); 

				
			}
			writer.flush();
			writer.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
    
    public static void main(String[] args) throws IOException 
    {
        ExcelMan o = new ExcelMan("Book1.csv");
        o.checkStock();
        o.writeOptions("result.csv");  
    }
}
