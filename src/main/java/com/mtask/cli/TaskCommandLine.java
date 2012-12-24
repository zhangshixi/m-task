package com.mtask.cli;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class TaskCommandLine {
	
	public static void main(String[] args) {
		Options options = buildOptions();
		
		CommandLine commandLine = null;
		try {
			commandLine = new GnuParser().parse(options, args);
		} catch (ParseException e) {
			System.err.println(e.getLocalizedMessage());
			usage(options);
		}
		
		commandLine.getOptionValue("h");
	}
	
	private static Options buildOptions() {
		Options options = new Options();
		{
			Option option = new Option("d", "dir", true, "Directory where CIM objects are stored");
			option.setRequired(true);
			options.addOption(option);
		}
		return options;
	}
	
	private static void usage(Options options) {
		new HelpFormatter().printHelp(TaskCommandLine.class.getSimpleName(), options, true);
		System.exit(1);
	}
	
}
