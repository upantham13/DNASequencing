package dna;


public class FastaRecord implements DNARecord
{	
	private String   defline;
	private String   sequence;


	//
	// Add a precondition check: throw RecordFormatException if the 1st char of the defline is 
	// not '>'. You will have to change the constructor declaration to say that it throws
	// the exception. The exception should contain a useful informative message.
	//
	public FastaRecord(String defline, String sequence) throws RecordFormatException
	{
		if (defline.charAt(0) != '>') {
			throw new RecordFormatException("Defline does not start with '>'.");
		}
		
		else {
			this.defline = defline;
			this.sequence = sequence;
		}
	}
	
	
	// Initialize defline and sequence from the input record. The defline should be the
	// defline of the fastq record, but with a '>' in the first position rather than a '@'.
	// If you're not sure how to do this, look up the substring method on the String API page.
	public FastaRecord(FastqRecord fastqRec)
	{
		fastqRec.setDefline(defline);
		int pos = 0;
		defline = defline.substring(0, pos) + '>' + defline.substring(pos + 1);
		fastqRec.setSequence(sequence);
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
	
	//
	// Provide an equals() method. 
	//
	public boolean equals(FastaRecord fastA) {
		return fastA.getDefline().equals(this.getDefline()) && fastA.getSequence().equals(this.getSequence());
	}

	//
	// Provide a hashCode() method that returns the sum of the hashcodes of 
	// defline and sequence.
	//
	public int hashCode() {
		return defline.hashCode() + sequence.hashCode();
	}
}
