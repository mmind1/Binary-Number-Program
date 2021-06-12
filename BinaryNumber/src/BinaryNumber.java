public class BinaryNumber {

		private int data[];
		private boolean overflow;
		
		// A constructor
		public BinaryNumber(int length) {
			if(length > 0) {
				// Array values are zero by default
				data = new int[length]; }
				else {
					System.out.println("Please enter a valid length");
			}
		}
		
		// A constructor for a binary number string 
		public BinaryNumber(String str) {
			boolean check = true; // Valid string checking are set to true by default
			int length = str.length(); // Returns the length of string
			for(int i = 0; i < length; i ++)
			{
				if(str.charAt(i) != '0' && str.charAt(i) != '1') {
					System.out.println("Not a Binary Number");
					check = false;
					break;
				}
				
			}
			if(check == true) {
				data = new int[length];
				for(int i = 0; i<length; i++) {
					if(str.charAt(i)=='1'){
						data[i] = 1;
						}
					else {
						data[i] = 0;
						}
				}
			}
		}	
		// Returns the length of binary number
		public int getLength() {
			return data.length;
		}
		
		// Returns the digit 0 or 1 
		public int getDigit(int index) {
			if(index>data.length) {
				System.out.println("Index Out of Bounds");
				System.exit(1);
				return 0;
			}
			else {
				return data[index];
			}
		}
		// This shifts the binary number to the right by the amount given
		public void shiftR(int amount) {
			if (amount < 0) {
				throw new IllegalArgumentException("Enter Positive Value");
			}

			BinaryNumber reallocate = new BinaryNumber(data.length + amount);
			for (int i = amount; i < reallocate.getLength(); i++) {
				reallocate.data[i] = data[i - amount];
			}
			this.data = reallocate.data;
			System.out.println("New Number after shift is as: " + this.toString());
		}
		
		// Adds two binary numbers
		public void add(BinaryNumber aBinaryNumber) {
			if (aBinaryNumber.getLength() != data.length) {
				System.out.println("Both binary number lengths should be the same");
			} else {
				System.out.print("Addition of " + aBinaryNumber + " and " + toString() + " = ");
				int carry = 0;
				int sum[] = new int[data.length];
				for (int i = 0; i < data.length; i++) {
					int c = carry + data[i] + aBinaryNumber.getDigit(i);
					if (c == 0) {
						sum[i] = 0;
						carry = 0;
					}
					if (c == 1) {
						sum[i] = 1;
						carry = 0;
					}
					if (c == 2) {
						sum[i] = 0;
						carry = 1;
					}
					if (c == 3) {
						sum[i] = 1;
						carry = 1;
					}
				}
				data = sum;
				if (carry == 1) {
					overflow = true;
				}
				System.out.println(toString());
			}
		}
		// Returns the binary number as a string or overflow if the number has an overflow value 
		public String toString() {
			if(overflow == true) {
				return "Overflow";
			}
			else {
				String st ="";
				for(int i = 0; i<data.length; ++i) {
					st += data[i];
				}
				return st;
			}	
		}
		// Converts a binary number into decimal form 
		public int toDecimal() {
			int decimal = 0;
			for(int i = 0; i< data.length; i++) {
				decimal= (int)(decimal + data[i] * Math.pow(2, i));
			}
			return decimal;
		}
		// Clears the overflow value
		public void clearOverflow() {
			overflow = false;
			System.out.println("Overflow has now been cleared");
		}
    
	/**
	 * @param args
	 */
		
	public static void main(String[] args) {
		
		BinaryNumber b1 = new BinaryNumber("10110");
		BinaryNumber b2 = new BinaryNumber("11101");
		BinaryNumber b3 = new BinaryNumber("11100");
		BinaryNumber b4 = new BinaryNumber(7);
		BinaryNumber Value1 = new BinaryNumber(5);
        System.out.println("Value = " + Value1);
        System.out.println(b2 + "'s length is = " + b2.getLength());
        System.out.println(b4.toString());
		System.out.println(b1 + "'s decimal value is = " + b1.toDecimal());
		System.out.println("The digit at the given index is = " + b2.getDigit(3));
		b2.shiftR(3);
        b1.add(b3);
		b1.add(b2);	
	}

}
