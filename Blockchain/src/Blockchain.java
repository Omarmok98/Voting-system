import java.util.ArrayList;
import com.google.gson.*;

public class Blockchain {
	
	public static ArrayList<Block> blockchain = new ArrayList<Block>();
	public static int difficulty = 4;
	public static boolean chainValid()
	{
		Block curr;
		Block prev;
		
		for(int i=0 ; i<blockchain.size() ; i++)
		{
			curr = blockchain.get(i);
			prev = blockchain.get(i-1);
			if(!curr.hash.equals(curr.calculateHash()))
			{
				System.out.println("Current Hashes not equal");
				return false;
			}
			if(!prev.hash.equals(curr.previousHash))
			{
				System.out.println("Previous Hashes not equal");
				return false;
				
			}
		}
		return true;
	}
	public void addBlock(Vote data)
	{
		int size = blockchain.size();
		
		if(size == 0)
		{
			Block b = new Block(data,"0000000000000000000000000000000000000000000000000000000000000000");
			blockchain.add(b);
		}else
		{
			String previousHash = blockchain.get(size - 1).hash;
			Block b = new Block(data, previousHash);
			b.mineBlock(this.difficulty);
			blockchain.add(b);
		}
		
	}
	public void getBlock(int i)
	{
		String blockJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain.get(i));
		System.out.println(blockJson);
	}
	public void PrintBlockChain()
	{
		String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);		
		System.out.println(blockchainJson);
	}
	

}
