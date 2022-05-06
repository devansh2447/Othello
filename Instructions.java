
/**
 * Write a description of class Instruction here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.awt.Desktop;
import java.net.URI; 

public class Instructions{
    public static void launchInstructions() throws Exception{
        Desktop d=Desktop.getDesktop();
        d.browse(new URI("https://www.ultraboardgames.com/othello/game-rules.php"));
    }
}

