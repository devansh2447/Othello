//Launches instruction url

import java.awt.Desktop;
import java.net.URI; 
public class Instructions{
    public static void launchInstructions() throws Exception{
        Desktop d=Desktop.getDesktop();
        d.browse(new URI("https://www.ultraboardgames.com/othello/game-rules.php"));
    }
}

