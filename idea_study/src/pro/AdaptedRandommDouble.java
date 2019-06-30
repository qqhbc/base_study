package pro;

import java.io.IOException;
import java.nio.CharBuffer;
import java.util.Scanner;

public class AdaptedRandommDouble extends RandommDoubles implements Readable {
	private int count;
	public AdaptedRandommDouble(int count) {
		this.count = count;
	}
	@Override
	public int read(CharBuffer cb) throws IOException {
		if(count-- == 0){
			return -1;
		}
		 String result = Double.toString(next())+" ";
		 cb.append(result);
		return 0;
	}
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner s = new Scanner(new AdaptedRandommDouble(7));
		while(s.hasNextDouble()){
			System.out.println(s.nextDouble());
		}
	}

}
