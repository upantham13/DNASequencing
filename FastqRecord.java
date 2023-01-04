package dna;


//
// FastqRecord contains the defline, sequence, and quality string
// from a record in a fastq file.
//


public class FastqRecord implements DNARecord 
{
	private String   defline;
	private String   sequence;
	private String   quality;

	
	//
	// Add a precondition check: throw RecordFormatException if the 1st char of the defline is 
	// not '@'. You will have to change the constructor declaration to say that it throws
	// the exception. The exception should contain a useful informative message.
	//
	public FastqRecord(String defline, String sequence, String quality) throws RecordFormatException
	{
		if (defline.charAt(0) != '@') {
			throw new RecordFormatException("Defline does not start with '@'.");
		}
		
		else {
			this.defline = defline;
			this.sequence = sequence;
			this.quality = quality;
		}
	}
	
	// 
	// Provide the 2 methods that satisfy the interface.
	//
	public String getDefline() {
		return defline;
	}
	
	public String getSequence() {
		return sequence;
	}
	
	public String getQuality() {
		return quality;
	}
	
	public void setDefline(String defline) {
		this.defline = defline;
	}

	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
	
	public void setQuality(String quality) {
		this.quality = quality;
	}
	//
	// Provide an equals() method that checks for deep equality of all 3 instance variables. 
	// When checking string variables, make sure you are using deep equality (hint: == won't cut it)
	//
	public boolean equals(FastqRecord fastQ) {
		return fastQ.getDefline().equals(this.getDefline()) && fastQ.getSequence().equals(this.getSequence()) && fastQ.getQuality().equals(this.getQuality());
	}
	
	//
	// Complete this according to the assignment instructions.
	//
	public boolean qualityIsLow()
	{
		if (quality.contains("$") && quality.contains("#")) {
			return true;
		}
		
		else {
			return false;
		}
	}
	
	
	//
	// Provide a hashCode() method that returns the sum of the hashcodes of 
	// defline, sequence, and quality
	public int hashCode() {
		return defline.hashCode() + sequence.hashCode() + quality.hashCode();
	}
	
}
