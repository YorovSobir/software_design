package ru.spbau.mit.sd.command;

import com.lexicalscope.jewel.cli.ArgumentValidationException;
import com.lexicalscope.jewel.cli.CliFactory;
import ru.spbau.mit.sd.Environment;
import ru.spbau.mit.sd.Runnable;

import java.io.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * class implements Runnable and work as grep utility in *nix systems.
 */
public class Grep implements Runnable {
    private Pattern pattern;
    private int linesCount;

    /**
     * Implements execute method in Runnable
     * @param env
     * @param inputStream InputStream object for read
     * @param outputStream OutputStream for write
     * @param param parameters of command cat
     */
    @Override
    public void execute(Environment env, InputStream inputStream,
                        OutputStream outputStream, String param) {
        String filename = getParams(param);
        BufferedReader reader;
        if (filename != null) {
            try {
                reader = new BufferedReader(new FileReader(filename));
            } catch (FileNotFoundException e) {
                throw new CommandException("grep: " + e.getMessage());
            }
        } else {
            reader = new BufferedReader(new InputStreamReader(inputStream));
        }

        StringBuilder sb = new StringBuilder();
        try {
            String line;
            int curLineCount = -1;
            while ((line = reader.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);
                if (matcher.find()) {
                    curLineCount = linesCount;
                }
                if (curLineCount >= 0) {
                    sb.append(line).append("\n");
                    curLineCount--;
                }
                if (!reader.ready()) {
                    break;
                }
            }
        } catch (IOException e) {
            throw new CommandException("grep: Error while reading file.");
        }

        try {
            outputStream.write(sb.toString().getBytes());
        } catch (IOException e) {
            throw new CommandException("grep: " + e.getMessage());
        }
    }

    private String getParams(String param) {

        GrepArgs args;
        try {
            args = CliFactory.parseArguments(GrepArgs.class, param.split(" "));
        } catch (ArgumentValidationException e) {
            throw new CommandException("grep: " + e.getMessage());
        }

        List<String> unparsed = args.getUnparsed();
        String stringPattern = unparsed.get(0).trim();
        if (stringPattern.charAt(0) == '\'' || stringPattern.charAt(0) == '\"') {
            stringPattern = stringPattern.substring(1, stringPattern.length() - 1);
        }

        String filename = null;

        if (unparsed.size() > 1) {
            filename = unparsed.get(1);
        }

        if (args.isWordRegexp()) {
            stringPattern = "\\b" + stringPattern + "\\b";
        }

        if (args.isIgnoreCase()) {
            pattern = Pattern.compile(stringPattern,
                                        Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
        } else {
            pattern = Pattern.compile(stringPattern);
        }

        linesCount = args.getLinesCount();

        return filename;
    }
}
