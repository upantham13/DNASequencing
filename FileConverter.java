package dna;

import java.io.*;

import java.util.*;


public class FileConverter 
{
	//create instance variables and constructor
	public File fastq;
	public File fasta;
	
	public FileConverter (File fastq, File fasta) {
		this.fastq = fastq;
		this.fasta = fasta;
	}
	
	//
	// Writes a fasta file consisting of conversion of all records from the fastq with
	// sufficient quality and unique defline.You are going to have to figure out how to 
	// guarantee that the defline is unique
	//
	// This method only throws IOException. Other exceptions throws by the code should
	// be handled inside the method, using try/catch.
	//
	//
	public void convert() throws IOException
	{
		// Build chain of readers for reading fast records from the fastq file.
			FileReader fr = new FileReader(fastq);
			BufferedReader br = new BufferedReader(fr);
			FastqReader fqr = new FastqReader(br);
			
		
		

			// Build chain of writers for writing fasta records to the fasta file.
			FileWriter fw = new FileWriter(fasta);
			PrintWriter pw = new PrintWriter(fw);
			FastaWriter faw = new FastaWriter(pw);
		
			// Read, translate, write.
			try {
			boolean done = false;
				while(!done) {
					FastqRecord fastqRec = null;
					try {
						fastqRec = fqr.readRecord();
					}
					catch (RecordFormatException e) {
						e.printStackTrace();
					}
					if (fastqRec == null) {
						done = true;
					}
					else if (fastqRec.qualityIsLow()){
						String defline = fastqRec.getDefline();
						String sequence = fastqRec.getSequence();
						try {
							FastaRecord fastaRec = new FastaRecord(defline, sequence);
							faw.writeRecord(fastaRec);
						}
						catch (RecordFormatException e) {
							e.printStackTrace();
						}
					}
				}
			}
			catch (IOException x) {
				x.printStackTrace();
			}
				// Close fr, br, fw, and pw in reverse order of creation.
			pw.close();
			fw.close();
			br.close();
			fr.close();
	}	
	
	public static void main(String[] args)
	{
		System.out.println("Starting");
		try
		{
			File fastq = new File("data/HW4.fastq");
			if (!fastq.exists())
			{
				System.out.println("Can't find input file " + fastq.getAbsolutePath());
				System.exit(1);
			}
			File fasta = new File("data/HW4.fasta");
			FileConverter converter = new FileConverter(fastq, fasta);
			converter.convert();
		}
		catch (IOException x)
		{
			System.out.println(x.getMessage());
		}
		System.out.println("Done");
	}
}
