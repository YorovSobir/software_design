package ru.spbau.mit.sd.command;

import com.lexicalscope.jewel.cli.Option;
import com.lexicalscope.jewel.cli.Unparsed;

import java.util.List;

/**
 * Interface for jewelCli arguments parser.
 * Every method define an argument option. Short name is "-v"
 * option, description is a message
 * that prints with this option in help. Unparsed is an option
 * without key (-v or --version).
 * All description gets from <man grep>
 */
public interface GrepArgs {
    @Option(shortName = "i",
            description = "Ignore case distinctions")
    boolean isIgnoreCase();

    @Option(shortName = "w",
            description = "Select  only  those  lines  containing  matches  that form whole"
            + " words.  The test is that the matching substring must  either  be"
            + " at  the  beginning  of  the  line,  or  preceded  by  a non-word"
            + " constituent character.  Similarly, it must be either at the  end"
            + " of  the  line  or  followed by a non-word constituent character."
            +" Word-constituent  characters  are  letters,  digits,   and   the"
            +" underscore.")
    boolean isWordRegexp();


    @Option(shortName = "A",
            defaultValue = "0",
            description = "Print NUM lines of trailing context"
                    + " after matching")
    int getLinesCount();

    @Unparsed(minimum = 1, maximum = 2)
    List<String> getUnparsed();

    @Option(helpRequest = true, description = "display help", shortName = "h")
    boolean getHelp();
}
