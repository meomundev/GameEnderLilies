package Objects;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class Key extends SuperObject{
    public Key() {
        name = "Key";
        try{
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/key.png")));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
