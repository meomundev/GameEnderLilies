package Objects;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Door extends SuperObject{
    public Door() {
        name = "Door";
        try{
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/door.png")));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
